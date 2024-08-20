package too.cheap.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import too.cheap.TooCheap;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 40))
    private int injectedMaxLevel(int value) {
        return TooCheap.CONFIG.maxLevelCost;
    }

    // modify i
    @ModifyVariable(method = "updateResult()V", at = @At("STORE"), ordinal = 0)
    private int injectedEnchantmentCost(int value) {
        if (TooCheap.CONFIG.doEnchantmentCost) {
            return TooCheap.CONFIG.maybeEnchantmentCost;
        } else {
            return value;
        }
    }

    // modify j
    @ModifyVariable(method = "updateResult()V", at = @At("STORE"), ordinal = 1)
    private int injectedRepairCost(int value) {
        if (TooCheap.CONFIG.doRepairCost) {
            return TooCheap.CONFIG.maybeRepairCost;
        } else {
            return value;
        }
    }

    // modify k; currently just changes the base value; TODO: fix this
    @ModifyVariable(method = "updateResult()V", at = @At("STORE"), ordinal = 2)
    private int injectedRenameCost(int value) {
        if (TooCheap.CONFIG.doRenameCost) {
            return TooCheap.CONFIG.renameCost;
        } else {
            return value;
        }
    }

    @ModifyConstant(method = "method_24922(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", constant = @Constant(floatValue = 0.12F))
    private static float injectedBreakChance(float value) {
        return TooCheap.CONFIG.breakChance;
    }
}
