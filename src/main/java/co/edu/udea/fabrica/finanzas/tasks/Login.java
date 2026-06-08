package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
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
        actor.attemptsTo(
                NavigateTo.login(),
                Enter.theValue(email).into(LoginPage.EMAIL_INPUT),
                Enter.theValue(password).into(LoginPage.PASSWORD_INPUT),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains("/dashboard"));
    }
}
