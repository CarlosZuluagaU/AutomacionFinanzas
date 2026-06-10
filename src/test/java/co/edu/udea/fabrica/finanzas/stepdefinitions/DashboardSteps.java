package co.edu.udea.fabrica.finanzas.stepdefinitions;

import co.edu.udea.fabrica.finanzas.questions.DashboardShowsData;
import co.edu.udea.fabrica.finanzas.tasks.Login;
import co.edu.udea.fabrica.finanzas.tasks.NavigateTo;
import co.edu.udea.fabrica.finanzas.userinterfaces.DashboardPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class DashboardSteps {

    private static final String TEST_EMAIL    = "test@finanzas.com";
    private static final String TEST_PASSWORD = "Test1234!";

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("navega al dashboard principal")
    public void navegaAlDashboardPrincipal() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.dashboard());
    }

    @Then("deberia ver el resumen financiero con balance, ingresos y gastos")
    public void deberiaVerElResumenFinanciero() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(DashboardShowsData.forCurrentUser(), is(true))
        );
    }

    @Given("que el usuario {string} acaba de iniciar sesion")
    public void queElUsuarioAcabaDeiniciarSesion(String actorName) {
        OnStage.theActorCalled(actorName).attemptsTo(
                Login.withCredentials(TEST_EMAIL, TEST_PASSWORD)
        );
    }

    @When("navega a la seccion de reporte")
    public void navegaALaSeccionDeReporte() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.reporte());
    }

    @Then("deberia ver el reporte con graficas de transacciones")
    public void deberiaVerElReporteConGraficas() {
        WebDriver driver = BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .ignoring(Exception.class)
                .until(d -> !DashboardPage.REPORTE_TITLE
                        .resolveAllFor(OnStage.theActorInTheSpotlight()).isEmpty());
    }
}
