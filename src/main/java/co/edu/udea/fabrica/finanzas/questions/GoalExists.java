package co.edu.udea.fabrica.finanzas.questions;

import co.edu.udea.fabrica.finanzas.userinterfaces.MetasPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

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
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(d -> MetasPage.GOAL_NAMES.resolveAllFor(actor)
                            .stream()
                            .anyMatch(el -> el.getText().contains(goalName)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
