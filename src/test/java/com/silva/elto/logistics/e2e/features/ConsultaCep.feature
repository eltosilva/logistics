Feature: Consulta CEP e Calcula Frete

  Scenario Outline: Consulta usando CEPs validos
    Given Dado o CEP <cep>
    When Quando consulta o CEP e calcula o valor do frete
    Then Entao verifica se o <uf> e <valor> do frete estão corretos

    Examples:
      | cep        | uf   | valor |
      | '68515000' | 'PA' | 20.83 |
      | '65940000' | 'MA' | 15.98 |
      | '75803205' | 'GO' | 12.50 |
      | '12130000' | 'SP' | 7.85  |
      | '90010340' | 'RS' | 17.30 |

  Scenario Outline: Consulta usando CEPs invalidos
    Given Dado o CEP <cep>
    When Quando consulta o CEP e calcula o valor do frete
    Then Entao retornara um bad request

    Examples:
      | cep |
      | '6851500' |
      | '685150000' |
      | '68.515-000' |
      | '68 51500' |
      | '' |
      | '68515o00' |

  Scenario Outline: Consulta usando CEPs que não existem
    Given Dado o CEP <cep>
    When Quando consulta o CEP e calcula o valor do frete
    Then Entao retornara um not found

    Examples:
      | cep |
      | '68515001' |
      | '65940001' |