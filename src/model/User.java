package model;

/** The User class contains fields, a constructor, and getter methods for accessing User objects.
 * User objects contain three fields (userId, username, password). This class exists for the sole purpose of populating
 * an observable list to match the username-password pairs from the database.
 */
public class User {
    private int userId;
    private String username;
    private String password;

    /**
     * @param userId is the unique user associated ID
     * @param username is the user associated username
     * @param password is the user associated password
     */
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the userId
     */
    public int getUserId() { return userId;}

    /**
     * @return the username
     */
    public String getUserName() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
