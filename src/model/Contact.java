package model;

/** The Contact class contains fields, a constructor, and getter methods for accessing Contact objects.
 * Contact objects contain three fields (contactId, contactName, email). This class exists to match Contact objects in the
 * application to contacts saved in the database. Contact objects are associated with Appointment objects and are used to
 * populate observable lists and combo boxes in the application.
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;

    /** This Contact object constructor has three required parameters.
     * @param contactId is the unique contact identifier
     * @param contactName is the contact name
     * @param email is the contact email
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * This method overrides the toString() method of the Contact class for display purposes.
     * When placed in the combo boxes a Contact object will be displayed as a string of the contactName.
     */
    @Override
    public String toString() {
        return(contactName);
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

}
