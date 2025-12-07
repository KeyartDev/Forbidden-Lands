package org.keyart.forbidden_lands.core.datagen.providers.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.registries.FLItems;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FLBlockLootProvider extends BlockLootSubProvider {
    public FLBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(FLBlocks.FL_GRASS_BLOCK.get());
        dropSelf(FLBlocks.FL_DIRT.get());
        dropSelf(FLBlocks.FL_STONE.get());
        dropSelf(FLBlocks.FL_VERUS_LOG.get());
        dropSelf(FLBlocks.FL_STRIPPED_VERUS_LOG.get());
        dropSelf(FLBlocks.FL_VERUS_SAPLING.get());

        add(FLBlocks.FL_VERUS_LEAVES.get(), block ->
                createLeavesDrops(block, FLBlocks.FL_VERUS_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    private void addOreDrop(Block oreBlock, Item drop, int min, int max) {
        add(oreBlock, createSilkTouchDispatchTable(oreBlock,
                applyExplosionDecay(oreBlock,
                        LootItem.lootTableItem(drop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Stream.of(
                FLBlocks.BLOCKS.getEntries().stream()
        )
                .flatMap(Function.identity())
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }
}
