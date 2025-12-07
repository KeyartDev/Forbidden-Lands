package org.keyart.forbidden_lands.core.datagen.providers.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;

import java.util.concurrent.CompletableFuture;

public class FLItemsTagProvider extends ItemTagsProvider {


    public FLItemsTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, ForbiddenLands.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(FLBlocks.FL_VERUS_LOG.get().asItem())
                .add(FLBlocks.FL_STRIPPED_VERUS_LOG.get().asItem());
    }
}
