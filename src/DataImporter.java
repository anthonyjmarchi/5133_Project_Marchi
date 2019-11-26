import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class DataImporter {

    private String type = "";
    private String firstName = "";
    private String lastName = "";
    private int patientID = 0;
    private int patientRoomNumber = 0;
    private String patientEmergencyContact = "";
    private String patientEmergencyContactNumber = "";
    private String patientInsurancePolicyNumber = "";
    private String patientInsurancePolicyCompany = "";
    private String patientsPrimaryDoctorLastName = "";
    private String patientDiagnosis = "";
    private String patientAdmissionDate = "";
    private String patientDischargeDate = "";
    private String privileges = "";
    private String treatmentType = "";
    private String timestamp = "";
    private String doctorLastName = "";
    private String patientTreatment = "";

    ArrayList patientAddition = new ArrayList();
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    ArrayList<Patient> patientList = new ArrayList<Patient>();
    ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
    ArrayList<Person> personList = new ArrayList<Person>();
    ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();

    /**
     * A long method that needs refactored.  Imports people from a file.
     * @param fileImportName the path to the file that we import.
     */
    public void importPerson(String fileImportName, int fileType) {
        ArrayList patientAddition = new ArrayList();
        String line;
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(fileImportName));
            if (fileType == 1) {
                while ((line = bufferreader.readLine()) != null) {
                    String[] singlePerson = line.split(",");

                        if (singlePerson[0].equals("V") || singlePerson[0].equals("A")
                                || singlePerson[0].equals("N") || singlePerson[0].equals("T")) {
                            type = singlePerson[0].toLowerCase();
                        }
                        boolean patientTrue = true;
                        //Doctor
                        if (singlePerson[0].equals("D")) {
                            type = singlePerson[0].toLowerCase();
                            if (!(singlePerson[1].contains("1") || singlePerson[1].contains("2") || singlePerson[1].contains("3") ||
                                    singlePerson[1].contains("4") || singlePerson[1].contains("5") || singlePerson[1].contains("6") ||
                                    singlePerson[1].contains("7") || singlePerson[1].contains("8") || singlePerson[1].contains("9") ||
                                    singlePerson[1].contains("0"))) {
                                firstName = singlePerson[1].toLowerCase();
                            }
                            if (!(singlePerson[2].contains("1") || singlePerson[2].contains("2") || singlePerson[2].contains("3") ||
                                    singlePerson[2].contains("4") || singlePerson[2].contains("5") || singlePerson[2].contains("6") ||
                                    singlePerson[2].contains("7") || singlePerson[2].contains("8") || singlePerson[2].contains("9") ||
                                    singlePerson[2].contains("0"))) {
                                lastName = singlePerson[2].toLowerCase();
                            }
                            privileges = singlePerson[3].toLowerCase();
                            Doctor newDoc = new Doctor(type, firstName, lastName, privileges);
                            doctorList.add(newDoc);
                            patientTrue = false;
                        }
                        //Patient IF Statements
                        if (singlePerson[0].equals("I") || singlePerson[0].equals("O")) {
                            type = singlePerson[0].toLowerCase();

                            //First Name
                            if (!(singlePerson[1].contains("1") || singlePerson[1].contains("2") || singlePerson[1].contains("3") ||
                                    singlePerson[1].contains("4") || singlePerson[1].contains("5") || singlePerson[1].contains("6") ||
                                    singlePerson[1].contains("7") || singlePerson[1].contains("8") || singlePerson[1].contains("9") ||
                                    singlePerson[1].contains("0"))) {
                                firstName = singlePerson[1].toLowerCase();
                            } else {
                                patientTrue = false;
                            }

                            //Last Name
                            if (!(singlePerson[2].contains("1") || singlePerson[2].contains("2") || singlePerson[2].contains("3") ||
                                    singlePerson[2].contains("4") || singlePerson[2].contains("5") || singlePerson[2].contains("6") ||
                                    singlePerson[2].contains("7") || singlePerson[2].contains("8") || singlePerson[2].contains("9") ||
                                    singlePerson[2].contains("0"))) {
                                lastName = singlePerson[2].toLowerCase();
                            } else {
                                patientTrue = false;
                            }

                            //Privileges Index 3
                            privileges = "no";

                            //Patient ID Index 4
                            boolean valueOne = true;
                            for (char letter : alphabet) {
                                if (Character.toString(letter).equals(singlePerson[4])) {
                                    valueOne = false;
                                    patientTrue = false;
                                }
                            }
                            if (valueOne) {
                                patientID = Integer.parseInt(singlePerson[4]);
                            }

                            //Patient Room Number Index 5
                            boolean valueTwo = true;
                            for (char letter : alphabet) {
                                if (Character.toString(letter).equals(singlePerson[5])) {
                                    valueTwo = false;
                                    patientTrue = false;
                                }
                            }
                            if (valueTwo) {
                                patientRoomNumber = Integer.parseInt(singlePerson[5]);
                            }

                            //Patient Emergency Contact Name Index 6
                            if (!(singlePerson[6].contains("1") || singlePerson[6].contains("2") || singlePerson[6].contains("3") ||
                                    singlePerson[6].contains("4") || singlePerson[6].contains("5") || singlePerson[6].contains("6") ||
                                    singlePerson[6].contains("7") || singlePerson[6].contains("8") || singlePerson[6].contains("9") ||
                                    singlePerson[6].contains("0"))) {
                                patientEmergencyContact = singlePerson[6].toLowerCase();
                            } else {
                                patientTrue = false;
                            }

                            //Patient Emergency Contact Number Index 7
                            patientEmergencyContactNumber = singlePerson[7];

                            //Patient Insurance Policy Number Index 8
                            patientInsurancePolicyNumber = singlePerson[8].toLowerCase();

                            //Patient Insurance Policy Company Index 9
                            if (!(singlePerson[9].contains("1") || singlePerson[9].contains("2") || singlePerson[9].contains("3") ||
                                    singlePerson[9].contains("4") || singlePerson[9].contains("5") || singlePerson[9].contains("6") ||
                                    singlePerson[9].contains("7") || singlePerson[9].contains("8") || singlePerson[9].contains("9") ||
                                    singlePerson[9].contains("0"))) {
                                patientInsurancePolicyCompany = singlePerson[9].toLowerCase();
                            } else {
                                patientTrue = false;
                            }

                            //Patient Primary Doctor Last Name Index 10
                            if (!(singlePerson[10].contains("1") || singlePerson[10].contains("2") || singlePerson[10].contains("3") ||
                                    singlePerson[10].contains("4") || singlePerson[10].contains("5") || singlePerson[10].contains("6") ||
                                    singlePerson[10].contains("7") || singlePerson[10].contains("8") || singlePerson[10].contains("9") ||
                                    singlePerson[10].contains("0"))) {
                                patientsPrimaryDoctorLastName = singlePerson[10];
                            } else {
                                patientTrue = false;
                            }

                            //Patient Diagnosis Index 11
                            if (!(singlePerson[11].contains("1") || singlePerson[11].contains("2") || singlePerson[11].contains("3") ||
                                    singlePerson[11].contains("4") || singlePerson[11].contains("5") || singlePerson[11].contains("6") ||
                                    singlePerson[11].contains("7") || singlePerson[11].contains("8") || singlePerson[11].contains("9") ||
                                    singlePerson[11].contains("0"))) {
                                patientDiagnosis = singlePerson[11].toLowerCase();
                            } else {
                                patientTrue = false;
                            }

                            //Patient Admission Date Index 12
                            patientAdmissionDate = singlePerson[12];

                            //Patient Discharge Date Index 13
                            patientDischargeDate = singlePerson[13];
                        }
                        if (patientTrue) {
                            Patient newPatient = new Patient(type, firstName, lastName, patientID, patientRoomNumber,
                                    patientEmergencyContact, patientEmergencyContactNumber, patientInsurancePolicyNumber,
                                    patientInsurancePolicyCompany, patientsPrimaryDoctorLastName, patientDiagnosis,
                                    patientAdmissionDate, patientDischargeDate);
                            patientList.add(newPatient);
                        }

                }
                for (Patient p : patientList) {
                    personList.add(p);
                }
                for (Doctor d : doctorList) {
                    personList.add(d);
                }
            }

            //Doctor
            if (fileType == 2) {
                while ((line = bufferreader.readLine()) != null) {
                    String[] additionalDoctor = line.split(",");
                    if (additionalDoctor.length == 2) {
                        if (!(additionalDoctor[0].contains("1") || additionalDoctor[0].contains("2") || additionalDoctor[0].contains("3") ||
                                additionalDoctor[0].contains("4") || additionalDoctor[0].contains("5") || additionalDoctor[0].contains("6") ||
                                additionalDoctor[0].contains("7") || additionalDoctor[0].contains("8") || additionalDoctor[0].contains("9") ||
                                additionalDoctor[0].contains("0"))) {
                            lastName = additionalDoctor[0].toLowerCase();
                        }
                        if (!(additionalDoctor[1].contains("1") || additionalDoctor[1].contains("2") || additionalDoctor[1].contains("3") ||
                                additionalDoctor[1].contains("4") || additionalDoctor[1].contains("5") || additionalDoctor[1].contains("6") ||
                                additionalDoctor[1].contains("7") || additionalDoctor[1].contains("8") || additionalDoctor[1].contains("9") ||
                                additionalDoctor[1].contains("0"))) {
                            firstName = additionalDoctor[1].toLowerCase();
                        }
                    }
                    type = "Doctor";
                    privileges = "C";
                    Doctor newAdditionalDoctor = new Doctor(type, firstName, lastName, privileges);
                    doctorList.add(newAdditionalDoctor);
                    boolean check = true;
                    for (Doctor z : doctorList) {
                        if (z.getLastName().equals(newAdditionalDoctor.getLastName())) {
                            check = true;
                        }
                    }
                    if (check) {
                        doctorList.add(newAdditionalDoctor);
                    }
                }
                for (Doctor d : doctorList) {
                    personList.add(d);
                }
            }
            if (fileType == 3) {
                while ((line = bufferreader.readLine()) != null) {
                    String[] treatment = line.split(",");
                    if (treatment.length == 5) {
                        if (!(treatment[0].contains("1") || treatment[0].contains("2") || treatment[0].contains("3") ||
                                treatment[0].contains("4") || treatment[0].contains("5") || treatment[0].contains("6") ||
                                treatment[0].contains("7") || treatment[0].contains("8") || treatment[0].contains("9") ||
                                treatment[0].contains("0"))) {
                            lastName = treatment[0].toLowerCase();
                        }
                        if (!(treatment[1].contains("1") || treatment[1].contains("2") || treatment[1].contains("3") ||
                                treatment[1].contains("4") || treatment[1].contains("5") || treatment[1].contains("6") ||
                                treatment[1].contains("7") || treatment[1].contains("8") || treatment[1].contains("9") ||
                                treatment[1].contains("0"))) {
                            doctorLastName = treatment[1].toLowerCase();
                        }
                        treatmentType = treatment[2].toLowerCase();
                        if (!(treatment[3].contains("1") || treatment[3].contains("2") || treatment[3].contains("3") ||
                                treatment[3].contains("4") || treatment[3].contains("5") || treatment[3].contains("6") ||
                                treatment[3].contains("7") || treatment[3].contains("8") || treatment[3].contains("9") ||
                                treatment[3].contains("0"))) {
                            patientTreatment = treatment[3].toLowerCase();
                        }
                        boolean valueTen = true;
                        for (char letter : alphabet) {
                            if (Character.toString(letter).equals(treatment[4])) {
                                valueTen = false;
                            }
                        }
                        if (valueTen) {
                            timestamp = treatment[4];
                        }
                    }
                    Treatment newTreatment = new Treatment(lastName, doctorLastName, treatmentType, patientTreatment, timestamp);
                    treatmentList.add(newTreatment);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList getTreatmentList(String fileImportName, int fileType) {
        importPerson(fileImportName, fileType);
        return treatmentList;
    }

    public ArrayList getPatientList() {
        return patientList;
    }

    public ArrayList getDoctorListNewDoctor(String fileImportName, int fileType) {
        importPerson(fileImportName, fileType);
        return doctorList;
    }

    public ArrayList getDoctorList() {
        return doctorList;
    }

}


