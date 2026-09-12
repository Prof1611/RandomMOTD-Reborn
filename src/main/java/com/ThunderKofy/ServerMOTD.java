package com.ThunderKofy;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;

public final class ServerMOTD implements Listener {
    private final RandomMOTD plugin;

    public ServerMOTD(RandomMOTD plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        if (!plugin.getConfig().getBoolean("system.enabled")) {
            return;
        }

        String path = plugin.getServer().hasWhitelist()
                ? "system.motd.whitelist"
                : "system.motd.normal";

        List<String> motds = plugin.getConfig().getStringList(path);
        String motd = MOTDUtil.randomEntry(motds);
        if (motd == null) {
            return;
        }

        String rendered = motd.replace("%VERSION%", Bukkit.getMinecraftVersion());
        event.setMotd(MOTDUtil.legacyText(rendered));
    }
}
