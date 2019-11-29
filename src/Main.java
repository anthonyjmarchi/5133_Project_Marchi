import java.lang.reflect.Array;
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

    public static ArrayList<Patient> importingPatients = new ArrayList<Patient>();
    public static ArrayList<Doctor> importingDoctors = new ArrayList<Doctor>();
    public static Boolean peopleImported = false;

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        DataImporter test = new DataImporter();
        int typeOfFile = 0;
        String fileNameInput = "";
        String input = "";
        Boolean continueImporting = true;
        while (continueImporting == true) {
            System.out.println("****************************");
            System.out.println("          Welcome");
            System.out.println("Please Select an Operation");
            System.out.println("****************************");
            System.out.println("1. General Import \n2. Import Additional Doctor \n3. Import Treatment \n4. Perform SQL Queries \n5. Delete All Table Data \n6. Reset Hospital Room Data \n7. Exit");
            System.out.println("****************************");
            System.out.println("Enter Selection: ");
            typeOfFile = Integer.parseInt(myObj.nextLine());
            if (typeOfFile == 1) {
                HospitalImport h = new HospitalImport();
                if (h.selectTotalRooms() < 20) {
                    int defaultRoomAddition = 1;
                    while (defaultRoomAddition  < 21) {
                        HospitalImport d = new HospitalImport();
                        d.insertRoomsDefault(defaultRoomAddition);
                        defaultRoomAddition++;
                    }
                }
                System.out.println("Enter File Location: ");
                fileNameInput = myObj.nextLine();
                test.importPerson(fileNameInput, 1);
                peopleImported = true;
                importingPatients = test.getPatientList();
                importingDoctors = test.getDoctorList();
                for (Patient p : importingPatients) {
                    h.insertPerson(p.getType(), p.getFirstName(), p.getLastName(), p.getPatientID(), p.getPatientRoomNumber(), p.getPatientEmergencyContact(), p.getPatientEmergencyContactNumber(), p.getPatientPolicyNumber(), p.getPatientInsurancePolicyCompany(), p.getPatientsPrimaryDoctorLastName(), p.getPatientDiagnosis(), p.getPatientAdmissionDate(), p.getPatientDischargeDate());
                    h.insertPatient(p.getType(), p.getFirstName(), p.getLastName(), p.getPatientID(), p.getPatientRoomNumber(), p.getPatientEmergencyContact(), p.getPatientEmergencyContactNumber(), p.getPatientPolicyNumber(), p.getPatientInsurancePolicyCompany(), p.getPatientsPrimaryDoctorLastName(), p.getPatientDiagnosis(), p.getPatientAdmissionDate(), p.getPatientDischargeDate());
                    h.updateRooms(p.getPatientRoomNumber(), p.getLastName(), p.getFirstName(), p.getPatientID(), p.getPatientAdmissionDate());
                    h.insertAdmission(p.getPatientID(), p.getFirstName(), p.getLastName(), p.getPatientDiagnosis(), p.getPatientsPrimaryDoctorLastName(), p.getPatientAdmissionDate(), p.getPatientDischargeDate());
                }

                for (Doctor d : importingDoctors) {
                    h.insertDoctor(d.getFirstName(), d.getLastName(), d.getprivileges());
                }
                System.out.println("****************************");
                System.out.println("Importing....");
                System.out.println("****************************");
                System.out.println("Imported File At: " + fileNameInput.toString());
                System.out.println("****************************");
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
            if (typeOfFile == 4) {
                boolean queryTrue = true;
                    while (queryTrue == true) {
                        int querySelection = 0;
                        System.out.println("****************************");
                        System.out.println("Please Select a Query Type:");
                        System.out.println("****************************");
                        System.out.println("1. Room Utilization \n2. Patient Information \n3. Diagnosis and Treatment Information \n4. Employee Information \n5. Go Back");
                        System.out.println("****************************");
                        System.out.println("Enter Selection: ");
                        querySelection = Integer.parseInt(myObj.nextLine());
                        if (querySelection == 1) {
                            boolean roomUtilQuery = true;
                            while (roomUtilQuery) {
                                System.out.println("****************************");
                                System.out.println("Please Select a Query Room Utilization Query: ");
                                System.out.println("****************************");
                                System.out.println("1. Occupied Rooms \n2. Unoccupied Rooms \n3. All Rooms \n4. Go Back");
                                System.out.println("****************************");
                                System.out.println("Enter Selection: ");
                                String roomQueryChoice = myObj.nextLine();
                                if (roomQueryChoice.equals("1")) {
                                    System.out.println("****************************");
                                    HospitalImport q = new HospitalImport();
                                    q.getOccupiedRooms();
                                    System.out.println("****************************");
                                }
                                if (roomQueryChoice.equals("2")) {
                                    System.out.println("****************************");
                                    HospitalImport q = new HospitalImport();
                                    q.getUnOccupiedRooms();
                                    System.out.println("****************************");
                                }
                                if (roomQueryChoice.equals("3")) {
                                    System.out.println("****************************");
                                    HospitalImport q = new HospitalImport();
                                    q.selectTotalRooms();
                                    System.out.println("****************************");
                                }
                                if (roomQueryChoice.equals("4")) {
                                    roomUtilQuery = false;
                                }
                            }
                        }
                        if (querySelection == 2) {
                            boolean roomUtilQuery = true;
                            while (roomUtilQuery) {
                                System.out.println("****************************");
                                System.out.println("Please Select a Patient Information Query: ");
                                System.out.println("****************************");
                                System.out.println("1. All Patients \n2. All Admitted Patients \n3. All Inpatient Patients w/ Date \n4. Discharged Patients w/ Date \n5. Outpatient Patients \n6. Outpatient Services w/ Date \n7. Patient Admission History \n8. Patient Treatment History \n9. Patients Admission 30 Days \n10. Patient Averages \n11. Go Back");
                                System.out.println("****************************");
                                System.out.println("Enter Selection: ");
                                String patientInfoChoice = myObj.nextLine();
                                if (patientInfoChoice.equals("1")) {
                                    System.out.println("****************************");
                                    HospitalImport p = new HospitalImport();
                                    p.getAllPatients();
                                }
                                if (patientInfoChoice.equals("2")) {
                                    System.out.println("****************************");
                                    HospitalImport p = new HospitalImport();
                                    p.getInpatients();
                                }
                                if (patientInfoChoice.equals("3")) {
                                    System.out.println("****************************");
                                    System.out.println("Please Enter a Start Date Range (mm/dd/yyyy: ");
                                    String startDate = myObj.nextLine();
                                    String startDateNew = startDate.replace("/", "");
                                    System.out.println("Please Enter a End Date Range (mm/dd/yyyy: ");
                                    String endDate = myObj.nextLine();
                                    String endDateNew = endDate.replace("/", "");
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                    p.getInpatientRange(startDateNew, endDateNew);
                                }
                                if (patientInfoChoice.equals("3")) {
                                    System.out.println("****************************");
                                    System.out.println("Please Enter a Start Discharge Date Range (mm/dd/yyyy: ");
                                    String startDateDischarge = myObj.nextLine();
                                    String startDateNewDischarge = startDateDischarge.replace("/", "");
                                    System.out.println("Please Enter an End Discharge Date Range (mm/dd/yyyy: ");
                                    String endDateDischarge = myObj.nextLine();
                                    String endDateNewDischarge = endDateDischarge.replace("/", "");
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                    p.getDischargeDateRange(startDateNewDischarge, endDateNewDischarge);
                                }
                                if (patientInfoChoice.equals("5")) {
                                    System.out.println("****************************");
                                    HospitalImport p = new HospitalImport();
                                    p.getOutpatients();
                                }
                                if (patientInfoChoice.equals("6")) {
                                    System.out.println("****************************");
                                    System.out.println("Please Enter a Start Outpatient Date Range (mm/dd/yyyy: ");
                                    String startDateOutpatient = myObj.nextLine();
                                    String startDateNewOutpatient = startDateOutpatient.replace("/", "");
                                    System.out.println("Please Enter an End Outpatient Date Range (mm/dd/yyyy: ");
                                    String endDateOutpatient = myObj.nextLine();
                                    String endDateNewOutpatient = endDateOutpatient.replace("/", "");
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                    p.getOutpatientDateRange(startDateNewOutpatient, endDateNewOutpatient);
                                }
                                if (patientInfoChoice.equals("7")) {
                                    System.out.println("****************************");
                                    System.out.println("Please Enter Patient ID or Last Name: ");
                                    String lookupPatient = myObj.nextLine().toLowerCase();
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                    p.lookUpAdmissionHistory(lookupPatient);
                                }
                                if (patientInfoChoice.equals("8")) {
                                    System.out.println("****************************");
                                    System.out.println("Results: ");
                                    String lookupPatient = myObj.nextLine().toLowerCase();
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                    p.lookUpAdmissionHistory(lookupPatient);
                                }
                                if (patientInfoChoice.equals("9")) {
                                    System.out.println("****************************");
                                    System.out.println("Patients Admitted Within 30 Days of Last Discharge Date: ");
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                }
                                if (patientInfoChoice.equals("10")) {
                                    System.out.println("****************************");
                                    System.out.println("Results: ");
                                    String lookupPatient = myObj.nextLine().toLowerCase();
                                    HospitalImport p = new HospitalImport();
                                    System.out.println("****************************");
                                    p.lookUpAdmissionHistory(lookupPatient);
                                }
                                if (patientInfoChoice.equals("11")) {
                                    roomUtilQuery = false;
                                }
                            }
                        }
                        if (querySelection == 5) {
                            queryTrue = false;
                        }
                }
            }
            if (typeOfFile == 5) {
                HospitalImport q = new HospitalImport();
                q.deletePersonTableData();
                q.deleteDoctorTableData();
                q.deleteRoomTableData();
                q.deletePatientData();
                importingPatients.clear();
                importingDoctors.clear();
            }
            if (typeOfFile == 6) {
                int defaultRoomAdditionReset = 1;
                while (defaultRoomAdditionReset  < 21) {
                    HospitalImport d = new HospitalImport();
                    d.insertRoomsDefault(defaultRoomAdditionReset);
                    defaultRoomAdditionReset++;
                }
            }
            if (typeOfFile == 7) {
                System.out.println("****************************");
                System.out.print("Exiting.");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                System.out.println("...Goodbye");
                System.out.print("****************************");
                continueImporting = false;
            }
        }
    }
}



