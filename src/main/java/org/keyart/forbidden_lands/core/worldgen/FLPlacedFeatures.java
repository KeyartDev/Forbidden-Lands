package org.keyart.forbidden_lands.core.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;

import java.util.List;

public class FLPlacedFeatures {
    public static final ResourceKey<PlacedFeature> VERUS_TREE_KEY = registerKey("verus_tree_placed");
    public static final ResourceKey<PlacedFeature> FL_GRASS_PATCH_KEY = registerKey("fl_grass_patch_placed");
    public static final ResourceKey<PlacedFeature> FL_GRASS_SINGLE_KEY = registerKey("fl_grass_single_placed");
    public static final ResourceKey<PlacedFeature> FL_LUMIR_PATCH_KEY = registerKey("fl_lumir_patch_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, VERUS_TREE_KEY, configuredFeatures.getOrThrow(FLConfiguredFeatures.VERUS_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(12, 0.1F, 2),
                        FLBlocks.FL_VERUS_SAPLING.get()));

        register(context, FL_GRASS_PATCH_KEY, configuredFeatures.getOrThrow(FLConfiguredFeatures.FL_GRASS_PATCH_KEY), VegetationPlacements.worldSurfaceSquaredWithCount(25));

        register(context, FL_GRASS_SINGLE_KEY, configuredFeatures.getOrThrow(FLConfiguredFeatures.FL_GRASS_SINGLE_KEY), List.of());

        PlacementUtils.register(context, FL_LUMIR_PATCH_KEY, configuredFeatures.getOrThrow(
                FLConfiguredFeatures.FL_LUMIR_PATCH_KEY),
                VegetationPlacements.worldSurfaceSquaredWithCount(10)
        );

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
