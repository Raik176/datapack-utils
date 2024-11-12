package org.rhm.datapack_utils.fabric;

import net.fabricmc.api.ModInitializer;
import org.rhm.datapack_utils.DatapackUtilsCommon;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;

public class DatapackUtilsFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		DatapackUtilsCommon.impl = new DatapackUtilsFabricImpl();
		ResourceLocation earlyReload = ResourceLocation.tryBuild(DatapackUtilsCommon.MOD_ID,"early_reload");
		ServerLifecycleEvents.SERVER_STARTED.register(earlyReload, DatapackUtilsCommon::reloadAll);
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(earlyReload, (x,y,z) -> DatapackUtilsCommon.reloadAll(x));
		DatapackUtilsCommon.init();
	}
}
