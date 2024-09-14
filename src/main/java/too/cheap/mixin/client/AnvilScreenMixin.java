package too.cheap.mixin.client;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import too.cheap.TooCheap;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

    // makes sure that the "Too Expensive!" text appears correctly
    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    private int injectedExpensiveText(int value) {
        return TooCheap.CONFIG.maxLevelCost;
    }
}
