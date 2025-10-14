package dev.scrythe.unlockallrecipes.mixin.client;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Map;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Shadow @Final private Map<RecipeDisplayId, RecipeDisplayEntry> known;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
//        this.known =
//        var bla =Minecraft.getInstance().level.recipeAccess();
//        Minecraft.getInstance().getResourceManager();
    }
}
