package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.HistorialPage;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteTransaction implements Task {

    public DeleteTransaction() {}

    public static DeleteTransaction first() {
        return new DeleteTransaction();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(HistorialPage.FIRST_DELETE_BUTTON));

        // Esperar a que aparezca el dialogo de confirmacion
        new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(8))
                .ignoring(Exception.class)
                .until(d -> !HistorialPage.CONFIRM_DELETE_BUTTON.resolveAllFor(actor).isEmpty());

        actor.attemptsTo(
                Click.on(HistorialPage.CONFIRM_DELETE_BUTTON),
                WaitTime.of(2000)
        );
    }
}
