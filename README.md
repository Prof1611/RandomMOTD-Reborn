# RandomMOTD Reborn

RandomMOTD Reborn is a maintained continuation of **RandomMOTD**, originally created by **Phoenix852 / ThunderKofy**.

The original Spigot resource was released in 2020 for Minecraft 1.15 and later became unmaintained. This continuation updates the plugin for modern Paper servers while preserving the familiar configuration, commands and permissions.

## Status

Current release: **1.4.0**  
Target server: **Paper 1.21.4**  
Java: **21**

## Features

- Random server-list MOTD on every ping
- Separate MOTD list when the server whitelist is enabled
- Random in-game message when a player joins
- `%PLAYER%` placeholder for in-game messages
- `%VERSION%` placeholder using the current Minecraft version
- Legacy `&` colour-code support
- Runtime config reload command
- Existing RandomMOTD config structure retained for straightforward migration

## Installation

1. Download the latest jar from GitHub Releases.
2. Stop the server.
3. Put the jar in the server's `plugins` directory.
4. If upgrading from RandomMOTD 1.3.0, keep your existing `plugins/RandomMOTD/config.yml`.
5. Start the server.

Do not use `/reload` to replace plugin jars on a running Paper server.

## Commands

| Command | Description | Permission |
| --- | --- | --- |
| `/randommotd` | Plugin information | `RandomMOTD.main` |
| `/randommotd help` | Command help | `RandomMOTD.help` |
| `/randommotd reload` | Reload `config.yml` | `RandomMOTD.reload` |
| `/randommotd motd` | Display configured MOTDs | `RandomMOTD.motd` |
| `/randommotd author` | Show the original author | `RandomMOTD.author` |

Aliases: `/rmotd`, `/randmotd`.

## Configuration

The configuration remains compatible with the original layout:

```yaml
in-game:
  enabled: true
  motd:
    - "Hello &4%PLAYER%&r!"

system:
  enabled: true
  motd:
    normal:
      - "Running Minecraft &3%VERSION%"
    whitelist:
      - "&4The server is currently whitelisted!"
```

An empty MOTD list is handled safely and simply leaves the MOTD unchanged.

## What changed from 1.3.0

The modernised version targets Paper 1.21.4 and Java 21. Server-list MOTDs use Paper's Adventure component API rather than the legacy string setter. The implementation also avoids errors on empty MOTD lists and uses the current Minecraft version for `%VERSION%`.

## Original project and attribution

RandomMOTD was originally created by **Phoenix852 / ThunderKofy** and published on SpigotMC:

https://www.spigotmc.org/resources/randommotd.75823/

The original project was distributed under the MIT License. The original copyright and licence notice are retained in [`LICENSE`](LICENSE).

This repository is an independently maintained continuation and is not presented as the original author's official repository.

## Building

```bash
mvn clean package
```

The output jar will be created in `target/`.

## License

MIT. See [`LICENSE`](LICENSE).
