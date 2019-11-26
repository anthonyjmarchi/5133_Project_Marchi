public class Treatment {

    private String doctorLastName = "";
    private String patientLastName = "";
    private String treatmentType = "";
    private String treatment = "";
    private String timestamp = "";

    public Treatment(String doctorLastName, String patientLastName, String treatmentType, String treatment, String timestamp) {
        this.doctorLastName = doctorLastName;
        this.patientLastName = patientLastName;
        this.treatmentType = treatmentType;
        this.treatment = treatment;
        this.timestamp = timestamp;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
