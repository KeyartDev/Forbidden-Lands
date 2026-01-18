package org.keyart.forbidden_lands.core.datagen.providers.worldgen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.worldgen.biome.FLBiomes;
import org.keyart.forbidden_lands.core.worldgen.FLConfiguredFeatures;
import org.keyart.forbidden_lands.core.worldgen.FLPlacedFeatures;
import org.keyart.forbidden_lands.core.worldgen.dimension.FLDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class FLWorldgenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, FLConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, FLPlacedFeatures::bootstrap)
            .add(Registries.BIOME, FLBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, FLDimensions::bootstrapStem)
            .add(Registries.NOISE_SETTINGS, FLDimensions::bootstrapNoise)
            .add(Registries.DIMENSION_TYPE, FLDimensions::bootstrapType);

    public FLWorldgenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ForbiddenLands.MODID));
    }
}
