package com.ThunderKofy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

final class MOTDUtil {
    private static final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.legacyAmpersand();

    private MOTDUtil() {}

    static String randomEntry(List<String> entries) {
        if (entries == null || entries.isEmpty()) {
            return null;
        }
        return entries.get(ThreadLocalRandom.current().nextInt(entries.size()));
    }

    static Component legacyComponent(String text) {
        return LEGACY.deserialize(text == null ? "" : text);
    }
}
