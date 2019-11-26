import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

    public void selectPatient() {
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
        String sql = "DELETE FROM doctor;";

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

        String sql = "INSERT INTO doctor(doctor_first_name, doctor_last_name, doctor_privileges) VALUES (?,?);";

        try (Connection conn = this.connect();) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, doctor_first_name); //First ? in sql
            ps.setString(2, doctor_last_name); //Second ? in sql
            ps.setString(2, doctor_privileges); //Third ? in sql
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectTheRooms() {
        String sql = "SELECT hospital_rooms.patient_id, hospital_rooms.patient_room_number, hospital_rooms.patient_last_name FROM hospital_rooms";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))   {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("patient_id") + " " + rs.getString("patient_room_number")
                        + " " + rs.getString("patient_last_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRooms(int hospital_room_number, String hostpial_patient_last_name, String hostpial_patient_first_name, int hospital_patient_id) {

          String sql = "INSERT INTO hospital_rooms(hospital_room_number, hospital_patient_last_name, hospital_patient_first_name, hospital_patient_id) VALUES (?,?,?,?);";

          try (Connection conn = this.connect();) {

              PreparedStatement ps = conn.prepareStatement(sql);
              ps.setInt(1, hospital_room_number); //First ? in sql
              ps.setString(2, hostpial_patient_last_name); //Second ? in sql
              ps.setString(3, hostpial_patient_first_name); //Third ? in sql
              ps.setInt(4, hospital_patient_id); //Third ? in sql
              ps.executeUpdate();
              ps.close();

          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }

      }
}

