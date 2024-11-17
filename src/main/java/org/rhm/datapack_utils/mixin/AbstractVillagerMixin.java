package org.rhm.datapack_utils.mixin;

import com.google.common.collect.Lists;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;

@Mixin(AbstractVillager.class)
public abstract class AbstractVillagerMixin extends AgeableMob implements InventoryCarrier, Npc, Merchant {
    protected AbstractVillagerMixin(EntityType<? extends AgeableMob> entityType, Level level) {
        super(entityType, level);
    }

    //? if >=1.20.6 {
    @Redirect(method = "addOffersFromItemListings", at = @At(
            value = "INVOKE",
            target = "Lcom/google/common/collect/Lists;newArrayList([Ljava/lang/Object;)Ljava/util/ArrayList;"
    ))
    protected ArrayList<VillagerTrades.ItemListing> datapack_utils$onUpdateTrades(Object[] elements) {
        return Lists.newArrayList((VillagerTrades.ItemListing[]) elements);
    }
    //?}
}
