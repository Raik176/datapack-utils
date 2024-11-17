package org.rhm.datapack_utils.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.rhm.datapack_utils.DatapackUtilsCommon;
import org.rhm.datapack_utils.types.OffersType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillagerMixin {
    @Shadow public abstract VillagerData getVillagerData();

    protected VillagerMixin(EntityType<? extends AgeableMob> entityType, Level level) {
        super(entityType, level);
    }

    //? if >=1.20.6 {
    @Override
    protected ArrayList<VillagerTrades.ItemListing> datapack_utils$onUpdateTrades(Object[] elements) {
        ArrayList<VillagerTrades.ItemListing> listings = super.datapack_utils$onUpdateTrades(elements);

        for (OffersType offersType : DatapackUtilsCommon.getOffersForProfession(getVillagerData().getProfession())) {
            if (offersType.replace()) listings.clear();

            for (MerchantOffer offer : offersType.offers()) {
                listings.add(new VillagerTrades.ItemListing() {
                    @Override
                    public @Nullable MerchantOffer getOffer(Entity entity, RandomSource randomSource) {
                        return offer;
                    }
                });
            }
        }

        return listings;
    }
    //?}
}
