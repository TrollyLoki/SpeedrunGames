package net.trollyloki.speedrungames;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameManager extends BukkitRunnable implements Listener {

    private final @NotNull Set<@NotNull Team> teams = new HashSet<>();

    public @NotNull @UnmodifiableView Set<@NotNull Team> getTeams() {
        return Collections.unmodifiableSet(teams);
    }

    public boolean registerTeam(@NotNull Team team) {
        return teams.add(team);
    }

    public @NotNull Optional<Team> getTeam(@NotNull Player player) {
        for (Team team : teams) {
            if (team.isMember(player))
                return Optional.of(team);
        }
        return Optional.empty();
    }

}
