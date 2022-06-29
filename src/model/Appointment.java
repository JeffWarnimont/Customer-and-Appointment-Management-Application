package model;

import java.time.LocalDateTime;

/** The Appointment class contains fields, constructors, and getter and setter methods for accessing Appointment objects.
 * The Appointment class has eleven fields (apptId, title, description, location, contact, type, start, end, custId, userId, contactId).
 * This class exists to match Appointment objects in the application to appointments saved in the database. Appointment objects
 * are used to populate observable lists and table views throughout the application.
 */
public class Appointment {
    private int apptId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int custId;
    private int userId;
    private int contactId;

    /**
     * @param apptId the unique appointment identifier
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param contact the appointment contact name
     * @param type the appointment type
     * @param start the appointment start date and time
     * @param end the appointment end date and time
     * @param custId the appointment associated customer ID
     * @param userId the appointment associated user ID
     */
    //constructor for appointment with fields matching table requirements
    public Appointment(int apptId, String title, String description, String location, String contact,
                       String type, LocalDateTime start, LocalDateTime end, int custId, int userId) {
        this.apptId = apptId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
    }

    /**
     * @param apptId the unique appointment identifier
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start date and time
     * @param end the appointment end date and time
     * @param custId the appointment associated customer ID
     * @param userId the appointment associated user ID
     * @param contactId the appointment associated contact ID
     */
    //constructor for appointment with fields matching database
    public Appointment(int apptId, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, int custId, int userId, int contactId) {
        this.apptId = apptId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return the apptId
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * @param apptId the apptId to set
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the desription
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * @return the custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

}
