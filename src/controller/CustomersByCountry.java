package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Customer;
import model.Register;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** CustomersByCountry is the controller class for the CustomersByCountry page.
 It contains FXML object fields and event handler methods to facilitate user interaction with the GUI.
 */
public class CustomersByCountry implements Initializable {

    @FXML
    private PieChart CustByCountryPieChart;

    /**
     *This method initializes the CustomersByCountry screen.  It uses customer data from the application to
     * create a pie chart displaying customer breakdown by country.
     *
     * I used a lambda in this method to format the way that labels are displayed in the pie chart visual.
     * Normally the labels would just display the country name, but I set it up so that the name and quantity of
     * customers both display in the label and legend.  Within this lambda I also changed the quantity display from
     * double to int as it would make no sense to have a partial customer, so a decimal would be arbitrary.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        double usaCount = 0;
        double ukCount = 0;
        double canadaCount = 0;

        for(Customer customer : Register.getAllCustomers()) {
            if((customer.getDivisionId() >= 1) && (customer.getDivisionId() <= 54)) {
                usaCount +=1;
            }
            if((customer.getDivisionId() >= 60) && (customer.getDivisionId() <= 72)) {
                ukCount +=1;
            }
            if((customer.getDivisionId() >= 101) && (customer.getDivisionId() <= 104)) {
                canadaCount +=1;
            }
        }
        pieChartData.add(0,new PieChart.Data("USA", usaCount));
        pieChartData.add(1,new PieChart.Data("UK", ukCount));
        pieChartData.add(2,new PieChart.Data("Canada", canadaCount));

        //lambda to improve pie slice name display in pie chart and legend
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.pieValueProperty().getValue().intValue()
                        )));

        CustByCountryPieChart.getData().addAll(pieChartData);
    }

    /**This handler method navigates back to the Main Menu screen when the Exit button is clicked.
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
