package co.edu.udea.fabrica.finanzas.models;

public class UserModel {

    private String name;
    private String email;
    private String password;

    private UserModel() {}

    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String email;
        private String password;

        public Builder name(String name)         { this.name = name;         return this; }
        public Builder email(String email)       { this.email = email;       return this; }
        public Builder password(String password) { this.password = password; return this; }

        public UserModel build() {
            UserModel u = new UserModel();
            u.name     = this.name;
            u.email    = this.email;
            u.password = this.password;
            return u;
        }
    }
}
