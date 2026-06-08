@metas
Feature: Gestion de metas de ahorro
  Como usuario autenticado
  Quiero crear y gestionar mis metas de ahorro
  Para alcanzar mis objetivos financieros

  Scenario: Crear una nueva meta de ahorro
    Given que el usuario "Carlos" esta autenticado en el sistema
    When navega a la seccion de metas de ahorro
    And crea una meta llamada "Vacaciones 2026" con monto objetivo "2000000"
    Then la meta "Vacaciones 2026" deberia aparecer en la lista

  Scenario: Aportar dinero a una meta existente
    Given que el usuario "Carlos" tiene una meta de ahorro creada
    When aporta "50000" a la primera meta
    Then la barra de progreso de la meta deberia aumentar
