package de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster;

import de.dummtrottel.magical_minerals.common.AllBlocks;
import de.dummtrottel.magical_minerals.common.block.geode.BaseClusterBlock;
import de.dummtrottel.magical_minerals.common.block.geode.MineralType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LazuliteClusterBlock extends BaseClusterBlock
{
    protected LazuliteClusterBlock(int size, int offset, BlockBehaviour.Properties properties)
    {
        super(MineralType.LAZULITE, size, offset, properties);
    }

    public LazuliteClusterBlock()
    {
        this(7, 3,
                BlockBehaviour.Properties
                        .copy(AllBlocks.LAZULITE_BLOCK.get())
                        .sound(SoundType.AMETHYST_CLUSTER)
                        .lightLevel((state) -> 5)
        );
    }
}
