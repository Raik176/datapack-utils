package org.rhm.datapack_utils.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.rhm.datapack_utils.DatapackUtilsCommon;
import org.rhm.datapack_utils.types.CustomAnvilRecipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=1.20.5 {
import net.minecraft.core.component.DataComponents;
//?}

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
    @Shadow @Final private DataSlot cost;

    @Shadow @Nullable private String itemName;

    //? if >=1.21.3 {
    /*public AnvilMenuMixin(@Nullable MenuType<?> menuType, int i, Inventory inventory, ContainerLevelAccess containerLevelAccess, ItemCombinerMenuSlotDefinition itemCombinerMenuSlotDefinition) {
        super(menuType, i, inventory, containerLevelAccess, itemCombinerMenuSlotDefinition);
    }
    *///?} else {
    public AnvilMenuMixin(@Nullable MenuType<?> menuType, int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(menuType, i, inventory, containerLevelAccess);
    }
    //?}

    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void createResult(CallbackInfo ci) {
        ItemStack input = this.inputSlots.getItem(0);
        ItemStack secondaryInput = this.inputSlots.getItem(1);

        if (!input.isEmpty()) {
            DatapackUtilsCommon.ANVIL_RECIPES.stream()
                    .filter(recipe -> datapack_utils$matchesInput(recipe, input, secondaryInput))
                    .findFirst()
                    .ifPresent((recipe) -> {
                        this.cost.set(recipe.cost());

                        ItemStack result = recipe.output().copy();

                        if (!recipe.ignoreName()) {
                            //? if >=1.20.5 {
                            if (this.itemName != null && !StringUtil.isBlank(this.itemName)) {
                                if (!this.itemName.equals(input.getHoverName().getString())) {
                                    //TODO: better compat?? idk if its worth it to try and make
                                    // this work with mods which allow e.g. colors in the name
                                    result.set(DataComponents.CUSTOM_NAME, Component.literal(this.itemName));
                                }
                            } else {
                                result.remove(DataComponents.CUSTOM_NAME);
                            }
                            //?} else {
                            /*if (this.itemName != null && !StringUtils.isBlank(this.itemName)) {
                                if (!this.itemName.equals(input.getHoverName().getString())) {
                                    result.setHoverName(Component.literal(this.itemName));
                                }
                            } else if (input.hasCustomHoverName()) {
                                result.resetHoverName();
                            }
                            *///?}
                        }

                        this.resultSlots.setItem(0, result);
                        this.broadcastChanges();

                        ci.cancel();
                    });
        }
    }

    @Unique
    private static boolean datapack_utils$matchesInput(CustomAnvilRecipe recipe, ItemStack input, ItemStack secondaryInput) {
        return recipe.input().test(input) &&
                recipe.secondaryInput().map(i -> i.test(secondaryInput)).orElse(true);
    }
}
