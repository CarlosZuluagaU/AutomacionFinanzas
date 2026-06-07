package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class TransactionPage {

    public static final Target TIPO_INGRESO =
            Target.the("tipo ingreso").locatedBy("//button[contains(text(),'Ingreso')]");

    public static final Target TIPO_GASTO =
            Target.the("tipo gasto").locatedBy("//button[contains(text(),'Gasto')]");

    public static final Target MONTO_INPUT =
            Target.the("campo monto").locatedBy("input[type='number']");

    public static final Target CATEGORIA_INPUT =
            Target.the("campo categoria").locatedBy("input[placeholder*='ategor'], select#categoria, input#categoria");

    public static final Target FECHA_INPUT =
            Target.the("campo fecha").locatedBy("input[type='date']");

    public static final Target DESCRIPCION_INPUT =
            Target.the("campo descripcion").locatedBy("textarea");

    public static final Target SUBMIT_BUTTON =
            Target.the("boton guardar transaccion").locatedBy("button[type='submit']");

    public static final Target SUCCESS_MESSAGE =
            Target.the("mensaje exito").locatedBy("[role='alert'].bg-green-50, .text-green-700");
}
