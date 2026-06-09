package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.models.TransactionModel;
import co.edu.udea.fabrica.finanzas.userinterfaces.TransactionPage;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateTransaction implements Task {

    private final TransactionModel transaction;

    private CreateTransaction(TransactionModel transaction) {
        this.transaction = transaction;
    }

    public static CreateTransaction with(TransactionModel transaction) {
        return new CreateTransaction(transaction);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Esperar a que el formulario de transaccion este listo
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> !TransactionPage.MONTO_INPUT.resolveAllFor(actor).isEmpty());

        if ("Ingreso".equalsIgnoreCase(transaction.getTipo())) {
            actor.attemptsTo(Click.on(TransactionPage.TIPO_INGRESO));
        } else {
            actor.attemptsTo(Click.on(TransactionPage.TIPO_GASTO));
        }

        actor.attemptsTo(WaitTime.of(500));

        actor.attemptsTo(Enter.theValue(transaction.getMonto()).into(TransactionPage.MONTO_INPUT));

        // Setear la fecha via JS para evitar problemas con el date-picker nativo de Chrome
        ((JavascriptExecutor) driver).executeScript(
                "var el = document.querySelector('input[type=\"date\"]');" +
                "if(el){ el.value='" + transaction.getFecha() + "';" +
                "el.dispatchEvent(new Event('input',{bubbles:true}));" +
                "el.dispatchEvent(new Event('change',{bubbles:true})); }");
        actor.attemptsTo(WaitTime.of(300));

        // Abrir dropdown y esperar a que aparezca la primera opcion
        actor.attemptsTo(Click.on(TransactionPage.CATEGORIA_TRIGGER));
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .ignoring(Exception.class)
                .until(d -> !TransactionPage.PRIMERA_CATEGORIA.resolveAllFor(actor).isEmpty());
        actor.attemptsTo(Click.on(TransactionPage.PRIMERA_CATEGORIA));
        actor.attemptsTo(WaitTime.of(500));

        if (transaction.getDescripcion() != null && !transaction.getDescripcion().isEmpty()) {
            actor.attemptsTo(
                    Enter.theValue(transaction.getDescripcion()).into(TransactionPage.DESCRIPCION_INPUT)
            );
        }

        actor.attemptsTo(Click.on(TransactionPage.SUBMIT_BUTTON));

        // Esperar a que el backend responda y la UI se actualice
        actor.attemptsTo(WaitTime.of(5000));
    }
}
