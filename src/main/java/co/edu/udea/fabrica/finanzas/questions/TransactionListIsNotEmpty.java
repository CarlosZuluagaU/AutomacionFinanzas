package co.edu.udea.fabrica.finanzas.questions;

import co.edu.udea.fabrica.finanzas.userinterfaces.HistorialPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class TransactionListIsNotEmpty implements Question<Boolean> {

    private TransactionListIsNotEmpty() {}

    public static TransactionListIsNotEmpty inHistorial() {
        return new TransactionListIsNotEmpty();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(d -> !HistorialPage.TRANSACTION_ROWS.resolveAllFor(actor).isEmpty());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
