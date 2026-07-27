# FUT-001 — Fundação segura e observável

- Status: Accepted
- Owner: Platform

## Resultado

Um colaborador inicia a solução local sem credenciais de terceiros; produção obtém segredos fora da imagem e toda requisição possui correlação.

## Escopo

- ambiente local reproduzível para API, front-end, PostgreSQL/pgvector, Redis e observabilidade;
- configuração exclusivamente por variáveis de ambiente, com valores locais não sensíveis documentados em `.env.example`;
- health/status da aplicação sem exposição de valores de configuração;
- correlação de requisições e exportação de traces, métricas e logs via OpenTelemetry;
- quality gates de backend, front-end e detecção de segredos executados pela CI.

## Fora de escopo

- provisionamento da infraestrutura AWS e deploy contínuo;
- autenticação de usuários, OAuth e credenciais dos MCP servers;
- disponibilidade de provedores reais de IA, calendário, clima ou GitHub;
- SLOs e alertas de produção, que dependem da primeira implantação observável.

## Regras e segurança

- a aplicação deve iniciar com todas as integrações externas desabilitadas;
- segredos, tokens, dados pessoais e valores de OAuth não podem aparecer em logs, traces, métricas, respostas de status ou imagens de contêiner;
- `.env` e variantes locais devem permanecer ignorados; somente exemplos sem credenciais podem ser versionados;
- o identificador de correlação recebido deve ser validado; quando ausente ou inválido, a API gera um novo identificador;
- imagens e dependências devem ser fixadas de forma reproduzível e verificadas pelos quality gates.

## Privacidade e dados

Esta fundação não coleta dados pessoais de produto. Dados técnicos de telemetria devem usar identificadores de correlação efêmeros e não podem conter payloads, prompts, tokens, cabeçalhos de autorização ou valores de variáveis de ambiente.

## Dependências

- Java 21, Spring Boot, Node.js 22 e pnpm;
- Docker Compose para PostgreSQL com pgvector, Redis, OpenTelemetry Collector, Prometheus e Grafana;
- GitHub Actions com os jobs obrigatórios `backend`, `frontend` e `secrets`.

As integrações externas são opcionais e controladas por configuração. Nenhuma feature flag remota é necessária nesta entrega.

## Observabilidade

- cada resposta da API inclui um identificador de correlação e ele é propagado para a telemetria;
- `/api/v1/system/status` informa somente disponibilidade e estado habilitado/desabilitado das dependências;
- falhas de exportação de telemetria não impedem a inicialização local e são registradas sem dados sensíveis.

## Rollback

A entrega é aditiva. O rollback consiste em reverter a imagem da aplicação e a configuração de telemetria para a versão anterior. Migrações desta fundação não devem remover nem transformar dados; qualquer migração futura incompatível exigirá estratégia própria de expansão e contração.

## Critérios de aceitação

```gherkin
Scenario: Inicialização sem provedor de IA
  Given que nenhuma chave de IA ou MCP está configurada
  When a API é iniciada
  Then o endpoint /api/v1/system/status responde 200
  And informa que integrações opcionais estão desabilitadas sem revelar configuração sensível

Scenario: Segredo não é versionado
  Given um arquivo .env local
  When o status do Git é consultado
  Then o arquivo não aparece como candidato a commit

Scenario: Requisição possui correlação
  Given que a API está em execução
  When uma requisição sem identificador de correlação acessa /api/v1/system/status
  Then a resposta contém um identificador de correlação válido
  And a telemetria da requisição usa o mesmo identificador

Scenario: Telemetria falha de forma segura
  Given que o collector OpenTelemetry está indisponível
  When a API é iniciada localmente
  Then o endpoint /api/v1/system/status continua respondendo 200
  And nenhum segredo ou payload é registrado pela falha

Scenario: Quality gates protegem a branch principal
  Given uma pull request destinada à branch main
  When a integração contínua é executada
  Then os jobs backend, frontend e secrets são concluídos com sucesso antes do merge
```
