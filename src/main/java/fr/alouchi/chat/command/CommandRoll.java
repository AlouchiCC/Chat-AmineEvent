package fr.alouchi.chat.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class CommandRoll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("roll")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Random random = new Random();
                int roll = random.nextInt(100) + 1;

                ChatColor color;

                if(roll < 50) {
                    color = ChatColor.RED;
                } else {
                    color = ChatColor.GREEN;
                }

                Location playerLocation = player.getLocation();
                for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                    if (nearbyPlayer.getLocation().distance(playerLocation) <= 15) {
                        nearbyPlayer.sendMessage("§7[§fRoll§7] §8" + player.getDisplayName() + " §fa obtenu : " + color + roll + "");
                    }
                }
                return false;
            } else {
                sender.sendMessage("Seuls les joueurs peuvent utiliser cette commande.");
                return false;
            }
        }
        return false;
    }
}
