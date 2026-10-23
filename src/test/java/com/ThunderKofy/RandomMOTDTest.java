package com.ThunderKofy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RandomMOTDTest {
    private final RandomMOTD plugin = mock(RandomMOTD.class);
    private final FileConfiguration config = mock(FileConfiguration.class);

    private void setup() {
        when(plugin.getConfig()).thenReturn(config);
    }

    @Test
    void normalServerMotdUsesColourAndVersion() {
        setup();
        when(config.getBoolean("system.enabled")).thenReturn(true);
        when(config.getStringList("system.motd.normal")).thenReturn(List.of("&aMinecraft %VERSION%"));
        var server = mock(org.bukkit.Server.class);
        when(server.hasWhitelist()).thenReturn(false);
        when(plugin.getServer()).thenReturn(server);
        var ping = mock(ServerListPingEvent.class);
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            bukkit.when(Bukkit::getMinecraftVersion).thenReturn("1.21");
            new ServerMOTD(plugin).onPing(ping);
        }
        verify(ping).setMotd("§aMinecraft 1.21");
    }

    @Test
    void whitelistSelectsSeparateMotd() {
        setup();
        when(config.getBoolean("system.enabled")).thenReturn(true);
        when(config.getStringList("system.motd.whitelist")).thenReturn(List.of("Maintenance"));
        var server = mock(org.bukkit.Server.class);
        when(server.hasWhitelist()).thenReturn(true);
        when(plugin.getServer()).thenReturn(server);
        var ping = mock(ServerListPingEvent.class);
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            bukkit.when(Bukkit::getMinecraftVersion).thenReturn("26.3");
            new ServerMOTD(plugin).onPing(ping);
        }
        verify(ping).setMotd("Maintenance");
        verify(config, never()).getStringList("system.motd.normal");
    }

    @Test
    void disabledServerMotdLeavesOriginalUntouched() {
        setup();
        var ping = mock(ServerListPingEvent.class);
        new ServerMOTD(plugin).onPing(ping);
        verify(ping, never()).setMotd(anyString());
    }

    @Test
    void emptyServerMotdLeavesOriginalUntouched() {
        setup();
        when(config.getBoolean("system.enabled")).thenReturn(true);
        when(config.getStringList("system.motd.normal")).thenReturn(List.of());
        var server = mock(org.bukkit.Server.class);
        when(plugin.getServer()).thenReturn(server);
        new ServerMOTD(plugin).onPing(mock(ServerListPingEvent.class));
    }

    @Test
    void joinMessageRendersPlayerVersionAndColour() {
        setup();
        when(config.getBoolean("in-game.enabled")).thenReturn(true);
        when(config.getStringList("in-game.motd")).thenReturn(List.of("&bHello %PLAYER% on %VERSION%"));
        var player = mock(Player.class);
        when(player.getName()).thenReturn("Alex");
        var join = mock(PlayerJoinEvent.class);
        when(join.getPlayer()).thenReturn(player);
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            bukkit.when(Bukkit::getMinecraftVersion).thenReturn("26.3");
            new InGameMOTD(plugin).onPlayerJoin(join);
        }
        verify(player).sendMessage("§bHello Alex on 26.3");
    }

    @Test
    void disabledJoinMessagesSendNothing() {
        setup();
        var player = mock(Player.class);
        var join = mock(PlayerJoinEvent.class);
        when(join.getPlayer()).thenReturn(player);
        new InGameMOTD(plugin).onPlayerJoin(join);
        verify(player, never()).sendMessage(anyString());
    }

    @Test
    void emptyJoinMessagesSendNothing() {
        setup();
        when(config.getBoolean("in-game.enabled")).thenReturn(true);
        var player = mock(Player.class);
        var join = mock(PlayerJoinEvent.class);
        when(join.getPlayer()).thenReturn(player);
        new InGameMOTD(plugin).onPlayerJoin(join);
        verify(player, never()).sendMessage(anyString());
    }

    @Test
    void randomSelectionStaysWithinConfiguredEntries() {
        var entries = List.of("one", "two", "three");
        for (int i = 0; i < 100; i++) {
            assertTrue(entries.contains(MOTDUtil.randomEntry(entries)));
        }
        assertNull(MOTDUtil.randomEntry(List.of()));
        assertNull(MOTDUtil.randomEntry(null));
    }

    @Test
    void reloadCommandReloadsConfig() {
        var sender = mock(CommandSender.class);
        var command = mock(Command.class);
        when(command.getName()).thenReturn("randommotd");
        when(sender.hasPermission("RandomMOTD.reload")).thenReturn(true);
        assertTrue(new Commands(plugin).onCommand(sender, command, "randommotd", new String[]{"reload"}));
        verify(plugin).reloadConfig();
        verify(sender).sendMessage(contains("Config reloaded"));
    }

    @Test
    void reloadCommandRequiresPermission() {
        var sender = mock(CommandSender.class);
        var command = mock(Command.class);
        when(command.getName()).thenReturn("randommotd");
        assertTrue(new Commands(plugin).onCommand(sender, command, "randommotd", new String[]{"reload"}));
        verify(plugin, never()).reloadConfig();
    }
}
