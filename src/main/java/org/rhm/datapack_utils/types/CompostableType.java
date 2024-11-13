package org.rhm.datapack_utils.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Optional;

public record CompostableType(Optional<Item> item, Optional<TagKey<Item>> tag, int chance) {
    public static final Codec<CompostableType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("item").forGetter(CompostableType::item),
            TagKey.hashedCodec(Registries.ITEM).optionalFieldOf("tag").forGetter(CompostableType::tag),
            Codec.intRange(1, 100).fieldOf("chance").forGetter(CompostableType::chance)
    ).apply(instance, CompostableType::new));
}
