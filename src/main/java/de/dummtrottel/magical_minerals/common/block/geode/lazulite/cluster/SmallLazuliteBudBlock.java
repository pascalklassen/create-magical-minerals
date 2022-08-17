package de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class SmallLazuliteBudBlock extends LazuliteClusterBlock
{
    public SmallLazuliteBudBlock()
    {
        super(3, 4, BlockBehaviour.Properties
                .copy(AllBlocks.LAZULITE_CLUSTER.get())
                .sound(SoundType.SMALL_AMETHYST_BUD)
                .lightLevel((state) -> 1)
        );
    }
}
