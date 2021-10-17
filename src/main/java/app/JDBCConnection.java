package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database. Allows SQL
 * queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies
 * Database This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/Milestone.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the Movies in the database
     */
    public ArrayList<String> getMovies() {
        ArrayList<String> movies = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM movie";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int id = results.getInt("mvnumb");
                String movieName = results.getString("mvtitle");
                int year = results.getInt("yrmde");
                String type = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                movies.add(movieName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return movies;
    }

    public ArrayList<String> getCountryList() {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String countryName = results.getString("CountryName");
                countries.add(countryName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<String> getStates(String countryName) {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Province_State FROM State WHERE Country_Region = '" + countryName + "'";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String stateName = results.getString("Province_State");
                countries.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public int countTotalCases(String countryName) {
        int cases = 0;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement
                    .executeQuery("SELECT CasesTotal FROM Country WHERE CountryName = '" + countryName + "'");

            results.next();

            cases = results.getInt("CasesTotal");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return cases;
    }

    public int countCountryLastWeek(String countryName) {
        int cases = 0;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement
                    .executeQuery("SELECT CasesLastWeek FROM Country WHERE CountryName = '" + countryName + "'");

            results.next();

            cases = results.getInt("CasesLastWeek");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return cases;
    }

    public int countCountryHighest(String countryName) {
        int cases = 0;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement
                    .executeQuery("SELECT HighestCases FROM Country WHERE CountryName = '" + countryName + "'");

            results.next();

            cases = results.getInt("HighestCases");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return cases;
    }

    public ArrayList<Integer> countStateTotalCases(String countryName) {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM State WHERE Country_Region = '" + countryName
                    + "' AND Province_State != ''";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesTotal = results.getInt("CasesTotal");
                cases.add(casesTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countStateLastWeek(String countryName) {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesLastWeek FROM State WHERE Country_Region = '" + countryName
                    + "' AND Province_State != ''";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("CasesLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<String> getStatesByWorstToLeast(String name) {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Province_State FROM State WHERE Country_Region = '" + name
                    + "' ORDER BY CasesTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String stateName = results.getString("Province_State");
                countries.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Integer> countStateTotalCasesWorstToLeast(String countryName) {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM State WHERE Country_Region = '" + countryName
                    + "' AND Province_State != '' ORDER BY CasesTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesTotal = results.getInt("CasesTotal");
                cases.add(casesTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countStateLastWeekWorstToLeast(String countryName) {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesLastWeek FROM State WHERE Country_Region = '" + countryName
                    + "' AND Province_State != '' ORDER BY CasesTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("CasesLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countTotalCasesAllCountries() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesTotal = results.getInt("CasesTotal");
                cases.add(casesTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countTotalDeathsAllCountries() {
        ArrayList<Integer> deaths = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer deathsTotal = results.getInt("DeathsTotal");
                deaths.add(deathsTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<Integer> countCountryCasesLastWeekAll() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesLastWeek FROM Country";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("CasesLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countCountryDeathsLastWeekAll() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathLastWeek FROM Country";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("DeathLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<String> getCountryListWorstToLeast() {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country ORDER BY DeathsTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String countryName = results.getString("CountryName");
                countries.add(countryName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Integer> countTotalCasesAllCountriesWorstToLeast() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country ORDER BY DeathsTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesTotal = results.getInt("CasesTotal");
                cases.add(casesTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countTotalDeathsAllCountriesWorstToLeast() {
        ArrayList<Integer> deaths = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country ORDER BY DeathsTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer deathsTotal = results.getInt("DeathsTotal");
                deaths.add(deathsTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<Integer> countCountryCasesLastWeekAllWorstToLeast() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesLastWeek FROM Country ORDER BY DeathsTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("CasesLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countCountryDeathsLastWeekAllWorstToLeast() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathLastWeek FROM Country ORDER BY DeathsTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("DeathLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Double> countDIR() {
        ArrayList<Double> ratios = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double DeathToInf = results.getDouble("DIR");
                ratios.add(DeathToInf);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratios;
    }

    public ArrayList<Double> countDIRWorstToLeast() {
        ArrayList<Double> ratios = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country ORDER BY DeathsTotal DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double DeathToInf = results.getDouble("DIR");
                ratios.add(DeathToInf);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratios;
    }

    public ArrayList<String> getCountryListDIR() {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country ORDER BY DIR DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String countryName = results.getString("CountryName");
                countries.add(countryName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Integer> countTotalCasesAllCountriesDIR() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country ORDER BY DIR DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesTotal = results.getInt("CasesTotal");
                cases.add(casesTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countTotalDeathsAllCountriesDIR() {
        ArrayList<Integer> deaths = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country ORDER BY DIR DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer deathsTotal = results.getInt("DeathsTotal");
                deaths.add(deathsTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<Integer> countCountryCasesLastWeekAllDIR() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesLastWeek FROM Country ORDER BY DIR DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("CasesLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countCountryDeathsLastWeekAllDIR() {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathLastWeek FROM Country ORDER BY DIR DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesLastWeek = results.getInt("DeathLastWeek");
                cases.add(casesLastWeek);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Double> countDIRDIR() {
        ArrayList<Double> ratios = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country ORDER BY DIR DESC";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double DeathToInf = results.getDouble("DIR");
                ratios.add(DeathToInf);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratios;
    }

    public double countCountryLongitude(String countryName) {
        double cases = 0.000000000000;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement
                    .executeQuery("SELECT Longitude FROM Country WHERE CountryName = '" + countryName + "'");

            results.next();

            cases = results.getDouble("Longitude");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return cases;
    }

    public double countCountryLatitude(String countryName) {
        double cases = 0.000000000;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement
                    .executeQuery("SELECT Latitude FROM Country WHERE CountryName = '" + countryName + "'");

            results.next();

            cases = results.getDouble("Latitude");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return cases;
    }

    public int countCountryPop(String countryName) {
        int cases = 0;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement
                    .executeQuery("SELECT Population FROM Country WHERE CountryName = '" + countryName + "'");

            results.next();

            cases = results.getInt("Population");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return cases;
    }

    public String getCountryDescription(String countryName) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Description FROM Country WHERE CountryName = '" + countryName + "'";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            countries = results.getString("Description");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;

    }

    public ArrayList<String> getCountryClimateName(String countryName) {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String ClimateName = results.getString("CountryName");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Integer> getCountryClimateNamePop(String countryName) {
        ArrayList<Integer> countries = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Population FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer ClimateName = results.getInt("Population");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Integer> countTotalCasesAllCountriesClimate(String countryName) {
        ArrayList<Integer> cases = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer casesTotal = results.getInt("CasesTotal");
                cases.add(casesTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }

    public ArrayList<Integer> countTotalDeathsAllCountriesClimate(String countryName) {
        ArrayList<Integer> deaths = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Integer deathsTotal = results.getInt("DeathsTotal");
                deaths.add(deathsTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<Double> countClimateIR(String countryName) {
        ArrayList<Double> deaths = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND((SELECT ((CasesTotal * 1.0) / (Population))),7) AS Infection_Ratio FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double deathsTotal = results.getDouble("Infection_Ratio");
                deaths.add(deathsTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<Double> countClimateDR(String countryName) {
        ArrayList<Double> deaths = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND((SELECT ((DeathsTotal * 1.0) / (Population))),7) AS Death_Ratio FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double deathsTotal = results.getDouble("Death_Ratio");
                deaths.add(deathsTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<String> getCountryName1500(String countryName) {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE Latitude BETWEEN ((SELECT Latitude FROM Country WHERE CountryName = '"
                    + countryName + "') - 2) AND ((SELECT Latitude FROM Country WHERE CountryName = '" + countryName
                    + "') + 2) ORDER BY CountryName = '" + countryName + "' DESC LIMIT 5;";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String ClimateName = results.getString("CountryName");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public Double countIR(String countryName) {
        double deaths = 0.00;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND((SELECT ((CasesTotal * 1.0) / (Population))),7) AS Infection_Ratio FROM Country WHERE  CountryName = '"
                    + countryName + "' ";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            deaths = results.getDouble("Infection_Ratio");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public ArrayList<Double> getIDR7(String countryName) {
        ArrayList<Double> countries = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND(((CasesLastWeek * 1.0)/(DeathLastWeek)),7) AS IDR7 FROM COUNTRY WHERE CountryName = '"
                    + countryName + "'";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double ClimateName = results.getDouble("IDR7");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Double> getIDPR7(String countryName) {
        ArrayList<Double> countries = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND((((CasesLastWeek* 1.0) + DeathLastWeek)/Population),7) AS IDPR7 FROM COUNTRY WHERE CountryName = '"
                    + countryName + "'";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double ClimateName = results.getDouble("IDPR7");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Double> getIDRBeginning(String countryName) {
        ArrayList<Double> countries = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND(((CasesTotal * 1.0)/(DeathsTotal)),7) AS IDRBeginning FROM COUNTRY WHERE CountryName = '"
                    + countryName + "'";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double ClimateName = results.getDouble("IDRBeginning");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public ArrayList<Double> getIDPRBeginning(String countryName) {
        ArrayList<Double> countries = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT ROUND((((CasesTotal* 1.0) + DeathsTotal)/Population),7) AS IDPRBeginning FROM COUNTRY WHERE CountryName = '"
                    + countryName + "'";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                Double ClimateName = results.getDouble("IDPRBeginning");
                countries.add(ClimateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage5(String namee) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM Country WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM Country WHERE CountryName = '"
                    + namee + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage5Two(String CountryName) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM Country WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage5DIR(String namee) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE DIR = (SELECT MAX(DIR) FROM Country WHERE DIR < (SELECT MAX(DIR) FROM Country WHERE CountryName = '"
                    + namee + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage5TwoDIR(String CountryName) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE DIR = (SELECT MIN(DIR) FROM Country WHERE DIR > (SELECT MIN(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage5MAX(String namee) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE HighestCases = (SELECT MAX(HighestCases) FROM Country WHERE HighestCases < (SELECT MAX(HighestCases) FROM Country WHERE CountryName = '"
                    + namee + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage5TwoMAX(String CountryName) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE HighestCases = (SELECT MIN(HighestCases) FROM Country WHERE HighestCases > (SELECT MIN(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public int countInfectionsPage5(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MAX(CasesTotal) FROM Country WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("MAX(CasesTotal)");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countInfectionsPage5Main(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE CountryName = '" + CountryName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countInfectionsPage5Two(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MIN(CasesTotal) FROM Country WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("MIN(CasesTotal)");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countInfectionsPage5DIR(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE DIR = (SELECT MAX(DIR) FROM Country WHERE DIR < (SELECT MAX(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countInfectionsPage5TwoDIR(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE DIR = (SELECT MIN(DIR) FROM Country WHERE DIR > (SELECT MIN(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countInfectionsPage5MAX(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE HighestCases = (SELECT MAX(HighestCases) FROM Country WHERE HighestCases < (SELECT MAX(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countInfectionsPage5TwoMAX(String CountryName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE HighestCases = (SELECT MIN(HighestCases) FROM Country WHERE HighestCases > (SELECT MIN(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public double countDirPage5(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MAX(CasesTotal),DIR FROM Country WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public double countDirPage5Main(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE CountryName = '" + CountryName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public double countDirPage5Two(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MIN(CasesTotal),DIR FROM Country WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public double countDirPage5DIR(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE DIR = (SELECT MAX(DIR) FROM Country WHERE DIR < (SELECT MAX(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "')); ";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public double countDirPage5TwoDIR(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE DIR = (SELECT MIN(DIR) FROM Country WHERE DIR > (SELECT MIN(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public double countDirPage5MAX(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE HighestCases = (SELECT MAX(HighestCases) FROM Country WHERE HighestCases < (SELECT MAX(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public double countDirPage5TwoMAX(String CountryName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE HighestCases = (SELECT MIN(HighestCases) FROM Country WHERE HighestCases > (SELECT MIN(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double dir = results.getDouble("DIR");
            ratio = dir;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public int countMdiPage5(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MAX(CasesTotal),HighestCases FROM Country WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMdiPage5Main(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE CountryName = '" + CountryName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMdiPage5Two(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MIN(CasesTotal),HighestCases FROM Country WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMdiPage5DIR(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE DIR = (SELECT MAX(DIR) FROM Country WHERE DIR < (SELECT MAX(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMdiPage5TwoDIR(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE DIR = (SELECT MIN(DIR) FROM Country WHERE DIR > (SELECT MIN(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMdiPage5MAX(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE HighestCases = (SELECT MAX(HighestCases) FROM Country WHERE HighestCases < (SELECT MAX(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMdiPage5TwoMAX(String CountryName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE HighestCases = (SELECT MIN(HighestCases) FROM Country WHERE HighestCases > (SELECT MIN(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMddPage5(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MAX(CasesTotal),HighestCases,HighestDeaths FROM Country WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countMddPage5Main(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE CountryName = '" + CountryName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countMddPage5Two(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MIN(CasesTotal),HighestCases,HighestDeaths FROM Country WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countMddPage5DIR(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE DIR = (SELECT MAX(DIR) FROM Country WHERE DIR < (SELECT MAX(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countMddPage5TwoDIR(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE DIR = (SELECT MIN(DIR) FROM Country WHERE DIR > (SELECT MIN(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countMddPage5MAX(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE HighestCases = (SELECT MAX(HighestCases) FROM Country WHERE HighestCases < (SELECT MAX(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countMddPage5TwoMAX(String CountryName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE HighestCases = (SELECT MIN(HighestCases) FROM Country WHERE HighestCases > (SELECT MIN(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int deaths = results.getInt("HighestDeaths");
            mdd = deaths;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public int countDeathsPage5(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MAX(CasesTotal),DeathsTotal FROM Country WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public int countDeathsPage5Main(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE CountryName = '" + CountryName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public int countDeathsPage5Two(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName,MIN(CasesTotal),DeathsTotal FROM Country WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM Country WHERE CountryName = '"
                    + CountryName + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;

    }

    public int countDeathsPage5DIR(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE DIR = (SELECT MAX(DIR) FROM Country WHERE DIR < (SELECT MAX(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public int countDeathsPage5TwoDIR(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE DIR = (SELECT MIN(DIR) FROM Country WHERE DIR > (SELECT MIN(DIR) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;

    }

    public int countDeathsPage5MAX(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE HighestCases = (SELECT MAX(HighestCases) FROM Country WHERE HighestCases < (SELECT MAX(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public int countDeathsPage5TwoMAX(String CountryName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE HighestCases = (SELECT MIN(HighestCases) FROM Country WHERE HighestCases > (SELECT MIN(HighestCases) FROM Country WHERE CountryName = '"
                    + CountryName + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;

    }

    public int countInfectionsPage5MainStates(String StateName) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM State WHERE Province_State = '" + StateName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countDeathsPage5MainStates(String StateName) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM State WHERE Province_State = '" + StateName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public double countDirPage5MainStates(String StateName) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM State WHERE Province_State = '" + StateName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double cases = results.getDouble("DIR");
            ratio = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public int countMdiPage5MainStates(String StateName) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM State WHERE Province_State = '" + StateName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMddPage5MainStates(String StateName) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM States WHERE Province_State = '" + StateName + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestDeaths");
            mdd = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public String getCountryListPage5States(String state, String country) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Province_State FROM State WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("Province_State");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public int countInfectionsPage5States(String state, String country) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM State WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countDeathsPage5States(String state, String country) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM State WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public double countDirPage5States(String state, String country) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM State WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double cases = results.getDouble("DIR");
            ratio = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public int countMdiPage5States(String state, String country) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM State WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMddPage5States(String state, String country) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM State WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestDeaths");
            mdd = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public String getCountryListPage5TwoStates(String state, String country) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Province_State FROM State WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("Province_State");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public int countInfectionsPage5TwoStates(String state, String country) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM State WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "'); ";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countDeathsPage5TwoStates(String state, String country) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM State WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public double countDirPage5TwoStates(String state, String country) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM State WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double cases = results.getDouble("DIR");
            ratio = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public int countMdiPage5TwoStates(String state, String country) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM State WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMddPage5TwoStates(String state, String country) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM State WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "') AND Country_Region = '" + country + "');";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestDeaths");
            mdd = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public String getCountryNameFromState(String state) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Country_Region FROM State WHERE Province_State = '" + state + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("Country_Region");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public String getCountryListPage6States(String state) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public int countInfectionsPage6States(String state) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countDeathsPage6States(String state) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public double countDirPage6States(String state) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double cases = results.getDouble("DIR");
            ratio = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public int countMdiPage6States(String state) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMddPage6States(String state) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE CasesTotal = (SELECT MAX(CasesTotal) FROM State WHERE CasesTotal < (SELECT MAX(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestDeaths");
            mdd = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

    public String getCountryListPage6TwoStates(String state) {
        String countries = new String();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            countries = results.getString("CountryName");

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

    public int countInfectionsPage6TwoStates(String state) {
        int infections = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("CasesTotal");
            infections = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;
    }

    public int countDeathsPage6TwoStates(String state) {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("DeathsTotal");
            deaths = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }

    public double countDirPage6TwoStates(String state) {
        double ratio = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DIR FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            double cases = results.getDouble("DIR");
            ratio = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ratio;
    }

    public int countMdiPage6TwoStates(String state) {
        int mdi = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestCases FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestCases");
            mdi = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdi;
    }

    public int countMddPage6TwoStates(String state) {
        int mdd = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT HighestDeaths FROM Country WHERE CasesTotal = (SELECT MIN(CasesTotal) FROM State WHERE CasesTotal > (SELECT MIN(CasesTotal) FROM State WHERE Province_State = '"
                    + state + "'));";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            int cases = results.getInt("HighestDeaths");
            mdd = cases;

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return mdd;
    }

}
