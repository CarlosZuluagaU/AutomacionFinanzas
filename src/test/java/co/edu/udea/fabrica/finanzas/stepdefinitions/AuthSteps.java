package co.edu.udea.fabrica.finanzas.stepdefinitions;

import co.edu.udea.fabrica.finanzas.models.UserModel;
import co.edu.udea.fabrica.finanzas.questions.IsLoggedIn;
import co.edu.udea.fabrica.finanzas.tasks.AttemptLogin;
import co.edu.udea.fabrica.finanzas.tasks.Login;
import co.edu.udea.fabrica.finanzas.tasks.NavigateTo;
import co.edu.udea.fabrica.finanzas.tasks.Register;
import co.edu.udea.fabrica.finanzas.userinterfaces.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class AuthSteps {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario {string} abre la pagina de login")
    public void queElUsuarioAbreLaPaginaDeLogin(String actorName) {
        OnStage.theActorCalled(actorName).attemptsTo(NavigateTo.login());
    }

    @When("ingresa el email {string} y la contrasena {string}")
    public void ingresaElEmailYLaContrasena(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(email, password)
        );
    }

    @Then("deberia ver el dashboard")
    public void deberiaVerElDashboard() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(IsLoggedIn.now(), is(true))
        );
    }

    @When("intenta iniciar sesion con email {string} y contrasena {string}")
    public void intentaIniciarSesionConEmailYContrasena(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AttemptLogin.withCredentials(email, password)
        );
    }

    @Then("deberia ver un mensaje de error")
    public void deberiaVerUnMensajeDeError() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(IsLoggedIn.now(), is(false))
        );
    }

    @Given("que el usuario {string} quiere registrarse")
    public void queElUsuarioQuiereRegistrarse(String actorName) {
        OnStage.theActorCalled(actorName).attemptsTo(NavigateTo.login());
    }

    @When("completa el formulario de registro con nombre {string}, email {string} y contrasena {string}")
    public void completaElFormularioDeRegistro(String nombre, String email, String password) {
        // Email unico para evitar conflicto 409 en re-ejecuciones (Render DB es persistente)
        String uniqueEmail = email.replace("@", "+" + System.currentTimeMillis() + "@");
        UserModel user = UserModel.builder()
                .name(nombre)
                .email(uniqueEmail)
                .password(password)
                .build();
        OnStage.theActorInTheSpotlight().attemptsTo(Register.withData(user));
    }

    @Then("deberia quedar autenticado en el sistema")
    public void deberiaQuedarAutenticadoEnElSistema() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(IsLoggedIn.now(), is(true))
        );
    }
}
