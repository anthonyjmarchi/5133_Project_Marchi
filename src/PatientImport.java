import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;


public class PatientImport {

    String fileName = "";
    String type = "";


    public void patientImportMethod(String fileName, String type) {
        fileName = fileName;
        type = type;
    }




}

/**

     while ((line = bufferreader.readLine()) != null) {
     String[] singlePerson = line.split(",");
     if (singlePerson[0].equals("I") || singlePerson[0].equals("O"))
     patientType = singlePerson[0];
     if (singlePerson[1] != "") {
     patientFirstName = singlePerson[1];
     }
     patientLastName = singlePerson[2];
     singlePerson[3] = "N/A";

     }


    public static void patientImport(String fileName, String type {



        System.out.println("Patient/Doctors: ");
        while ((line = bufferreader.readLine()) != null) {
        String[] singlePerson = line.split(",");


            //Patient IF Statements
            boolean patientTrue = true;
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
                        patientInsurancePolicyCompany, patientInsurancePolicyNumber, patientDiagnosis,
                        patientAdmissionDate, patientDischargeDate);
                patientList.add(newPatient);
            }
        }
    }
                    for (Patient p : patientList) {
        personList.add(p);
    }
                    for (Doctor d : doctorList) {
        personList.add(d);
    }

**/

