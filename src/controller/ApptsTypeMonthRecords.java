package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ApptsTypeMonthRecords is the controller class for the Appts Type Month Records Screen.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class ApptsTypeMonthRecords implements Initializable {
    @FXML
    private ComboBox<Customer> CustomerCB;
    @FXML
    private ComboBox<String> TypeCB;
    @FXML
    private ComboBox<String> MonthCB;

    /** This method initializes the ApptTyptMonthRecords Screen.  It sets up the combo boxes and populates them with data.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCB.setItems(Register.getAllCustomers());
        TypeCB.setItems(Register.getAllApptTypes());
        MonthCB.setItems(Register.getAllMonths());
        CustomerCB.setVisibleRowCount(6);
        TypeCB.setVisibleRowCount(6);
        MonthCB.setVisibleRowCount(6);

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
     * This method assesses the criteria from the three combo boxes and displays a dialog box with the quantity
     * of appointments that meet said criteria. It then clears the selections from the combo boxes.
     * @param actionEvent represents a run report button click
     */
    public void OnActionRunReport(ActionEvent actionEvent) {

        ObservableList<Appointment> matches = FXCollections.observableArrayList();
        Customer customer = CustomerCB.getSelectionModel().getSelectedItem();
        String type = TypeCB.getSelectionModel().getSelectedItem();
        String month = MonthCB.getSelectionModel().getSelectedItem();

        if((customer != null) && !(type.equals("")) && !(month.equals(""))) {

            int custId = customer.getCustId();

            for(Appointment appt : Register.getAllAppts()) {
                if((custId == appt.getCustId()) && type.equals(appt.getType()) &&
                        month.equals(appt.getStart().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH))) {
                    matches.add(appt);
                }
            }
            int size = matches.size();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Total " + type + " appointments for " + customer + " in " + month + " is " + size + ".");
            alert.showAndWait();

        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields are required.");
            alert.showAndWait();
        }
        CustomerCB.setValue(null);
        TypeCB.setValue(null);
        MonthCB.setValue(null);
    }
}
