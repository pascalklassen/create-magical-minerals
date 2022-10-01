package de.dummtrottel.magical_minerals.common.block.geode;

import org.jetbrains.annotations.NotNull;

public enum MineralType
{
    LAZULITE("lazulite");

    private final @NotNull String id;

    MineralType(@NotNull String id)
    {
        this.id = id;
    }

    public @NotNull String getId()
    {
        return id;
    }
}
