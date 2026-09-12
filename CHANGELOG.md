# Changelog

## 1.4.1 - 2026-09-12

- Fixed a runtime `NoSuchMethodError` on Paper servers caused by linking directly to an incompatible Adventure legacy serializer.
- MOTD colour codes are now translated through Bukkit's stable legacy text API for both server-list and in-game MOTDs.

## 1.4.0 - 2026-09-12

First maintained continuation release.

- Updated for Paper 1.21.4.
- Updated build target to Java 21.
- Added `api-version: 1.21.4`.
- Updated server-list MOTDs to Paper's Adventure component API.
- Preserved original RandomMOTD configuration paths, commands and permissions.
- Preserved `%PLAYER%` and `%VERSION%` placeholders.
- Added safe handling for empty MOTD lists.
- Improved tab completion.
- Retained original project attribution and MIT licence.

## 1.3.0 - 2020-03-19

Original RandomMOTD release by Phoenix852 / ThunderKofy.
