package co.edu.udea.fabrica.finanzas.utils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Pause;

public class WaitTime implements Task {

    private final long milliseconds;

    private WaitTime(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public static WaitTime of(long milliseconds) {
        return new WaitTime(milliseconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Pause.for_(milliseconds));
    }
}
