package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.models.SavingGoalModel;
import co.edu.udea.fabrica.finanzas.userinterfaces.MetasPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class CreateSavingGoal implements Task {

    private final SavingGoalModel goal;

    private CreateSavingGoal(SavingGoalModel goal) {
        this.goal = goal;
    }

    public static CreateSavingGoal with(SavingGoalModel goal) {
        return new CreateSavingGoal(goal);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(goal.getNombre()).into(MetasPage.NOMBRE_INPUT),
                Enter.theValue(goal.getMontoObjetivo()).into(MetasPage.MONTO_OBJETIVO_INPUT)
        );

        if (goal.getFechaLimite() != null && !goal.getFechaLimite().isEmpty()) {
            actor.attemptsTo(
                    Enter.theValue(goal.getFechaLimite()).into(MetasPage.FECHA_LIMITE_INPUT)
            );
        }

        actor.attemptsTo(Click.on(MetasPage.CREAR_META_BUTTON));
    }
}
