package org.keyart.forbidden_lands.core.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.worldgen.FLPlacedFeatures;

public class FLBiomes {
    public static final ResourceKey<Biome> DORMANT_FOREST_BIOME =
            ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ForbiddenLands.MODID, "dormant_forest_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(DORMANT_FOREST_BIOME, dormantForestBiome(context));
    }


    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) { //Дефолтные значения
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome dormantForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder(); //Билдер для добавления спавна сущностей в биоме
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ANKI.get(), 6, 1, 2));

        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        //BiomeDefaultFeatures.farmAnimals(spawnBuilder); //Сельские мобы
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder); //Стандартные спавны

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //НЕОБХОДИМО СЛЕДОВАТЬ ТОМУ ЖЕ ПОРЯДКУ, ЧТО И В ВАНИЛЬНЫХ БИОМАХ ДЛЯ BiomeDefaultFeatures
        //BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        //globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEVENCOLOR_PLACED_KEY);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        //biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SOME_ORE_PLACED_KEY);


        //BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLPlacedFeatures.VERUS_TREE_KEY); //Добавление своего дерева в генерацию
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLPlacedFeatures.FL_GRASS_PATCH_KEY); //Добавление своего дерева в генерацию
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLPlacedFeatures.FL_LUMIR_PATCH_KEY); //Добавление своего дерева в генерацию


        return new Biome.BiomeBuilder() //Возвращаемый результат - билдер.
                .hasPrecipitation(true) //Наличие осадков
                .downfall(0.8f) //Обвалы(?)
                .temperature(0.7f) //Температура
                .generationSettings(biomeBuilder.build()) //Конфигурация генерации
                .mobSpawnSettings(spawnBuilder.build()) //Конфигурация спавна
                .specialEffects((new BiomeSpecialEffects.Builder()) //Особые эффекты:
                        .waterColor(0x6A5ACD) //Цвет воды
                        .waterFogColor(0x483D8B) //Цвет тумана в воде
                        .skyColor(0xBA55D3) //Цвет неба
                        .grassColorOverride(0x7f03fc) //Цвет растительности
                        .foliageColorOverride(0xd203fc) //Цвет листвы
                        .fogColor(0xDDA0DD) //Цвет тумана
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS) //Звуки эмбиента
                        .build())
                .build();
    }
}
