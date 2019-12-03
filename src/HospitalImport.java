import jdk.jshell.Diag;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class HospitalImport {

    int diagnosisID = 1;
    int treamentID = 1;
    int roomCount = 0;

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/anthonyjmarchi/AuburnProgram/5133_Database_2/5133_Project_Marchi/sqlite/hospital.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

            //This will turn on foreign keys
            //by default SQLite turns them off
            conn.createStatement().executeUpdate("PRAGMA foreign_keys = ON;");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void selectTreatment() {
        String sql = "SELECT treatment.patient_last_name, treatment.doctor_last_name, treatment.treatment_type, treatment.treatment_method, treatment.treatment_timestamp FROM treatment;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("patient_last_name") + " " + rs.getString("doctor_last_name")
                        + " " + rs.getString("treatment_type") + " " + rs.getString("treatment_method")
                        + " " + rs.getString("treatment_timestamp"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTreatment(String patientLastName, String doctorLastName, String treatmentType, String treatment,
                                String timestamp) {

        String sql = "INSERT INTO treatment(patient_last_name, doctor_last_name, treatment_type, treatment_method, treatment_timestamp) VALUES (?,?,?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientLastName); //First ? in sql
            ps.setString(2, doctorLastName); //Second ? in sql
            ps.setString(3, treatmentType); //Third ? in sql
            ps.setString(4, treatment); //Fourth ? in sql
            ps.setString(5, timestamp); //Fifth ? in sql
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void selectAllPatients() {
        String sql = "SELECT person_information.patient_id, person_information.patient_last_name, person_information.patient_initial_diagnosis FROM person_information;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("patient_id") + " " + rs.getString("patient_last_name")
                        + " " + rs.getString("patient_initial_diagnosis"));
            }
            System.out.println("***********************************************");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPerson(String person_type, String patient_first_name, String patient_last_name, int patient_id,
                             int patient_room_number, String patient_emergency_contact_name, String patient_emergency_contact_phone,
                             String patient_insurance_policy_number, String patient_insurance_policy_company,
                             String patient_primary_doctor_last_name, String patient_initial_diagnosis, String patient_admission_date,
                             String patient_discharge_date) {

        String sql = "INSERT INTO person_information(person_type, patient_first_name, patient_last_name, patient_id, patient_room_number, patient_emergency_contact_name, patient_emergency_contact_phone, patient_insurance_policy_number, patient_insurance_policy_company, patient_primary_doctor_last_name, patient_initial_diagnosis, patient_admission_date, patient_discharge_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, person_type); //First ? in sql
            ps.setString(2, patient_first_name); //Second ? in sql
            ps.setString(3, patient_last_name); //Third ? in sql
            ps.setInt(4, patient_id); //Fourth ? in sql
            ps.setInt(5, patient_room_number); //Fifth ? in sql
            ps.setString(6, patient_emergency_contact_name);
            ps.setString(7, patient_emergency_contact_phone);
            ps.setString(8, patient_insurance_policy_number);
            ps.setString(9, patient_insurance_policy_company);
            ps.setString(10, patient_primary_doctor_last_name);
            ps.setString(11, patient_initial_diagnosis);
            ps.setString(12, patient_admission_date);
            ps.setString(13, patient_discharge_date);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteTableData() {
        String sql = "DELETE FROM treatment;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePersonTableData() {
        String sql = "DELETE FROM person_information;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDoctorTableData() {
        String sql = "DELETE FROM doctor_information;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectDoctor() {
        String sql = "SELECT treatment.patient_last_name, treatment.doctor_last_name, treatment.treatment_type, treatment.treatment_method, treatment.treatment_timestamp FROM treatment;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("patient_last_name") + " " + rs.getString("doctor_last_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDoctor(String doctor_first_name, String doctor_last_name, String doctor_privileges) {

        String sql = "INSERT INTO doctor_information(doctor_first_name, doctor_last_name, doctor_privileges) VALUES (?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, doctor_first_name); //First ? in sql
            ps.setString(2, doctor_last_name); //Second ? in sql
            ps.setString(3, doctor_privileges); //Third ? in sql
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRoomsDefault(int number) {

        String sql = "INSERT INTO hospital_rooms(hospital_room_number, hospital_patient_last_name, hospital_patient_first_name, hospital_patient_id, hospital_patient_admission_date) VALUES (?,?,?,?,?);";
        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, number); //First ? in sql
            ps.setString(2, "N/A"); //Second ? in sql
            ps.setString(3, "N/A"); //Third ? in sql
            ps.setInt(4, 0); //Third ? in sql
            ps.setString(5, "N/A"); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRooms(int hospital_room_number_input, String hospital_patient_last_name_input, String hospital_patient_first_name_input, int hospital_patient_id_input, String hospital_patient_admission_date_input) {
        String sql = ("UPDATE hospital_rooms SET hospital_patient_last_name = '" + hospital_patient_last_name_input + "', hospital_patient_first_name = '" + hospital_patient_first_name_input + "', hospital_patient_id = '" + hospital_patient_id_input + "', hospital_patient_admission_date = '" + hospital_patient_admission_date_input + "' WHERE hospital_room_number = '" + hospital_room_number_input + "';");

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectTotalRooms() {
        String sql = "SELECT hospital_rooms.hospital_room_number, hospital_rooms.hospital_patient_last_name, hospital_rooms.hospital_patient_first_name FROM hospital_rooms";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (rs.getString("hospital_patient_last_name").equals("N/A")) {
                    System.out.println("Unoccupied Room Number: " + rs.getString("hospital_room_number"));
                    roomCount++;
                } else {
                    System.out.println("| Occupied Room Number: " + rs.getString("hospital_room_number") + " | Patient Last Name: " + rs.getString("hospital_patient_last_name") + " | Patient First Name: " + rs.getString("hospital_patient_first_name") + " |");
                    roomCount++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void getUnOccupiedRooms() {
        String sql = "SELECT hospital_rooms.hospital_room_number, hospital_patient_id FROM hospital_rooms";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                if (rs.getString("hospital_patient_id").equals("0")) {
                    System.out.println("Unoccupied Room Number: " + rs.getString("hospital_room_number"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getOccupiedRooms() {
        String sql = "SELECT hospital_rooms.hospital_room_number, hospital_rooms.hospital_patient_last_name, hospital_rooms.hospital_patient_first_name FROM hospital_rooms";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                if (!(rs.getString("hospital_patient_last_name").equals("N/A"))) {
                    System.out.println("Occupied Room Number: " + rs.getString("hospital_room_number") + " " + rs.getString("hospital_patient_last_name") + " " + rs.getString("hospital_patient_first_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRoomTableData() {
        String sql = "DELETE FROM hospital_rooms;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertPatient(String patient_type, String patient_first_name, String patient_last_name, int patient_id,
                              int patient_room_number, String patient_emergency_contact_name, String patient_emergency_contact_phone,
                              String patient_insurance_policy_number, String patient_insurance_policy_company,
                              String patient_primary_doctor_last_name, String patient_initial_diagnosis, String patient_admission_date,
                              String patient_discharge_date) {

        String sql = "INSERT INTO patient_data(patient_type, patient_first_name, patient_last_name, patient_id, patient_room_number, patient_emergency_contact_name, patient_emergency_contact_phone, patient_insurance_policy_number, patient_insurance_policy_company, patient_primary_doctor_last_name, patient_initial_diagnosis, patient_admission_date, patient_discharge_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patient_type); //First ? in sql
            ps.setString(2, patient_first_name); //Second ? in sql
            ps.setString(3, patient_last_name); //Third ? in sql
            ps.setInt(4, patient_id); //Fourth ? in sql
            ps.setInt(5, patient_room_number); //Fifth ? in sql
            ps.setString(6, patient_emergency_contact_name);
            ps.setString(7, patient_emergency_contact_phone);
            ps.setString(8, patient_insurance_policy_number);
            ps.setString(9, patient_insurance_policy_company);
            ps.setString(10, patient_primary_doctor_last_name);
            ps.setString(11, patient_initial_diagnosis);
            ps.setString(12, patient_admission_date);
            ps.setString(13, patient_discharge_date);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void getAllPatients() {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_first_name, patient_data.patient_last_name, patient_data.patient_id, patient_data.patient_room_number, patient_data.patient_emergency_contact_name, patient_data.patient_emergency_contact_phone, patient_data.patient_insurance_policy_number, patient_data.patient_insurance_policy_company, patient_data.patient_primary_doctor_last_name, patient_data.patient_initial_diagnosis, patient_data.patient_admission_date, patient_data.patient_discharge_date FROM patient_data";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("| Patient Type: " + rs.getString("patient_type") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name") + "| Diagnosis: " + rs.getString("patient_initial_diagnosis") + " | Admission Date: " + rs.getString("patient_admission_date") + " | Patient Discharge Date: " + rs.getString("patient_discharge_date") + " | Patient Emergency Contact Name: " + rs.getString("patient_emergency_contact_name") + " | Patient Emergency Contact Phone Number: " + rs.getString("patient_emergency_contact_phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInpatients() {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_id, patient_data.patient_first_name, patient_data.patient_last_name, patient_data.patient_initial_diagnosis FROM patient_data";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (rs.getString("patient_type").equals("i")) {
                    System.out.println("| Patient ID: " + rs.getString("patient_id") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getOutpatients() {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_id, patient_data.patient_first_name, patient_data.patient_last_name FROM patient_data";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (rs.getString("patient_type").equals("o")) {
                    System.out.println("| Patient ID: " + rs.getString("patient_id") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePatientData() {
        String sql = "DELETE FROM patient_data;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInpatientRange(String startDate, String endDate) {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_id, patient_data.patient_first_name, patient_data.patient_last_name, patient_data.patient_admission_date FROM patient_data";
        int startDateInput = Integer.parseInt(startDate);
        int endDateInput = Integer.parseInt(endDate);
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String admissionDateFromTable = rs.getString("patient_admission_date");
                String admissionDateNew = admissionDateFromTable.replace("-", "");
                int admissionDateNewInt = Integer.parseInt(admissionDateNew);
                if (rs.getString("patient_type").equals("i")) {
                    if (admissionDateNewInt >= startDateInput && admissionDateNewInt <= endDateInput) {
                        System.out.println("| Patient ID: " + rs.getString("patient_id") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getDischargeDateRange(String startDateDischarge, String endDateDischarge) {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_id, patient_data.patient_first_name, patient_data.patient_last_name, patient_data.patient_admission_date FROM patient_data";
        int startDateInput = Integer.parseInt(startDateDischarge);
        int endDateInput = Integer.parseInt(endDateDischarge);
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String admissionDateFromTable = rs.getString("patient_discharge_date");
                String admissionDateNew = admissionDateFromTable.replace("-", "");
                int admissionDateNewInt = Integer.parseInt(admissionDateNew);
                if (rs.getString("patient_type").equals("i")) {
                    if (admissionDateNewInt >= startDateInput && admissionDateNewInt <= endDateInput) {
                        System.out.println("| Patient ID: " + rs.getString("patient_id") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getOutpatientDateRange(String startDateOutpatient, String endDateOutpatient) {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_id, patient_data.patient_first_name, patient_data.patient_last_name, patient_data.patient_admission_date FROM patient_data";
        int startDateInput = Integer.parseInt(startDateOutpatient);
        int endDateInput = Integer.parseInt(endDateOutpatient);
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String admissionDateFromTable = rs.getString("patient_discharge_date");
                String admissionDateNew = admissionDateFromTable.replace("-", "");
                int admissionDateNewInt = Integer.parseInt(admissionDateNew);
                if (rs.getString("patient_type").equals("o")) {
                    if (admissionDateNewInt >= startDateInput && admissionDateNewInt <= endDateInput) {
                        System.out.println("| Patient ID: " + rs.getString("patient_id") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAdmission(int patientID, String patientFirstName, String patientLastName, String patientDiagnosis, String patientDoctor,
                                String patientAdmission, String dischargeDate, int admissionCount) {


        String sql = "INSERT INTO admission_history(admitted_patient_id, admitted_patient_first_name, admitted_patient_last_name, admitted_patient_diagnosis, admitted_patient_doctor, admitted_patient_admission_date, admitted_patient_discharge_date, admissions_count) VALUES (?,?,?,?,?,?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientID); //First ? in sql
            ps.setString(2, patientFirstName); //Second ? in sql
            ps.setString(3, patientLastName); //Third ? in sql
            ps.setString(4, patientDiagnosis); //Fourth ? in sql
            ps.setString(5, patientDoctor); //Fifth ? in sql
            ps.setString(6, patientAdmission); //Fifth ? in sql
            ps.setString(7, dischargeDate); //Fifth ? in sql
            ps.setInt(8, getAdmissionCount("String lastName"));
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        updateAdmission(patientLastName);

    }

    public void updateAdmission(String lastName) {

        int admissions = getAdmissionCount(lastName);

        String sql = "UPDATE admission_history SET admissions_count = '" + admissions + "';";
        try (Connection conn = this.connect();) {


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private int getAdmissionCount(String lastName) {

        String sql = "SELECT admission_history.admitted_patient_last_name FROM admission_history";

        int count = 1;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("admitted_patient_last_name").equals(lastName)) {
                    count++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }


    public void deleteAdmissionData() {
        String sql = "DELETE FROM admission_history;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDiagnosisData() {
        String sql = "DELETE FROM diagnosis_data;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDiagnosis(String diagnosis_name, String patient_type) {

        String sql = "INSERT INTO diagnosis_data(diagnosis_id, diagnosis_name, diagnosis_count, diagnosis_patient_type) VALUES (?,?,?,?);";

        String formatDiagnosis = diagnosis_name.toLowerCase();
        int newID = diagnosisID;

        if (diagnosisCheck(formatDiagnosis)) {
            try (Connection conn = this.connect();) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, newID); //First ? in sql
                ps.setString(2, formatDiagnosis);
                ps.setInt(3, getOccurences(formatDiagnosis));
                ps.setString(4, patient_type);
                diagnosisID++;//Second ? in sql
                ps.executeUpdate();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!(diagnosisCheck(formatDiagnosis))) {
            insertOccurence(formatDiagnosis);
        }
    }

    public void insertOccurence(String diagnosisNameInput) {

        int occurences = getOccurences(diagnosisNameInput);
        occurences++;
        String sql = "UPDATE diagnosis_data SET diagnosis_count = '" + occurences + "' WHERE diagnosis_name = '" + diagnosisNameInput + "';";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean diagnosisCheck(String diagnosisName) {

        String sql = "SELECT diagnosis_data.diagnosis_name FROM diagnosis_data";

        boolean check = true;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("diagnosis_name").equals(diagnosisName)) {
                    check = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public int getDiagnosisID() {
        return diagnosisID;
    }

    private int getOccurences(String diagnosisName) {

        String sql = "SELECT diagnosis_data.diagnosis_name, diagnosis_data.diagnosis_count FROM diagnosis_data";

        boolean check = true;
        int occurence = 0;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("diagnosis_name").equals(diagnosisName)) {
                    occurence = rs.getInt("diagnosis_count");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return occurence;
    }

    public void getAdmittedPatientDiagnosis() {

        String sql = "SELECT diagnosis_data.diagnosis_id, diagnosis_data.diagnosis_name, diagnosis_data.diagnosis_count, diagnosis_data.diagnosis_patient_type FROM diagnosis_data GROUP BY diagnosis_name ORDER BY diagnosis_count";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                if (rs.getString("diagnosis_patient_type").equals("i")) {
                    System.out.println("Diagnosis ID: " + rs.getInt("diagnosis_id") + " Diagnosis Name: " + rs.getString("diagnosis_name") + "Diagnosis Occurrences: " + rs.getInt("diagnosis_count"));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getOutpatientPatientDiagnosis() {

        String sql = "SELECT diagnosis_data.diagnosis_id, diagnosis_data.diagnosis_name, diagnosis_data.diagnosis_count, diagnosis_data.diagnosis_patient_type FROM diagnosis_data GROUP BY diagnosis_name ORDER BY diagnosis_count";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                if (rs.getString("diagnosis_patient_type").equals("o")) {
                    System.out.println("Diagnosis ID: " + rs.getInt("diagnosis_id") + " Diagnosis Name: " + rs.getString("diagnosis_name") + "Diagnosis Occurrences: " + rs.getInt("diagnosis_count"));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllPatientDiagnosis() {

        String sql = "SELECT diagnosis_data.diagnosis_id, diagnosis_data.diagnosis_name, diagnosis_data.diagnosis_count, diagnosis_data.diagnosis_patient_type FROM diagnosis_data GROUP BY diagnosis_name ORDER BY diagnosis_count";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Diagnosis ID: " + rs.getInt("diagnosis_id") + " Diagnosis Name: " + rs.getString("diagnosis_name") + "Diagnosis Occurrences: " + rs.getInt("diagnosis_count"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDiagnosisTableData() {
        String sql = "DELETE FROM diagnosis_data;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertTreatmentData(String treatment_name, String patient_type, String treatment_type) {

        String sql = "INSERT INTO treatment_data(treatment_id, treatment_name, treatment_count, treatment_patient_type, treatment_type) VALUES (?,?,?,?,?);";

        String formatTreatment = treatment_name.toLowerCase();
        int newID = treamentID;

        if (treatmentCheck(formatTreatment)) {
            insertOccurenceTreatment(formatTreatment);
            try (Connection conn = this.connect();) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, newID); //First ? in sql
                ps.setString(2, formatTreatment);
                ps.setInt(3, getTreatmentOccurences(formatTreatment));
                ps.setString(4, patient_type);
                ps.setString(5, treatment_type);
                treamentID++;//Second ? in sql
                ps.executeUpdate();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            insertOccurenceTreatment(formatTreatment);
        }
    }

    private boolean treatmentCheck(String treatmentName) {

        String sql = "SELECT treatment_data.treatment_name FROM treatment_data";

        boolean check = true;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("treatment_name").equals(treatmentName)) {
                    check = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }


    private int getTreatmentOccurences(String treatmentName) {

        String sql = "SELECT treatment_data.treatment_name, treatment_data.treatment_count FROM treatment_data";

        boolean check = true;
        int occurence = 1;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("treatment_name").equals(treatmentName)) {
                    occurence = rs.getInt("treatment_count");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return occurence;
    }

    public void insertOccurenceTreatment(String treatmentNameInput) {

        int occurences = getTreatmentOccurences(treatmentNameInput);
        occurences++;
        String sql = "UPDATE treatment_data SET treatment_count = '" + occurences + "' WHERE treatment_name = '" + treatmentNameInput + "';";
        try (Connection conn = this.connect();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteTreatmentDataTable() {
        String sql = "DELETE FROM treatment_data;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllTreatments() {

        String sql = "SELECT treatment_data.treatment_id, treatment_data.treatment_name, treatment_data.treatment_count, treatment_data.treatment_patient_type FROM treatment_data GROUP BY treatment_name ORDER BY treatment_count";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Treatment ID: " + rs.getInt("Treatment_id") + " Treatment Name: " + rs.getString("treatment_name") + " Treatment Occurrences: " + rs.getInt("treatment_count"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInpatientTreatments() {

        String sql = "SELECT treatment_data.treatment_id, treatment_data.treatment_name, treatment_data.treatment_count, treatment_data.treatment_patient_type FROM treatment_data GROUP BY treatment_name ORDER BY treatment_count";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("treatment_patient_type").equals("Inpatient")) {
                    System.out.println("Treatment ID: " + rs.getInt("Treatment_id") + " Treatment Name: " + rs.getString("treatment_name") + " Treatment Occurrences: " + rs.getInt("treatment_count"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getOutpatientTreatments() {

        String sql = "SELECT treatment_data.treatment_id, treatment_data.treatment_name, treatment_data.treatment_count, treatment_data.treatment_patient_type FROM treatment_data GROUP BY treatment_name ORDER BY treatment_count";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("treatment_patient_type").equals("Outpatient")) {
                    System.out.println("Treatment ID: " + rs.getInt("Treatment_id") + " Treatment Name: " + rs.getString("treatment_name") + " Treatment Occurrences: " + rs.getInt("treatment_count"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAdmissionByName(String input) {

        String sql = "SELECT admission_history.admitted_patient_last_name, admission_history.admitted_patient_diagnosis, admission_history.admitted_patient_id FROM admission_history";
        String inputNameNum = input.toLowerCase();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("admitted_patient_last_name").equals(inputNameNum) || rs.getString("admitted_patient_id").equals(inputNameNum)) {
                    System.out.println("Last Name: " + rs.getString("admitted_patient_last_name") + " Diagnosis: " + rs.getString("admitted_patient_diagnosis"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void getTreatmentWorkersInvolved() {

        String sql = "SELECT treatment.treatment_method, treatment.patient_last_name, treatment.doctor_last_name FROM treatment";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Treatment Method: " + rs.getString("treatment_method") + " Patient Last Name: " + rs.getString("patient_last_name") + " Doctor Last Name: " + rs.getString("doctor_last_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertEmployee(String employee_last_name, String employee_first_name, String employee_type) {

        String sql = "INSERT INTO hospital_employees(employee_last_name, employee_first_name, employee_type) VALUES (?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee_last_name); //First ? in sql
            ps.setString(2, employee_first_name); //Second ? in sql
            ps.setString(3, employee_type); //Third ? in sql
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteHospitalEmployeeData() {
        String sql = "DELETE FROM hospital_employees;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllHospitalWorkers() {

        String sql = "SELECT hospital_employees.employee_last_name, hospital_employees.employee_first_name, hospital_employees.employee_type  FROM hospital_employees";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Occupation: " + rs.getString("employee_type").toUpperCase() + " First Name: " + rs.getString("employee_first_name") + " Last Name: " + (rs.getString("employee_last_name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getHighAdmissionDoctors() {

        String sql = "SELECT admission_history.admitted_patient_last_name, admission_history.admitted_patient_doctor FROM admission_history";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ArrayList<String> name = new ArrayList<>();
            while (rs.next()) {
                name.add((rs.getString("admitted_patient_last_name")));
            }

            ArrayList<String> nameAgain = new ArrayList<>();
            int count = 0;

            for (int i = 0; i < name.size(); i++) {
                for (int j = 0; j < name.size(); j++) {
                    if (name.get(i) == name.get(j)) {
                        count++;
                        if (count >= 4) {
                            nameAgain.add(name.get(i));
                        }
                    }
                    count = 0;
                }
            }
            for (String q : nameAgain) {
                System.out.println("High Admission Doctors: " + q);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTreatmentByName(String input) {

        String sql = "SELECT treatment.patient_last_name, treatment.treatment_method FROM treatment";
        String inputNameNum = input.toLowerCase();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("patient_last_name").equals(inputNameNum)) {
                    System.out.println("Last Name: " + rs.getString("patient_last_name") + " Diagnosis: " + rs.getString("treatment_method"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllDoctorDiagnosis(String input) {

        String sql = "SELECT admission_history.admitted_patient_doctor, admission_history.admitted_patient_diagnosis FROM admission_history";
        String inputNameNum = input.toLowerCase();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("admitted_patient_doctor").equals(inputNameNum)) {
                    System.out.println("Doctor Last Name: " + rs.getString("admitted_patient_doctor") + " Diagnosis: " + rs.getString("admitted_patient_diagnosis"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllTreatmentsByDoctor(String input) {

        String sql = "SELECT treatment.doctor_last_name, treatment.treatment_method FROM treatment";
        String inputNameNum = input.toLowerCase();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("doctor_last_name").equals(inputNameNum)) {
                    System.out.println("Doctor Last Name: " + rs.getString("doctor_last_name") + " Diagnosis: " + rs.getString("treatment_method"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}


