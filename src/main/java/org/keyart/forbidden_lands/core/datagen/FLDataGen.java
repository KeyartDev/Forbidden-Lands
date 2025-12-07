package org.keyart.forbidden_lands.core.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.datagen.providers.loot.FLBlockLootProvider;
import org.keyart.forbidden_lands.core.datagen.providers.loot.FLGlobalLootModifiersProvider;
import org.keyart.forbidden_lands.core.datagen.providers.models.FLBlockStateProvider;
import org.keyart.forbidden_lands.core.datagen.providers.models.FLItemModelProvider;
import org.keyart.forbidden_lands.core.datagen.providers.recipe.FLRecipeProvider;
import org.keyart.forbidden_lands.core.datagen.providers.tags.FLBlocksTagProvider;
import org.keyart.forbidden_lands.core.datagen.providers.tags.FLItemsTagProvider;
import org.keyart.forbidden_lands.core.datagen.providers.worldgen.FLWorldgenProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ForbiddenLands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FLDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeClient())
            addClientProviders(generator, packOutput, fileHelper);
        
        if (event.includeServer())
            addServerProviders(generator, packOutput, fileHelper, lookupProvider);
    }

    private static void addClientProviders(DataGenerator generator, PackOutput packOutput,
                                           ExistingFileHelper fileHelper) {
        generator.addProvider(true, new FLBlockStateProvider(packOutput, fileHelper));
        generator.addProvider(true, new FLItemModelProvider(packOutput, fileHelper));
    }

    private static void addServerProviders(DataGenerator generator, PackOutput packOutput,
                                           ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        generator.addProvider(true, new FLRecipeProvider(packOutput));

        FLBlocksTagProvider blocksTagProvider = generator.addProvider(true, new FLBlocksTagProvider(packOutput, lookupProvider, fileHelper));

        generator.addProvider(true, new FLItemsTagProvider(packOutput, lookupProvider, blocksTagProvider.contentsGetter(), fileHelper));

        generator.addProvider(true, new FLGlobalLootModifiersProvider(packOutput));

        generator.addProvider(true, new LootTableProvider(packOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(FLBlockLootProvider::new, LootContextParamSets.BLOCK))));

        generator.addProvider(true, new FLWorldgenProvider(packOutput, lookupProvider));

    }
}






















