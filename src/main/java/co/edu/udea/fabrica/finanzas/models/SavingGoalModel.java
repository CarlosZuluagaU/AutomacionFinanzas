package co.edu.udea.fabrica.finanzas.models;

public class SavingGoalModel {

    private String nombre;
    private String montoObjetivo;
    private String fechaLimite;

    private SavingGoalModel() {}

    public String getNombre()       { return nombre; }
    public String getMontoObjetivo() { return montoObjetivo; }
    public String getFechaLimite()  { return fechaLimite; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String nombre;
        private String montoObjetivo;
        private String fechaLimite;

        public Builder nombre(String nombre)               { this.nombre = nombre;               return this; }
        public Builder montoObjetivo(String montoObjetivo) { this.montoObjetivo = montoObjetivo; return this; }
        public Builder fechaLimite(String fechaLimite)     { this.fechaLimite = fechaLimite;     return this; }

        public SavingGoalModel build() {
            SavingGoalModel g = new SavingGoalModel();
            g.nombre       = this.nombre;
            g.montoObjetivo = this.montoObjetivo;
            g.fechaLimite  = this.fechaLimite;
            return g;
        }
    }
}
