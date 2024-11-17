package org.rhm.datapack_utils.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Optional;

//? if <1.20.1 {
/*import net.minecraft.core.Registry;
*///?} else {
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//?}

public record CompostableType(Optional<Item> item, Optional<TagKey<Item>> tag, int chance) {
    public static final Codec<CompostableType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            //? if <1.20.1 {
            /*Registry.ITEM.byNameCodec().optionalFieldOf("item").forGetter(CompostableType::item),
            TagKey.hashedCodec(Registry.ITEM_REGISTRY).optionalFieldOf("tag").forGetter(CompostableType::tag),
            *///?} else {
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("item").forGetter(CompostableType::item),
            TagKey.hashedCodec(Registries.ITEM).optionalFieldOf("tag").forGetter(CompostableType::tag),
            //?}
            Codec.intRange(1, 100).fieldOf("chance").forGetter(CompostableType::chance)
    ).apply(instance, CompostableType::new));
}
