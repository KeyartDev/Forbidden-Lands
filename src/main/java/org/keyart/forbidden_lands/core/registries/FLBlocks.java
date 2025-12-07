package org.keyart.forbidden_lands.core.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.common.blocks.custom.FLGrassBlock;
import org.keyart.forbidden_lands.common.blocks.custom.FLRotatedPillarFlammableBlock;
import org.keyart.forbidden_lands.common.blocks.custom.FlVerusLogBlock;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusGrower;

import java.util.function.Supplier;

public class FLBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ForbiddenLands.MODID);

    public static final RegistryObject<Block> FL_GRASS_BLOCK =
            registerBlock("fl_grass_block", FLGrassBlock::new);

    public static final RegistryObject<Block> FL_DIRT =
            registerBlock("fl_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> FL_STONE =
            registerBlock("fl_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> FL_VERUS_LOG =
            registerBlock("fl_verus_log", FlVerusLogBlock::new);

    public static final RegistryObject<Block> FL_STRIPPED_VERUS_LOG =
            registerBlock("fl_stripped_verus_log", () -> new FLRotatedPillarFlammableBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> FL_VERUS_LEAVES =
            registerBlock("fl_verus_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> FL_VERUS_SAPLING =
            registerBlock("fl_verus_sapling", () -> new SaplingBlock(new FLVerusGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> sup) {
        RegistryObject<T> toReturn = BLOCKS.register(name, sup);
        FLItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }
}
