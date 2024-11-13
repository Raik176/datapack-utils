package org.rhm.datapack_utils.mixin;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(TagLoader.class)
public abstract class TagLoaderMixin<T> {
    @Shadow
    @Final
    private String directory;

    @Inject(method = "load", at = @At("TAIL"), cancellable = true)
    private void load(ResourceManager resourceManager, CallbackInfoReturnable<Map<ResourceLocation, List<TagLoader.EntryWithSource>>> cir) {
        String registry = directory.replaceFirst("tags/", "");
        Map<ResourceLocation, List<TagLoader.EntryWithSource>> loadedTags = cir.getReturnValue();
        for (ResourceLocation resourceLocation : loadedTags.keySet()) {
            Set<String> toRemove = new HashSet<>();
            for (Map.Entry<ResourceLocation, Resource> tags : resourceManager.listResources("utils/inverse-tags/" + registry, (x) -> x.getPath().endsWith(resourceLocation.getPath() + ".json")).entrySet()) {
                if (!tags.getKey().getNamespace().equals(resourceLocation.getNamespace())) continue;
                try {
                    toRemove.addAll(TagEntry.CODEC.listOf().decode(
                            JsonOps.INSTANCE,
                            JsonParser.parseReader(tags.getValue().openAsReader())
                    ).getOrThrow().getFirst().stream().map(TagEntry::toString).collect(Collectors.toSet()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!toRemove.isEmpty()) {
                List<TagLoader.EntryWithSource> tags = loadedTags.get(resourceLocation);
                for (TagLoader.EntryWithSource entryWithSource : new HashSet<>(tags)) {
                    if (toRemove.contains(entryWithSource.entry().toString()))
                        tags.remove(entryWithSource);
                }
                loadedTags.put(resourceLocation, tags);
            }
        }

        cir.setReturnValue(loadedTags);
    }
}
