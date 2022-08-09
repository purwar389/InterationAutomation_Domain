import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author gauravpurwar
 * Class is to create reusable methods to be used in the runnner class in test directory
 *
 */
public class RunnerFD {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dbo";
    static final String USER = "newuser";
    static final String PASS = "password";

    static Connection conn;
    static Statement stmt;


    public RunnerFD() throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
    }



/*
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        Timestamp t = Timestamp.valueOf("0001-01-01 00:00:00.0");

             //createDatabase();
        insertVal("urgent","need a flat in America","abcd324@mail.com","2022-08-07",t);

        String query = "SELECT * FROM ContactUsRequests";
        DBTablePrinter.printTable(conn, "ContactUsRequests");

        ResultSet rs = stmt.executeQuery(query);

        boolean flag = false;
            while(rs.next()){
                //Display values
                System.out.print("type: " + rs.getString("type"));
                System.out.print(" message: " + rs.getString("message"));
                System.out.print(", emailaddress: " + rs.getString("emailaddress"));
                System.out.print(", createddate: " + rs.getString("createddate"));
                System.out.println(", processeddate: " + rs.getString("processeddate"));

                if(rs.getString("processeddate").equalsIgnoreCase(String.valueOf(t))) {
                    if (rs.getString("createddate").equalsIgnoreCase(getYesterdayDateString()) && rs.getString("type").equalsIgnoreCase("urgent")) {
                        createJiraTask(rs.getString("emailaddress"));
                        processed(rs.getString("emailaddress"), rs.getString("processeddate"));
                        flag =true;
                    } else {
                        sendEmail(rs.getString("emailaddress"));
                        processed(rs.getString("emailaddress"), rs.getString("processeddate"));
                        flag =true;
                    }
                }
            }

            if(!flag) System.out.println("No Records are pending to be processed!");
    }

    */


    public void setProcessed(String act,String email, Timestamp processedDate) throws SQLException {
        String setProcessed = "UPDATE ContactUsRequests SET processeddate = '" + processedDate + "' WHERE emailaddress = '" + email + "'";
        stmt.executeUpdate(setProcessed);
        System.out.println("User with "+email + " processed on " + processedDate+": Logged through "+act);
    }


    public void createDatabase(String dbName) throws SQLException {
        String sql = "CREATE DATABASE " + dbName + "";
        stmt.executeUpdate(sql);
        System.out.println("Database created successfully...");
    }

    public void insertVal(String type, String message, String email, String createdDate, Timestamp processedDate) throws SQLException {

        stmt.executeUpdate("INSERT INTO ContactUsRequests " + "VALUES ('" + type + "','" + message + "','" + email + "','" + createdDate + "','" + processedDate + "')");
        System.out.println("'" + type + "','" + message + "','" + email + "','" + createdDate + "','" + processedDate + ": Data Inserted successfully...");
    }

    public void createJiraTask(String emailAddress) {

   /* Commented out for JIRA authentication API
   try {
            JiraRestClient jrc = new JiraRestClient("https://jira.atlassian.com", "purwar389", "testing123");
            String issue = jrc.getIssue("PROJ-954");
            System.out.println(issue);
        } catch (Exception ex) {
            System.err.println(ex);
        }
      */
        System.out.println("JIRA Task created successfully for the user email Address " + emailAddress);
    }

    public void sendEmail(String from, String to, String subject, String text, String password) {

     // Commented out for sending emails , Google has stopped injecting emails from the 3rd party apps from 30 May 2022
        //   SendEmail.sendEmailToRecipient(from,to,subject,text,password);
        System.out.println("Email sent successfully for the user email Address " + to);
    }

    public String getYesterdayDateString() {

        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());

    }


}