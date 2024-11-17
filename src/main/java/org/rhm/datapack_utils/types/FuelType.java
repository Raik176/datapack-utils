package org.rhm.datapack_utils.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Optional;

//? if <1.20.1 {
/*import net.minecraft.core.Registry;
*///?} else {
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//?}

public record FuelType(Optional<Item> item, Optional<TagKey<Item>> tag, int duration) {
    public static final Codec<FuelType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            //? if <1.20.1 {
            /*Registry.ITEM.byNameCodec().optionalFieldOf("item").forGetter(FuelType::item),
            TagKey.hashedCodec(Registry.ITEM_REGISTRY).optionalFieldOf("tag").forGetter(FuelType::tag),
            *///?} else {
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("item").forGetter(FuelType::item),
            TagKey.hashedCodec(Registries.ITEM).optionalFieldOf("tag").forGetter(FuelType::tag),
            //?}
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("duration").forGetter(FuelType::duration)
    ).apply(instance, FuelType::new));
}
