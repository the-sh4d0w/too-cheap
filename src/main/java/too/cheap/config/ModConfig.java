package too.cheap.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "too-cheap")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public int maxLevelCost = Integer.MAX_VALUE;
    @ConfigEntry.Gui.Tooltip
    public float breakChance = 0.12F;
    @ConfigEntry.Gui.Tooltip
    public boolean doLevelCostCap = false;
    @ConfigEntry.Gui.Tooltip
    public int levelCostCap = 30;
    @ConfigEntry.Gui.Tooltip
    public int nextCoefficient = 2;
    @ConfigEntry.Gui.Tooltip
    public int nextSummand = 1;

    // doesn't do much currently, expand later
    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.Tooltip
    public boolean doRenameCost = false;
    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.Tooltip
    public int renameCost = 1;
    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.Tooltip
    public boolean doRepairCost = false;
    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.Tooltip
    public int maybeRepairCost = 0;
    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.Tooltip
    public boolean doEnchantmentCost = false;
    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.Tooltip
    public int maybeEnchantmentCost = 0;
}
