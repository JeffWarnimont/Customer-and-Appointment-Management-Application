package model;

/** The FirstLevelDivision class contains fields, a constructor, and getter methods for accessing FirstLevelDivision objects.
 * FirstLevelDivision objects contain three fields (divisionId, division, countryId). This class exists to match FirstLevelDivision
 * objects in the application to first level divisions saved in the database. FirstLevelDivision objects are associated with Customer
 * objects and are used to populate observable lists and combo boxes in the application.
 */
public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private int countryId;

    /**
     * @param divisionId is the unique division identifier
     * @param division is the division name
     * @param countryId is the unique country identifier
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * This method overrides the toString() method of the FirstLevelDivision class for display purposes.
     * When placed in the combo boxes a FirstLevelDivision object will be displayed as a string of the division.
     */
    @Override
    public String toString() {
        return(division);
    }

    /**
     * @return the divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

}
