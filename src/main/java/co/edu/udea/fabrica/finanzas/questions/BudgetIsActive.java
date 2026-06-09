package co.edu.udea.fabrica.finanzas.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BudgetIsActive implements Question<Boolean> {

    private BudgetIsActive() {}

    public static BudgetIsActive now() {
        return new BudgetIsActive();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .ignoring(Exception.class)
                    .until(d -> !d.findElements(
                            By.xpath("//*[contains(text(),'Presupuesto Activo')]")).isEmpty());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
