package model;

/** The Country class contains fields, a constructor, and getter and setter methods for accessing Country objects.
 * Country objects contain two fields (countryId, country). This class exists to match Country objects in the
 * application to countries saved in the database. Country objects are associated with Customer objects and are used to
 * populate observable lists and combo boxes in the application.
 */
public class Country {
    private int countryId;
    private String country;

    /**
     * @param countryId is the unique country identifier
     * @param country is the country name
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * This method overrides the toString() method of the Country class for display purposes.
     * When placed in the combo boxes a Country object will be displayed as a string of the country.
     */
    @Override
    public String toString() {
        return(country);
    }

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
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

}
