package com.thegost.evolvedfaction.database;

import java.sql.SQLException;

public class DBManager {
    private SQLConnection gradeConnection;

    public DBManager() {
        this.gradeConnection = new SQLConnection(new SQLInit("localhost", "root", "huFt56Rp0", "evolvedfaction", 3306));
    }

    public SQLConnection getGradeConnection() {
        return gradeConnection;
    }

    public void close() {
        try {
            this.gradeConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
