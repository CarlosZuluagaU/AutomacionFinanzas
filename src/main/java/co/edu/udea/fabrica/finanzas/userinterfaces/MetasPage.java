package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class MetasPage {

    public static final Target NOMBRE_INPUT =
            Target.the("campo nombre meta").locatedBy("#name");

    public static final Target MONTO_OBJETIVO_INPUT =
            Target.the("campo monto objetivo").locatedBy("#targetAmount");

    public static final Target FECHA_LIMITE_INPUT =
            Target.the("campo fecha limite").locatedBy("#deadline");

    public static final Target CREAR_META_BUTTON =
            Target.the("boton crear meta").locatedBy("//button[@type='submit']");

    public static final Target GOAL_CARDS =
            Target.the("tarjetas de metas").locatedBy("//*[@data-testid='goal-card' or contains(@class,'goal-card') or self::article]");

    public static final Target APORTE_INPUT =
            Target.the("campo aporte").locatedBy("//input[contains(@placeholder,'porte') or (@type='number' and contains(@aria-label,'porte'))]");

    public static final Target APORTE_BUTTON =
            Target.the("boton aporte").locatedBy("//button[contains(text(),'+') or @aria-label='Agregar aporte']");

    public static final Target PROGRESS_BAR =
            Target.the("barra de progreso").locatedBy("//*[@role='progressbar' or contains(@class,'progress-bar') or self::progress]");

    public static final Target GOAL_NAMES =
            Target.the("nombres de metas").locatedBy("//h3 | //*[contains(@class,'goal-name') or @data-testid='goal-name']");
}
