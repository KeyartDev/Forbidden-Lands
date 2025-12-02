package org.keyart.forbidden_lands.core.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.common.blocks.custom.GroundBlock;

import java.util.function.Supplier;

public class FLBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ForbiddenLands.MODID);

    public static final RegistryObject<Block> GROUND_BLOCK =
            registerBlock("ground_block", GroundBlock::new);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> sup) {
        RegistryObject<T> toReturn = BLOCKS.register(name, sup);
        FLItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }
}
