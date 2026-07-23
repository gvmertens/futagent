# FUT-030 — Sincronização segura do calendário

- Status: Draft
- Owner: Integrations

## Resultado

Partidas escolhidas entram no Google Calendar e mudanças são reconciliadas sem duplicar eventos.

## Regras

- mostrar preview e pedir consentimento antes da primeira escrita;
- usar chave de idempotência `provider:fixtureId:userId`;
- guardar o ID remoto e o hash da versão sincronizada;
- alterações relevantes geram atualização e aviso; cancelamento não apaga silenciosamente;
- OAuth usa menor escopo possível e tokens ficam em cofre, nunca no prompt.

## Critérios de aceitação

```gherkin
Scenario: Retry não duplica evento
  Given que uma partida já foi criada e a resposta anterior se perdeu
  When a sincronização é repetida com a mesma chave
  Then o mesmo evento é retornado e nenhum novo evento é criado
```

