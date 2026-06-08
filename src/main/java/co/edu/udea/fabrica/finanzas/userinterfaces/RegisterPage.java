package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class RegisterPage {

    public static final Target NAME_INPUT =
            Target.the("campo nombre").locatedBy("#name");

    public static final Target EMAIL_INPUT =
            Target.the("campo email").locatedBy("#email");

    public static final Target PASSWORD_INPUT =
            Target.the("campo password").locatedBy("#password");

    public static final Target CONFIRM_PASSWORD_INPUT =
            Target.the("campo confirmar password").locatedBy("#confirmPassword");

    public static final Target REGISTER_BUTTON =
            Target.the("boton crear cuenta").locatedBy("//button[@type='submit']");

    public static final Target LOGIN_LINK =
            Target.the("enlace iniciar sesion").locatedBy("//a[@href='/']");
}
