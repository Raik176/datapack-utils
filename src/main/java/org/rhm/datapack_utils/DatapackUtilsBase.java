package org.rhm.datapack_utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public interface DatapackUtilsBase {
    void registerFuel(ItemLike item, int duration);
    void registerFuel(TagKey<Item> tag, int duration);

    void unregisterFuel(ItemLike item);
    void unregisterFuel(TagKey<Item> tag);

    void registerCompostable(ItemLike item, float chance);
    void registerCompostable(TagKey<Item> tag, float chance);

    void unregisterCompostable(ItemLike item);
    void unregisterCompostable(TagKey<Item> tag);

    default void forAllItemsInTag(TagKey<Item> tag, Consumer<Item> consumer) {
        BuiltInRegistries.ITEM.stream().filter(item -> item.getDefaultInstance().is(tag)).forEach(consumer);
    }
}
