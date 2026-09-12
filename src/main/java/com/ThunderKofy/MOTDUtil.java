package com.ThunderKofy;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

final class MOTDUtil {
    private MOTDUtil() {}

    static String randomEntry(List<String> entries) {
        if (entries == null || entries.isEmpty()) {
            return null;
        }
        return entries.get(ThreadLocalRandom.current().nextInt(entries.size()));
    }

    static String legacyText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text == null ? "" : text);
    }
}
