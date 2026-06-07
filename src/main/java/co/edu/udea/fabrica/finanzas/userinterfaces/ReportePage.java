package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ReportePage {

    public static final Target REPORTE_TITLE =
            Target.the("titulo reporte").locatedBy("h1, h2");

    public static final Target CHART_CONTAINER =
            Target.the("contenedor grafica").locatedBy("canvas, svg, [data-testid='chart']");

    public static final Target SUMMARY_CARDS =
            Target.the("tarjetas resumen").locatedBy("[data-testid='summary-card'], .summary-card");

    public static final Target PERIODO_SELECT =
            Target.the("selector periodo").locatedBy("select[name='periodo'], select#periodo");
}
