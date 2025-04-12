package org.rhm.datapack_utils;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.rhm.datapack_utils.types.CompostableType;
import org.rhm.datapack_utils.types.CustomAnvilRecipe;
import org.rhm.datapack_utils.types.FuelType;
import org.rhm.datapack_utils.types.OffersType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DatapackUtilsCommon {
    public static final String MOD_ID = "datapack_utils";
    public static DatapackUtilsBase impl;
    private static Set<FuelType> FUELS = new HashSet<>();
    private static Set<CompostableType> COMPOSTABLES = new HashSet<>();
    private static final Set<OffersType> OFFERS = new HashSet<>();
    public static final Set<CustomAnvilRecipe> ANVIL_RECIPES = new HashSet<>();

    public static void init() {

    }

    public static void reloadAll(MinecraftServer server) {
        ResourceManager manager = server.getResourceManager();
        reloadFuels(manager);
        reloadCompostables(manager);
        reloadCustomTrades(manager);
        reloadAnvilRecipes(manager);
    }

    private static void reloadFuels(ResourceManager manager) {
        Set<FuelType> temp = new HashSet<>();
        for (Map.Entry<ResourceLocation, Resource> customFuels : manager.listResources("utils", (x) -> x.getPath().equalsIgnoreCase("utils/custom_fuels.json")).entrySet()) {
            Resource resource = customFuels.getValue();
            try {
                for (FuelType fuelType : FuelType.CODEC.listOf().decode(JsonOps.INSTANCE, JsonParser.parseReader(resource.openAsReader())).getOrThrow(
                        //? if <1.20.6 {
                        /*false,
                        (string) -> {
                            //fuck, should probably log here
                        }
                        *///?}
                ).getFirst()) {
                    temp.add(fuelType);
                    if (fuelType.tag().isPresent())
                        impl.registerFuel(fuelType.tag().get(), fuelType.duration());
                    else if (fuelType.item().isPresent())
                        impl.registerFuel(fuelType.item().get(), fuelType.duration());
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        for (FuelType fuelType : FUELS) {
            if (!temp.contains(fuelType)) {
                if (fuelType.tag().isPresent())
                    impl.unregisterFuel(fuelType.tag().get());
                if (fuelType.item().isPresent())
                    impl.unregisterFuel(fuelType.item().get());
            }
        }
        FUELS = temp;
    }

    private static void reloadCompostables(ResourceManager manager) {
        Set<CompostableType> temp = new HashSet<>();
        for (Map.Entry<ResourceLocation, Resource> customFuels : manager.listResources("utils", (x) -> x.getPath().equalsIgnoreCase("utils/custom_compostables.json")).entrySet()) {
            Resource resource = customFuels.getValue();
            try {
                for (CompostableType compostType : CompostableType.CODEC.listOf().decode(JsonOps.INSTANCE, JsonParser.parseReader(resource.openAsReader())).getOrThrow(
                        //? if <1.20.6 {
                        /*false,
                        (string) -> {
                            //fuck, should probably log here
                        }
                        *///?}
                ).getFirst()) {
                    temp.add(compostType);

                    if (compostType.tag().isPresent())
                        impl.registerCompostable(compostType.tag().get(), compostType.chance() / 100f);
                    else if (compostType.item().isPresent())
                        impl.registerCompostable(compostType.item().get(), compostType.chance() / 100f);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        for (CompostableType compostType : COMPOSTABLES) {
            if (!temp.contains(compostType)) {
                if (compostType.tag().isPresent())
                    impl.unregisterCompostable(compostType.tag().get());
                if (compostType.item().isPresent())
                    impl.unregisterCompostable(compostType.item().get());
            }
        }
        COMPOSTABLES = temp;
    }


    private static void reloadCustomTrades(ResourceManager manager) {
        OFFERS.clear();

        //? if >=1.20.6 {
        for (Map.Entry<ResourceLocation, Resource> customTrades : manager.listResources("utils", (x) -> x.getPath().equalsIgnoreCase("utils/custom_trades.json")).entrySet()) {
            Resource resource = customTrades.getValue();
            try {
                OFFERS.addAll(OffersType.CODEC.listOf().decode(JsonOps.INSTANCE, JsonParser.parseReader(resource.openAsReader())).getOrThrow(
                        //? if <1.20.6 {
                        /*false,
                        (string) -> {
                            //fuck, should probably log here
                        }
                        *///?}
                ).getFirst());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        //?}
    }

    private static void reloadAnvilRecipes(ResourceManager manager) {
        ANVIL_RECIPES.clear();

        for (Map.Entry<ResourceLocation, Resource> customFuels : manager.listResources("utils", (x) ->
                x.getPath().startsWith("utils/anvil_recipe/")
                && x.getPath().endsWith(".json")
        ).entrySet()) {
            Resource resource = customFuels.getValue();
            try {
                ANVIL_RECIPES.add(CustomAnvilRecipe.CODEC.decode(JsonOps.INSTANCE, JsonParser.parseReader(resource.openAsReader())).getOrThrow(
                        //? if <1.20.6 {
                        /*false,
                        (string) -> {
                            //fuck, should probably log here
                        }
                        *///?}
                ).getFirst());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static Set<OffersType> getOffersForProfession(VillagerProfession profession) {
        return OFFERS.stream().filter((offer) -> offer.profession() == profession).collect(Collectors.toSet());
    }
}
