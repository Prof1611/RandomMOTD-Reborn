package com.ThunderKofy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class Commands implements CommandExecutor {
    private final RandomMOTD plugin;

    public Commands(RandomMOTD plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("randommotd")) {
            return true;
        }

        if (args.length == 0) {
            if (!sender.hasPermission("RandomMOTD.main")) return true;
            sender.sendMessage("§aRandomMOTD from ThunderKofy");
            sender.sendMessage("Version: §e" + plugin.getDescription().getVersion());
            sender.sendMessage("Originally developed by §ePhoenix852 | ThunderKofy");
            sender.sendMessage("Maintained by §eProf1611");
            sender.sendMessage("If you have any issues regarding this plugin, see: §b" + plugin.getDescription().getWebsite());
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> {
                if (!sender.hasPermission("RandomMOTD.reload")) return true;
                plugin.reloadConfig();
                sender.sendMessage("§a[RandomMOTD] Config reloaded!");
                return true;
            }
            case "motd" -> {
                if (!sender.hasPermission("RandomMOTD.motd")) return true;
                sender.sendMessage("§bIn-game MOTDs: §f" + plugin.getConfig().getBoolean("in-game.enabled"));
                for (String motd : plugin.getConfig().getStringList("in-game.motd")) {
                    sender.sendMessage(" - " + motd);
                }
                sender.sendMessage("§bSystem MOTDs: §f" + plugin.getConfig().getBoolean("system.enabled"));
                sender.sendMessage(" Normal:");
                for (String motd : plugin.getConfig().getStringList("system.motd.normal")) {
                    sender.sendMessage(" - " + motd);
                }
                sender.sendMessage(" Whitelist:");
                for (String motd : plugin.getConfig().getStringList("system.motd.whitelist")) {
                    sender.sendMessage(" - " + motd);
                }
                return true;
            }
            case "help" -> {
                if (!sender.hasPermission("RandomMOTD.help")) return true;
                sender.sendMessage("§a/RandomMOTD§f --> Shows the plugin description.");
                sender.sendMessage("§a/RandomMOTD help§f --> Shows this help page.");
                sender.sendMessage("§a/RandomMOTD reload§f --> Reloads RandomMOTD configuration.");
                sender.sendMessage("§a/RandomMOTD motd§f --> Shows the MOTDs being used by the server.");
                sender.sendMessage("§a/RandomMOTD author§f --> Shows the original author.");
                return true;
            }
            case "author" -> {
                if (!sender.hasPermission("RandomMOTD.author")) return true;
                sender.sendMessage("Originally developed by §ePhoenix852 | ThunderKofy");
                sender.sendMessage("Maintained by §eProf1611");
                return true;
            }
            default -> {
                sender.sendMessage("§c[ERROR] Unknown command!");
                sender.sendMessage("To see the available commands, use §e/RandomMOTD help§f.");
                return true;
            }
        }
    }
}
