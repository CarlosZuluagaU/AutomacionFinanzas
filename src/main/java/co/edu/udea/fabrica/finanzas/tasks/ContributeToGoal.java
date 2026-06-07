package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.MetasPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class ContributeToGoal implements Task {

    private final String amount;

    private ContributeToGoal(String amount) {
        this.amount = amount;
    }

    public static ContributeToGoal withAmount(String amount) {
        return new ContributeToGoal(amount);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(amount).into(MetasPage.APORTE_INPUT),
                Click.on(MetasPage.APORTE_BUTTON)
        );
    }
}
