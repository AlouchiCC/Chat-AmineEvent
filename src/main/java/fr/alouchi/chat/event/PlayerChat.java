package fr.alouchi.chat.event;

import fr.alouchi.chat.command.SpyCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        Player ignoredPlayer = Bukkit.getPlayer("AmineLeJoueurRP");

        if (ignoredPlayer != null && ignoredPlayer.isOnline()) {
            event.getRecipients().remove(ignoredPlayer);
        }

        event.setFormat(ChatColor.GREEN + "§7[§fHRP§7] " + ChatColor.RESET + "%s: %s");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = Bukkit.getPlayer("AmineLeJoueurRP");
        if (player != null && player.isOnline()) {
            event.setJoinMessage(null);
        }

        for(Player all : Bukkit.getOnlinePlayers()) {
            if (all != player) {
                all.sendMessage("§7[§a+§7] " + event.getPlayer());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = Bukkit.getPlayer("AmineLeJoueurRP");
        if (player != null && player.isOnline()) {
            event.setQuitMessage(null);
        }

        for(Player all : Bukkit.getOnlinePlayers()) {
            if (all != player) {
                all.sendMessage("§7[§c-§7] " + event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player sender = event.getPlayer();
        String command = event.getMessage();
        for (Player op : SpyCommand.spyPlayers) {
            op.sendMessage("§f[§dSPY§f] §d" + sender.getName() + "§f " + command);
        }
    }
}