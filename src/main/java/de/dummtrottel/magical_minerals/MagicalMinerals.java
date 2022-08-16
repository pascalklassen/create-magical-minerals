package de.dummtrottel.magical_minerals;

import de.dummtrottel.magical_minerals.common.block.AllBlocks;
import de.dummtrottel.magical_minerals.common.item.AllItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(MagicalMinerals.ID)
public class MagicalMinerals
{
    public static final String ID = "magical_minerals";
    public static final String NAME = "Create: Magical Minerals";
    public static final String VERSION = "1.18.2-0.0.0.1";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab MINERAL_TAB = new CreativeModeTab("mineral_tab")
    {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(AllItems.LAZULITE_SHARD.get());
        }
    };

    public MagicalMinerals()
    {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        AllBlocks.register(bus);
        AllItems.register(bus);

        bus.addListener(this::clientSetup);
        bus.addListener(EventPriority.LOWEST, this::gather);
    }

    private void clientSetup(FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.LAZULITE_CLUSTER.get(), RenderType.cutout());
    }

    private void gather(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var fileHelper = event.getExistingFileHelper();

        generator.addProvider(AllItems.provider(generator, fileHelper));
    }
}
