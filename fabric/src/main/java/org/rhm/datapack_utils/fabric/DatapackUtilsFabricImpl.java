package org.rhm.datapack_utils.fabric;

import net.minecraft.world.level.Level;
import org.rhm.datapack_utils.DatapackUtilsBase;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

//? if <1.21.2 {
/*import net.fabricmc.fabric.api.registry.FuelRegistry;
 *///?} else {
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
//?}

public class DatapackUtilsFabricImpl implements DatapackUtilsBase {
    //? if >=1.21.2
    public static final Object2IntMap<ItemLike> FUELS = new Object2IntOpenHashMap<>();


    @Override
    public void registerFuel(ItemLike item, int duration) {
        //? if <1.21.2 {
        /*FuelRegistry.INSTANCE.add(item, duration);
        *///?} else
        FUELS.put(item, duration);
    }

    @Override
    public void registerFuel(TagKey<Item> tag, int duration) {
        //? if <1.21.2 {
        /*FuelRegistry.INSTANCE.add(tag, duration);
         *///?} else
        forAllItemsInTag(tag, (item) -> FUELS.put(item, duration));
    }

    @Override
    public void unregisterFuel(ItemLike item) {
        //? if <1.21.2 {
        /*FuelRegistry.INSTANCE.remove(item);
         *///?} else
        FUELS.remove(item);
    }

    @Override
    public void unregisterFuel(TagKey<Item> tag) {
        //? if <1.21.2 {
        /*FuelRegistry.INSTANCE.remove(tag);
         *///?} else
        forAllItemsInTag(tag, FUELS::remove);
    }

    @Override
    public void registerCompostable(ItemLike item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item, chance);
    }

    @Override
    public void registerCompostable(TagKey<Item> tag, float chance) {
        CompostingChanceRegistry.INSTANCE.add(tag, chance);
    }

    @Override
    public void unregisterCompostable(ItemLike item) {
        CompostingChanceRegistry.INSTANCE.remove(item);
    }

    @Override
    public void unregisterCompostable(TagKey<Item> tag) {
        CompostingChanceRegistry.INSTANCE.remove(tag);
    }
}
