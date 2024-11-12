package org.rhm.datapack_utils.forge;

import org.rhm.datapack_utils.DatapackUtilsCommon;
import net.minecraftforge.fml.common.Mod;

@Mod(DatapackUtilsCommon.MOD_ID)
public class DatapackUtilsForge {
	public DatapackUtilsForge() {
		DatapackUtilsCommon.impl = new DatapackUtilsForgeImpl();
		DatapackUtilsCommon.init();
	}
}
