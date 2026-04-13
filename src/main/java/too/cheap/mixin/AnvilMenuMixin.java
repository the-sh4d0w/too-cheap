package too.cheap.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import too.cheap.TooCheap;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Shadow
    @Final
    private DataSlot cost;

    // modify the next cost calculation
    @Inject(method = "calculateIncreasedRepairCost(I)I", at = @At("HEAD"), cancellable = true)
    private static void injectedNextCost(int cost, CallbackInfoReturnable<Integer> info) {
        info.setReturnValue(cost * TooCheap.CONFIG.nextCoefficient + TooCheap.CONFIG.nextSummand);
    }

    // modifies the max cost level
    @ModifyConstant(method = "createResult()V", constant = @Constant(intValue = 40))
    private int injectedMaxLevel(int value) {
        return TooCheap.CONFIG.maxLevelCost;
    }

    // modified the max cost level replacement
    @ModifyConstant(method = "createResult()V", constant = @Constant(intValue = 39))
    private int injectedMaxLevelReplace(int value) {
        return TooCheap.CONFIG.maxLevelCost - 1;
    }

    @Inject(method = "createResult()V", at = @At("RETURN"))
    private void injectedLevelCostCap(CallbackInfo ci) {
        if (TooCheap.CONFIG.doLevelCostCap && this.cost.get() >= TooCheap.CONFIG.levelCostCap) {
            this.cost.set(TooCheap.CONFIG.levelCostCap);
        }
    }

    // modify anvil break chance
    @ModifyConstant(method = "lambda$onTake$0", constant = @Constant(floatValue = 0.12F))
    private static float injectedBreakChance(float value) {
        return TooCheap.CONFIG.breakChance;
    }

    // TODO: remove the experimental stuff
    // modifies of initial value of i
    @ModifyVariable(method = "createResult()V", at = @At("STORE"), ordinal = 0)
    private int injectedEnchantmentCost(int value) {
        if (TooCheap.CONFIG.doEnchantmentCost) {
            return TooCheap.CONFIG.maybeEnchantmentCost;
        } else {
            return value;
        }
    }

    // modifies initial value of j
    @ModifyVariable(method = "createResult()V", at = @At("STORE"), ordinal = 1)
    private int injectedRepairCost(int value) {
        if (TooCheap.CONFIG.doRepairCost) {
            return TooCheap.CONFIG.maybeRepairCost;
        } else {
            return value;
        }
    }

    // modifies initial value of k
    @ModifyVariable(method = "createResult()V", at = @At("STORE"), ordinal = 2)
    private int injectedRenameCost(int value) {
        if (TooCheap.CONFIG.doRenameCost) {
            return TooCheap.CONFIG.renameCost;
        } else {
            return value;
        }
    }
}
