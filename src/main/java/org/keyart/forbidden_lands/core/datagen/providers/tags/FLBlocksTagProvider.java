package org.keyart.forbidden_lands.core.datagen.providers.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;

import java.util.concurrent.CompletableFuture;

public class FLBlocksTagProvider extends BlockTagsProvider {
    public FLBlocksTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ForbiddenLands.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        registerMineableTags();
        registerToolRequirementsTags();

        tag(BlockTags.LOGS_THAT_BURN)
                .add(FLBlocks.FL_VERUS_LOG.get())
                .add(FLBlocks.FL_STRIPPED_VERUS_LOG.get());

    }

    private void registerMineableTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE);
    }

    private void registerToolRequirementsTags() {
        tag(BlockTags.NEEDS_IRON_TOOL);
    }

}
