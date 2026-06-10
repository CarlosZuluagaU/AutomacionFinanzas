package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.models.SavingGoalModel;
import co.edu.udea.fabrica.finanzas.userinterfaces.MetasPage;
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
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Esperar a que el formulario de metas este completamente cargado
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> !MetasPage.NOMBRE_INPUT.resolveAllFor(actor).isEmpty());

        actor.attemptsTo(WaitTime.of(500));

        // Limpiar el campo fecha con JS para evitar que Chrome autofill lo llene con basura
        ((JavascriptExecutor) driver).executeScript(
                "var el = document.getElementById('deadline'); if(el) el.value = '';");

        actor.attemptsTo(
                Enter.theValue(goal.getNombre()).into(MetasPage.NOMBRE_INPUT),
                Enter.theValue(goal.getMontoObjetivo()).into(MetasPage.MONTO_OBJETIVO_INPUT)
        );

        // React controla el input con value={state}, por lo que el.value = x no actualiza
        // el estado interno. Hay que usar el setter nativo del prototipo para que React
        // procese el cambio como si viniera del usuario.
        String fechaValue = (goal.getFechaLimite() != null && !goal.getFechaLimite().isEmpty())
                ? goal.getFechaLimite() : "";
        ((JavascriptExecutor) driver).executeScript(
                "var el = document.getElementById('deadline');" +
                "if(el){" +
                "  var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,'value').set;" +
                "  setter.call(el,'" + fechaValue + "');" +
                "  el.dispatchEvent(new Event('input',{bubbles:true}));" +
                "  el.dispatchEvent(new Event('change',{bubbles:true}));" +
                "}");

        actor.attemptsTo(Click.on(MetasPage.CREAR_META_BUTTON));

        // Esperar confirmacion visible del frontend antes de continuar
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .ignoring(Exception.class)
                .until(d -> !MetasPage.META_CREADA_OK.resolveAllFor(actor).isEmpty());

        // Dar tiempo al refreshKey de React para que la lista se recargue
        actor.attemptsTo(WaitTime.of(2000));
    }
}
