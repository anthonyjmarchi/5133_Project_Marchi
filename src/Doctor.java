public class Doctor extends Person {

    String privileges = "";

    public Doctor(String type, String firstName, String lastName, String privileges) {
        super(type, firstName, lastName);
        this.privileges = privileges;
    }

    public String getprivileges() {
        return privileges;
    }

    public void setprivileges(String admittingPrivileges) {
        this.privileges = privileges;
    }

}
