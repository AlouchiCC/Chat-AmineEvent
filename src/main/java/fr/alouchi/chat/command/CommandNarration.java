package fr.alouchi.chat.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandNarration implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /narration <all|r=numéro> <message>");
            return false;
        }

        String target = args[0];
        String message = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
        message = "§8[§cNarration§8] " + ChatColor.RED + message;

        if (target.equalsIgnoreCase("all")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(message);
            }
        } else if (target.startsWith("r=")) {
            try {
                int radius = Integer.parseInt(target.substring(2));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getLocation().distance(player.getLocation()) <= radius) {
                        p.sendMessage(message);
                    }
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§7[§fEvent§7] §cLe rayon doit être un nombre valide.");
            }
        } else {
            sender.sendMessage("§7[§fEvent§7] §cArgument invalide. Utilisez 'all' ou 'r=numéro'.");
        }
        return false;
    }
}