# FUT-001 — Fundação segura e observável

- Status: Accepted
- Owner: Platform

## Resultado

Um colaborador inicia a solução local sem credenciais de terceiros; produção obtém segredos fora da imagem e toda requisição possui correlação.

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
```

