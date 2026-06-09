package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class DashboardPage {

    public static final Target BALANCE_TOTAL =
            Target.the("encabezado bienvenida").locatedBy("//h1[contains(@class,'text-3xl')]");

    public static final Target TOTAL_INGRESOS =
            Target.the("total ingresos").locatedBy("//p[contains(@class,'text-green-600') and contains(@class,'text-2xl')]");

    public static final Target TOTAL_GASTOS =
            Target.the("total gastos").locatedBy("//p[contains(@class,'text-red-600') and contains(@class,'text-2xl')]");

    public static final Target DASHBOARD_TITLE =
            Target.the("titulo dashboard").locatedBy("h1, h2");

    public static final Target LOGOUT_BUTTON =
            Target.the("boton cerrar sesion").locatedBy("button[aria-label='Cerrar sesion'], button:contains('Salir')");
}
