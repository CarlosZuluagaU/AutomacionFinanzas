package co.edu.udea.fabrica.finanzas.questions;

import co.edu.udea.fabrica.finanzas.userinterfaces.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;

public class DashboardShowsData implements Question<Boolean> {

    private DashboardShowsData() {}

    public static DashboardShowsData forCurrentUser() {
        return new DashboardShowsData();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return DashboardPage.BALANCE_TOTAL.resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}
