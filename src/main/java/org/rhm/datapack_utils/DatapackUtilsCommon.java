package org.rhm.datapack_utils;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DatapackUtilsCommon {
	public static final String MOD_ID = "datapack_utils";
	public static DatapackUtilsBase impl;

	public static void init() {

	}

	public static void reloadAll(MinecraftServer server) {
		reloadFuels(server.getResourceManager());
		reloadCompostables(server.getResourceManager());
	}

	public static void reloadAll(ResourceManager manager) {
		reloadFuels(manager);
		reloadCompostables(manager);
	}

	private static Set<FuelType> FUELS = new HashSet<>();
	private static void reloadFuels(ResourceManager manager) {
		Set<FuelType> temp = new HashSet<>();
		for (Map.Entry<ResourceLocation, Resource> customFuels : manager.listResources("utils", (x) -> x.getPath().endsWith("custom_fuels.json")).entrySet()) {
			Resource resource = customFuels.getValue();
			try {
				for (FuelType fuelType : FuelType.CODEC.listOf().decode(JsonOps.INSTANCE, JsonParser.parseReader(resource.openAsReader())).getOrThrow().getFirst()) {
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

	private static Set<CompostableType> COMPOSTABLES = new HashSet<>();
	private static void reloadCompostables(ResourceManager manager) {
		Set<CompostableType> temp = new HashSet<>();
		for (Map.Entry<ResourceLocation, Resource> customFuels : manager.listResources("utils", (x) -> x.getPath().endsWith("custom_compostables.json")).entrySet()) {
			Resource resource = customFuels.getValue();
			try {
				for (CompostableType compostType : CompostableType.CODEC.listOf().decode(JsonOps.INSTANCE, JsonParser.parseReader(resource.openAsReader())).getOrThrow().getFirst()) {
					temp.add(compostType);

					if (compostType.tag().isPresent())
						impl.registerCompostable(compostType.tag().get(), compostType.chance()/100f);
					else if (compostType.item().isPresent())
						impl.registerCompostable(compostType.item().get(), compostType.chance()/100f);
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
}
