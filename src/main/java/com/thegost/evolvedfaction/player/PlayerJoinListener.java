package com.thegost.evolvedfaction.player;

import com.thegost.evolvedfaction.EvolvedFaction;
import com.thegost.evolvedfaction.SQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private EvolvedFaction evolvedFaction;

    public PlayerJoinListener(EvolvedFaction evolvedFaction) {
        this.evolvedFaction = evolvedFaction;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        final SQLConnection gradeConnection = evolvedFaction.getDbManager().getGradeConnection();

        Bukkit.getScheduler().runTaskAsynchronously(evolvedFaction, () -> {
            try {
                final Connection connection = gradeConnection.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, grade FROM player_grade WHERE uuid = ?");

                preparedStatement.setString(1, uuid.toString());

                final ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                    final String grade = resultSet.getString("grade");

                    evolvedFaction.getPlayerGrade().put(uuid, grade);
                } else {
                    createUserGrade(connection, uuid);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createUserGrade(Connection connection, UUID uuid) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player_grade VALUES (?, ?, ?, ?)");
            final long time = System.currentTimeMillis();

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, "Vagabond");
            preparedStatement.setTimestamp(3, new Timestamp(time));
            preparedStatement.setTimestamp(4, new Timestamp(time));
            preparedStatement.executeUpdate();

            evolvedFaction.getPlayerGrade().put(uuid, "Vagabond");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
