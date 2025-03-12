package fr.alouchi.chat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SpyCommand implements CommandExecutor {

    public static Set<Player> spyPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spycommand")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Cette commande est réservée aux joueurs.");
                return false;
            }

            Player player = (Player) sender;
            if (spyPlayers.contains(player)) {
                spyPlayers.remove(player);
                player.sendMessage("§7[§fEvent§7] §cVous ne voyez plus les commandes exécutées.");
            } else {
                spyPlayers.add(player);
                player.sendMessage("§7[§fEvent§7] §aVous voyez maintenant toutes les commandes exécutées.");
            }
        }
        return false;
    }
}