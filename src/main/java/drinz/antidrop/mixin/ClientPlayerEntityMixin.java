package drinz.antidrop.mixin;

import drinz.antidrop.config.TotemDropConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(
            method = "dropSelectedItem(Z)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventTotemDrop(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
        if (!TotemDropConfig.enableMod) {
            return;
        }
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            cir.setReturnValue(false);
        }
    }
}
