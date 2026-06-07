package co.edu.udea.fabrica.finanzas.questions;

import co.edu.udea.fabrica.finanzas.userinterfaces.HistorialPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class TransactionListIsNotEmpty implements Question<Boolean> {

    private TransactionListIsNotEmpty() {}

    public static TransactionListIsNotEmpty inHistorial() {
        return new TransactionListIsNotEmpty();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            List<?> rows = HistorialPage.TRANSACTION_ROWS.resolveAllFor(actor);
            return !rows.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
