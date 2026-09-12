# RandomMOTD Reborn

[![Latest release](https://img.shields.io/github/v/release/Prof1611/RandomMOTD-Reborn?style=flat-square&label=release)](https://github.com/Prof1611/RandomMOTD-Reborn/releases)
[![Build](https://github.com/Prof1611/RandomMOTD-Reborn/actions/workflows/build.yml/badge.svg)](https://github.com/Prof1611/RandomMOTD-Reborn/actions/workflows/build.yml)
[![SpigotMC](https://img.shields.io/badge/SpigotMC-Resource-F07A0A?style=flat-square)](https://www.spigotmc.org/resources/randommotd-reborn.138755/)
[![Paper](https://img.shields.io/badge/Paper-1.21.4-222222?style=flat-square)](https://papermc.io/)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![License](https://img.shields.io/github/license/Prof1611/RandomMOTD-Reborn?style=flat-square)](LICENSE)

A lightweight Paper plugin for **random server-list MOTDs** and **random join messages**, modernised for current Minecraft servers while preserving the original RandomMOTD configuration, commands and permissions.

**[Download on SpigotMC](https://www.spigotmc.org/resources/randommotd-reborn.138755/)** · **[GitHub Releases](https://github.com/Prof1611/RandomMOTD-Reborn/releases)** · **[Report an issue](https://github.com/Prof1611/RandomMOTD-Reborn/issues)**

RandomMOTD Reborn is an independently maintained continuation of **RandomMOTD**, originally created by **Phoenix852 / ThunderKofy**.

## Features

- Random server-list MOTD on every ping
- Separate MOTD pool while the server whitelist is enabled
- Random in-game message when a player joins
- `%PLAYER%` placeholder for join messages
- `%VERSION%` placeholder using the current Minecraft version
- Legacy `&` colour-code support
- Runtime configuration reload
- Safe handling of empty MOTD lists
- Original RandomMOTD configuration structure retained for straightforward migration

## Compatibility

| | Supported |
| --- | --- |
| Server | Paper 1.21.4 |
| Java | 21 |
| Current release | 1.4.0 |

RandomMOTD Reborn is currently tested against **Paper 1.21.4**. Other server implementations or Minecraft versions should not be assumed compatible unless explicitly listed.

## Quick start

1. Download the latest jar from [SpigotMC](https://www.spigotmc.org/resources/randommotd-reborn.138755/) or [GitHub Releases](https://github.com/Prof1611/RandomMOTD-Reborn/releases).
2. Stop the server.
3. Place the jar in the server's `plugins` directory.
4. Start the server.

If you are upgrading from RandomMOTD 1.3.0, keep your existing `plugins/RandomMOTD/config.yml`.

> Do not replace plugin jars using `/reload` on a running Paper server.

## Configuration

The familiar RandomMOTD configuration layout is retained:

```yaml
in-game:
  enabled: true
  motd:
    - "Hello &4%PLAYER%&r!"
    - "Welcome back, &3%PLAYER%&r."

system:
  enabled: true
  motd:
    normal:
      - "&3Welcome!&r Running Minecraft &3%VERSION%&r"
      - "A different MOTD every time!"
    whitelist:
      - "&4The server is currently whitelisted!&r"
      - "Please come back later."
```

An empty MOTD list is handled safely and leaves the existing MOTD unchanged.

## Commands and permissions

| Command | Description | Permission |
| --- | --- | --- |
| `/randommotd` | Plugin information | `RandomMOTD.main` |
| `/randommotd help` | Command help | `RandomMOTD.help` |
| `/randommotd reload` | Reload `config.yml` | `RandomMOTD.reload` |
| `/randommotd motd` | Display configured MOTDs | `RandomMOTD.motd` |
| `/randommotd author` | Show the original author | `RandomMOTD.author` |

Aliases: `/rmotd`, `/randmotd`

Wildcard permission: `RandomMOTD.*`

## What changed from RandomMOTD 1.3.0?

RandomMOTD Reborn modernises the original plugin for current Paper servers rather than simply repackaging the old jar.

- Updated for Paper 1.21.4 and Java 21
- Server-list MOTDs use Paper's Adventure component API instead of the legacy string setter
- Empty MOTD lists no longer cause selection errors
- `%VERSION%` reports the current Minecraft version
- Modern Maven build and GitHub Actions workflow
- Public source, issue tracking and maintained release process

## Building from source

Requirements:

- JDK 21
- Maven

```bash
git clone https://github.com/Prof1611/RandomMOTD-Reborn.git
cd RandomMOTD-Reborn
mvn clean package
```

The built jar will be created in `target/`.

## Support

For bugs, compatibility problems or feature requests, use the [GitHub issue tracker](https://github.com/Prof1611/RandomMOTD-Reborn/issues).

For normal downloads and the public resource page, see [RandomMOTD Reborn on SpigotMC](https://www.spigotmc.org/resources/randommotd-reborn.138755/).

## Original project and attribution

RandomMOTD was originally created by **Phoenix852 / ThunderKofy** and published on SpigotMC:

- [Original RandomMOTD resource](https://www.spigotmc.org/resources/randommotd.75823/)

The original project was distributed under the MIT License. The original copyright and licence notice are retained in [`LICENSE`](LICENSE).

This repository is an independently maintained continuation and is not presented as the original author's official repository.

## License

RandomMOTD Reborn is distributed under the **MIT License**. See [`LICENSE`](LICENSE) for the full licence text.
