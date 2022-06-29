package controller;

import helper.DBQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Add Appointment is the controller class for the Add Appointment screen.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class AddAppointment implements Initializable {

    @FXML
    private ComboBox<String> ApptTypeCB;
    @FXML
    private TextField ApptCustIdTF;
    @FXML
    private TextField ApptLocationTF;
    @FXML
    private TextField ApptDescTF;
    @FXML
    private TextField ApptTitleTF;
    @FXML
    private ComboBox<Contact> ApptContactCB;
    @FXML
    private DatePicker ApptDateDP;
    @FXML
    private ComboBox<LocalTime> ApptStartCB;
    @FXML
    private ComboBox<LocalTime> ApptEndCB;
    @FXML
    private TextField ApptUserIdTF;


    /**
     * This method initializes the Add Appointment Screen.  It sets up the ComboBoxes and DatePicker.
     *
     * I put a lambda in this method that disables all dates before the current date in the DatePicker as it would make
     * no sense in a real world application to schedule an appointment in the past.  This reduces the chance of human error
     * by making the choice unavailable.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApptContactCB.setItems(Register.getAllContacts());
        ApptStartCB.setItems(Register.getAllStartTimes());
        ApptTypeCB.setItems(Register.getAllApptTypes());
        ApptStartCB.setVisibleRowCount(6);
        ApptEndCB.setVisibleRowCount(6);

        //lambda Makes days prior to current day non-selectable in date picker
        ApptDateDP.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(LocalDate.now()));
                    }});

    }

    /**
     * This method inserts a new appointment into the database after performing error checks
     * and time conversions. This method also performs the checks for overlapping appointments.
     * After saving to the database it then routes the user back to the MainMenu screen.
     * @param actionEvent represents a save button click
     * @throws IOException
     */
    public void OnActionAddAppointment(ActionEvent actionEvent) throws IOException {

        try {
            String title = ApptTitleTF.getText();
            String description = ApptDescTF.getText();
            String location = ApptLocationTF.getText();
            String type = ApptTypeCB.getSelectionModel().getSelectedItem();
            Contact contact = ApptContactCB.getSelectionModel().getSelectedItem();
            int custId = Integer.parseInt(ApptCustIdTF.getText());
            int userId = Integer.parseInt(ApptUserIdTF.getText());
            LocalTime startTime = ApptStartCB.getSelectionModel().getSelectedItem();
            LocalTime endTime = ApptEndCB.getSelectionModel().getSelectedItem();
            LocalDate date = ApptDateDP.getValue();

            boolean userMatch = false;
            boolean custMatch = false;
            boolean overlap = false;

            if ((!(title.equals("")) && !(description.equals("")) && !(location.equals("")) && !(type.equals(""))
                    && contact != null) && (custId > 0) && (userId > 0)
                    && startTime != null && endTime != null && date != null) {

                Timestamp start = Register.locDateAndTimeToUtcTimestamp(date, startTime);
                Timestamp end = Register.locDateAndTimeToUtcTimestamp(date, endTime);
                LocalDateTime startLDT = LocalDateTime.of(date, startTime);
                LocalDateTime endLDT = LocalDateTime.of(date, endTime);

                int contactId = contact.getContactId();

                for(User user : Register.getAllUsers()){
                    if (user.getUserId() == userId) {
                        userMatch = true;
                        break;
                    }
                }

                if(!userMatch) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid User Id. Please enter valid Id.");
                    alert.showAndWait();
                    return;

                }

                for(Customer customer : Register.getAllCustomers()){
                    if (customer.getCustId() == custId) {
                        custMatch = true;
                        break;
                    }
                }

                if(!custMatch) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid Customer Id. Please enter valid Id.");
                    alert.showAndWait();
                    return;

                }

                for(Appointment appt : Register.getAllTableAppts()) {
                    if(appt.getCustId() == custId) {
                      LocalDateTime eAS = appt.getStart(); //Existing Appointment Start
                      LocalDateTime eAE = appt.getEnd(); //Existing Appointment End
                        if(eAS.isBefore(startLDT) && eAE.isAfter(startLDT)) {
                            overlap = true;
                            break;
                        }
                        if(eAS.isBefore(endLDT) && eAE.isAfter(endLDT)) {
                            overlap = true;
                            break;
                        }
                        if((eAS.isEqual(startLDT)) || (eAE.isEqual(endLDT))) {
                            overlap = true;
                            break;
                        }
                        if(eAS.isAfter(startLDT) && eAE.isBefore(endLDT)) {
                            overlap = true;
                            break;
                        }
                    }
                }

                if(overlap) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The proposed appointment causes a scheduling conflict for this customer.");
                    alert.showAndWait();
                    return;
                }

                DBQueries.addNewAppointment(title,description,location,type,start,end,custId,userId,contactId);

            }

            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("All fields are required.");
                alert.showAndWait();
                return;
            }

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 350, 450));
            stage.show();

        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter valid data type.");
            alert.showAndWait();
        }
    }

    /**
     * This handler method navigates to the Main Menu Screen
     * @param actionEvent represents a cancel button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToMainMenu (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 350, 450));
        stage.show();
    }

    /**
     * This method filters available end times by the selected start time and then adds them to the endTime comboBox.
     * @param actionEvent represents a startTime selection
     */
    public void OnStartSelection(ActionEvent actionEvent) {
        Register.getAllAvailableEndTimes().clear();
        ApptEndCB.setItems(Register.findAvailableEndTimes(ApptStartCB.getSelectionModel().getSelectedItem()));
    }
}
