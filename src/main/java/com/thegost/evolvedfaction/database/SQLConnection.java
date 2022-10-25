package com.thegost.evolvedfaction.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SQLConnection {
    private SQLInit sqlInit;
    private Connection connection;

    public SQLConnection(SQLInit sqlInit) {
        this.sqlInit = sqlInit;
        this.connect();
    }

    private void connect() {
        try {
                Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.sqlInit.toURI(), this.sqlInit.getUser(), this.sqlInit.getPass());

            Logger.getLogger("Minecraft").info("Succesfully connected to MySQL.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws SQLException {
        if(this.connection != null) {
            if(!this.connection.isClosed()) {
                this.connection.close();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        if(this.connection != null) {
            if(!this.connection.isClosed()) {
                return this.connection;
            }
        }

        connect();
        return this.connection;
    }
}
