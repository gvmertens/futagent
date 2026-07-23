# ADR 0001 — Spring Boot, LangChain4j e ECS

- Status: Accepted
- Data: 2026-07-23

## Contexto

O produto precisa validar integrações, memória e recomendação antes de exigir uma plataforma Kubernetes.

## Decisão

Usar Java 21 com Spring Boot 4, LangChain4j e um monólito modular. Implantar inicialmente em ECS Fargate com RDS PostgreSQL/pgvector, ElastiCache Redis, Secrets Manager e OpenTelemetry Collector sidecar.

## Consequências

Menor carga operacional e deploy independente dos MCP servers. A separação por portas permite extrair módulos. EKS será reconsiderado caso haja múltiplos times, workloads GPU, políticas de malha ou escala que compensem seu custo operacional.

