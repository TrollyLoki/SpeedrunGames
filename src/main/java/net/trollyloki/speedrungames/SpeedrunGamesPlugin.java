package net.trollyloki.speedrungames;

import org.bukkit.plugin.java.JavaPlugin;

public class SpeedrunGamesPlugin extends JavaPlugin {

    private static SpeedrunGamesPlugin instance;

    private static GoalListener goalListener;

    @Override
    public void onEnable() {
        instance = this;

        goalListener = new GoalListener();
        getServer().getPluginManager().registerEvents(goalListener, this);

    }

    @Override
    public void onDisable() {
        instance = null;

        goalListener = null;

    }

    public static SpeedrunGamesPlugin getInstance() {
        return instance;
    }

    public static GoalListener getGoalListener() {
        return goalListener;
    }

}
