package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class DashboardPage {

    public static final Target BALANCE_TOTAL =
            Target.the("balance total").locatedBy("h2.text-3xl, [data-testid='balance-total']");

    public static final Target TOTAL_INGRESOS =
            Target.the("total ingresos").locatedBy("[data-testid='total-ingresos'], .text-green-600");

    public static final Target TOTAL_GASTOS =
            Target.the("total gastos").locatedBy("[data-testid='total-gastos'], .text-red-600");

    public static final Target DASHBOARD_TITLE =
            Target.the("titulo dashboard").locatedBy("h1, h2");

    public static final Target LOGOUT_BUTTON =
            Target.the("boton cerrar sesion").locatedBy("button[aria-label='Cerrar sesion'], button:contains('Salir')");
}
