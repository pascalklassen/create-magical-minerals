package de.dummtrottel.magical_minerals.common.block.geode.lazulite;

import de.dummtrottel.magical_minerals.common.block.geode.BaseGeodeBlock;
import de.dummtrottel.magical_minerals.common.block.geode.MineralType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class LazuliteBlock extends BaseGeodeBlock
{
    public static final String MINERAL_NAME = "lazulite";

    public LazuliteBlock()
    {
        super(
                MineralType.LAZULITE,
                BlockBehaviour.Properties
                        .of(Material.AMETHYST)
                        .strength(1.5F)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.AMETHYST)
        );
    }
}
