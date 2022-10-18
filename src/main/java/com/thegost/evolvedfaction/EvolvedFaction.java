package com.thegost.evolvedfaction;

import com.thegost.evolvedfaction.player.PlayerChatListener;
import com.thegost.evolvedfaction.player.PlayerJoinListener;
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

        dbManager = new DBManager();
        playerGrade = new HashMap<>();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
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