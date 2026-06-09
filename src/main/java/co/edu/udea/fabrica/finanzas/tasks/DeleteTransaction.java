package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.HistorialPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class DeleteTransaction implements Task {

    public DeleteTransaction() {}

    public static DeleteTransaction first() {
        return new DeleteTransaction();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HistorialPage.FIRST_DELETE_BUTTON),
                Click.on(HistorialPage.CONFIRM_DELETE_BUTTON)
        );
    }
}
