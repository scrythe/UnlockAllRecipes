package dev.scrythe.unlockallrecipes.client;

import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;

import java.util.List;
import java.util.Map;

public interface IClientRecipeBookMixin {
    Map<RecipeDisplayId, RecipeDisplayEntry> unlockAllRecipes$getUnlockedRecipes();
    Map<RecipeDisplayId, RecipeDisplayEntry> unlockAllRecipes$getKnownRecipes();
}
