import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    /**
     * Main Method
     *
     * @param args
     **/

    public static ArrayList<Patient> importingPatients = new ArrayList<Patient>();
    public static ArrayList<Doctor> importingDoctors = new ArrayList<Doctor>();
    public static ArrayList<Volunteer> importingVolunteers = new ArrayList<Volunteer>();
    public static ArrayList<Administrator> importingAdministrators = new ArrayList<Administrator>();
    public static ArrayList<Nurse> importingNurses = new ArrayList<Nurse>();
    public static ArrayList<Technician> importingTechnicians = new ArrayList<Technician>();
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
                if (h.getRoomCount() < 20) {
                    int defaultRoomAddition = 1;
                    while (defaultRoomAddition < 21) {
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
                    h.insertAdmission(p.getPatientID(), p.getFirstName(), p.getLastName(), p.getPatientDiagnosis(), p.getPatientsPrimaryDoctorLastName(), p.getPatientAdmissionDate(), p.getPatientDischargeDate(), 0);
                    h.insertDiagnosis(p.getPatientDiagnosis(), p.getType());
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
                System.out.println("Enter File Location: ");
                String fileNameInputDoctor = myObj.nextLine();
                ArrayList<Doctor> theList = new ArrayList<Doctor>((test.getDoctorListNewDoctor(fileNameInputDoctor, 2)));
                HospitalImport h = new HospitalImport();
                h.deleteDoctorTableData();
                for (Doctor d : theList) {
                    h.insertDoctor(d.getFirstName(), d.getLastName(), d.getType());
                }
                h.selectDoctor();
            }
            if (typeOfFile == 3) {
                System.out.println("Enter File Location: ");
                String treatmentFileNameInput = myObj.nextLine();
                ArrayList<Treatment> theList = new ArrayList<Treatment>((test.getTreatmentList(treatmentFileNameInput, 3)));
                HospitalImport h = new HospitalImport();
                h.deleteTableData();
                for (Treatment t : theList) {
                    h.insertTreatment(t.getPatientLastName(), t.getDoctorLastName(), t.getTreatmentType(), t.getTreatment(), t.getTimestamp());
                    h.insertTreatmentData(t.getTreatment(), "Inpatient", t.getTreatmentType());
                }
                System.out.println("****************************");
                System.out.println("Importing....");
                System.out.println("****************************");
                System.out.println("Imported File At: " + treatmentFileNameInput.toString());
                System.out.println("****************************");
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
                                System.out.println("Enter Last Name or ID: ");
                                String inputName = myObj.nextLine();
                                HospitalImport p = new HospitalImport();
                                p.getAdmissionByName(inputName);
                            }
                            if (patientInfoChoice.equals("8")) {
                                System.out.println("****************************");
                                System.out.println("Enter Last Name: ");
                                String inputNameStuff = myObj.nextLine();
                                HospitalImport p = new HospitalImport();
                                p.getTreatmentByName(inputNameStuff);
                            }
                            if (patientInfoChoice.equals("9")) {
                                System.out.println("****************************");
                                HospitalImport p = new HospitalImport();
                                p.getAdmissions();
                            }
                            if (patientInfoChoice.equals("10")) {
                                System.out.println("****************************");
                                HospitalImport p = new HospitalImport();
                                p.getAdmissionHistory();
                            }
                            if (patientInfoChoice.equals("11")) {
                                roomUtilQuery = false;
                            }
                        }
                    }
                    if (querySelection == 3) {
                        boolean treatmentQuery = true;
                        while (treatmentQuery) {
                            System.out.println("****************************");
                            System.out.println("Please Select a Treatment Query: ");
                            System.out.println("****************************");
                            System.out.println("1. Admitted Patient Diagnosis \n2. Outpatient Diagnosis \n3. Diagnosis List \n4. Hospital Treatments \n5. Admitted Patient Treatments \n6. Outpatient Treatments \n7. Frequent Flier \n8. Employee Treatment Involvement  \n9. Go Back");
                            System.out.println("****************************");
                            System.out.println("Enter Selection: ");
                            String treatmentQueryChoice = myObj.nextLine();
                            if (treatmentQueryChoice.equals("1")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getAdmittedPatientDiagnosis();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("2")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getOutpatientPatientDiagnosis();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("3")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getAllPatientDiagnosis();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("4")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getAllTreatments();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("5")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getInpatientTreatments();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("6")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getOutpatientTreatments();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("7")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getAdmittedPatientDiagnosis();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("8")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getTreatmentWorkersInvolved();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("9")) {
                                treatmentQuery = false;
                            }
                        }
                    }
                    if (querySelection == 4) {
                        boolean employeeQuery = true;
                        while (employeeQuery) {
                            System.out.println("****************************");
                            System.out.println("Please Select an Employee Query: ");
                            System.out.println("****************************");
                            System.out.println("1. All Hospital Workers \n2. Primary Doctors \n3. Doctor/Diagnosis Correlations \n4. Treatments Ordered by Doctor Lookup \n5. Doctor/Treatment Participation \n6. Employee Treatment Involvement \n7. Go Back");
                            System.out.println("****************************");
                            System.out.println("Enter Selection: ");
                            String treatmentQueryChoice = myObj.nextLine();
                            if (treatmentQueryChoice.equals("1")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getAllHospitalWorkers();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("2")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getHighAdmissionDoctors();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("3")) {
                                System.out.println("****************************");
                                System.out.println("Enter Doctor Last Name: ");
                                String inputDoctorName = myObj.nextLine();
                                HospitalImport p = new HospitalImport();
                                p.getAllDoctorDiagnosis(inputDoctorName);
                            }
                            if (treatmentQueryChoice.equals("4")) {
                                System.out.println("****************************");
                                System.out.println("Enter Doctor Last Name: ");
                                String inputDoctorName = myObj.nextLine();
                                HospitalImport p = new HospitalImport();
                                p.getAllTreatmentsByDoctor(inputDoctorName);
                            }
                            if (treatmentQueryChoice.equals("5")) {
                                System.out.println("****************************");
                                System.out.println("Enter Doctor Last Name: ");
                                String inputDoctorName = myObj.nextLine();
                                HospitalImport p = new HospitalImport();
                                p.getAllTreatmentsByDoctor(inputDoctorName);
                            }
                            if (treatmentQueryChoice.equals("6")) {
                                System.out.println("****************************");
                                HospitalImport m = new HospitalImport();
                                m.getAllHospitalWorkers();
                                System.out.println("****************************");
                            }
                            if (treatmentQueryChoice.equals("7")) {
                                employeeQuery = false;
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
                q.deleteAdmissionData();
                q.deleteDiagnosisData();
                q.deleteDoctorTableData();
                q.deleteTreatmentDataTable();
                q.deleteDiagnosisTableData();
                q.deleteHospitalEmployeeData();
                importingPatients.clear();
                importingDoctors.clear();
            }
            if (typeOfFile == 6) {
                int defaultRoomAdditionReset = 1;
                while (defaultRoomAdditionReset < 21) {
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


