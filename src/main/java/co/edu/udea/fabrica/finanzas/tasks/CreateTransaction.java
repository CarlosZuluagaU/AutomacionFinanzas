package co.edu.udea.fabrica.finanzas.tasks;

import co.edu.udea.fabrica.finanzas.models.TransactionModel;
import co.edu.udea.fabrica.finanzas.userinterfaces.TransactionPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class CreateTransaction implements Task {

    private final TransactionModel transaction;

    private CreateTransaction(TransactionModel transaction) {
        this.transaction = transaction;
    }

    public static CreateTransaction with(TransactionModel transaction) {
        return new CreateTransaction(transaction);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if ("Ingreso".equalsIgnoreCase(transaction.getTipo())) {
            actor.attemptsTo(Click.on(TransactionPage.TIPO_INGRESO));
        } else {
            actor.attemptsTo(Click.on(TransactionPage.TIPO_GASTO));
        }

        actor.attemptsTo(
                Enter.theValue(transaction.getMonto()).into(TransactionPage.MONTO_INPUT),
                Click.on(TransactionPage.FECHA_INPUT),
                Enter.theValue(transaction.getFecha()).into(TransactionPage.FECHA_INPUT)
        );

        // Seleccionar la primera categoria disponible en el Radix Select
        actor.attemptsTo(Click.on(TransactionPage.CATEGORIA_TRIGGER));
        actor.attemptsTo(Click.on(TransactionPage.PRIMERA_CATEGORIA));

        if (transaction.getDescripcion() != null && !transaction.getDescripcion().isEmpty()) {
            actor.attemptsTo(
                    Enter.theValue(transaction.getDescripcion()).into(TransactionPage.DESCRIPCION_INPUT)
            );
        }

        actor.attemptsTo(Click.on(TransactionPage.SUBMIT_BUTTON));
    }
}
