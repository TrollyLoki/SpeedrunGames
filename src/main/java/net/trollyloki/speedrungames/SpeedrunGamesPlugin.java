package net.trollyloki.speedrungames;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.trollyloki.speedrungames.revive.ReviveGUI;
import net.trollyloki.speedrungames.revive.ReviveListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SpeedrunGamesPlugin extends JavaPlugin {

    public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private static SpeedrunGamesPlugin instance;

    private static GameManager gameManager;

    private static ReviveListener reviveListener;
    private static ReviveGUI reviveGUI;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        gameManager = new GameManager();
        getServer().getPluginManager().registerEvents(gameManager, this);
        gameManager.runTaskTimer(this, 0, 1);

        reviveListener = new ReviveListener();
        getServer().getPluginManager().registerEvents(reviveListener, this);
        reviveGUI = new ReviveGUI();
        getServer().getPluginManager().registerEvents(reviveGUI, this);

    }

    @Override
    public void onDisable() {
        instance = null;

        gameManager = null;

        reviveListener = null;
        reviveGUI = null;
    }

    public static SpeedrunGamesPlugin getInstance() {
        return instance;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static ReviveListener getReviveListener() {
        return reviveListener;
    }

    /**
     * Gets a message from the configuration, formatted using MiniMessage.
     *
     * @param path     path of the message
     * @param fallback fallback message
     * @return message or fallback message if the path does not exist
     */
    public @NotNull Component getConfigMessage(@NotNull String path, @NotNull Component fallback) {
        return MINI_MESSAGE.deserializeOr(getConfig().getString(path), fallback);
    }

    /**
     * Gets a message from the configuration, formatted using MiniMessage.
     *
     * @param path path of the message
     * @return message or {@code null} if the path does not exist
     */
    public Component getConfigMessage(@NotNull String path) {
        return MINI_MESSAGE.deserializeOrNull(getConfig().getString(path));
    }

}
