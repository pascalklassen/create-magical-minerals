package de.dummtrottel.magical_minerals.common.block.geode.lazulite;

import de.dummtrottel.magical_minerals.common.block.geode.BaseGeodeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class LazuliteBlock extends BaseGeodeBlock
{
    public LazuliteBlock()
    {
        super(
                BlockBehaviour.Properties
                        .of(Material.AMETHYST)
                        .sound(SoundType.AMETHYST)
        );
    }
}
