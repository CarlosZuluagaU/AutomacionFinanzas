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

public class AttemptLogin implements Task {

    private final String email;
    private final String password;

    private AttemptLogin(String email, String password) {
        this.email    = email;
        this.password = password;
    }

    public static AttemptLogin withCredentials(String email, String password) {
        return new AttemptLogin(email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        actor.attemptsTo(NavigateTo.login());

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> !LoginPage.EMAIL_INPUT.resolveAllFor(actor).isEmpty());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String setReactValue =
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,'value').set;" +
                "var el = document.getElementById('%s');" +
                "if(el){ setter.call(el,'%s'); el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true})); }";

        js.executeScript(String.format(setReactValue, "email", email));
        js.executeScript(String.format(setReactValue, "password", password));

        actor.attemptsTo(WaitTime.of(300), Click.on(LoginPage.LOGIN_BUTTON));
    }
}
