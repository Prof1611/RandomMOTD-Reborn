package com.ThunderKofy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public final class InGameMOTD implements Listener {
    private final RandomMOTD plugin;

    public InGameMOTD(RandomMOTD plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!plugin.getConfig().getBoolean("in-game.enabled")) {
            return;
        }

        List<String> motds = plugin.getConfig().getStringList("in-game.motd");
        String motd = MOTDUtil.randomEntry(motds);
        if (motd == null) {
            return;
        }

        Player player = event.getPlayer();
        String rendered = motd
                .replace("%PLAYER%", player.getName())
                .replace("%VERSION%", Bukkit.getMinecraftVersion());
        player.sendMessage(MOTDUtil.legacyText(rendered));
    }
}
