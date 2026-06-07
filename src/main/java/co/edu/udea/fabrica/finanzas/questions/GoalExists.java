package co.edu.udea.fabrica.finanzas.questions;

import co.edu.udea.fabrica.finanzas.userinterfaces.MetasPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GoalExists implements Question<Boolean> {

    private final String goalName;

    private GoalExists(String goalName) {
        this.goalName = goalName;
    }

    public static GoalExists withName(String goalName) {
        return new GoalExists(goalName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return MetasPage.GOAL_NAMES
                    .resolveAllFor(actor)
                    .stream()
                    .anyMatch(el -> el.getText().contains(goalName));
        } catch (Exception e) {
            return false;
        }
    }
}
