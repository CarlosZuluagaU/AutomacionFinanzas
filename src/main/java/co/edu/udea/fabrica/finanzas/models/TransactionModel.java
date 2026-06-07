package co.edu.udea.fabrica.finanzas.models;

public class TransactionModel {

    private String tipo;
    private String monto;
    private String categoria;
    private String fecha;
    private String descripcion;

    private TransactionModel() {}

    public String getTipo()        { return tipo; }
    public String getMonto()       { return monto; }
    public String getCategoria()   { return categoria; }
    public String getFecha()       { return fecha; }
    public String getDescripcion() { return descripcion; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String tipo;
        private String monto;
        private String categoria;
        private String fecha;
        private String descripcion;

        public Builder tipo(String tipo)               { this.tipo = tipo;               return this; }
        public Builder monto(String monto)             { this.monto = monto;             return this; }
        public Builder categoria(String categoria)     { this.categoria = categoria;     return this; }
        public Builder fecha(String fecha)             { this.fecha = fecha;             return this; }
        public Builder descripcion(String descripcion) { this.descripcion = descripcion; return this; }

        public TransactionModel build() {
            TransactionModel t = new TransactionModel();
            t.tipo        = this.tipo;
            t.monto       = this.monto;
            t.categoria   = this.categoria;
            t.fecha       = this.fecha;
            t.descripcion = this.descripcion;
            return t;
        }
    }
}
