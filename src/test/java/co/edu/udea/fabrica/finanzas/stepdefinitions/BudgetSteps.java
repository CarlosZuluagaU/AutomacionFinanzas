package co.edu.udea.fabrica.finanzas.stepdefinitions;

import co.edu.udea.fabrica.finanzas.models.BudgetModel;
import co.edu.udea.fabrica.finanzas.questions.BudgetIsActive;
import co.edu.udea.fabrica.finanzas.tasks.CreateBudget;
import co.edu.udea.fabrica.finanzas.tasks.NavigateTo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class BudgetSteps {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("navega a la seccion de presupuesto")
    public void navegaALaSeccionDePresupuesto() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.presupuesto());
    }

    @When("crea un presupuesto con limite {string} desde {string} hasta {string}")
    public void creaUnPresupuestoConLimite(String monto, String fechaInicio, String fechaFin) {
        BudgetModel budget = BudgetModel.builder()
                .montoLimite(monto)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .build();
        OnStage.theActorInTheSpotlight().attemptsTo(CreateBudget.with(budget));
    }

    @Then("el presupuesto deberia aparecer como activo")
    public void elPresupuestoDeberiaAparecerComoActivo() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(BudgetIsActive.now(), is(true))
        );
    }
}
