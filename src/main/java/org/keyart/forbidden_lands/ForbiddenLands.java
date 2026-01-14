package org.keyart.forbidden_lands;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.keyart.forbidden_lands.common.blocks.custom.models.bakery.BakedModelLayerFullbright;
import org.keyart.forbidden_lands.core.registries.*;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ForbiddenLands.MODID)
public class ForbiddenLands {
    private static final List<String> FULLBRIGHTS = List.of("forbidden_lands:lumir#");

    public static final String MODID = "forbidden_lands";

    private static final Logger LOGGER = LogUtils.getLogger();


    public ForbiddenLands(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        FLBlocks.register(modEventBus);
        FLItems.register(modEventBus);
        FLCreativeTabs.register(modEventBus);
        FLTrunkPlacers.register(modEventBus);
        FLFoliagePlacers.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::bakeModels);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private void bakeModels(final ModelEvent.ModifyBakingResult event) {
        for (ResourceLocation id : event.getModels().keySet()) {
            if (FULLBRIGHTS.stream().anyMatch(str -> id.toString().startsWith(str))) {
                event.getModels().put(id, new BakedModelLayerFullbright(event.getModels().get(id)));
            }
        }
    }
}
