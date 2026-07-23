# Arquitetura

## Decisões de primeira entrega

- monólito modular em Spring Boot 4 e Java 21;
- LangChain4j atrás do módulo `assistant`, sem acoplá-lo ao domínio;
- PostgreSQL como fonte de verdade, pgvector para RAG e Redis apenas para cache/locks;
- MCP remoto via Streamable HTTP, com allowlist de ferramentas e timeouts;
- React 19 como cliente e REST/OpenAPI como contrato;
- ECS Fargate como alvo inicial; EKS só quando requisitos operacionais justificarem;
- OpenTelemetry como API/protocolo, Prometheus/Grafana no ambiente local.

## Módulos do domínio

- `identity`: perfil, consentimentos e conexão OAuth;
- `preferences`: times, campeonatos, disponibilidade e sinais de recomendação;
- `fixtures`: partidas, fontes e mudanças de agenda;
- `calendar`: plano de sincronização, confirmação, idempotência e reconciliação;
- `assistant`: orquestração, ferramentas permitidas e memória conversacional;
- `knowledge`: ingestão, proveniência, chunks, embeddings e citações;
- `notifications`: regras, deduplicação, canais e quiet hours.

Os adapters Calendar, Weather e GitHub são servidores MCP separados do backend. O agente nunca recebe tokens OAuth brutos; o adapter resolve credenciais por identidade e escopo.

## Evolução

Análise de vídeo será um bounded context assíncrono separado (`scouting`) com armazenamento de objetos, filas e workers GPU. O núcleo guarda apenas referências e resultados normalizados, evitando transformar o monólito em pipeline multimídia.

