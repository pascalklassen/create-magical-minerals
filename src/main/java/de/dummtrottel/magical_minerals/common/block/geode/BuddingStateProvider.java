package de.dummtrottel.magical_minerals.common.block.geode;

import net.minecraft.world.level.block.Block;

public interface BuddingStateProvider
{
    Block full();
    Block large();
    Block medium();
    Block small();
}
