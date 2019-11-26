import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    /**
     * Imports data into SQL table.
     * Use the following files:
     * C://Users//antho//sqlite//Treatment.txt
     * C://Users//antho//sqlite//additionalDoctor.txt
     * C://Users//antho//sqlite//Patient.txt
     * @param args
     */

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        DataImporter test = new DataImporter();
        int typeOfFile = 0;
        String input = "";
        Boolean continueImporting = true;
        while (continueImporting == true) {
            System.out.println("Please select data file type: ");
            System.out.println("1. General Import 2. Additional Doctor 3. Treatment");
            typeOfFile = Integer.parseInt(myObj.nextLine());
            if (typeOfFile == 1) {
                ArrayList<Patient> theList = new ArrayList<Patient>((test.getPatientList("/Users/anthonyjmarchi/AuburnProgram/5133_Database_2/5133_Project_Marchi/sqlite/Patient.txt", 1)));
                HospitalImport h = new HospitalImport();
                h.deletePersonTableData();
                for (Patient p : theList) {
                    h.insertPerson(p.getType(), p.getFirstName(), p.getLastName(), p.getPatientID(), p.getPatientRoomNumber(), p.getPatientEmergencyContact(), p.getPatientEmergencyContactNumber(), p.getPatientPolicyNumber(), p.getPatientInsurancePolicyCompany(), p.getPatientsPrimaryDoctorLastName(), p.getPatientDiagnosis(), p.getPatientAdmissionDate(), p.getPatientDischargeDate());
                }
                ArrayList<Doctor> theDoctorList = new ArrayList<Doctor>((test.getDoctorList()));
                HospitalImport x = new HospitalImport();
                x.deleteDoctorTableData();
                for (Doctor d : theDoctorList) {
                    x.insertDoctor(d.getFirstName(), d.getLastName(), d.getprivileges());
                }
            }
            if (typeOfFile == 2) {
                ArrayList<Doctor> theList = new ArrayList<Doctor>((test.getDoctorListNewDoctor("", 2)));
                HospitalImport h = new HospitalImport();
                h.deleteDoctorTableData();
                for (Doctor d : theList) {
                    h.insertDoctor(d.getFirstName(), d.getLastName(), d.getType());
                }
                h.selectDoctor();
            }
            if (typeOfFile == 3) {
                ArrayList<Treatment> theList = new ArrayList<Treatment>((test.getTreatmentList("/Users/anthonyjmarchi/AuburnProgram/5133_Database_2/5133_Project_Marchi/sqlite/Treatment.txt", 3)));
                HospitalImport h = new HospitalImport();
                h.deleteTableData();
                for (Treatment t : theList) {
                    h.insertTreatment(t.getPatientLastName(), t.getDoctorLastName(), t.getTreatmentType(), t.getTreatment(), t.getTimestamp());
                }
                h.selectTreatment();
            }
            System.out.println("Would you like to import more files?");
            System.out.println("Y/N: ");
            input = myObj.nextLine().toUpperCase();
            if (input.equals("Y")) {
                continueImporting = true;
            }
            else {
                continueImporting = false;
            }
        }
    }
}



