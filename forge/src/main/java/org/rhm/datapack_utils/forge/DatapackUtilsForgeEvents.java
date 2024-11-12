package org.rhm.datapack_utils.forge;

import org.rhm.datapack_utils.DatapackUtilsCommon;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DatapackUtilsCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DatapackUtilsForgeEvents {
    @SubscribeEvent
    public static void fuelCheck(FurnaceFuelBurnTimeEvent event) {
        if (DatapackUtilsForgeImpl.FUELS.containsKey(event.getItemStack().getItem())) {
            event.setBurnTime(DatapackUtilsForgeImpl.FUELS.getInt(event.getItemStack().getItem()));
        }
    }

    @SubscribeEvent
    public static void serverStarted(ServerStartedEvent event) {
        DatapackUtilsCommon.reloadAll(event.getServer());
    }

    @SubscribeEvent
    public static void serverReloaded(OnDatapackSyncEvent event) {
        DatapackUtilsCommon.reloadAll(event.getPlayerList().getServer());
    }
}
