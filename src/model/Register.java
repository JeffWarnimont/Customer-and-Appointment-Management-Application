package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.*;

/**
 * The Register class contains several observable lists and methods for working with them. These lists are used to
 * represent the tables in the database and are used to populate table views and combo boxes within the application.
 */
public class Register {

    /**
     * An ObservableList of Appointments matching the fields required for Tableviews within the application.
     */
    private static ObservableList<Appointment> allTableAppts = FXCollections.observableArrayList();

    /**
     * An ObservableList of Appointments matching the fields required for transfer of data to and from the database.
     */
    private static ObservableList<Appointment> allAppts = FXCollections.observableArrayList();

    /**
     * An ObservableList of Customers matching the fields required for Tableviews within the application.
     */
    private static ObservableList<Customer> allTableCustomers = FXCollections.observableArrayList();

    /**
     * An ObservableList of Customers matching the fields required for transfer of data to and from the database.
     */
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * An ObservableList of Users matching the fields required for transfer of data to and from the database.
     */
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * An ObservableList of Countries matching the fields required for transfer of data to and from the database.
     */
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * An ObservableList of FirstLevelDivisions matching the fields required for transfer of data to and from the database.
     */
    private static ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();

    /**
     * An ObservableList of Contacts matching the fields required for transfer of data to and from the database.
     */
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * An ObservableList of matching FirstLevelDivisions used for filtering.
     */
    private static ObservableList<FirstLevelDivision> matchingDivisions = FXCollections.observableArrayList();

    /**
     * An ObservableList of matching Appointments used for filtering.
     */
    private static ObservableList<Appointment> matchingAppts = FXCollections.observableArrayList();

    /**
     * An ObservableList of StartTimes used for populating combo boxes.
     */
    private static ObservableList<LocalTime> allStartTimes = FXCollections.observableArrayList();

    /**
     * An ObservableList of EndTimes used for populating combo boxes.
     */
    private static ObservableList<LocalTime> allEndTimes = FXCollections.observableArrayList();

    /**
     * An ObservableList of availableEndTimes used for filtering and populating combo boxes.
     */
    private static ObservableList<LocalTime> availableEndTimes = FXCollections.observableArrayList();

    /**
     * An ObservableList of Appointment Types used for populating combo boxes.
     */
    private static final ObservableList<String> allApptTypes = FXCollections.observableArrayList("Coffee Meeting",
            "Lunch Meeting", "Meeting Over Drinks", "15 Minute Consult", "Morning Briefing", "Conference Call");

