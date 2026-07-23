# Futagend

Assistente pessoal de futebol que conversa, recomenda partidas, mantém preferências e agenda jogos com consentimento explícito.

## Estado atual

Este repositório contém o *walking skeleton* da primeira entrega: API Java 21/Spring Boot, SPA React, PostgreSQL com pgvector, Redis, migrações, telemetria e o processo spec-driven. Integrações de IA e MCP são desligadas por padrão e entram por adapters.

## Início rápido

Pré-requisitos: Java 21, Docker com Compose, Node 22+ e Corepack/pnpm.

```bash
cp .env.example .env
docker compose up -d postgres redis otel-collector prometheus grafana
cd backend && mvn spring-boot:run
cd frontend && corepack enable && pnpm install && pnpm dev
```

No Windows use `Copy-Item .env.example .env`. A API fica em `http://localhost:8080`, o frontend em `http://localhost:5173`, o Grafana em `http://localhost:3000` e a documentação OpenAPI em `http://localhost:8080/swagger-ui.html`.

## Fluxo de desenvolvimento

1. Escreva ou altere uma especificação em `specs/`.
2. Registre decisões arquiteturais relevantes em `docs/adr/`.
3. Crie uma issue usando os formulários do repositório e referencie o requisito (`FUT-*`).
4. Implemente em branch `feat/FUT-xxx-resumo` com testes de aceitação.
5. Abra PR ligando a issue e preenchendo o checklist de segurança/observabilidade.

Consulte [o processo spec-driven](docs/spec-driven.md), [a arquitetura](docs/architecture.md) e [o backlog inicial](docs/backlog.md).

## Segurança

Segredos são fornecidos exclusivamente por variáveis de ambiente ou, em produção, AWS Secrets Manager/SSM. `.env`, chaves privadas e keystores são ignorados. O calendário exige OAuth por usuário e confirmação antes da primeira escrita; tokens nunca devem ir para logs ou memória vetorial.
