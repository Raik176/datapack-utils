package org.rhm.datapack_utils.neoforge.mixin;

import org.rhm.datapack_utils.neoforge.DatapackUtilsNeoforgeImpl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {
    @Inject(method = "getValue", at = @At("TAIL"), cancellable = true)
    private static void getValue(ItemStack item, CallbackInfoReturnable<Float> cir) {
        if (DatapackUtilsNeoforgeImpl.COMPOSTABLES.containsKey(item.getItem()))
            cir.setReturnValue(DatapackUtilsNeoforgeImpl.COMPOSTABLES.get(item.getItem()));
    }
}
