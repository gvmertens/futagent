# FUT-010 — Preferências de futebol

- Status: Draft
- Owner: Product

## Resultado

O usuário pode registrar times e campeonatos preferidos, fuso horário e janelas disponíveis, e pode apagar esses dados.

## Regras

- times e campeonatos usam IDs canônicos da fonte, não apenas nomes livres;
- disponibilidade é armazenada no fuso IANA do usuário;
- memória derivada deve ter proveniência, validade e opção de exclusão;
- dados de um usuário nunca participam da recuperação de outro.

## Critérios de aceitação

```gherkin
Scenario: Preferência influencia recomendação
  Given que o usuário segue um time
  When partidas futuras são ranqueadas
  Then partidas desse time recebem um sinal positivo explicável

Scenario: Direito de exclusão
  Given que o usuário solicita exclusão
  When a operação termina
  Then preferências, memória, embeddings pessoais e tokens delegados são removidos ou revogados
```

