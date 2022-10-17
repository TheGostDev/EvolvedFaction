package com.thegost.evolvedfaction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class EvolvedFaction extends JavaPlugin {

    private Connection connection;
    public String host, database, username, password;
    public int port;

    @Override
    public void onEnable() {
        mysqlSetup();
        getServer().getConsoleSender().sendMessage("§aEvolvedFaction > Turned ON");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§cEvolvedFaction > Turned OFF");
    }

    public void mysqlSetup() {
        host = "localhost";
        port = 80;
        database = "evolvedfaction";
        username = "root";
        password = "";

        try{
            synchronized (this) {
                if(getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/"
                        + this.database, this.username, this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
