import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class Runner {

    RunnerFD s = new RunnerFD();
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dbo";
    static final String USER = "newuser";
    static final String PASS = "password";

    static Connection conn;
    static Statement stmt;

    public Runner() throws SQLException {
    }

    /**
     * To Create Database
     * @throws SQLException
     */
    @Test
    public void createDataBase() throws SQLException {
       s.createDatabase("DBO");

    }

    /**
     * Example:  Scenario when creating the Requests from the Website that will create records in the database
     * @throws SQLException
     */
    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(){
            Timestamp t = Timestamp.valueOf("0001-01-01 00:00:00.0");
        return new Object[][] {

                {"urgent","Want an apartment in Melbourne","John@gmail.com","2022-08-01",t},//Email Scenario
                {"Urgent","Looking for a house in Australia","Charles@gmail.com","2022-08-08",t},//JIRA Scenario - Modify the date to Yesterday Date
                {"General","Looking for a rent house in Sydney","Peter@gmail.com","2022-08-07",t},//Email Scenario
                {"High","Want to sell house","Sam@gmail.com","2022-08-07",t},//Email Scenario
        };
    }

    @Test (dataProvider = "data-provider")
    public void createRecords(String type, String message, String email, String createdDate, Timestamp processedDate) throws SQLException {
        s.insertVal( type,  message,  email,  createdDate,  processedDate);
        System.out.println("'" + type + "','" + message + "','" + email + "','" + createdDate + "','" + processedDate + ": Data Inserted successfully...");

    }


    /**
     * To trigger when processing below requests sent from the website and uploaded in DB
     * 1. JIRA for the Type Urgent and Yesterday
     * 2. Email for Rest conditions
     * Note: Records those are processed shall not be reprocessed again
     * @throws SQLException
     */
    @Test
    public void execute() throws SQLException {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            Timestamp t = Timestamp.valueOf("0001-01-01 00:00:00");

            String query = "SELECT * FROM ContactUsRequests";
            DBTablePrinter.printTable(conn, "ContactUsRequests");

            ResultSet rs = stmt.executeQuery(query);

            boolean flag = false;
            while(rs.next()){
                //Display values
                System.out.println("type: " + rs.getString("type"));
                System.out.println(" message: " + rs.getString("message"));
                System.out.println(", emailaddress: " + rs.getString("emailaddress"));
                System.out.println(", createddate: " + rs.getString("createddate"));
                System.out.println(", processeddate: " + rs.getTimestamp("processeddate"));

                if(rs.getTimestamp("processeddate").equals(t)) {
                    if (rs.getString("createddate").equalsIgnoreCase(s.getYesterdayDateString()) && rs.getString("type").equalsIgnoreCase("urgent")) {
                        s.createJiraTask(rs.getString("emailaddress"));
                        Reporter.log("JIRA Task created successfully for the user email Address " + rs.getString("emailaddress"));
                        s.setProcessed("JIRA" ,rs.getString("emailaddress"), new Timestamp(System.currentTimeMillis()));
                        Reporter.log("User with "+rs.getString("emailaddress") + " processed on " + new Timestamp(System.currentTimeMillis())+": Logged through "+"JIRA");
                        flag =true;
                    } else {
                        s.sendEmail("domaintest2908@gmail.com",rs.getString("emailaddress"),"Thanks for your query!","We receieved your enquery, we will get back to your shortly!","test2908");
                        Reporter.log("Email sent successfully for the user email Address " + rs.getString("emailaddress"));
                        s.setProcessed("Email" ,rs.getString("emailaddress"), new Timestamp(System.currentTimeMillis()));
                        Reporter.log("User with "+rs.getString("emailaddress") + " processed on " + new Timestamp(System.currentTimeMillis())+": Logged through "+"Email");
                        flag =true;
                    }
                }
            }

            if(!flag) Reporter.log("*******No Records are pending to process!*******");


    }
}
