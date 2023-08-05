package net.trollyloki.speedrungames;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team {

    private @NotNull String name;
    private @NotNull NamedTextColor color;

    private final @NotNull Set<@NotNull UUID> players = new HashSet<>();

    private @NotNull Goal goal;
    private boolean canRespawn;

    public Team(@NotNull String name, @NotNull NamedTextColor color, @NotNull Goal goal, boolean canRespawn) {
        this.name = name;
        this.color = color;
        this.goal = goal;
        this.canRespawn = canRespawn;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull NamedTextColor getColor() {
        return color;
    }

    public void setColor(@NotNull NamedTextColor color) {
        this.color = color;
    }

    public @NotNull Component getFormattedName() {
        return Component.text(name, color);
    }

    public @NotNull @UnmodifiableView Set<@NotNull UUID> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public boolean isMember(@NotNull Player player) {
        return players.contains(player.getUniqueId());
    }

    public boolean addPlayer(@NotNull Player player) {
        return players.add(player.getUniqueId());
    }

    public boolean removePlayer(@NotNull Player player) {
        return players.remove(player.getUniqueId());
    }

    public @NotNull Goal getGoal() {
        return goal;
    }

    public void setGoal(@NotNull Goal goal) {
        this.goal = goal;
    }

    public boolean canRespawn() {
        return canRespawn;
    }

    public void setCanRespawn(boolean canRespawn) {
        this.canRespawn = canRespawn;
    }

}
