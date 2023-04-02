Feature: Consulta CEP e Calcula Frete

  Scenario: Consulta um CEP valido
    Given Dado o CEP <cep>
    When Quando consulta o CEP e calcula o valor do frete
    Then Entao verifica se o <uf> e <valor> do frete estão corretos

  Examples:
    | cep | uf | valor |
    | '68515000' | 'PA' | 20.83 |
    | '65940000' | 'MA' | 15.98 |
    | '75803205' | 'GO' | 12.50 |
    | '12130000' | 'SP' | 7.85 |
    | '90010340' | 'RS' | 17.30 |