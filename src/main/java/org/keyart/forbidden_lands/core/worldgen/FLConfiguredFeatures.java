package org.keyart.forbidden_lands.core.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusFoliagePlacer;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusTrunkPlacer;

import java.util.List;

public class FLConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERUS_TREE_KEY = registerKey("verus_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FL_GRASS_PATCH_KEY = registerKey("fl_grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FL_GRASS_SINGLE_KEY = registerKey("fl_grass_single");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FL_LUMIR_PATCH_KEY = registerKey("fl_lumir_single");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, VERUS_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(FLBlocks.FL_VERUS_LOG.get()),
                new FLVerusTrunkPlacer(5, 2, 0),
                BlockStateProvider.simple(FLBlocks.FL_VERUS_LEAVES.get()),
                new FLVerusFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 1),
                new TwoLayersFeatureSize(1, 0, 2)).build()
        );

        register(context, FL_GRASS_PATCH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(FLBlocks.FL_GRASS.get())))));

        register(context, FL_GRASS_SINGLE_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(FLBlocks.FL_GRASS.get())));
        register(context, FL_LUMIR_PATCH_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(FLBlocks.FL_LUMIR.get())));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
