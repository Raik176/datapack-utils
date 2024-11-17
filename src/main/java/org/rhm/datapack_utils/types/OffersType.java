package org.rhm.datapack_utils.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
//? if >=1.20.6 {
import net.minecraft.core.registries.BuiltInRegistries;
//?}
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.List;

public record OffersType(VillagerProfession profession, List<MerchantOffer> offers, boolean replace) {
    //? if >=1.20.6 {
    public static final Codec<OffersType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.VILLAGER_PROFESSION.byNameCodec().fieldOf("profession").forGetter(OffersType::profession),
            MerchantOffer.CODEC.listOf().fieldOf("offers").forGetter(OffersType::offers),
            Codec.BOOL.fieldOf("replace").orElse(false).forGetter(OffersType::replace)
    ).apply(instance, OffersType::new));
    //?}
}
