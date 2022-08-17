package de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class MediumLazuliteBudBlock extends LazuliteClusterBlock
{
    public MediumLazuliteBudBlock()
    {
        super(4, 3, BlockBehaviour.Properties
                .copy(AllBlocks.LAZULITE_CLUSTER.get())
                .sound(SoundType.SMALL_AMETHYST_BUD)
                .lightLevel((state) -> 3)
        );
    }
}
