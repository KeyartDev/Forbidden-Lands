package org.keyart.forbidden_lands.core.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.keyart.forbidden_lands.ForbiddenLands;

public class FLItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForbiddenLands.MODID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
