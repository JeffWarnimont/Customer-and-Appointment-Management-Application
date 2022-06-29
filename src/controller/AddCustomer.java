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
import model.FirstLevelDivision;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * AddCustomer is the controller class for the Add Customer screen.
 * It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class AddCustomer implements Initializable {

    @FXML
    private TextField CustAddressTF;
    @FXML
    private TextField CustNameTF;
    @FXML
    private TextField CustPostalCodeTF;
    @FXML
    private TextField CustPhoneTF;
    @FXML
    private TextField CustIdTF;
    @FXML
    private ComboBox<FirstLevelDivision> CustStateProvinceCB;
    @FXML
    private ComboBox<Country> CustCountryCB;

    /** This method initializes the AddCustomer Screen.  It sets items to the comboBox.
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustCountryCB.setItems(Register.getAllCountries());
    }

    /**
     * This method inserts a new customer into the database after performing error checks.
     * It then routes the user back to the MainMenu screen.
     * @param actionEvent represents a save button click
     * @throws IOException
     */
    public void OnActionAddCustomer(ActionEvent actionEvent) throws IOException {

        String name = CustNameTF.getText();
        String address = CustAddressTF.getText();
        String postalCode = CustPostalCodeTF.getText();
        String  phone = CustPhoneTF.getText();
        FirstLevelDivision division = CustStateProvinceCB.getSelectionModel().getSelectedItem();

        if (!(name.equals("")) && !(address.equals("")) && !(postalCode.equals("")) && !(phone.equals("")) && division != null) {
            int divisionId = division.getDivisionId();
            DBQueries.addNewCustomer(name,address,postalCode,phone,divisionId); }
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
     * This handler method sets the list of available divisions in the division ComboBox when a country is selected in the country ComboBox
     * @param actionEvent represents a country selection in the ComboBox
     */
    public void OnCountrySelection(ActionEvent actionEvent) {
        Register.getMatchingDivisions().clear();
        CustStateProvinceCB.setItems(Register.filterDivisionByCountry(CustCountryCB.getSelectionModel().getSelectedItem()));
    }
}
