package de.dummtrottel.magical_minerals.common.block.geode.lazulite;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import de.dummtrottel.magical_minerals.common.block.geode.BaseBuddingBlock;
import de.dummtrottel.magical_minerals.common.block.geode.BaseClusterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class BuddingLazuliteBlock extends BaseBuddingBlock
{
    private static final int GROWTH_CHANCE = 5;

    public BuddingLazuliteBlock()
    {
        super(
                LazuliteBlock.MINERAL_NAME,
                BlockBehaviour.Properties
                        .copy(AllBlocks.LAZULITE_BLOCK.get()), GROWTH_CHANCE
        );
    }

    @Override
    public BaseClusterBlock full()
    {
        return AllBlocks.LAZULITE_CLUSTER.get();
    }

    @Override
    public BaseClusterBlock large()
    {
        return AllBlocks.LARGE_LAZULITE_BUD.get();
    }

    @Override
    public BaseClusterBlock medium()
    {
        return AllBlocks.MEDIUM_LAZULITE_BUD.get();
    }

    @Override
    public BaseClusterBlock small()
    {
        return AllBlocks.SMALL_LAZULITE_BUD.get();
    }
}
