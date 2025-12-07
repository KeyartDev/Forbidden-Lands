package org.keyart.forbidden_lands.core.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.keyart.forbidden_lands.ForbiddenLands;
import org.keyart.forbidden_lands.core.worldgen.tree.FLVerusTrunkPlacer;

public class FLTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ForbiddenLands.MODID);

    public static final RegistryObject<TrunkPlacerType<FLVerusTrunkPlacer>> VERUS_TRUNK_PLACER =
            TRUNK_PLACERS.register("verus_trunk_placer", () -> new TrunkPlacerType<>(FLVerusTrunkPlacer.CODEC));

    public static void register(IEventBus bus) {
        TRUNK_PLACERS.register(bus);
    }
}
