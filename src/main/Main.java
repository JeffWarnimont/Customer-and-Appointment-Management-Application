package main;

import helper.DBQueries;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Register;
import java.io.IOException;
import java.util.Locale;

/** The main class is the initial entrypoint into the program. This program is a scheduling application.
 * Scheduling functionality mostly deals with adding, removing, and modifying customers and their appointments.
 */
public class Main extends Application {

    /** The start method loads the Login screen of the program.
     * @param stage holds the scene to show. In this instance it will show the Login Screen.
     * @throws IOException An IO Exception is possible if the fxml file cannot be found.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login_Form.fxml"));
        stage.setTitle("Jeff_Warnimont_C195_PA");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    /** The main method launches the program with all arguments passed. It opens the database connection at the start
     * of the session and closes the connection at termination.  It also calls several methods that populate the ObservableLists
     * used in the program by obtaining the info from the database as well as calling methods that build ObservableLists within the program.
     * @param args the arguments passed to the launcher.
     */
    public static void main(String[] args) {
        //Locale.setDefault(new Locale("fr")); //used for testing language conversion without changing system settings or reboot
        JDBC.openConnection();
        DBQueries.getAllUsersFromDB();
        DBQueries.getAllTableApptsFromDB();
        DBQueries.getAllApptsFromDB();
        DBQueries.getAllTableCustsFromDB();
        DBQueries.getAllCustsFromDB();
        DBQueries.getAllContactsFromDB();
        DBQueries.getAllCountriesFromDB();
        DBQueries.getAllDivisionsFromDB();
        Register.buildStartTimeList();
        Register.buildEndTimeList();
        launch(args);
        JDBC.closeConnection();
    }

}
