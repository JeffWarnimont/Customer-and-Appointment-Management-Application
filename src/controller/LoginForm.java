package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Register;
import model.User;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LoginForm is the controller class for the Login Form.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class LoginForm implements Initializable {

    @FXML
    private TextField UsernameTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private Label PasswordLabel;
    @FXML
    private Label DisplayLabel;
    @FXML
    private Label LoginTitleLabel;
    @FXML
    private Button LoginButton;
    @FXML
    private Label UsernameLabel;

    /** This method initializes the Login Form Screen.  It checks the system language settings
     * and assigns all labels to display in either English or French based on those settings.  It
     * also checks the system time zone settings and displays the Zone ID in a label on the screen.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(Locale.getDefault().getLanguage().equals("fr")) {
            PasswordLabel.setText("Mot de passe");
            UsernameLabel.setText("Nom d'utilisateur");
            LoginButton.setText("Connexion");
            LoginTitleLabel.setText("Veuillez saisir les identifiants de connexion");
        }

        DisplayLabel.setText(String.valueOf(ZoneId.systemDefault()));

    }

    /** This method reads user input from the Username and Password text fields and validates against a list of stored
     * user login info when the Login button is clicked.  If the user is valid, navigation to the Main Menu will commence.
     * If invalid, the text fields will be cleared and a dialog box will display with an error message in either French or
     * English depending on system language settings. It also logs all login attempts to a file with username, password, and timestamp.
     * @param actionEvent represents a Login button click.
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionLogin(ActionEvent actionEvent) throws IOException {

        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();
        Timestamp tsUTC = Register.locDateAndTimeToUtcTimestamp(LocalDate.now(), LocalTime.now());
        boolean match = false;

        for (User user : Register.getAllUsers()) {
            if ((user.getUserName().equals(username)) && (user.getPassword().equals(password))) {
                match = true;
                break; }
        }

        String filename = "login_activity.txt";
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fw);


        if (match) {
            outputFile.println("Attempted login with username " + username + " and password " + password + " at " + tsUTC + " UTC successful");
            outputFile.close();

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 350, 450));
            stage.show();

            boolean upcoming = false;

            for(Appointment appt : Register.getAllTableAppts()) {
                if (LocalDateTime.now().isBefore(appt.getStart()) && LocalDateTime.now().plusMinutes(15).isAfter(appt.getStart())) {
                    upcoming = true;
                    if(Locale.getDefault().getLanguage().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Rendez-vous à venir: Identifiant de rendez-vous " + appt.getApptId() +
                                " sur " + appt.getStart().toLocalDate() + " à " + appt.getStart().toLocalTime() + " !");
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Upcoming appointment: Appt ID " + appt.getApptId() +
                                " on " + appt.getStart().toLocalDate() + " at " + appt.getStart().toLocalTime() + " !");
                        alert.showAndWait();
                    }
                }
            }

            if(!upcoming) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Aucun rendez-vous dans les 15 prochaines minutes.");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("No appointments within the next 15 minutes.");
                    alert.showAndWait();
                }
            }
        }

        else {
            outputFile.println("Attempted login with username " + username + " and password " + password + " at " + tsUTC + " UTC failed");
            outputFile.close();

            UsernameTextField.setText("");
            PasswordTextField.setText("");
            if(Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer des informations d'identification valides.");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid credentials.");
                alert.showAndWait();
            }
        }
    }
}
