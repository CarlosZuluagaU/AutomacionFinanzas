package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PresupuestoPage {

    public static final Target MONTO_LIMITE_INPUT =
            Target.the("campo monto limite").locatedBy("#limitAmount");

    public static final Target FECHA_INICIO_INPUT =
            Target.the("campo fecha inicio").locatedBy("#startDate");

    public static final Target FECHA_FIN_INPUT =
            Target.the("campo fecha fin").locatedBy("#endDate");

    public static final Target SUBMIT_BUTTON =
            Target.the("boton crear presupuesto").locatedBy("//button[@type='submit']");

    public static final Target SUCCESS_MESSAGE =
            Target.the("mensaje presupuesto creado")
                  .locatedBy("//*[contains(text(),'Presupuesto creado exitosamente')]");

    public static final Target PRESUPUESTO_ACTIVO =
            Target.the("card presupuesto activo")
                  .locatedBy("//*[contains(text(),'Presupuesto Activo')]");
}
