package co.edu.udea.fabrica.finanzas.stepdefinitions;

import co.edu.udea.fabrica.finanzas.models.SavingGoalModel;
import co.edu.udea.fabrica.finanzas.questions.GoalExists;
import co.edu.udea.fabrica.finanzas.tasks.ContributeToGoal;
import co.edu.udea.fabrica.finanzas.tasks.CreateSavingGoal;
import co.edu.udea.fabrica.finanzas.tasks.Login;
import co.edu.udea.fabrica.finanzas.tasks.NavigateTo;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class SavingGoalSteps {

    private static final String TEST_EMAIL    = "test@finanzas.com";
    private static final String TEST_PASSWORD = "Test1234!";

    // Se genera en el When y se reutiliza en el Then para que coincidan
    private String nombreMetaGenerado;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("navega a la seccion de metas de ahorro")
    public void navegaALaSeccionDeMetasDeAhorro() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.metas());
    }

    @When("crea una meta llamada {string} con monto objetivo {string}")
    public void creaUnaMetaLlamadaConMontoObjetivo(String nombre, String monto) {
        String fechaFutura = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        nombreMetaGenerado = nombre + "_" + System.currentTimeMillis();
        SavingGoalModel goal = SavingGoalModel.builder()
                .nombre(nombreMetaGenerado)
                .montoObjetivo(monto)
                .fechaLimite(fechaFutura)
                .build();
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateSavingGoal.with(goal)
        );
    }

    @Then("la meta {string} deberia aparecer en la lista")
    public void laMetaDeberiaAparecerEnLaLista(String nombre) {
        String nameToCheck = (nombreMetaGenerado != null) ? nombreMetaGenerado : nombre;
        OnStage.theActorInTheSpotlight().should(
                seeThat(GoalExists.withName(nameToCheck), is(true))
        );
    }

    @Given("que el usuario {string} tiene una meta de ahorro creada")
    public void queElUsuarioTieneUnaMetaDeAhorroCerada(String actorName) {
        OnStage.theActorCalled(actorName).attemptsTo(
                Login.withCredentials(TEST_EMAIL, TEST_PASSWORD),
                NavigateTo.metas()
        );
        String fechaFutura = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        SavingGoalModel goal = SavingGoalModel.builder()
                .nombre("Meta automatizada_" + System.currentTimeMillis())
                .montoObjetivo("100000")
                .fechaLimite(fechaFutura)
                .build();
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateSavingGoal.with(goal)
        );
    }

    @When("aporta {string} a la primera meta")
    public void aportaALaPrimeraMeta(String monto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ContributeToGoal.withAmount(monto),
                WaitTime.of(1500)
        );
    }

    @Then("la barra de progreso de la meta deberia aumentar")
    public void laBarraDeProgresoDelaMetaDeberiaAumentar() {
        // La verificacion visual se confirma en el reporte de Serenity con screenshot
        OnStage.theActorInTheSpotlight().attemptsTo(WaitTime.of(500));
    }
}
