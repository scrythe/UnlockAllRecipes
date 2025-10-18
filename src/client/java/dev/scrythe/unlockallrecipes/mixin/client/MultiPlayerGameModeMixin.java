package dev.scrythe.unlockallrecipes.mixin.client;

import dev.scrythe.unlockallrecipes.client.IClientRecipeBookMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.network.protocol.game.ClientboundPlaceGhostRecipePacket;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @Shadow @Final private ClientPacketListener connection;

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "handlePlaceRecipe", at = @At("HEAD"), cancellable = true)
    void handlePlaceRecipe(int containerId, RecipeDisplayId recipe, boolean useMaxItems, CallbackInfo ci) {
        IClientRecipeBookMixin clientRecipeBookMixin = (IClientRecipeBookMixin) this.minecraft.player.getRecipeBook();
        boolean hasClientUnlockedRecipe = clientRecipeBookMixin.unlockAllRecipes$getUnlockedRecipes().containsKey(recipe);
        if (!hasClientUnlockedRecipe) {
            RecipeDisplay recipeDisplay = clientRecipeBookMixin.unlockAllRecipes$getKnownRecipes().get(recipe).display();
            ClientboundPlaceGhostRecipePacket clientboundPlaceGhostRecipePacket = new ClientboundPlaceGhostRecipePacket(containerId, recipeDisplay);
            connection.handlePlaceRecipe(clientboundPlaceGhostRecipePacket);
            ci.cancel();
        }
    }
}
