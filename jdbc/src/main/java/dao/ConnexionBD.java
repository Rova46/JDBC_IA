package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {
	private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String dbname;
    
    public ConnexionBD() {
    	this.con();
    }
    
    public ConnexionBD(String db) throws Exception {
    	this.dbname = db;
    	this.con();
    }
    
    private void con(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.setConnection(
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.dbname+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", ""));
            
            this.setStatement(
                    this.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));
            System.out.println("Database Connected!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void clear() throws Exception {
        statement.close();
        connection.close();
        System.out.println("Database Disconnected!");
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the statement
     */
    public Statement getStatement() {
        return this.statement;
    }

    /**
     * @param statement the statement to set
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * @return the resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @param resultSet the resultSet to set
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
