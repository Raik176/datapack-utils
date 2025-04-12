package org.rhm.datapack_utils.fabric.mixin;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.world.item.ItemStack;
import org.rhm.datapack_utils.fabric.DatapackUtilsFabricImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if >=1.21.2 {
import net.minecraft.world.level.block.entity.FuelValues;
@Mixin(FuelValues.class)
public class FuelValuesMixin {
    @Inject(method = "isFuel", at = @At("TAIL"), cancellable = true)
    private void isFuel(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (DatapackUtilsFabricImpl.FUELS.containsKey(itemStack.getItem())) cir.setReturnValue(true);
    }

    @Inject(method = "burnDuration", at = @At("TAIL"), cancellable = true)
    private void burnDuration(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(DatapackUtilsFabricImpl.FUELS.getOrDefault(itemStack.getItem(),cir.getReturnValue()));
    }
}

//?} else {
/*import com.mojang.authlib.minecraft.client.MinecraftClient;
@Mixin(MinecraftClient.class)
public class FuelValuesMixin { //Don't really want to add the json processor just for this

}
*///?}