package co.edu.udea.fabrica.finanzas.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigateTo implements Task {

    public enum Page {
        LOGIN, DASHBOARD, TRANSACCION, HISTORIAL, METAS, REPORTE, PRESUPUESTO
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
    public static NavigateTo metas()        { return new NavigateTo(Page.METAS); }
    public static NavigateTo reporte()      { return new NavigateTo(Page.REPORTE); }
    public static NavigateTo presupuesto()  { return new NavigateTo(Page.PRESUPUESTO); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String baseUrl = System.getProperty("pages.baseUrl", "http://localhost:3000");
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        if (page == Page.LOGIN) {
            actor.attemptsTo(Open.url(baseUrl + "/"));
            return;
        }

        String targetPath = switch (page) {
            case DASHBOARD   -> "/dashboard";
            case TRANSACCION -> "/dashboard/transaccion";
            case HISTORIAL   -> "/dashboard/historial";
            case METAS        -> "/dashboard/metas";
            case REPORTE      -> "/dashboard/reporte";
            case PRESUPUESTO  -> "/dashboard/presupuesto";
            default           -> "/";
        };

        // Cuando ya estamos dentro del dashboard, usamos el sidebar (navegacion client-side).
        // Esto evita el full page reload que dispara el auth guard antes de que React lea localStorage.
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl != null && currentUrl.contains("/dashboard")) {
            actor.attemptsTo(Click.on(
                Target.the("sidebar " + page.name().toLowerCase())
                      .locatedBy("//aside//a[@href='" + targetPath + "']")
            ));
        } else {
            actor.attemptsTo(Open.url(baseUrl + targetPath));
        }

        // Esperar a que la URL cambie a la pagina destino
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(Exception.class)
                .until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains(targetPath));
    }
}
