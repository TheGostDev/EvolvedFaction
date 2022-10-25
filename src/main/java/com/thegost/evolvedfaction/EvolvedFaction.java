package com.thegost.evolvedfaction;

import com.thegost.evolvedfaction.commands.GradeCMD;
import com.thegost.evolvedfaction.database.DBManager;
import com.thegost.evolvedfaction.player.PlayerChatListener;
import com.thegost.evolvedfaction.player.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class EvolvedFaction extends JavaPlugin {


    public static EvolvedFaction instance;

    private DBManager dbManager;
    private HashMap<UUID, String> playerGrade;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§aEvolvedFaction > Turned ON");

        instance = this;
        registerEvents();
        registerCommands();

        dbManager = new DBManager();
        playerGrade = new HashMap<>();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerChatListener(this), this);
    }

    private void registerCommands() {
        getCommand("grade").setExecutor(new GradeCMD(this));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§cEvolvedFaction > Turned OFF");
        this.dbManager.close();
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public HashMap<UUID, String> getPlayerGrade() {
        return playerGrade;
    }

    private EvolvedFaction getInstance() {
        return instance;
    }
}