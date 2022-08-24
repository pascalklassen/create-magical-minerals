package de.dummtrottel.magical_minerals.common.block.geode;

import org.jetbrains.annotations.NotNull;

public interface BuddingStateProvider
{
    @NotNull BaseClusterBlock full();
    @NotNull BaseClusterBlock large();
    @NotNull BaseClusterBlock medium();
    @NotNull BaseClusterBlock small();
}
