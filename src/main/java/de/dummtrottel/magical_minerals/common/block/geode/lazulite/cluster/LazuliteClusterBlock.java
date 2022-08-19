package de.dummtrottel.magical_minerals.common.block.geode.lazulite.cluster;

import de.dummtrottel.magical_minerals.common.block.geode.BaseClusterBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class LazuliteClusterBlock extends BaseClusterBlock
{
    protected LazuliteClusterBlock(int size, int offset, BlockBehaviour.Properties properties)
    {
        super(size, offset, properties);
    }

    public LazuliteClusterBlock()
    {
        this(7, 3, BlockBehaviour.Properties
                        .of(Material.AMETHYST)
                        .randomTicks()
                        .sound(SoundType.AMETHYST_CLUSTER)
                        .noOcclusion()
                        .strength(1.5F)
                        .lightLevel((state) -> 5)
        );
    }
}
