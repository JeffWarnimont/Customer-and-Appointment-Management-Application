package controller;

import helper.DBQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Customers is the controller class for the Customers screen.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class Customers implements Initializable {

    @FXML
    private TableView<Customer> CustomerTV;
    @FXML
    private TableColumn<Customer, String> CustomerNameCol;
    @FXML
    private TableColumn<Customer, String> CustomerAddressCol;
    @FXML
    private TableColumn<Customer, Integer> CustomerPostalCodeCol;
    @FXML
    private TableColumn<Customer, String> CustomerCountryCol;
    @FXML
    private TableColumn<Customer, String> CustomerStateProvCol;
    @FXML
    private TableColumn<Customer, Integer> CustomerPhoneCol;
    @FXML
    private TableColumn<Customer, Integer> CustomerIdCol;
    @FXML
    private TableView<Appointment> AssocApptTV;
    @FXML
    private TableColumn<Appointment, Integer> AssocApptApptIdCol;
    @FXML
    private TableColumn<Appointment, String> AssocApptTitleCol;
    @FXML
    private TableColumn<Appointment, String> AssocApptDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> AssocApptLocationCol;
    @FXML
    private TableColumn<Appointment, String> AssocApptContactCol;
    @FXML
    private TableColumn<Appointment, String> AssocApptTypeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> AssocApptStartDTCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> AssocApptEndDTCol;
    @FXML
    private TableColumn<Appointment, Integer> AssocApptCustIdCol;
    @FXML
    private TableColumn<Appointment, Integer> AssocApptUserIdCol;


    /** This method initializes the Customers Screen.  It sets up the TableViews.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerTV.setItems(Register.getAllTableCustomers());

        CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CustomerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustomerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CustomerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        CustomerStateProvCol.setCellValueFactory(new PropertyValueFactory<>("stateProvince"));
        CustomerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));

        AssocApptApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        AssocApptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        AssocApptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        AssocApptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        AssocApptContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        AssocApptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        AssocApptStartDTCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        AssocApptEndDTCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        AssocApptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        AssocApptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    /**
     * This handler method navigates to the Add Customer Screen
     * @param actionEvent represents an add customer button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionNavToAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Add_Customer.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    /**
     * This handler method navigates to the Modify Customer Screen and transfers information to the form fields on that screen.
     * @param actionEvent represents an modify customer button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionNavToModCustomer(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Modify_Customer.fxml"));
        loader.load();

        try{
            ModifyCustomer ModCust = loader.getController();
            ModCust.SetModCustomerFields(CustomerTV.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NullPointerException e)
        {

            Alert alertB = new Alert(Alert.AlertType.ERROR);
            alertB.setContentText("No customer record was selected to modify.");
            alertB.showAndWait();
        }

    }

    /**
     * This method deletes the selected customer record from the database after performing error checks.
     * It will not perform the deletion if there are appointments scheduled for the customer.
     * After deletion the user is returned to the Main Menu screen.
     * @param actionEvent represents a delete customer button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionDeleteCustomer(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This customer record will be permanently deleted. Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            try {
                Customer customer = CustomerTV.getSelectionModel().getSelectedItem();
                int customerId = customer.getCustId();

                if (Register.getMatchingAppts().size() != 0) {
                    Alert alertB = new Alert(Alert.AlertType.ERROR, "This customer has associated appointments and cannot be deleted.  " +
                            "If you wish to continue, associated appointments must first be deleted.");
                    alertB.showAndWait();
                }
                else {
                    Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                    alertC.setContentText("Customer: " + customer.getCustomerName() + " successfully deleted.");
                    alertC.showAndWait();

                    DBQueries.deleteCustomer(customerId);

                    Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
                    Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root, 350, 450));
                    stage.show();
                }

            }
            catch (NullPointerException e) {
                Alert alertC = new Alert(Alert.AlertType.ERROR);
                alertC.setContentText("Cannot delete. No customer was selected.");
                alertC.showAndWait();
            }
        }


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
            ModAppt.SetModApptFields(AssocApptTV.getSelectionModel().getSelectedItem());

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
                Appointment appointment = AssocApptTV.getSelectionModel().getSelectedItem();
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
     * This method filters appointments by customer.
     * @param customer the selected customer
     */
    public void filterApptByCustomer(Customer customer) {
        try{
            for(Appointment appointment : Register.getAllTableAppts()) {
                if (appointment.getCustId() == customer.getCustId()) {
                    Register.addMatchingAppointment(appointment);
                }
            }
        }
        catch (NullPointerException ignored){
        }
    }

    /**
     * This method displays appointments in the appointment table belonging to the customer selected in the customers table.
     * @param mouseEvent represents a customer selection
     */
    public void OnCustomerSelection(MouseEvent mouseEvent) {

        Register.getMatchingAppts().clear(); //clear contents of associated appointments table
        filterApptByCustomer(CustomerTV.getSelectionModel().getSelectedItem()); //builds list of appointments for selected customer
        AssocApptTV.setItems(Register.getMatchingAppts()); //displays list in associated appointments table
    }
}
