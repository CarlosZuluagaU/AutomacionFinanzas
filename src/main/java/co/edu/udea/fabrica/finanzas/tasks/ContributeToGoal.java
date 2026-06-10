package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.MetasPage;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Esperar a que el input de aporte sea visible (las tarjetas ya cargaron)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> !MetasPage.APORTE_INPUT.resolveAllFor(actor).isEmpty());

        // Usar el setter nativo para que React actualice el estado del input controlado
        ((JavascriptExecutor) driver).executeScript(
                "var el = document.querySelector('input[placeholder=\"Monto a aportar\"]');" +
                "if(el){" +
                "  var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,'value').set;" +
                "  setter.call(el, '" + amount + "');" +
                "  el.dispatchEvent(new Event('input',{bubbles:true}));" +
                "  el.dispatchEvent(new Event('change',{bubbles:true}));" +
                "}");

        // Pequeña pausa para que React procese el cambio de estado antes del click
        actor.attemptsTo(WaitTime.of(400));
        actor.attemptsTo(Click.on(MetasPage.APORTE_BUTTON));

        // Esperar a que el API procese el aporte y la lista se refresque
        actor.attemptsTo(WaitTime.of(2500));
    }
}
