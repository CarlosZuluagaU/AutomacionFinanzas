package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class TransactionPage {

    public static final Target TIPO_INGRESO =
            Target.the("tipo ingreso").locatedBy("//button[contains(.,'Ingreso') and @type='button']");

    public static final Target TIPO_GASTO =
            Target.the("tipo gasto").locatedBy("//button[contains(.,'Gasto') and @type='button']");

    public static final Target MONTO_INPUT =
            Target.the("campo monto").locatedBy("//input[@type='number']");

    public static final Target CATEGORIA_TRIGGER =
            Target.the("selector categoria").locatedBy("//button[@role='combobox']");

    public static final Target PRIMERA_CATEGORIA =
            Target.the("primera opcion categoria").locatedBy("//div[@role='option'][1]");

    public static final Target FECHA_INPUT =
            Target.the("campo fecha").locatedBy("//input[@type='date']");

    public static final Target DESCRIPCION_INPUT =
            Target.the("campo descripcion").locatedBy("//textarea");

    public static final Target SUBMIT_BUTTON =
            Target.the("boton guardar transaccion").locatedBy("//button[@type='submit']");

    public static final Target SUCCESS_MESSAGE =
            Target.the("mensaje exito").locatedBy("//*[contains(@class,'text-green') or @role='alert']");
}
