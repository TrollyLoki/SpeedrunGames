package net.trollyloki.speedrungames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class GoalListener implements Listener {

    private final @NotNull Set<@NotNull Goal> goals = new HashSet<>();

    /**
     * Registers a goal to receive events.
     * 
     * @param goal goal
     * @return {@code true} if the goal was not already registered
     */
    public boolean registerGoal(@NotNull Goal goal) {
        return goals.add(goal);
    }

    /**
     * Unregisters a goal.
     * 
     * @param goal goal
     * @return {@code true} if the goal was registered before
     */
    public boolean unregisterGoal(@NotNull Goal goal) {
        return goals.remove(goal);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(@NotNull EntityDamageEvent event) {
        goals.forEach(g -> g.onEntityDamage(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(@NotNull EntityDamageByEntityEvent event) {
        goals.forEach(g -> g.onEntityDamageByEntity(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(@NotNull EntityDeathEvent event) {
        goals.forEach(g -> g.onEntityDeath(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        goals.forEach(g -> g.onPlayerDeath(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawn(@NotNull PlayerRespawnEvent event) {
        goals.forEach(g -> g.onPlayerRespawn(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPortal(@NotNull PlayerPortalEvent event) {
        goals.forEach(g -> g.onPlayerPortal(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityPortal(@NotNull EntityPortalEvent event) {
        goals.forEach(g -> g.onEntityPortal(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        goals.forEach(g -> g.onPlayerJoin(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerAdvancementDone(@NotNull PlayerAdvancementDoneEvent event) {
        goals.forEach(g -> g.onPlayerAdvancementDone(event));
    }

}
