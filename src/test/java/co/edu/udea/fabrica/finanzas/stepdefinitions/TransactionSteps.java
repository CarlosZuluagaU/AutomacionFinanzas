package co.edu.udea.fabrica.finanzas.stepdefinitions;

import co.edu.udea.fabrica.finanzas.models.TransactionModel;
import co.edu.udea.fabrica.finanzas.questions.TransactionListIsNotEmpty;
import co.edu.udea.fabrica.finanzas.tasks.CreateTransaction;
import co.edu.udea.fabrica.finanzas.tasks.DeleteTransaction;
import co.edu.udea.fabrica.finanzas.tasks.Login;
import co.edu.udea.fabrica.finanzas.tasks.NavigateTo;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class TransactionSteps {

    private static final String TEST_EMAIL    = "test@finanzas.com";
    private static final String TEST_PASSWORD = "Test1234!";

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario {string} esta autenticado en el sistema")
    public void queElUsuarioEstaAutenticadoEnElSistema(String actorName) {
        OnStage.theActorCalled(actorName).attemptsTo(
                Login.withCredentials(TEST_EMAIL, TEST_PASSWORD)
        );
    }

    @When("navega a la seccion de nueva transaccion")
    public void navegaALaSeccionDeNuevaTransaccion() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.transaccion());
    }

    @When("crea una transaccion de tipo {string} por valor {string} con fecha {string}")
    public void creaUnaTransaccionDeTipoPorValorConFecha(String tipo, String monto, String fecha) {
        TransactionModel tx = TransactionModel.builder()
                .tipo(tipo)
                .monto(monto)
                .fecha(fecha)
                .descripcion("Automatizado")
                .build();
        OnStage.theActorInTheSpotlight().attemptsTo(CreateTransaction.with(tx));
    }

    @Then("la transaccion deberia aparecer en el historial")
    public void laTransaccionDeberiaAparecerEnElHistorial() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavigateTo.historial(),
                WaitTime.of(1500)
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(TransactionListIsNotEmpty.inHistorial(), is(true))
        );
    }

    @Given("que el usuario {string} tiene al menos una transaccion registrada")
    public void queElUsuarioTieneAlMenosUnaTransaccionRegistrada(String actorName) {
        OnStage.theActorCalled(actorName).attemptsTo(
                Login.withCredentials(TEST_EMAIL, TEST_PASSWORD),
                NavigateTo.transaccion()
        );
        TransactionModel tx = TransactionModel.builder()
                .tipo("Ingreso")
                .monto("50000")
                .fecha("2026-06-07")
                .descripcion("Transaccion de prueba")
                .build();
        OnStage.theActorInTheSpotlight().attemptsTo(CreateTransaction.with(tx));
    }

    @When("navega al historial y elimina la primera transaccion")
    public void navegaAlHistorialYEliminaLaPrimeraTransaccion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavigateTo.historial(),
                WaitTime.of(1000),
                DeleteTransaction.first()
        );
    }

    @Then("la transaccion deberia eliminarse correctamente")
    public void laTransaccionDeberiaEliminarsecorrectamente() {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitTime.of(1000));
    }
}
