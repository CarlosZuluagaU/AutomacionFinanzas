package co.edu.udea.fabrica.finanzas.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class HistorialPage {

    public static final Target TRANSACTION_ROWS =
            Target.the("filas de transacciones").locatedBy("tbody tr");

    public static final Target FIRST_DELETE_BUTTON =
            Target.the("primer boton eliminar").locatedBy("(//span[contains(@class,'sr-only') and contains(text(),'Eliminar')]/..)[1]");

    public static final Target DELETE_BUTTONS =
            Target.the("botones eliminar").locatedBy("//span[contains(@class,'sr-only') and contains(text(),'Eliminar')]/..");

    public static final Target EDIT_BUTTONS =
            Target.the("botones editar").locatedBy("//span[contains(@class,'sr-only') and contains(text(),'Editar')]/..");

    public static final Target CONFIRM_DELETE_BUTTON =
            Target.the("boton confirmar eliminar").locatedBy("//button[contains(text(),'Eliminar') and not(contains(@class,'sr-only'))]");

    public static final Target CANCEL_DELETE_BUTTON =
            Target.the("boton cancelar eliminar").locatedBy("//button[contains(text(),'Cancelar')]");

    public static final Target EMPTY_STATE =
            Target.the("estado vacio").locatedBy("//p[contains(text(),'No hay transacciones')]");

    public static final Target SEARCH_INPUT =
            Target.the("buscador").locatedBy("input[placeholder*='uscar'], input[type='search']");

    public static final Target FILTER_TYPE =
            Target.the("filtro tipo").locatedBy("select[name='tipo'], select#tipo");
}
