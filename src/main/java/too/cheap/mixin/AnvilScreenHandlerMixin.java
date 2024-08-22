package too.cheap.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import too.cheap.TooCheap;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    // modify the next cost calculation
    @Inject(method = "getNextCost(I)I", at = @At("HEAD"), cancellable = true)
    private static void injectedNextCost(int cost, CallbackInfoReturnable<Integer> info) {
        info.setReturnValue(cost * TooCheap.CONFIG.nextCoefficient + TooCheap.CONFIG.nextSummand);
    }

    // modifies the max cost level
    @ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 40))
    private int injectedMaxLevel(int value) {
        return TooCheap.CONFIG.maxLevelCost;
    }

    // modified the max cost level replacement
    @ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 39))
    private int injectedMaxLevelReplace(int value) {
        return TooCheap.CONFIG.maxLevelCost - 1;
    }

    // modifies of initial value of i
    @ModifyVariable(method = "updateResult()V", at = @At("STORE"), ordinal = 0)
    private int injectedEnchantmentCost(int value) {
        if (TooCheap.CONFIG.doEnchantmentCost) {
            return TooCheap.CONFIG.maybeEnchantmentCost;
        } else {
            return value;
        }
    }

    // modifies initial value of j
    @ModifyVariable(method = "updateResult()V", at = @At("STORE"), ordinal = 1)
    private int injectedRepairCost(int value) {
        if (TooCheap.CONFIG.doRepairCost) {
            return TooCheap.CONFIG.maybeRepairCost;
        } else {
            return value;
        }
    }

    // modifies initial value of k
    @ModifyVariable(method = "updateResult()V", at = @At("STORE"), ordinal = 2)
    private int injectedRenameCost(int value) {
        if (TooCheap.CONFIG.doRenameCost) {
            return TooCheap.CONFIG.renameCost;
        } else {
            return value;
        }
    }

    // modify anvil break chance
    @ModifyConstant(method = "method_24922(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", constant = @Constant(floatValue = 0.12F))
    private static float injectedBreakChance(float value) {
        return TooCheap.CONFIG.breakChance;
    }
}
