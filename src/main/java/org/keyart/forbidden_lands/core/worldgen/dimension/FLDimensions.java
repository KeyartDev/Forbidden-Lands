package org.keyart.forbidden_lands.core.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.registries.FLBlocks;
import org.keyart.forbidden_lands.core.worldgen.biome.FLBiomes;

import java.util.List;
import java.util.OptionalLong;

public class FLDimensions {
    public static final ResourceKey<LevelStem> FL_DIM_KEY =
            ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "fl_dim"));
    public static final ResourceKey<Level> FL_DIM_LEVEL_KEY =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "fl_dim"));
    public static final ResourceKey<DimensionType> FL_DIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "fl_dim_type"));


    public static final ResourceKey<NoiseGeneratorSettings> FL_NOISE_GEN =
            ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "forbidden_lands"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(FL_DIM_TYPE, new DimensionType(
                OptionalLong.of(18000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                2.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0.3f, // ambientLight
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F), biomeRegistry.getOrThrow(FLBiomes.DORMANT_FOREST_BIOME))
                        ))),
                noiseGenSettings.getOrThrow(FL_NOISE_GEN)
        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(FLDimensions.FL_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(FL_DIM_KEY, stem);
    }

    public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);
        DensityFunction densityfunction = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_X);
        DensityFunction densityfunction1 = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_Z);

        context.register(FL_NOISE_GEN, new NoiseGeneratorSettings(
                NoiseSettings.create(0, 64, 2, 2),
                FLBlocks.FL_STONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                new NoiseRouter(
                        DensityFunctions.yClampedGradient(
                                -64, -59,
                                1.0D, 0.0D
                        ), //Barrier
                        DensityFunctions.zero(), //FL floodedness
                        DensityFunctions.zero(), //FL spread
                        DensityFunctions.zero(), //lava depth
                        DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, noises.getOrThrow(Noises.TEMPERATURE)), //temperature
                        DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, noises.getOrThrow(Noises.VEGETATION)), //vegetation
                        DensityFunctions.zero(), //continents
                        DensityFunctions.zero(), //erosions
                        DensityFunctions.zero(), //depth
                        DensityFunctions.zero(), //ridges
                        DensityFunctions.constant(53), //init density
                        DensityFunctions.rangeChoice(
                                DensityFunctions.yClampedGradient(
                                        -64, 64, 0, 1
                                ),
                                0, 0.921875D,
                                DensityFunctions.constant(0.921875D),
                                DensityFunctions.add(
                                        DensityFunctions.constant(-0.5D),
                                        DensityFunctions.rangeChoice(
                                                DensityFunctions.yClampedGradient(
                                                        53, 57, 0, 1
                                                ),
                                                0, 1,
                                                DensityFunctions.add(
                                                        DensityFunctions.yClampedGradient(
                                                                53, 57, 1, 0
                                                        ),
                                                        DensityFunctions.interpolated(
                                                                DensityFunctions.add(
                                                                        DensityFunctions.constant(-0.1),
                                                                        DensityFunctions.noise(noises.getOrThrow(Noises.SURFACE), 1, 1)
                                                                )
                                                        )
                                                ),
                                                DensityFunctions.zero()
                                        )
                                )
                        ), //final density
                        DensityFunctions.zero(), //vein toggle
                        DensityFunctions.zero(), //vein ridged
                        DensityFunctions.zero()  //vein gap
                ),
                SurfaceRules.sequence(
                        //bedrock floor
                        SurfaceRules.ifTrue(SurfaceRules.verticalGradient("minecraft:bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                        SurfaceRules.state(FLBlocks.FL_GRASS_BLOCK.get().defaultBlockState())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(1, true, 0, CaveSurface.FLOOR),
                                        SurfaceRules.state(FLBlocks.FL_DIRT.get().defaultBlockState())
                                )
                        )
                ),
                List.of(), //spawn targets
                32, //water level
                false,
                false,
                false,
                false
        ));
    }
}
