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
            System.out.println("1. General Import \n2. Import Additional Doctor \n3. Import Treatment \n4. Delete Table Data \n5. Perform SQL Queries \n6. Reset Hospital Room Table");
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
                    h.updateRooms(p.getPatientRoomNumber(), p.getLastName(), p.getFirstName(), p.getPatientID(), p.getPatientAdmissionDate());

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
                HospitalImport q = new HospitalImport();
                q.deletePersonTableData();
                q.deleteDoctorTableData();
                q.deleteRoomTableData();
                importingPatients.clear();
                importingDoctors.clear();
            }
            if (typeOfFile == 5) {
                boolean queryTrue = true;
                    while (queryTrue == true) {
                        int querySelection = 0;
                        System.out.println("****************************");
                        System.out.println("Please Select a Query:");
                        System.out.println("****************************");
                        System.out.println("1. Show Current Patients. \n2. Show Hospital Doctors. \n3. Show Unoccupied Rooms. \n50. Return to Main");
                        System.out.println("****************************");
                        System.out.println("Enter Selection: ");
                        querySelection = Integer.parseInt(myObj.nextLine());
                        if (querySelection == 1) {
                            System.out.println("****************************");
                            System.out.println("Patients:");
                            for (Patient r : importingPatients) {
                                System.out.println(r.getFirstName() + " " + r.getLastName() + " " + r.getPatientAdmissionDate() + " " + r.getPatientDiagnosis() + " " + r.getPatientRoomNumber());
                            }
                            System.out.println("****************************");
                            System.out.println("Would you perform any additional SQL Queries?");
                            System.out.println("****************************");
                            System.out.println("Y/N: ");
                            input = myObj.nextLine().toUpperCase();
                            if (input.equals("Y")) {
                                queryTrue = true;
                            } else {
                                queryTrue = false;
                                System.out.println("****************************");
                                System.out.println("     Returning to Main");
                                System.out.println("****************************");
                            }
                        }
                        if (querySelection == 2) {
                            System.out.println("****************************");
                            System.out.println("tonz");
                            System.out.println("****************************");
                            System.out.println("Would you perform any additional SQL Queries?");
                            System.out.println("****************************");
                            System.out.println("Y/N: ");
                            input = myObj.nextLine().toUpperCase();
                            if (input.equals("Y")) {
                                queryTrue = true;
                            } else {
                                queryTrue = false;
                                System.out.println("****************************");
                                System.out.println("     Returning to Main");
                                System.out.println("****************************");
                            }
                        }
                        if (querySelection == 3) {
                            queryTrue = false;
                            System.out.println("****************************");
                            HospitalImport q = new HospitalImport();
                            q.getUnOccupiedRooms();
                            System.out.println("****************************");
                        }
                        if (querySelection == 50) {
                            queryTrue = false;
                            System.out.println("****************************");
                            System.out.println("     Returning.....");
                            System.out.println("****************************");
                        }

                }

            }
            if (typeOfFile == 6) {
                int defaultRoomAdditionReset = 1;
                while (defaultRoomAdditionReset  < 21) {
                    HospitalImport d = new HospitalImport();
                    d.insertRoomsDefault(defaultRoomAdditionReset);
                    defaultRoomAdditionReset++;
                }

            }

            System.out.println("Would you perform any additional Main Menu operations?");
            System.out.println("Y/N: ");
            input = myObj.nextLine().toUpperCase();
            if (input.equals("Y")) {
                continueImporting = true;
            }
            else {
                System.out.println("****************************");
                System.out.println("          Goodbye");
                System.out.println("****************************");
                continueImporting = false;
            }
        }
    }
}



