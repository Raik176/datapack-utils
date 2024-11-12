package org.rhm.datapack_utils.forge;

import org.rhm.datapack_utils.DatapackUtilsBase;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class DatapackUtilsForgeImpl implements DatapackUtilsBase {
    public static final Object2IntMap<ItemLike> FUELS = Object2IntMaps.emptyMap();

    @Override
    public void registerFuel(ItemLike item, int duration) {
        FUELS.put(item, duration);
    }

    @Override
    public void registerFuel(TagKey<Item> tag, int duration) {
        forAllItemsInTag(tag, (item) -> FUELS.put(item, duration));
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
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    @Override
    public void registerCompostable(TagKey<Item> tag, float chance) {
        forAllItemsInTag(tag, (item) -> ComposterBlock.COMPOSTABLES.put(item, chance));
    }

    @Override
    public void unregisterCompostable(ItemLike item) {
        ComposterBlock.COMPOSTABLES.remove(item);
    }

    @Override
    public void unregisterCompostable(TagKey<Item> tag) {
        forAllItemsInTag(tag, ComposterBlock.COMPOSTABLES::remove);
    }
}
