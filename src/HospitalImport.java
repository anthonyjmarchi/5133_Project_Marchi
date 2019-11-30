import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HospitalImport {

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

    public int selectTotalRooms() {
        String sql = "SELECT hospital_rooms.hospital_room_number, hospital_rooms.hospital_patient_last_name, hospital_rooms.hospital_patient_first_name FROM hospital_rooms";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
            // loop through the result set
            while (rs.next()) {
                if (rs.getString("hospital_patient_last_name").equals("N/A")) {
                    System.out.println("Unoccupied Room Number: " + rs.getString("hospital_room_number"));
                }
                else {
                    System.out.println("| Occupied Room Number: " + rs.getString("hospital_room_number") + " | Patient Last Name: " + rs.getString("hospital_patient_last_name") + " | Patient First Name: " + rs.getString("hospital_patient_first_name") + " |");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public void getUnOccupiedRooms() {
        String sql = "SELECT hospital_rooms.hospital_room_number, hospital_patient_id FROM hospital_rooms";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {

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
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {

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
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
            while (rs.next()) {
                System.out.println("| Patient Type: " + rs.getString("patient_type") + " | First Name: " + rs.getString("patient_first_name") + " | Last Name: " + rs.getString("patient_last_name") + "| Diagnosis: " + rs.getString("patient_initial_diagnosis") + " | Admission Date: " + rs.getString("patient_admission_date") + " | Patient Discharge Date: " + rs.getString("patient_discharge_date") + " | Patient Emergency Contact Name: " + rs.getString("patient_emergency_contact_name") + " | Patient Emergency Contact Phone Number: " + rs.getString("patient_emergency_contact_phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInpatients() {
        String sql = "SELECT patient_data.patient_type, patient_data.patient_id, patient_data.patient_first_name, patient_data.patient_last_name FROM patient_data";
        int count = 0;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
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
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
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
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
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
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
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
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
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
                                String patientAdmission, String dischargeDate) {

        String sql = "INSERT INTO admission_history(admitted_patient_id, admitted_patient_first_name, admitted_patient_last_name, admitted_patient_diagnosis, admitted_patient_doctor, admitted_patient_admission_date, admitted_patient_discharge_date) VALUES (?,?,?,?,?,?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientID); //First ? in sql
            ps.setString(2, patientFirstName); //Second ? in sql
            ps.setString(3, patientLastName); //Third ? in sql
            ps.setString(4, patientDiagnosis); //Fourth ? in sql
            ps.setString(5, patientDoctor); //Fifth ? in sql
            ps.setString(6, patientAdmission); //Fifth ? in sql
            ps.setString(7, dischargeDate); //Fifth ? in sql
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void lookUpAdmissionHistory(String lookUpPatient) {

        String sql = "SELECT admission_history.admitted_patient_id, admission_history.admitted_patient_first_name, admission_history.admitted_patient_last_name, admission_history.admitted_patient_admission_date, admission_history.admitted_patient_diagnosis FROM admission_history";
        String lookUpNew = lookUpPatient.toLowerCase().trim();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {
            while (rs.next()) {
                String admitted_id = rs.getString("admitted_patient_id").toLowerCase();
                String admitted_last = rs.getString("admitted_patient_last_name").toLowerCase();
                if (admitted_id.equals(lookUpNew) || admitted_last.equals(lookUpNew)) {
                    System.out.println("| Admission Date: " + rs.getString("admitted_patient_admission_date") + " | Diagnosis: " + rs.getString("admitted_patient_diagnosis") + " |");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}


