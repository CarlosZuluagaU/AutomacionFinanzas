package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.models.UserModel;
import co.edu.udea.fabrica.finanzas.userinterfaces.LoginPage;
import co.edu.udea.fabrica.finanzas.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Register implements Task {

    private final UserModel user;

    private Register(UserModel user) {
        this.user = user;
    }

    public static Register withData(UserModel user) {
        return new Register(user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
        } catch (Exception ignored) {}
        actor.attemptsTo(
                NavigateTo.login(),
                Click.on(LoginPage.REGISTER_LINK),
                Enter.theValue(user.getName()).into(RegisterPage.NAME_INPUT),
                Enter.theValue(user.getEmail()).into(RegisterPage.EMAIL_INPUT),
                Enter.theValue(user.getPassword()).into(RegisterPage.PASSWORD_INPUT),
                Enter.theValue(user.getPassword()).into(RegisterPage.CONFIRM_PASSWORD_INPUT),
                Click.on(RegisterPage.REGISTER_BUTTON)
        );
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains("/dashboard"));
    }
}
