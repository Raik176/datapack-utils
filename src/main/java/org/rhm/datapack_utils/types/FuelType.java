package org.rhm.datapack_utils.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Optional;

public record FuelType(Optional<Item> item, Optional<TagKey<Item>> tag, int duration) {
    public static final Codec<FuelType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("item").forGetter(FuelType::item),
            TagKey.hashedCodec(Registries.ITEM).optionalFieldOf("tag").forGetter(FuelType::tag),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("duration").forGetter(FuelType::duration)
    ).apply(instance, FuelType::new));
}
