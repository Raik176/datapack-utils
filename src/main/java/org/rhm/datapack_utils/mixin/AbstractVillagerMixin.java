package org.rhm.datapack_utils.mixin;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(AbstractVillager.class)
public abstract class AbstractVillagerMixin extends AgeableMob implements InventoryCarrier, Npc, Merchant {
    protected AbstractVillagerMixin(EntityType<? extends AgeableMob> entityType, Level level) {
        super(entityType, level);
    }

    //? if >=1.20.6 {
    @ModifyVariable(
            method = "addOffersFromItemListings",
            at = @At("HEAD"),
            argsOnly = true
    )
    private VillagerTrades.ItemListing[] datapack_utils$onUpdateTrades(VillagerTrades.ItemListing[] itemListings) {
        return datapack_utils$modifyTradings(new ArrayList<>(List.of(itemListings))).toArray(VillagerTrades.ItemListing[]::new);
    }
    //?}

    @Unique
    protected List<VillagerTrades.ItemListing> datapack_utils$modifyTradings(List<VillagerTrades.ItemListing> original) {
        return original;
    }
}
