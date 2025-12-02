package org.keyart.forbidden_lands.core.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.keyart.forbidden_lands.ForbiddenLands;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ForbiddenLands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {

    }

    @Mod.EventBusSubscriber(modid = ForbiddenLands.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeBusEvents {

    }
}
