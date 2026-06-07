#language: es
@dashboard
Feature: Visualizacion del dashboard financiero
  Como usuario autenticado
  Quiero ver un resumen de mi situacion financiera
  Para tomar decisiones informadas sobre mis finanzas

  Scenario: Ver el resumen financiero en el dashboard
    Given que el usuario "Carlos" acaba de iniciar sesion
    When navega al dashboard principal
    Then deberia ver el resumen financiero con balance, ingresos y gastos

  Scenario: Acceder al reporte de transacciones
    Given que el usuario "Carlos" acaba de iniciar sesion
    When navega a la seccion de reporte
    Then deberia ver el reporte con graficas de transacciones
