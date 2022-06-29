package controller;

import helper.DBQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ModifyCustomer is the controller class for the Modify Customer screen.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class ModifyCustomer implements Initializable {

    @FXML
    private TextField CustIdTF;
    @FXML
    private TextField CustNameTF;
    @FXML
    private TextField CustAddressTF;
    @FXML
    private TextField CustPostalCodeTF;
    @FXML
    private ComboBox<Country> CustCountryCB;
    @FXML
    private ComboBox<FirstLevelDivision> CustStateProvinceCB;
    @FXML
    private TextField CustPhoneTF;

    /**
     * This method initializes the Modify Customer Screen.  It formats and sets items to the Country ComboBox.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustCountryCB.setItems(Register.getAllCountries());
        CustStateProvinceCB.setVisibleRowCount(4);
    }

    /**
     * This method retrieves data from the form fields and uses it to update the customer row in the database.
     * Several error checks are performed for input validation.  It then navigates to the MainMenu screen
     * @param actionEvent represents a save button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionModifyCustomer(ActionEvent actionEvent) throws IOException {

        String name = CustNameTF.getText();
        String address = CustAddressTF.getText();
        String postalCode = CustPostalCodeTF.getText();
        String  phone = CustPhoneTF.getText();
        FirstLevelDivision division = CustStateProvinceCB.getSelectionModel().getSelectedItem();
        int customerId = Integer.parseInt(CustIdTF.getText());

        if (!(name.equals("")) && !(address.equals("")) && !(postalCode.equals("")) && !(phone.equals("")) && division != null) {
            int divisionId = division.getDivisionId();
            DBQueries.modifyCustomer(name,address,postalCode,phone,divisionId,customerId); }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields are required.");
            alert.showAndWait();
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 350, 450));
        stage.show();
    }

    /**
     * This handler method navigates to the Main Menu Screen
     * @param actionEvent represents a cancel button click
     * @throws IOException This method can throw an IO Exception if the fxml file cannot be found.
     */
    public void OnActionToMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Menu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 350, 450));
        stage.show();
    }

    /**
     * This method is used to find the customer associated country.
     * @param selectedCustomer the selected customer
     * @return the country
     */
    public Country lookupCustomerCountry(Customer selectedCustomer) {
        for (Country country : Register.getAllCountries()) {
            if (selectedCustomer.getCountry().equals(country.getCountry()))
                return country;
        }
        return null;
    }

    /**
     * This method is used to find the customer associated division.
     * @param selectedCustomer the selected customer
     * @return the division
     */
    public FirstLevelDivision lookupCustomerDivision(Customer selectedCustomer) {
        for (FirstLevelDivision division : Register.getAllFirstLevelDivisions()) {
            if (selectedCustomer.getStateProvince().equals(division.getDivision()))
                return division;
        }
        return null;
    }

    /**
     * This method is used to pass customer information between the TableView on the previous screen to the form fields
     * on this screen.
     * @param customer the customer
     */
    public void SetModCustomerFields(Customer customer) {
        CustIdTF.setText(String.valueOf(customer.getCustId()));
        CustNameTF.setText(customer.getCustomerName());
        CustAddressTF.setText(String.valueOf(customer.getAddress()));
        CustPostalCodeTF.setText(String.valueOf(customer.getPostalCode()));
        CustCountryCB.setValue(lookupCustomerCountry(customer));
        CustStateProvinceCB.setValue(lookupCustomerDivision(customer));
        CustStateProvinceCB.setItems(Register.filterDivisionByCountry(CustCountryCB.getSelectionModel().getSelectedItem()));
        CustPhoneTF.setText(String.valueOf(customer.getPhoneNumber()));
    }


    /**
     * This handler method sets the list of available divisions in the division ComboBox when a country is selected in the country ComboBox
     * @param actionEvent represents a country selection in the ComboBox
     */
    public void OnCountrySelection(ActionEvent actionEvent) {
        Register.getMatchingDivisions().clear(); //clears division list
        CustStateProvinceCB.setValue(null); //clears division selection
        CustStateProvinceCB.setItems(Register.filterDivisionByCountry(
                CustCountryCB.getSelectionModel().getSelectedItem())); //sets division list based on country selection
    }
}
