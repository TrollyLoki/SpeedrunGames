package net.trollyloki.speedrungames.revive;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReviveListener implements Listener {

    private final @NotNull Map<@NotNull UUID, @NotNull Integer> revives = new HashMap<>();
    private final @NotNull Map<@NotNull UUID, @NotNull PlayerDeathEventHolder> holders = new HashMap<>();

    /**
     * Sets the number of revives a player has remaining.
     *
     * @param player  player
     * @param revives number of revives remaining
     */
    public void setRevives(@NotNull Player player, int revives) {
        if (revives > 0)
            this.revives.put(player.getUniqueId(), revives);
        else
            this.revives.remove(player.getUniqueId());
    }

    /**
     * Gets the number of revives a player has remaining.
     *
     * @param player player
     * @return number of revives remaining
     */
    public int getRevives(@NotNull Player player) {
        return this.revives.getOrDefault(player.getUniqueId(), 0);
    }

    private @NotNull PlayerDeathEventHolder getHolder(@NotNull Player player) {
        if (!holders.containsKey(player.getUniqueId()))
            throw new IllegalStateException("No held death information available for " + player.getName());
        return holders.get(player.getUniqueId());
    }

    /**
     * Revives a player.
     *
     * @param player player
     * @throws IllegalStateException if the player has not died
     */
    public void revive(@NotNull Player player) {
        getHolder(player).restore(player);
        if (revives.containsKey(player.getUniqueId()))
            setRevives(player, getRevives(player) - 1);
    }

    /**
     * Drops a player's held drops.
     *
     * @param player player
     * @throws IllegalStateException if the player has not died
     */
    public void dropDrops(@NotNull Player player) {
        getHolder(player).drop();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        holders.put(event.getPlayer().getUniqueId(), new PlayerDeathEventHolder(event));
        event.getDrops().clear();
        event.setDroppedExp(0);
    }

}
