package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

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
        actor.attemptsTo(
                NavigateTo.login(),
                Enter.theValue(email).into(LoginPage.EMAIL_INPUT),
                Enter.theValue(password).into(LoginPage.PASSWORD_INPUT),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}
