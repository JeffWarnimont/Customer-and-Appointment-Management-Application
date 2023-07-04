# Customer and Appointment Management Application

Appointment scheduling application written in Java utilizing IntelliJ IDEA and MySQL Workbench.

Title and Purpose:
This is a customer management and scheduling application used to view, add, delete, and modify customer records and appointments.
This application works in tandem with a MySQL database and reads data from and writes data to the database via the JDBC connector
and SQL prepared statements.  All appointment times are saved to the database in UTC and are displayed in the application in the 
user's local time based off of their device default timezone setting. All text on the login screen will display in either English 
or French depending on the user's device default locale setting.  The GUI was created using JavaFX and the design structure follows 
the Model-View-Controller method.

Created by:
Jefferson Warnimont
jwarni3@wgu.edu
version 1.0.1
4-19-2022

Created using:
IntelliJ IDEA Community 2021.2.1
Java-JDK-17.0.2
JavaFX-SDK-17.0.2
mysql-connector-java-8.0.25

Directions for use:

LOGIN SCREEN
When the application starts, the user will be presented with a login screen with the user's time zone displayed. Enter
appropriate login credentials in the username and password text fields and click the Login button.  If credentials match
user data stored in the database the user will be sent to the main menu and the user will be alerted to whether any
appointments are scheduled to begin in the next fifteen minutes, or if credentials do not match, an error dialog
will appear.  All login attempts whether successful or not will be recorded in an activity log and saved in a .txt file.

MAIN MENU SCREEN
The main menu contains six buttons. This screen is the central hub for the application. All writes to the database will
return the user to this menu as it refreshes the application data to ensure data integrity between the database and what
is viewed in the application.
1. View Customer Records -- Navigates to the customers screen.
2. View Appointments -- Navigates to the appointments screen.
3. View Customers By Country -- Navigates to the customers by country report screen.
4. View Customer Appt Totals -- Navigates to the customer appointment totals report screen.
5. View Contact Schedules -- Navigates to the contact schedules report screen.
6. Close Program -- Exits the program.

CUSTOMERS SCREEN
The customers screen contains two table views. The top table view can be used to view and sort customer information and
the bottom table can be used to view and sort appointment information for a customer selected from the top table. When a
row is clicked in the upper table of customers, all appointments associated with the selected customer automatically
appear in the lower appointments table.

Below each table are corresponding buttons labeled Add, Modify, and Delete and in the bottom right corner of the screen
is an Exit button for seven total buttons.
1. The Add button below customers will navigate to the add customer screen.
2. The Add button below appointments will navigate to the add appointment screen.
3. The Modify button below customers requires a selection from the customers table and will navigate to the modify
customer screen. The customer data from the table will be transferred into the fields on the modify screen.
4. The Modify button below appointments requires a selection from the appointments table and will navigate to the modify
appointment screen. The appointment data from the table will be transferred into the fields on the modify screen.
5. The Delete button below customers will delete a selected customer from the database and return the user to the main
menu.  If the selected customer has associated appointments an error message will notify the user and appointments will
require deletion before the customer can be removed from the database.
6. The Delete button below appointments will delete a selected appointment from the database and return the user to the
main menu.
7. The Exit button returns the user to the main menu screen.

ADD CUSTOMER SCREEN
The add customer screen consists of multiple text fields and two combo boxes as well as two buttons labeled Save and
Cancel.  The Country selection must be made before the State/Province selection as the first selection generates the
data list used for the second selection.

The Save button will insert a new customer record into the database and then navigate to the main menu.  All fields are
required for this operation. If any fields are blank the user will be notified via dialog box.
The Cancel button returns the user to the main menu.

MODIFY CUSTOMER SCREEN
The modify customer screen consists of multiple text fields and two combo boxes as well as two buttons labeled Save and
Cancel.  The fields will be filled in with the corresponding data from the selection made on the previous screen.  If
the Country selection is changed, the State/Province selection will be deselected and the combo box will be updated with
the relevant list of available selections.

The Save button will update the existing customer record in the database and then navigate to the main menu.  All fields
are required for this operation. If any fields are blank the user will be notified via dialog box.
The Cancel button returns the user to the main menu.

ADD APPOINTMENT SCREEN
The add appointment screen consists of multiple text fields and combo boxes as well as two buttons labeled Save and
Cancel.  The start time selection must be made before the end time selection as the first selection generates the
data list used for the second selection.

The Save button will insert a new customer record into the database and then navigate to the main menu.  All fields are
required for this operation and the Customer ID and User ID fields must contain existing valid IDs. If any fields are
blank or one of the IDs do not exist, the user will be notified via dialog box.
The Cancel button returns the user to the main menu.

MODIFY APPOINTMENT SCREEN
The modify appointment screen consists of multiple text fields and combo boxes as well as two buttons labeled Save and
Cancel.  The fields will be filled in with the corresponding data from the selection made on the previous screen.  If
the appointment time is being updated the start time selection must be made before the end time selection as the first
selection generates the data list used for the second selection.

The Save button will update the existing customer record in the database and then navigate to the main menu.  All fields
are required for this operation and the customer ID and user ID fields must contain existing valid IDs. If any fields
are blank or one of the IDs do not exist, the user will be notified via dialog box.
The Cancel button returns the user to the main menu.

APPOINTMENTS SCREEN
The appointments screen contains a table view of appointments that can be set with radio buttons to display either all
appointments, current week appointments, or current month appointments.  Appointments can be viewed and sorted in this
table.

Four buttons labeled Add, Modify, Delete, and Exit exist below the table.
1. The Add button will navigate to the add appointment screen.
2. The Modify button below requires a selection from the table and will navigate to the modify appointment screen. The
appointment data from the table will be transferred into the fields on the modify screen.
3. The Delete button below will delete a selected appointment from the database and return the user to the main menu.
4. The Exit button returns the user to the main menu screen.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CUSTOMERS BY COUNTRY REPORT SCREEN
The customers by country report screen displays a pie chart of customers.  Each slice represents a country and labels
display the country name and quantity of customers.  When customer entries are added, modified, or deleted this chart
will automatically update to match the customer records in the database.  With few customers it isn't very impressive,
but with a vast and widespread customer base, this feature could give a good view of where resources would be best
deployed to meet customer and market demands.
The only button on this screen is an Exit button which will take the user back to the main menu.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CUSTOMER APPOINTMENT TOTALS REPORT SCREEN
The customer appointment totals report screen contains three combo boxes and two buttons labeled Run Report and Exit.
Each of the three combo boxes contains a list of selections. These are Customer, Meeting Type, and Month.  When the user
makes the selections and clicks the Run Report button A dialog box displays the number of appointments meeting the three
conditions.
The Exit button returns the user to the main menu.

CONTACT SCHEDULES REPORT SCREEN
The contact schedule report screen contains a table view of appointments that can be viewed and sorted and can be set
with radio buttons to display the appointments associated with each of the three contacts.
The only button on this screen is an Exit button which will take the user back to the main menu.
