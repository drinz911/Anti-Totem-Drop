package drinz.antidrop.mixin;

import drinz.antidrop.config.TotemDropConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class AntiDropHandledScreenMixin {
    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (!TotemDropConfig.enableMod) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        Slot slot = ((HandledScreenMixin) (Object) this).getFocusedSlot();
        if (slot != null && slot.getStack().getItem() == Items.TOTEM_OF_UNDYING) {
            KeyBinding dropKey = mc.options.dropKey;
            if (dropKey.matchesKey(keyCode, scanCode)) {
                cir.setReturnValue(true);
            }
        }
    }
}
