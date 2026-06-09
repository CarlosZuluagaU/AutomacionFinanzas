package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.models.BudgetModel;
import co.edu.udea.fabrica.finanzas.userinterfaces.PresupuestoPage;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateBudget implements Task {

    private final BudgetModel budget;

    private CreateBudget(BudgetModel budget) {
        this.budget = budget;
    }

    public static CreateBudget with(BudgetModel budget) {
        return new CreateBudget(budget);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> !PresupuestoPage.MONTO_LIMITE_INPUT.resolveAllFor(actor).isEmpty());

        actor.attemptsTo(WaitTime.of(500));
        actor.attemptsTo(Enter.theValue(budget.getMontoLimite()).into(PresupuestoPage.MONTO_LIMITE_INPUT));

        // Usar nativeInputValueSetter para que React detecte el cambio en inputs controlados
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String setDateScript =
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "var el = document.getElementById('%s');" +
                "if(el){" +
                "  setter.call(el, '%s');" +
                "  el.dispatchEvent(new Event('input',{bubbles:true}));" +
                "  el.dispatchEvent(new Event('change',{bubbles:true}));" +
                "}";

        js.executeScript(String.format(setDateScript, "startDate", budget.getFechaInicio()));
        js.executeScript(String.format(setDateScript, "endDate",   budget.getFechaFin()));

        actor.attemptsTo(WaitTime.of(300));
        actor.attemptsTo(Click.on(PresupuestoPage.SUBMIT_BUTTON));
        actor.attemptsTo(WaitTime.of(3000));
    }
}
