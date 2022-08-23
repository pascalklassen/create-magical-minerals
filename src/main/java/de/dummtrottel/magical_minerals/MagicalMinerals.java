package de.dummtrottel.magical_minerals;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import de.dummtrottel.magical_minerals.common.AllItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(MagicalMinerals.ID)
public final class MagicalMinerals
{
    public static final String ID = "magical_minerals";
    public static final String NAME = "Create: Magical Minerals";
    public static final String VERSION = "1.18.2-0.0.0.1";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab MINERAL_TAB = new CreativeModeTab("mineral_tab")
    {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(AllItems.LAZULITE_CLUSTER.get());
        }
    };

    public MagicalMinerals()
    {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        AllBlocks.register(bus);
        AllItems.register(bus);

        bus.addListener(this::setup);
        bus.addListener(Client::setup);
        bus.addListener(EventPriority.LOWEST, this::gather);
    }

    private void setup(FMLCommonSetupEvent event)
    {
    }

    private void gather(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var fileHelper = event.getExistingFileHelper();

        generator.addProvider(AllBlocks.models(generator, fileHelper));
        generator.addProvider(AllBlocks.states(generator, fileHelper));
        generator.addProvider(AllItems.models(generator, fileHelper));
    }

    @Mod.EventBusSubscriber(Dist.CLIENT)
    public static final class Client
    {
        private static void setup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.LAZULITE_CLUSTER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.SMALL_LAZULITE_BUD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MEDIUM_LAZULITE_BUD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.LARGE_LAZULITE_BUD.get(), RenderType.cutout());
        }
    }
}
