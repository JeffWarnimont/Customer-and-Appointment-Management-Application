package helper;

import model.*;
import java.sql.*;

/**
 * The DBQueries class contains methods for reading data from and writing data to the database.
 */
public abstract class DBQueries {


    /**
     * This method retrieves customer data from the database, constructs the data into Customer objects and adds those objects to
     * the relevant ObservableList.  It has built SQLException handling.
     */
    public static  void getAllTableCustsFromDB() {
        try {
            String sql = "SELECT Customer_ID,Customer_Name,Address,Postal_Code,Country,Division,Phone " +
                    "FROM Customers,Countries,First_Level_Divisions " +
                    "WHERE (Customers.Division_ID = First_Level_Divisions.Division_ID) " +
                    "AND (First_Level_Divisions.Country_ID = Countries.Country_ID)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addTableCustomer(new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"),
                        rs.getString("Address"), rs.getString("Postal_Code"),
                        rs.getString("Country"),rs.getString("Division"),rs.getString("Phone")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves customer data from the database, constructs the data into Customer objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static  void getAllCustsFromDB() {
        try {
            String sql = "SELECT Customer_ID,Customer_Name,Address,Postal_Code,Phone,Division_ID " +
                    "FROM Customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addCustomer(new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"),
                        rs.getString("Address"), rs.getString("Postal_Code"),
                        rs.getString("Phone"),rs.getInt("Division_ID")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves appointment data from the database, constructs the data into Appointment objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static void getAllTableApptsFromDB() {
        try {
            String sql = "SELECT Appointment_ID,Title,Description,Location,Contact_Name,Type,Start,End,Customer_ID,User_ID " +
                    "FROM Appointments,Contacts WHERE Appointments.Contact_ID = Contacts.Contact_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addTableAppt(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Contact_Name"),
                        rs.getString("Type"),Register.timestampUtcToLDT(rs.getTimestamp("Start")),
                        Register.timestampUtcToLDT(rs.getTimestamp("End")),rs.getInt("Customer_ID"),rs.getInt("User_ID")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves appointment data from the database, constructs the data into Appointment objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static void getAllApptsFromDB() {
        try {
            String sql = "SELECT Appointment_ID,Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID " +
                    "FROM Appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addAppt(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"),rs.getString("Type"),
                        Register.timestampUtcToLDT(rs.getTimestamp("Start")), Register.timestampUtcToLDT(rs.getTimestamp("End")),
                        rs.getInt("Customer_ID"),rs.getInt("User_ID"), rs.getInt("Contact_ID")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves user data from the database, constructs the data into User objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static void getAllUsersFromDB() {

        try {
            String sql = "SELECT User_ID,User_Name,Password FROM Users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addUser(new User(rs.getInt("User_ID"),rs.getString("User_Name"),rs.getString("Password")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves contact data from the database, constructs the data into Contact objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static void getAllContactsFromDB() {

        try {
            String sql = "SELECT Contact_ID,Contact_Name,Email FROM Contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addContact(new Contact(rs.getInt("Contact_ID"),rs.getString("Contact_Name"),rs.getString("Email")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves division data from the database, constructs the data into FirstLevelDivision objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static void getAllDivisionsFromDB() {

        try {
            String sql = "SELECT Division_ID,Division,Country_ID FROM First_Level_Divisions";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addFirstLevelDivision(new FirstLevelDivision(rs.getInt("Division_ID"),rs.getString("Division"),rs.getInt("Country_ID")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method retrieves country data from the database, constructs the data into Country objects and adds those objects to
     * the relevant ObservableList.  It has built in SQLException handling.
     */
    public static void getAllCountriesFromDB() {

        try {
            String sql = "SELECT Country_ID,Country FROM Countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Register.addCountry(new Country(rs.getInt("Country_ID"),rs.getString("Country")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**This method inserts a new customer record into the database. It has built in SQLException handling.
     * @param customerName the customer name
     * @param address the customer address
     * @param postalCode the customer postal code
     * @param phoneNumber the customer phone number
     * @param divisionId the customer division ID
     */
    public static void addNewCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        try {
            String sql = "INSERT INTO Customers (Customer_Name,Address,Postal_Code,Phone,Division_ID) VALUES (?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setInt(5, divisionId);
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**This method updates an existing customer record in the database. It has built in SQLException handling.
     * @param customerName the customer name
     * @param address the customer address
     * @param postalCode the customer postal code
     * @param phoneNumber the customer phone number
     * @param divisionId the customer division ID
     */
    public static void modifyCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId, int customerId) {
        try {
            String sql = "UPDATE Customers SET Customer_Name = ?,Address = ?,Postal_Code = ?,Phone = ?,Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**This method deletes an existing customer record from the database. It has built in SQLException handling.
     * @param customerId the id of the customer record to delete
     */
    public static void deleteCustomer(int customerId) {
        try {
            String sql = "DELETE FROM Customers WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**This method inserts a new appointment record into the database. It has built in SQLException handling.
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start
     * @param end the appointment end
     * @param custId the appointment custId
     * @param userId the appointment userId
     * @param contactId the appointment contactId
     */
    public static void addNewAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, int custId, int userId, int contactId) {
        try {
            String sql = "INSERT INTO Appointments (Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setInt(7, custId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**This method updates an existing appointment record into the database. It has built in SQLException handling.
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start
     * @param end the appointment end
     * @param custId the appointment custId
     * @param userId the appointment userId
     * @param contactId the appointment contactId
     */
    public static void modifyAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, int custId, int userId, int contactId, int apptId) {
        try {
            String sql = "UPDATE Appointments SET Title = ?,Description = ?,Location = ?,Type = ?,Start = ?,End = ?,Customer_ID = ?," +
                    "User_ID = ?,Contact_ID = ? WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setInt(7, custId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.setInt(10, apptId);
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**This method deletes an existing appointment record from the database. It has built in SQLException handling.
     * @param apptId the id of the customer record to delete
     */
    public static void deleteAppointment(int apptId) {
        try {
            String sql = "DELETE FROM Appointments WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, apptId);
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
