#language: es
@transacciones
Feature: Gestion de transacciones financieras
  Como usuario autenticado
  Quiero registrar y gestionar mis transacciones
  Para llevar un control de mis ingresos y gastos

  Scenario: Crear una transaccion de ingreso
    Given que el usuario "Carlos" esta autenticado en el sistema
    When navega a la seccion de nueva transaccion
    And crea una transaccion de tipo "Ingreso" por valor "150000" con fecha "2026-06-07"
    Then la transaccion deberia aparecer en el historial

  Scenario: Crear una transaccion de gasto
    Given que el usuario "Carlos" esta autenticado en el sistema
    When navega a la seccion de nueva transaccion
    And crea una transaccion de tipo "Gasto" por valor "50000" con fecha "2026-06-07"
    Then la transaccion deberia aparecer en el historial

  Scenario: Eliminar una transaccion existente
    Given que el usuario "Carlos" tiene al menos una transaccion registrada
    When navega al historial y elimina la primera transaccion
    Then la transaccion deberia eliminarse correctamente
