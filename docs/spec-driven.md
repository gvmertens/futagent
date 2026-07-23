# Processo spec-driven

## Unidade de rastreabilidade

Cada comportamento observável recebe um identificador imutável `FUT-NNN`. O ID aparece na especificação, issue, nome do teste e PR. A especificação descreve o resultado; a ADR explica decisões; a issue representa o trabalho.

## Ciclo

`Draft → Review → Accepted → Implementing → Verified → Released`

- Draft: problema, cenário e critérios ainda negociáveis.
- Review: produto, engenharia e segurança avaliam impactos.
- Accepted: critérios Given/When/Then fechados e dependências conhecidas.
- Implementing: issue no quadro e PR em andamento.
- Verified: testes automatizados e evidências de observabilidade.
- Released: mudança implantada e monitorada.

Nenhuma integração externa que escreva dados é considerada pronta sem idempotência, consentimento e teste de falha/retry.

## Definition of Ready

- requisito e fora de escopo explícitos;
- critérios de aceitação verificáveis;
- riscos de privacidade e fontes de dados registrados;
- dependências, flags e plano de rollback definidos.

## Definition of Done

- testes unitários, integração e contrato relevantes verdes;
- telemetria sem PII/segredos e alertas definidos;
- documentação/OpenAPI atualizadas;
- threat-model revisado quando houver OAuth, MCP, RAG ou escrita externa;
- requisito vinculado à issue e ao PR.

## GitHub Project recomendado

Crie um Project v2 com as visões **Backlog** (tabela), **Delivery** (kanban) e **Roadmap**. Campos: Status (`Inbox`, `Ready`, `In progress`, `In review`, `Done`), Priority (`P0`–`P3`), Size (`XS`–`XL`), Iteration, Target date e Requirement. Ative auto-add para issues do repositório e automações nativas de PR.

