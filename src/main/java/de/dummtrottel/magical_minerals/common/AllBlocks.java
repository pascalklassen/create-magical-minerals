package de.dummtrottel.magical_minerals.common;

import de.dummtrottel.magical_minerals.MagicalMinerals;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.BuddingLazuliteBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LargeLazuliteBudBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.LazuliteClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.LazuliteBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.MediumLazuliteBudBlock;
import de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster.SmallLazuliteBudBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AllBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagicalMinerals.ID);

    public static final RegistryObject<LazuliteBlock> LAZULITE_BLOCK = BLOCKS.register("lazulite_block", LazuliteBlock::new);

    public static final RegistryObject<LazuliteClusterBlock> LAZULITE_CLUSTER = BLOCKS.register("lazulite_cluster", LazuliteClusterBlock::new);
    public static final RegistryObject<SmallLazuliteBudBlock> SMALL_LAZULITE_BUD = BLOCKS.register("small_lazulite_bud", SmallLazuliteBudBlock::new);
    public static final RegistryObject<MediumLazuliteBudBlock> MEDIUM_LAZULITE_BUD = BLOCKS.register("medium_lazulite_bud", MediumLazuliteBudBlock::new);
    public static final RegistryObject<LargeLazuliteBudBlock> LARGE_LAZULITE_BUD = BLOCKS.register("large_lazulite_bud", LargeLazuliteBudBlock::new);
    public static final RegistryObject<BuddingLazuliteBlock> BUDDING_LAZULITE = BLOCKS.register("budding_lazulite", BuddingLazuliteBlock::new);

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
