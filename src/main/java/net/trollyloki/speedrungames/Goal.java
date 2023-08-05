package net.trollyloki.speedrungames;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Defines a team's goal in a speedrun.
 */
public interface Goal {

    /**
     * Checks if the goal has been reached.
     * 
     * @return {@code true} if the team has completed their goal, otherwise {@code false}
     */
    boolean isComplete();

    default void onEntityDamage(@NotNull EntityDamageEvent event) {
        // to be overridden
    }

    default void onEntityDamageByEntity(@NotNull EntityDamageByEntityEvent event) {
        // to be overridden
    }

    default void onEntityDeath(@NotNull EntityDeathEvent event) {
        // to be overridden
    }

    default void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        // to be overridden
    }

    default void onPlayerPortal(@NotNull PlayerPortalEvent event) {
        // to be overridden
    }

    default void onEntityPortal(@NotNull EntityPortalEvent event) {
        // to be overridden
    }

    default void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        // to be overridden
    }

    default void onPlayerAdvancementDone(@NotNull PlayerAdvancementDoneEvent event) {
        // to be overridden
    }

}
