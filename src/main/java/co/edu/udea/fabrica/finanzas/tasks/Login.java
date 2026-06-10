package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.LoginPage;
import co.edu.udea.fabrica.finanzas.utils.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Login implements Task {

    private final String email;
    private final String password;

    private Login(String email, String password) {
        this.email    = email;
        this.password = password;
    }

    public static Login withCredentials(String email, String password) {
        return new Login(email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
        } catch (Exception ignored) {}

        actor.attemptsTo(NavigateTo.login());

        // Esperar a que los inputs esten listos antes de escribir
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> !LoginPage.EMAIL_INPUT.resolveAllFor(actor).isEmpty());

        // Usar setter nativo para que React actualice el estado de inputs controlados
        // (type="email" y type="password" no siempre disparan onChange con sendKeys)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String setReactValue =
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,'value').set;" +
                "var el = document.getElementById('%s');" +
                "if(el){ setter.call(el,'%s'); el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true})); }";

        js.executeScript(String.format(setReactValue, "email", email));
        js.executeScript(String.format(setReactValue, "password", password));

        actor.attemptsTo(WaitTime.of(300));
        actor.attemptsTo(Click.on(LoginPage.LOGIN_BUTTON));

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains("/dashboard"));
    }
}
