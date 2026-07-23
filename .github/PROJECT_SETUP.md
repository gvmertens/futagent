# Configuração do GitHub

Após publicar o repositório:

1. troque a branch padrão para `main` e proteja-a exigindo PR, uma aprovação e os três jobs da CI;
2. ative Issues, Projects, Discussions, Dependabot alerts, secret scanning e push protection;
3. crie os labels descritos em `.github/labels.yml`;
4. crie um Project v2 **Futagend Delivery** conforme `docs/spec-driven.md`;
5. configure o auto-add para issues deste repositório e as automações `item added → Inbox`, `PR opened → In review`, `PR merged/issue closed → Done`;
6. adicione um `CODEOWNERS` com o usuário ou time real — não há placeholder inválido versionado;
7. transforme cada linha de `docs/backlog.md` em epic e depois em sub-issues que apontem para `specs/`.

Antes do primeiro deploy, use GitHub OIDC para assumir uma role AWS; não armazene access keys em GitHub Secrets. Secrets do runtime devem permanecer no AWS Secrets Manager/SSM e ser injetados nas tasks ECS.
