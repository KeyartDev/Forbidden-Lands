package org.keyart.forbidden_lands.core.datagen.providers.worldgen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.worldgen.FLConfiguredFeatures;
import org.keyart.forbidden_lands.core.worldgen.FLPlacedFeatures;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class FLWorldgenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, FLConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, FLPlacedFeatures::bootstrap);

    public FLWorldgenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ForbiddenLands.MODID));
    }
}
