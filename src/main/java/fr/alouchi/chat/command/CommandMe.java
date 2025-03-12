package fr.alouchi.chat.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandMe implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /me <message>");
            return true;
        }

        String message = String.join(" ", args);
        Location playerLocation = player.getLocation();
        for (Player nearbyPlayer : player.getWorld().getPlayers()) {
            if (nearbyPlayer.getLocation().distance(playerLocation) <= 15) {
                nearbyPlayer.sendMessage("§7[§fRP§7] §8*" + player.getDisplayName() + " " + message);
            }
        }
        return false;
    }
}
