package controller;

import helper.DBQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Appointments is the controller class for the Appointments screen.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class Appointments implements Initializable {

    @FXML
    private TableView<Appointment> ApptsTV;
    @FXML
    private TableColumn<Appointment, Integer> ApptIdCol;
    @FXML
    private TableColumn<Appointment, String> TitleCol;
    @FXML
    private TableColumn<Appointment, String> DescriptionCol;
    @FXML
    private TableColumn<Appointment, String> LocationCol;
    @FXML
    private TableColumn<Appointment, String> ContactCol;
    @FXML
    private TableColumn<Appointment, String> TypeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> StartDTCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> EndDTCol;
    @FXML
    private TableColumn<Appointment, Integer> CustIdCol;
    @FXML
    private TableColumn<Appointment, Integer> UserIdCol;

    /** This method initializes the Appointments Screen.  It sets up the TableView.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ApptsTV.setItems(Register.getAllTableAppts());

        ApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartDTCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndDTCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    /**
     * This handler method navigates to the Add Appointment Screen
     * @param actionEvent represents an add appointment button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionNavToAddAppt(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Add_Appointment.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    /**
     * This handler method navigates to the Modify Appointment Screen and transfers information to the form fields on that screen.
     * @param actionEvent represents an modify appointment button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionNavToModAppt(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Modify_Appointment.fxml"));
        loader.load();

        try{
            ModifyAppointment ModAppt = loader.getController();
            ModAppt.SetModApptFields(ApptsTV.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NullPointerException e)
        {

            Alert alertB = new Alert(Alert.AlertType.ERROR);
            alertB.setContentText("No appointment record was selected to modify.");
            alertB.showAndWait();
        }

    }

    /**
     * This method deletes the selected appointment record from the database after performing error checks.
     * After deletion the user is returned to the Main Menu screen.
     * @param actionEvent represents a delete customer button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionDeleteAppt(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This appointment will be deleted. Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            try {
                Appointment appointment = ApptsTV.getSelectionModel().getSelectedItem();
                int apptId = appointment.getApptId();
                String type = appointment.getType();

                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                alertC.setContentText("Appointment: " + apptId + " of type " + type + " successfully deleted.");
                alertC.showAndWait();

                DBQueries.deleteAppointment(apptId);

                Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
                Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 350, 450));
                stage.show();
            }
            catch (NullPointerException | IOException e) {
                Alert alertC = new Alert(Alert.AlertType.ERROR);
                alertC.setContentText("Cannot delete. No appointment was selected.");
                alertC.showAndWait();
            }
        }

    }

    /**
     * This handler method navigates to the Main Menu Screen
     * @param actionEvent represents an exit button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 350, 450));
        stage.show();
    }

    /**
     * This method displays all appointments in the TableView when the appropriate radio button is selected.
     * @param actionEvent represents a radio button selection
     */
    public void OnActionDisplayAll(ActionEvent actionEvent) {
        ApptsTV.setItems(Register.getAllTableAppts());
    }

    /**
     * This method displays current week appointments in the TableView when the appropriate radio button is selected.
     * @param actionEvent represents a radio button selection
     */
    public void OnActionDisplayWeekly(ActionEvent actionEvent) {
        ObservableList<Appointment> matchingAppts = FXCollections.observableArrayList();
        for(Appointment appointment : Register.getAllTableAppts()) {
            if(appointment.getStart().get(ChronoField.ALIGNED_WEEK_OF_YEAR) == LocalDateTime.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
                matchingAppts.add(appointment);
            }
        }
        ApptsTV.setItems(matchingAppts);
    }

    /**
     * This method displays current month appointments in the TableView when the appropriate radio button is selected.
     * @param actionEvent represents a radio button selection
     */
    public void OnActionDisplayMonthly(ActionEvent actionEvent) {
        ObservableList<Appointment> matchingAppts = FXCollections.observableArrayList();
        for(Appointment appointment : Register.getAllTableAppts()) {
            if(appointment.getStart().getMonth() == LocalDateTime.now().getMonth()) {
                matchingAppts.add(appointment);
            }
        }
        ApptsTV.setItems(matchingAppts);
    }
}
