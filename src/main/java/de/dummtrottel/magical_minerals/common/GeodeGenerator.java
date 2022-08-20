package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicalMinerals.ID)
public final class GeodeGenerator
{
    @SubscribeEvent
    public static void generate(BiomeLoadingEvent event)
    {
        event
                .getGeneration()
                .getFeatures(GenerationStep.Decoration.LOCAL_MODIFICATIONS)
                .add(AllFeatures.LAZULITE_GEODE_PLACED);
    }
}
