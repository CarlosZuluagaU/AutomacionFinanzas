package co.edu.udea.fabrica.finanzas.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

public class IsLoggedIn implements Question<Boolean> {

    private IsLoggedIn() {}

    public static IsLoggedIn now() {
        return new IsLoggedIn();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String url = ThucydidesWebDriverSupport.getDriver().getCurrentUrl();
        return url != null && url.contains("/dashboard");
    }
}
