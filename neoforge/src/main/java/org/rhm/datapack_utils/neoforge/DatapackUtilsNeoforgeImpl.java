package org.rhm.datapack_utils.neoforge;

import org.rhm.datapack_utils.DatapackUtilsBase;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class DatapackUtilsNeoforgeImpl implements DatapackUtilsBase {
    public static final Object2IntMap<ItemLike> FUELS = new Object2IntOpenHashMap<>();
    public static final Object2FloatMap<ItemLike> COMPOSTABLES = new Object2FloatOpenHashMap<>();


    @Override
    public void registerFuel(ItemLike item, int duration) {
        FUELS.put(item, duration);
    }

    @Override
    public void registerFuel(TagKey<Item> tag, int duration) {
        forAllItemsInTag(tag, (item) -> FUELS.put(item,duration));
    }

    @Override
    public void unregisterFuel(ItemLike item) {
        FUELS.remove(item);
    }

    @Override
    public void unregisterFuel(TagKey<Item> tag) {
        forAllItemsInTag(tag, FUELS::remove);
    }

    @Override
    public void registerCompostable(ItemLike item, float chance) {
        COMPOSTABLES.put(item, chance);
    }

    @Override
    public void registerCompostable(TagKey<Item> tag, float chance) {
        forAllItemsInTag(tag, (item) -> COMPOSTABLES.put(item,chance));
    }

    @Override
    public void unregisterCompostable(ItemLike item) {
        COMPOSTABLES.remove(item);
    }

    @Override
    public void unregisterCompostable(TagKey<Item> tag) {
        forAllItemsInTag(tag, COMPOSTABLES::remove);
    }
}
