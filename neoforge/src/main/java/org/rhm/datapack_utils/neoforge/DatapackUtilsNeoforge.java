package org.rhm.datapack_utils.neoforge;

import org.rhm.datapack_utils.DatapackUtilsCommon;
import net.neoforged.fml.common.Mod;

@Mod(DatapackUtilsCommon.MOD_ID)
public class DatapackUtilsNeoforge {
	public DatapackUtilsNeoforge() {
		DatapackUtilsCommon.impl = new DatapackUtilsNeoforgeImpl();
		DatapackUtilsCommon.init();
	}
}
