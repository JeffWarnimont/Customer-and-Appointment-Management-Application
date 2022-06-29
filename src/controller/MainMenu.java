package controller;

import helper.DBQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainMenu is the controller class for the Main Menu Screen. This screen is like the central hub of this application.
 * Buttons are used to navigate to various screens and most other screens have buttons to navigate back to this screen
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class MainMenu implements Initializable {

    /**
     * This method initializes the MainMenu screen. It clears the data from any ObservableLists involving Customer or
     * Appointment objects and then refreshes those lists from the database.  This ensures that what the user sees
     * in the application always matches what is stored in the database, as the application always sends the user back to
     * this screen after any change to the database is recorded.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Register.getAllAppts().clear();
        Register.getAllCustomers().clear();
        Register.getAllTableAppts().clear();
        Register.getAllTableCustomers().clear();

        DBQueries.getAllTableApptsFromDB();
        DBQueries.getAllApptsFromDB();
        DBQueries.getAllTableCustsFromDB();
        DBQueries.getAllCustsFromDB();
    }

    /**
     * This method navigates to the Customers screen
     * @param actionEvent represents a view customer records button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToCusts(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    /**
     * This method navigates to the Appointments screen
     * @param actionEvent represents a view appointments button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToAppts(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    /**
     * Exits the application
     * @param actionEvent represents an exit button click
     */
    public void OnActionClose(ActionEvent actionEvent) {
        System.out.println("Goodbye");
        System.exit(0);
    }

    /**
     * This method navigates to the Contact Records screen
     * @param actionEvent represents a view contact schedules button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToContacts(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Contact_Records.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    /**
     * This method navigates to the Appointments by Type and Month screen
     * @param actionEvent represents a View Customer Appt Totals button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToTypes(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appts_Type_Month_Records.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

    /**
     * This method navigates to the Customers By Country screen
     * @param actionEvent represents a view customers by country button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToSpecialReport(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers_By_Country.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
