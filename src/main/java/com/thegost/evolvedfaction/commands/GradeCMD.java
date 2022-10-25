package com.thegost.evolvedfaction.commands;

import com.thegost.evolvedfaction.EvolvedFaction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GradeCMD implements CommandExecutor {

    private EvolvedFaction evolvedFaction;
    public GradeCMD(EvolvedFaction evolvedFaction) {
        this.evolvedFaction = evolvedFaction;
    }

    String errorCMD = "§aPlease use this command with args : /g (add/remove/set) [grade_name/player_name] [grade_name]";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou can't execute this command if you're not a player!");
            return true;
        }

        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("grade") && player.hasPermission("evolvedfaction.grade.manage")) {
            if(args.length == 0) {
                player.sendMessage(errorCMD);
                return true;
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("add")) {
                    player.sendMessage(errorCMD);
                    return true;
                }
            } else if(args.length > 1) {
                String grade_name = args[1];
                if(args[0].equalsIgnoreCase("add") && args[1].equalsIgnoreCase(grade_name)) {
                    evolvedFaction.getServer().getConsoleSender().sendMessage("[DEBUG LOG] grade_name = " + grade_name);
                    sender.sendMessage("[DEBUG LOG] grade_name = " + grade_name);
                    return true;
                }
            }

        }
        return false;
    }
}
