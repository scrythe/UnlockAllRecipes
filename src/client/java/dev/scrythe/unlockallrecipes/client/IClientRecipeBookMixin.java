package dev.scrythe.unlockallrecipes.client;

import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;

import java.util.List;

public interface IClientRecipeBookMixin {
    List<RecipeDisplayEntry> unlockAllRecipes$getAllRecipeDisplayEntries();
}
