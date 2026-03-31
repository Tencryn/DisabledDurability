package dev.tencryn.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryChangeEvent;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.tencryn.DisabledDurability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerInventoryChange extends EntityEventSystem<EntityStore, InventoryChangeEvent> {
    public PlayerInventoryChange() {
        super(InventoryChangeEvent.class);
    }

    public void handle(int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull InventoryChangeEvent event) {
        if (!DisabledDurability.get().getConfig().getDisableItemDurability()) {
            return;
        }

        Player playerComponent = archetypeChunk.getComponent(index, Player.getComponentType());
        assert playerComponent != null;

        ItemContainer itemContainer = event.getItemContainer();
        for (short slot = 0; slot < itemContainer.getCapacity(); slot++) {
            ItemStack stack = itemContainer.getItemStack(slot);

            if (stack == null || stack.isUnbreakable()) continue;

            double maxDurability = stack.getMaxDurability();
            if (stack.getDurability() < maxDurability) {
                itemContainer.setItemStackForSlot(slot, stack.withDurability(maxDurability));
            }
        }
    }

    @Nullable
    public Query<EntityStore> getQuery() {
        return Query.and(Player.getComponentType(), InventoryComponent.Hotbar.getComponentType());
    }
}
