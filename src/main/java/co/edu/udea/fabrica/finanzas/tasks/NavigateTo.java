package co.edu.udea.fabrica.finanzas.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;

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

        if (page == Page.LOGIN) {
            actor.attemptsTo(Open.url(baseUrl + "/"));
            return;
        }

        // Cuando ya estamos dentro del dashboard, usamos el sidebar (navegacion client-side).
        // Esto evita el full page reload que dispara el auth guard antes de que React lea localStorage.
        String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        if (currentUrl != null && currentUrl.contains("/dashboard")) {
            String href = switch (page) {
                case DASHBOARD   -> "/dashboard";
                case TRANSACCION -> "/dashboard/transaccion";
                case HISTORIAL   -> "/dashboard/historial";
                case METAS       -> "/dashboard/metas";
                case REPORTE     -> "/dashboard/reporte";
                default          -> null;
            };
            if (href != null) {
                actor.attemptsTo(Click.on(
                    Target.the("sidebar " + page.name().toLowerCase())
                          .locatedBy("//aside//a[@href='" + href + "']")
                ));
                return;
            }
        }

        String url = switch (page) {
            case DASHBOARD   -> baseUrl + "/dashboard";
            case TRANSACCION -> baseUrl + "/dashboard/transaccion";
            case HISTORIAL   -> baseUrl + "/dashboard/historial";
            case METAS       -> baseUrl + "/dashboard/metas";
            case REPORTE     -> baseUrl + "/dashboard/reporte";
            default          -> baseUrl + "/";
        };
        actor.attemptsTo(Open.url(url));
    }
}
