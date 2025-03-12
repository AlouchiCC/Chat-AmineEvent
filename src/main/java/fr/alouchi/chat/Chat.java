package fr.alouchi.chat;

import fr.alouchi.chat.command.CommandMe;
import fr.alouchi.chat.command.CommandNarration;
import fr.alouchi.chat.command.CommandRoll;
import fr.alouchi.chat.command.SpyCommand;
import fr.alouchi.chat.event.PlayerChat;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chat extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("§cChat - Amine by Alouchi");

        getCommand("spycommand").setExecutor(new SpyCommand());
        getCommand("roll").setExecutor(new CommandRoll());
        getCommand("narration").setExecutor(new CommandNarration());
        getCommand("me").setExecutor(new CommandMe());

        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("§cChat - Amine by Alouchi");
    }
}