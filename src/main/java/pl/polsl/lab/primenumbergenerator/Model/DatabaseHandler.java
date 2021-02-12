package pl.polsl.lab.primenumbergenerator.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Database handler.
 */
public class DatabaseHandler {
    /**
     * The Jdbc driver.
     */
    static final String JDBC_DRIVER = "org.h2.Driver";
    /**
     * The Db url.
     */
    static final String DB_URL = "jdbc:h2:~/primeNumbers";

    /**
     * The User.
     */
    static final String USER = "";
    /**
     * The Pass.
     */
    static final String PASS = "";


    /**
     * Inserting into table INFORMATION a row and getting his id.
     *
     * @param IP   the ip
     * @param Date the date
     * @return the integer
     */
    public Integer getLastId(String IP,String Date){
        Connection connection = null;
        Statement statement = null;
        Integer ID;
        try {
            Class.forName(JDBC_DRIVER); // Register JDBC driver

            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open a connection

            statement = connection.createStatement(); // Execute a query

            statement.executeUpdate("INSERT INTO INFORMATION(IP, DATE) VALUES (" // Adding a row to information table
                    + "'" + IP + "', "
                    + "'" + Date + "'"
                    + ")");

            ResultSet result = statement.executeQuery("SELECT TOP 1 ID FROM INFORMATION ORDER BY ID DESC");
            // Selecting the last generated ID for future use
            if (result.next()){
                ID = Math.toIntExact(result.getInt("ID"));
            }
            else {
                ID=0;
            }
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace(); //Handle errors for JDBC
            ID=0;
        }

        finally { //Handle errors for Class.forName

            try {    // Block used to close resources
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException ignored) { } // Nothing should be done

            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ID;
    }

    /**
     * Inserting into table GENERATION a row and setting the link between two tables
     *
     * @param lastID the last id
     * @param model  the model
     */
    public void insertGenerationResult (Integer lastID,ConsoleModel model) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER); // Register JDBC driver

            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open a connection

            statement = connection.createStatement(); // Execute a query

            statement.executeUpdate( // Insert into a new row with all new generation details
                    "INSERT INTO GENERATION(INFORMATIONID, INPUT, AMOUNT, THEHIGHEST, SUM, MEDIAN) VALUES (" +
                    + lastID
                    + ", " + model.getInputNumber()
                    + ", " + model.getQuantity()
                    + ", " + model.getTheHighestSpacingValue()
                    + ", " + model.getNumberSum()
                    + ", " + model.getMedian()
                            + ")");

            connection.close(); // Clean-up environment

            }
        catch (Exception e) {
            e.printStackTrace(); //Handle errors for JDBC
        }

        finally { //Handle errors for Class.forName

            try {    // Block used to close resources
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException ignored) { } // Nothing should be done

            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Show history of generations by GENERATION table and INFORMATION table.
     *
     * @return the string
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     */
    public String showHistory() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            Class.forName(JDBC_DRIVER); // Register JDBC driver

            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open a connection

            statement = connection.createStatement(); // Execute a query

            ResultSet result = statement.executeQuery("SELECT * FROM GENERATION"); // Getting all past generations

            List<GenerationSQL> list = new ArrayList<>(); // List to store them

            if (result!=null){
                while (result.next()){
                    GenerationSQL generation = new GenerationSQL( // Filling the list of generations
                            result.getInt("ID"),
                            result.getInt( "INFORMATIONID"),
                            result.getInt("INPUT"),
                            result.getInt("AMOUNT"),
                            result.getInt("THEHIGHEST"),
                            result.getInt("SUM"),
                            result.getInt("MEDIAN")
                            );
                    list.add(generation);
                }
            }
            for(GenerationSQL generation : list){ // Showing all details of each generation

                stringBuilder.append("  GENERATION ID: " + generation.getID());
                stringBuilder.append("<br>");
                stringBuilder.append("  Start number: " + generation.getInput());
                stringBuilder.append("  Amount of generated numbers : " + generation.getAmount());
                stringBuilder.append("  The highest spacing value: " + generation.getTheHighest());
                stringBuilder.append("  Sum : " + generation.getSum());
                stringBuilder.append("  Median: " + generation.getMedian());
                stringBuilder.append("<br>");

                if (generation.getInformationID() !=0) { // Getting into second table to get details about the ip and time of generation

                    ResultSet resultOfInformation = statement.executeQuery("SELECT * FROM INFORMATION WHERE ID = "
                            + generation.getInformationID());

                    if (resultOfInformation.next()){
                        stringBuilder.append("  IP: " + resultOfInformation.getString("IP"));
                        stringBuilder.append("  Date of generation: " + resultOfInformation.getString("DATE"));
                    }
                    else {
                        stringBuilder.append("  No data available !");
                    }
                }
                else {
                    stringBuilder.append("  No data available !");
                }

                stringBuilder.append("<br>");
            }

            connection.close(); // Clean-up environment
            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace(); //Handle errors for JDBC
            stringBuilder.append(e.getCause());
        }

        finally { //Handle errors for Class.forName

            try {    // Block used to close resources
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException ignored) { } // Nothing should be done

            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
