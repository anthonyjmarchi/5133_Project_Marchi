



public class Patient extends Person {

    private int patientID = 0;
    private int patientRoomNumber = 0;
    private String patientEmergencyContact = "";
    private String patientEmergencyContactNumber = "";
    private String patientPolicyNumber = "";
    private String patientInsurancePolicyCompany = "";
    private String patientsPrimaryDoctorLastName = "";
    private String patientDiagnosis = "";
    private String patientAdmissionDate = "";
    private String patientDischargeDate = "";

    public Patient(String type, String firstName, String lastName, int patientID, int patientRoomNumber, String patientEmergencyContact, String patientEmergencyContactNumber, String patientPolicyNumber, String patientInsurancePolicyCompany, String patientsPrimaryDoctorLastName, String patientDiagnosis, String patientAdmissionDate, String patientDischargeDate) {
        super(type, firstName, lastName);
        this.patientID = patientID;
        this.patientRoomNumber = patientRoomNumber;
        this.patientEmergencyContact = patientEmergencyContact;
        this.patientEmergencyContactNumber = patientEmergencyContactNumber;
        this.patientPolicyNumber = patientPolicyNumber;
        this.patientInsurancePolicyCompany = patientInsurancePolicyCompany;
        this.patientsPrimaryDoctorLastName = patientsPrimaryDoctorLastName;
        this.patientDiagnosis = patientDiagnosis;
        this.patientAdmissionDate = patientAdmissionDate;
        this.patientDischargeDate = patientDischargeDate;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getPatientRoomNumber() {
        return patientRoomNumber;
    }

    public void setPatientRoomNumber(int patientRoomNumber) {
        this.patientRoomNumber = patientRoomNumber;
    }

    public String getPatientEmergencyContact() {
        return patientEmergencyContact;
    }

    public void setPatientEmergencyContact(String patientEmergencyContact) {
        this.patientEmergencyContact = patientEmergencyContact;
    }

    public String getPatientEmergencyContactNumber() {
        return patientEmergencyContactNumber;
    }

    public void setPatientEmergencyContactNumber(String patientEmergencyContactNumber) {
        this.patientEmergencyContactNumber = patientEmergencyContactNumber;
    }

    public String getPatientPolicyNumber() {
        return patientPolicyNumber;
    }

    public void setPatientPolicyNumber(String patientPolicyNumber) {
        this.patientPolicyNumber = patientPolicyNumber;
    }

    public String getPatientInsurancePolicyCompany() {
        return patientInsurancePolicyCompany;
    }

    public void setPatientInsurancePolicyCompany(String patientInsurancePolicyCompany) {
        this.patientInsurancePolicyCompany = patientInsurancePolicyCompany;
    }

    public String getPatientsPrimaryDoctorLastName() {
        return patientsPrimaryDoctorLastName;
    }

    public void setPatientsPrimaryDoctorLastName(String patientsPrimaryDoctorLastName) {
        this.patientsPrimaryDoctorLastName = patientsPrimaryDoctorLastName;
    }

    public String getPatientDiagnosis() {
        return patientDiagnosis;
    }

    public void setPatientDiagnosis(String patientDiagnosis) {
        this.patientDiagnosis = patientDiagnosis;
    }

    public String getPatientAdmissionDate() {
        return patientAdmissionDate;
    }

    public void setPatientAdmissionDate(String patientAdmissionDate) {
        this.patientAdmissionDate = patientAdmissionDate;
    }

    public String getPatientDischargeDate() {
        return patientDischargeDate;
    }

    public void setPatientDischargeDate(String patientDischargeDate) {
        this.patientDischargeDate = patientDischargeDate;
    }
}
