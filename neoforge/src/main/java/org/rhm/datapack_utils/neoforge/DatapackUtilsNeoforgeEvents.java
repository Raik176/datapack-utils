package org.rhm.datapack_utils.neoforge;

import org.rhm.datapack_utils.DatapackUtilsCommon;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(modid = DatapackUtilsCommon.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class DatapackUtilsNeoforgeEvents {
    @SubscribeEvent
    public static void fuelCheck(FurnaceFuelBurnTimeEvent event) {
        if (DatapackUtilsNeoforgeImpl.FUELS.containsKey(event.getItemStack().getItem())) {

            event.setBurnTime(DatapackUtilsNeoforgeImpl.FUELS.getInt(event.getItemStack().getItem()));
        }
    }

    @SubscribeEvent
    public static void serverStarted(ServerStartedEvent event) {
        DatapackUtilsCommon.reloadAll(event.getServer());
    }

    @SubscribeEvent
    public static void serverReloaded(OnDatapackSyncEvent event) {
        DatapackUtilsCommon.reloadAll(event.getPlayer().getServer());
    }
}
