package co.edu.udea.fabrica.finanzas.models;

public class BudgetModel {

    private String montoLimite;
    private String fechaInicio;
    private String fechaFin;

    private BudgetModel() {}

    public String getMontoLimite() { return montoLimite; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin()    { return fechaFin; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String montoLimite;
        private String fechaInicio;
        private String fechaFin;

        public Builder montoLimite(String montoLimite) { this.montoLimite = montoLimite; return this; }
        public Builder fechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; return this; }
        public Builder fechaFin(String fechaFin)       { this.fechaFin = fechaFin;       return this; }

        public BudgetModel build() {
            BudgetModel b = new BudgetModel();
            b.montoLimite = this.montoLimite;
            b.fechaInicio = this.fechaInicio;
            b.fechaFin    = this.fechaFin;
            return b;
        }
    }
}
