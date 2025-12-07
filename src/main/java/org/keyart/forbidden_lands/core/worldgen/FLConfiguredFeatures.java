package org.keyart.forbidden_lands.core.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusFoliagePlacer;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusTrunkPlacer;

public class FLConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERUS_TREE_KEY = registerKey("verus_tree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, VERUS_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(FLBlocks.FL_VERUS_LOG.get()),
                new FLVerusTrunkPlacer(7, 2, 0),
                BlockStateProvider.simple(FLBlocks.FL_VERUS_LEAVES.get()),
                new FLVerusFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 1),
                new TwoLayersFeatureSize(1, 0, 2)).build()
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
