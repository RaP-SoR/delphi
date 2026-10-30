# Delphi for Paper 26.2

Fork of [JulieWoolie/delphi](https://github.com/JulieWoolie/delphi) targeting **Paper 26.2** / **Java 25**.

Upstream Hangar builds only cover Paper 1.21.7–1.21.8. On Paper 26.x, text measuring crashes with:

`NoSuchMethodError: UnsafeValues.componentFlattener()`

## Fix

`TextUtil` uses `PaperComponents.flattener()` instead of `Bukkit.getUnsafe().componentFlattener()`.

Build targets:

- `paper-api` / `dev-bundle`: `26.2.build.121-stable`
- Java release / toolchain: **25**
- Gradle: **9.7.1** (required by paperweight-userdev 2.0.0-beta.24)

## Build

```powershell
$env:JAVA_HOME = "C:\Users\babar\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.4.101-hotspot"
.\gradlew.bat :plugin:shadowJar
```

Output:

```
plugin/build/libs/delphi-papermc-26.2-0.7.2-paper26-all.jar
```

Copy that jar to `server/plugins/` (replace the old 1.21.7 Delphi jar), then restart the server.

UI modules from **delphi-ui** stay separate: `plugins/Delphi/modules/scoreboard.zip`.

## License

GPL-3.0, same as upstream.
