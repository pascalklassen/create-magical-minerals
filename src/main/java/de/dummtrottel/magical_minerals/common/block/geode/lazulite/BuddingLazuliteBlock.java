package de.dummtrottel.magical_minerals.common.block.geode.lazulite;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import de.dummtrottel.magical_minerals.common.block.geode.BaseBuddingBlock;
import de.dummtrottel.magical_minerals.common.block.geode.BaseClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.MineralType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public final class BuddingLazuliteBlock extends BaseBuddingBlock
{
    private static final int GROWTH_CHANCE = 5;

    public BuddingLazuliteBlock()
    {
        super(
                MineralType.LAZULITE,
                BlockBehaviour.Properties
                        .copy(AllBlocks.LAZULITE_BLOCK.get()),
                GROWTH_CHANCE
        );
    }

    @Override
    public @NotNull BaseClusterBlock full()
    {
        return AllBlocks.LAZULITE_CLUSTER.get();
    }

    @Override
    public @NotNull BaseClusterBlock large()
    {
        return AllBlocks.LARGE_LAZULITE_BUD.get();
    }

    @Override
    public @NotNull BaseClusterBlock medium()
    {
        return AllBlocks.MEDIUM_LAZULITE_BUD.get();
    }

    @Override
    public @NotNull BaseClusterBlock small()
    {
        return AllBlocks.SMALL_LAZULITE_BUD.get();
    }
}
