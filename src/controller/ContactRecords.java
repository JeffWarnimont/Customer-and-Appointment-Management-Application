package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** ContactRecords is the controller class for the Contact Records Screen. This screen allows for viewing of
 * appointments separated by each of the three contacts.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class ContactRecords implements Initializable {

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
    private TableColumn<Appointment, String> TypeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> StartDTCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> EndDTCol;
    @FXML
    private TableColumn<Appointment, Integer> CustIdCol;
    @FXML
    private TableColumn<Appointment, Integer> UserIdCol;

    /** This method initializes the Contact Records Screen.  It sets the value factories for TableView.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartDTCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndDTCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }


    /**
     * This handler method filters appointments by the contact selected and displays the results in the TableView.
     * @param actionEvent represents selecting the Anika Costa radio button
     */
    public void OnActionAnikaCosta(ActionEvent actionEvent) {
        ObservableList<Appointment> matchingAppts = FXCollections.observableArrayList();
        for(Appointment appointment : Register.getAllAppts()) {
            if(appointment.getContactId() == (1)) {
                matchingAppts.add(appointment);
            }
        }
        ApptsTV.setItems(matchingAppts);
    }

    /**
     * This handler method filters appointments by the contact selected and displays the results in the TableView.
     * @param actionEvent represents selecting the Daniel Garcia radio button
     */
    public void OnActionDanielGarcia(ActionEvent actionEvent) {
        ObservableList<Appointment> matchingAppts = FXCollections.observableArrayList();
        for(Appointment appointment : Register.getAllAppts()) {
            if(appointment.getContactId() == (2)) {
                matchingAppts.add(appointment);
            }
        }
        ApptsTV.setItems(matchingAppts);
    }

    /**
     * This handler method filters appointments by the contact selected and displays the results in the TableView.
     * @param actionEvent represents selecting the Li Lee radio button
     */
    public void OnActionLiLee(ActionEvent actionEvent) {
        ObservableList<Appointment> matchingAppts = FXCollections.observableArrayList();
        for(Appointment appointment : Register.getAllAppts()) {
            if(appointment.getContactId() == (3)) {
                matchingAppts.add(appointment);
            }
        }
        ApptsTV.setItems(matchingAppts);
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
}