    /**
     * An ObservableList of Months used for populating combo boxes.
     */
    private static final ObservableList<String> allMonths = FXCollections.observableArrayList("January", "February",
            "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    /**This method adds an Appointment object to the allTableAppts ObservableList
     * @param newAppt the Appointment to add
     */
    public static void addTableAppt(Appointment newAppt) {
        allTableAppts.add(newAppt);
    }

    /**This method adds an Appointment object to the allAppts ObservableList
     * @param newAppt the Appointment to add
     */
    public static void addAppt(Appointment newAppt) {
        allAppts.add(newAppt);
    }

    /**This method adds a Customer object to the allTableCustomers ObservableList
     * @param newCustomer the Customer to add
     */
    public static void addTableCustomer(Customer newCustomer) {
        allTableCustomers.add(newCustomer);
    }

    /**This method adds a Customer object to the allCustomers ObservableList
     * @param newCustomer the Customer to add
     */
    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    /**This method adds a User object to the allUsers ObservableList
     * @param newUser the User to add
     */
    public static void addUser(User newUser) {
        allUsers.add(newUser); }

    /**This method adds a Country object to the allCountries ObservableList
     * @param newCountry the Country to add
     */
    public static void addCountry(Country newCountry) {
        allCountries.add(newCountry); }

    /**This method adds a FirstLevelDivision object to the allFirstLevelDivisions ObservableList
     * @param newFLD the FirstLevelDivision to add
     */
    public static void addFirstLevelDivision(FirstLevelDivision newFLD) {
        allFirstLevelDivisions.add(newFLD); }

    /**This method adds a Contact object to the allContacts ObservableList
     * @param newContact the Contact to add
     */
    public static void addContact(Contact newContact) {
        allContacts.add(newContact); }

    /**This method adds a FirstLevelDivision object to the matchingDivisions ObservableList
     * @param matchFLD the FirstLevelDivision to add
     */
    public static void addMatchingDivision(FirstLevelDivision matchFLD) {
        matchingDivisions.add(matchFLD); }

    /**This method adds an Appointment object to the matchingAppts ObservableList
     * @param matchAppt the Appointment to add
     */
    public static void addMatchingAppointment(Appointment matchAppt) {
        matchingAppts.add(matchAppt);
    }

    /**This method adds a LocalTime object to the availableEndTimes ObservableList
     * @param newTime the LocalTime to add
     */
    public static void addAvailableEndTime(LocalTime newTime) { availableEndTimes.add(newTime); }

    /**
     * @return allTableAppts
     */
    public static ObservableList<Appointment> getAllTableAppts() {
        return allTableAppts;
    }

    /**
     * @return allAppts
     */
    public static ObservableList<Appointment> getAllAppts() {
        return allAppts;
    }

    /**
     * @return allTableCustomers
     */
    public static ObservableList<Customer> getAllTableCustomers() {
        return allTableCustomers;
    }

    /**
     * @return allCustomers
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     * @return allUsers
     */
    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * @return allCountries
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    /**
     * @return allFirstLevelDIvisions
     */
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() {
        return allFirstLevelDivisions;
    }

    /**
     * @return allContacts
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    /**
     * @return matchingDivisions
     */
    public static ObservableList<FirstLevelDivision> getMatchingDivisions() {
        return matchingDivisions;
    }

    /**
     * @return matchingAppts
     */
    public static ObservableList<Appointment> getMatchingAppts() {
        return matchingAppts;
    }

    /**
     * @return allStartTimes
     */
    public static ObservableList<LocalTime> getAllStartTimes() {
        return allStartTimes;
    }

    /**
     * @return availableEndTimes
     */
    public static ObservableList<LocalTime> getAllAvailableEndTimes() {
        return availableEndTimes;
    }

    /**
     * @return allApptTypes
     */
    public static ObservableList<String> getAllApptTypes() {
        return allApptTypes;
    }

    /**
     * @return allMonths
     */
    public static ObservableList<String> getAllMonths() {
        return allMonths;
    }

    /**This method is passed a country object and filters a list of division objects by countryId.
     * It is used to narrow selections in the combo boxes to only the divisions matching the countryID.
     * @param country the country to filter by
     * @return matchingDivisions
     */
    public static ObservableList<FirstLevelDivision> filterDivisionByCountry(Country country) {
        if (country != null) {
            for (FirstLevelDivision division : allFirstLevelDivisions) {
                if(division.getCountryId() == country.getCountryId()) {
                    addMatchingDivision(division);
                }
            }
        }
        return matchingDivisions;
    }

    /**This method is passed a start LocalTime object and filters a list of end LocalTime objects.
     * It is used to narrow selections in the combo boxes for end times that only occur after the selected start time.
     * @param start the start time to filter by
     * @return availableEndTimes
     */
    public static ObservableList<LocalTime> findAvailableEndTimes(LocalTime start) {
        for(LocalTime time : allEndTimes) {
            if(time.isAfter(start)) {
                addAvailableEndTime(time);
            }
        }
        return availableEndTimes;
    }

    /**
     * This method is used to build the list of LocalTime objects for the allStartTimes ObservableList.
     * It builds this list based off of a list of ZonedDateTime objects tied to EST for the business location
     * to handle time conversions within the application.
     */
    public static void buildStartTimeList() {
        LocalDate  ld = LocalDate.now();
        ZonedDateTime start = (LocalDateTime.of(ld.getYear(),ld.getMonth(),ld.getDayOfMonth(),8,0)).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime end = (LocalDateTime.of(ld.getYear(),ld.getMonth(),ld.getDayOfMonth(),21,45)).atZone(ZoneId.of("America/New_York"));
        ObservableList<ZonedDateTime> zdtStartTimes = FXCollections.observableArrayList();
        while(start.isBefore(end.plusMinutes(1))) {
            zdtStartTimes.add(start);
            start = start.plusMinutes(15);
        }
        for(ZonedDateTime zdt : zdtStartTimes) {
            ZonedDateTime estToLtz = zdt.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            LocalTime lt = estToLtz.toLocalTime();
            allStartTimes.add(lt);
        }
    }

    /**
     * This method is used to build the list of LocalTime objects for the allEndTimes ObservableList.
     * It builds this list based off of a list of ZonedDateTime objects tied to EST for the business location
     * to handle time conversions within the application.
     */
    public static void buildEndTimeList() {
        LocalDate  ld = LocalDate.now();
        ZonedDateTime start = (LocalDateTime.of(ld.getYear(),ld.getMonth(),ld.getDayOfMonth(),8,15)).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime end = (LocalDateTime.of(ld.getYear(),ld.getMonth(),ld.getDayOfMonth(),22,00)).atZone(ZoneId.of("America/New_York"));
        ObservableList<ZonedDateTime> zdtEndTimes = FXCollections.observableArrayList();
        while(start.isBefore(end.plusMinutes(1))) {
            zdtEndTimes.add(start);
            start = start.plusMinutes(15);
        }
        for(ZonedDateTime zdt : zdtEndTimes) {
            ZonedDateTime estToLtz = zdt.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            LocalTime lt = estToLtz.toLocalTime();
            allEndTimes.add(lt);
        }
    }


    /**This method is passed LocalDate and LocalTime parameters and converts them into a timestamp equivalent in UTC.
     * @param ld is the LocalDate
     * @param lt is the LocalTime
     * @return the UTC Timestamp
     */
    public static Timestamp locDateAndTimeToUtcTimestamp(LocalDate ld, LocalTime lt) {
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime zdtOfUtc = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtOfUtc = zdtOfUtc.toLocalDateTime();
        Timestamp ts = Timestamp.valueOf(ldtOfUtc);
        return ts;
    }

    /**This method is passed a Timestamp parameter in UTC and converts it to a LocalDateTime of the user's timezone.
     * @param ts is the Timestamp to convert
     * @return the LocalDateTime equivalent in the user's timezone
     */
    public static LocalDateTime timestampUtcToLDT(Timestamp ts) {
        LocalDateTime ldtOfUtc = ts.toLocalDateTime();
        ZonedDateTime zdtOfUtc = ldtOfUtc.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOfLdt = zdtOfUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldt = zdtOfLdt.toLocalDateTime();
        return ldt;
    }

}
