package com.thegost.evolvedfaction.player;

import com.thegost.evolvedfaction.EvolvedFaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class PlayerChatListener implements Listener {

    private EvolvedFaction evolvedFaction;

    public PlayerChatListener(EvolvedFaction evolvedFaction) {
        this.evolvedFaction = evolvedFaction;
    }

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();

        if(evolvedFaction.getPlayerGrade().containsKey(uuid)) {
            final String grade = evolvedFaction.getPlayerGrade().get(uuid);

            event.setFormat("[" + grade + "] - " + player.getDisplayName() + " > " + event.getMessage());
        }
    }
}
