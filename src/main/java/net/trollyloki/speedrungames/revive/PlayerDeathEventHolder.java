package net.trollyloki.speedrungames.revive;

import org.bukkit.Location;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information from a {@link PlayerDeathEvent} so that it can be handled at a later time.
 */
public class PlayerDeathEventHolder {

    private final @NotNull Location location;
    private final @Nullable ItemStack @NotNull [] items;
    private final int exp;
    private final @NotNull List<ItemStack> droppedItems;
    private final int droppedExp;

    /**
     * Constructs a new holder.
     *
     * @param event player death event
     */
    public PlayerDeathEventHolder(@NotNull PlayerDeathEvent event) {
        this.location = event.getPlayer().getLocation();
        this.items = event.getPlayer().getInventory().getContents();
        this.exp = event.getPlayer().getTotalExperience();
        this.droppedItems = new ArrayList<>(event.getDrops());
        this.droppedExp = event.getDroppedExp();
    }

    /**
     * Restores the state of the player before they died.
     *
     * @param player player
     */
    public void restore(@NotNull Player player) {
        player.getInventory().setContents(items);
        player.setTotalExperience(exp);
        player.teleport(location);
    }

    /**
     * Drops the items and experience.
     */
    public void drop() {
        if (location.getWorld() == null)
            return;
        for (ItemStack item : droppedItems) {
            if (item != null)
                location.getWorld().dropItemNaturally(location, item);
        }
        location.getWorld().spawn(location, ExperienceOrb.class, orb -> orb.setExperience(droppedExp));
    }

}
