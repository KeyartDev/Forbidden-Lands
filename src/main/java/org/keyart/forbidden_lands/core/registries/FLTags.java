package org.keyart.forbidden_lands.core.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.keyart.forbidden_lands.ForbiddenLands;

public class FLTags {
    public static final TagKey<Block> FL_PLANTS_GROW_ON = create("fl_plants_grows_on");


    private static TagKey<Block> create(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, name));
    }
}
