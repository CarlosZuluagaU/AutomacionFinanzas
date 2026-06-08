@autenticacion @registro
Feature: Registro de nuevos usuarios en MS Finanzas
  Como nuevo usuario
  Quiero registrarme en el sistema
  Para poder gestionar mis finanzas personales

  Scenario: Registro exitoso de nuevo usuario
    Given que el usuario "NuevoUsuario" quiere registrarse
    When completa el formulario de registro con nombre "Usuario Test", email "nuevo@finanzas.com" y contrasena "Test1234!"
    Then deberia quedar autenticado en el sistema
