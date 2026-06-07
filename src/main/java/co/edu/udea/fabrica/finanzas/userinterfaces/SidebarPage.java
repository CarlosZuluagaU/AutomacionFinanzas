package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class SidebarPage {

    public static final Target NAV_DASHBOARD =
            Target.the("nav dashboard").locatedBy("a[href='/dashboard']");

    public static final Target NAV_TRANSACCION =
            Target.the("nav nueva transaccion").locatedBy("a[href='/dashboard/transaccion']");

    public static final Target NAV_HISTORIAL =
            Target.the("nav historial").locatedBy("a[href='/dashboard/historial']");

    public static final Target NAV_METAS =
            Target.the("nav metas").locatedBy("a[href='/dashboard/metas']");

    public static final Target NAV_REPORTE =
            Target.the("nav reporte").locatedBy("a[href='/dashboard/reporte']");
}
