package co.edu.udea.fabrica.finanzas.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class IsLoggedIn implements Question<Boolean> {

    private IsLoggedIn() {}

    public static IsLoggedIn now() {
        return new IsLoggedIn();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains("/dashboard"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
