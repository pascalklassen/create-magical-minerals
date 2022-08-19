package de.dummtrottel.magical_minerals.common.block.geode.lazulite;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import de.dummtrottel.magical_minerals.common.block.geode.BaseBuddingBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class BuddingLazuliteBlock extends BaseBuddingBlock
{
    private static final int GROWTH_CHANCE = 5;

    public BuddingLazuliteBlock()
    {
        super(BlockBehaviour.Properties
                .copy(AllBlocks.LAZULITE_BLOCK.get()), GROWTH_CHANCE);
    }

    @Override
    public Block full()
    {
        return AllBlocks.LAZULITE_CLUSTER.get();
    }

    @Override
    public Block large()
    {
        return AllBlocks.LARGE_LAZULITE_BUD.get();
    }

    @Override
    public Block medium()
    {
        return AllBlocks.MEDIUM_LAZULITE_BUD.get();
    }

    @Override
    public Block small()
    {
        return AllBlocks.SMALL_LAZULITE_BUD.get();
    }
}
