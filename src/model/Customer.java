package model;

/** The Customer class contains fields, constructors, and getter and setter methods for accessing Customer objects.
 * The Customer class has eight fields (custId, customerName, address, postalCode, country, stateProvince, phoneNumber, divisionId).
 * This class exists to match Customer objects in the application to appointments saved in the database. Customer objects
 * are used to populate observable lists and table views throughout the application.
 */
public class Customer {
    private int custId;
    private String customerName;
    private String address;
    private String postalCode;
    private String country;
    private String stateProvince;
    private String phoneNumber;
    private int divisionId;

    /**
     * @param custId the unique customer identifier
     * @param customerName the customer name
     * @param address the customer address
     * @param postalCode the customer postal code
     * @param country the customer country
     * @param stateProvince the customer state or province
     * @param phoneNumber the customer phone number
     */
    //constructor for customer matching table requirements
    public Customer(int custId, String customerName, String address, String postalCode, String country, String stateProvince, String phoneNumber){
        this.custId = custId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.stateProvince = stateProvince;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param custId the unique customer identifier
     * @param customerName the customer name
     * @param address the customer address
     * @param postalCode the customer postal code
     * @param phoneNumber the customer phone number
     * @param divisionId the customer division ID
     */
    //constructor for customer with fields matching database
    public Customer(int custId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        this.custId = custId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    /**
     * This method overrides the toString() method of the Customer class for display purposes.
     * When placed in the combo boxes a Customer object will be displayed as a string of the customerName.
     */
    @Override
    public String toString() {
        return(customerName);
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param name the name to set
     */
    public void setCustomerName(String name) {
        this.customerName = customerName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * @param custId thecustId to set
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the stateProvince
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * @param stateProvince the stateProvince to set
     */
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    /**
     * @return the divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the divisionId to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

}
