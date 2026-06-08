package co.edu.udea.fabrica.finanzas.utils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

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
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
