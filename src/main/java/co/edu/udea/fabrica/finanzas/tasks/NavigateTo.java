package co.edu.udea.fabrica.finanzas.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo implements Task {

    public enum Page {
        LOGIN, DASHBOARD, TRANSACCION, HISTORIAL, METAS, REPORTE
    }

    private final Page page;

    private NavigateTo(Page page) {
        this.page = page;
    }

    public static NavigateTo the(Page page) {
        return new NavigateTo(page);
    }

    public static NavigateTo login()       { return new NavigateTo(Page.LOGIN); }
    public static NavigateTo dashboard()   { return new NavigateTo(Page.DASHBOARD); }
    public static NavigateTo transaccion() { return new NavigateTo(Page.TRANSACCION); }
    public static NavigateTo historial()   { return new NavigateTo(Page.HISTORIAL); }
    public static NavigateTo metas()       { return new NavigateTo(Page.METAS); }
    public static NavigateTo reporte()     { return new NavigateTo(Page.REPORTE); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String baseUrl = System.getProperty("pages.baseUrl", "http://localhost:3000");

        String url = switch (page) {
            case LOGIN       -> baseUrl + "/";
            case DASHBOARD   -> baseUrl + "/dashboard";
            case TRANSACCION -> baseUrl + "/dashboard/transaccion";
            case HISTORIAL   -> baseUrl + "/dashboard/historial";
            case METAS       -> baseUrl + "/dashboard/metas";
            case REPORTE     -> baseUrl + "/dashboard/reporte";
        };

        actor.attemptsTo(Open.url(url));
    }
}
