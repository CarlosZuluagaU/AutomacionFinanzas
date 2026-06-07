package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final Target EMAIL_INPUT =
            Target.the("campo email").locatedBy("#email");

    public static final Target PASSWORD_INPUT =
            Target.the("campo password").locatedBy("#password");

    public static final Target LOGIN_BUTTON =
            Target.the("boton iniciar sesion").locatedBy("button[type='submit']");

    public static final Target REGISTER_LINK =
            Target.the("enlace registrarse").locatedBy("a[href='/register']");

    public static final Target ERROR_MESSAGE =
            Target.the("mensaje de error").locatedBy("[role='alert'], .error-message, p.text-red-500");
}
