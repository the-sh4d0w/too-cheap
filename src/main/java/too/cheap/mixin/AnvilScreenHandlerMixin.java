package too.cheap.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
	@ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
	private int injected(int value) {
		return Integer.MAX_VALUE;
	}
}