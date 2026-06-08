@autenticacion @login
Feature: Autenticacion de usuarios en MS Finanzas
  Como usuario del sistema financiero
  Quiero poder iniciar sesion con mis credenciales
  Para acceder a mis datos financieros de forma segura

  Scenario: Login exitoso con credenciales validas
    Given que el usuario "Carlos" abre la pagina de login
    When ingresa el email "test@finanzas.com" y la contrasena "Test1234!"
    Then deberia ver el dashboard

  Scenario: Login fallido con contrasena incorrecta
    Given que el usuario "Carlos" abre la pagina de login
    When intenta iniciar sesion con email "test@finanzas.com" y contrasena "contrasena_incorrecta"
    Then deberia ver un mensaje de error

  Scenario: Login fallido con email no registrado
    Given que el usuario "Carlos" abre la pagina de login
    When intenta iniciar sesion con email "noexiste@finanzas.com" y contrasena "Test1234!"
    Then deberia ver un mensaje de error
