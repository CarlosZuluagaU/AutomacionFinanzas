@presupuesto
Feature: Gestion de presupuestos
  Como usuario autenticado
  Quiero crear un presupuesto mensual
  Para controlar mis gastos y no exceder mi limite

  Scenario: Crear un nuevo presupuesto
    Given que el usuario "Carlos" esta autenticado en el sistema
    When navega a la seccion de presupuesto
    And crea un presupuesto con limite "500000" desde "2026-06-01" hasta "2026-06-30"
    Then el presupuesto deberia aparecer como activo
