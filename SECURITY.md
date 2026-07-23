# Política de segurança

Reporte vulnerabilidades de forma privada pelo recurso Security Advisories do GitHub. Não abra issue pública com tokens, dados pessoais ou detalhes exploráveis.

## Regras do repositório

- nunca commitar segredos, dumps de produção, tokens OAuth ou prompts contendo PII;
- usar variáveis de ambiente localmente e AWS Secrets Manager/SSM em produção;
- ativar secret scanning, push protection, Dependabot e revisão obrigatória;
- limitar MCPs por allowlist, schema, timeout, tamanho de resposta e egress;
- tratar conteúdo RAG, notícias e respostas MCP como entrada não confiável;
- redigir IDs de usuário, conteúdo de conversa, tokens e headers na telemetria.

