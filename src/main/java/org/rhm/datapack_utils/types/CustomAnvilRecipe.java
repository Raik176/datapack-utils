package org.rhm.datapack_utils.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

//? if <1.20.1 {
/*import net.minecraft.core.Registry;
 *///?} else {
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//?}

public record CustomAnvilRecipe(AnvilRecipeInput input, Optional<AnvilRecipeInput> secondaryInput,
                                ItemStack output, int cost, boolean ignoreName) {
    public static final Codec<CustomAnvilRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AnvilRecipeInput.CODEC.fieldOf("input").forGetter(CustomAnvilRecipe::input),
            AnvilRecipeInput.CODEC.optionalFieldOf("secondaryInput").forGetter(CustomAnvilRecipe::secondaryInput),
            ItemStack.CODEC.fieldOf("output").forGetter(CustomAnvilRecipe::output),
            Codec.INT.optionalFieldOf("cost", 1).forGetter(CustomAnvilRecipe::cost),
            Codec.BOOL.optionalFieldOf("ignoreName", true).forGetter(CustomAnvilRecipe::ignoreName)
    ).apply(instance, CustomAnvilRecipe::new));

    public record AnvilRecipeInput(Optional<Item> item, Optional<TagKey<Item>> tag) {
        public static final Codec<AnvilRecipeInput> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                //? if <1.20.1 {
                /*Registry.ITEM.byNameCodec().optionalFieldOf("item").forGetter(AnvilRecipeInput::item),
                TagKey.hashedCodec(Registry.ITEM_REGISTRY).optionalFieldOf("tag").forGetter(AnvilRecipeInput::tag)
                *///?} else {
                BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("item").forGetter(AnvilRecipeInput::item),
                TagKey.hashedCodec(Registries.ITEM).optionalFieldOf("tag").forGetter(AnvilRecipeInput::tag)
                //?}
        ).apply(instance, AnvilRecipeInput::new));

        public boolean test(ItemStack stack) {
            return item.map(stack::is).orElseGet(() -> tag.isPresent() && stack.is(tag.get()));
        }
    }
}
