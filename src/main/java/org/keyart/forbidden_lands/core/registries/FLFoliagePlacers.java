package org.keyart.forbidden_lands.core.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusFoliagePlacer;

public class FLFoliagePlacers {


    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ForbiddenLands.MODID);

    public static final RegistryObject<FoliagePlacerType<FLVerusFoliagePlacer>> VERUS_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("verus_foliage_placer", () -> new FoliagePlacerType<>(FLVerusFoliagePlacer.CODEC));

    public static void register(IEventBus bus) {
        FOLIAGE_PLACERS.register(bus);
    }
}
