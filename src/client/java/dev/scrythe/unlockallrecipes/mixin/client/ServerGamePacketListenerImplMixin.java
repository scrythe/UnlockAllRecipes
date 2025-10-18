package dev.scrythe.unlockallrecipes.mixin.client;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//@Mixin(ServerGamePacketListenerImpl.class)
//public class ServerGamePacketListenerImplMixin {
//    @Redirect(method = "handlePlaceRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/RecipeBookMenu;handlePlacement(ZZLnet/minecraft/world/item/crafting/RecipeHolder;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/player/Inventory;)Lnet/minecraft/world/inventory/RecipeBookMenu$PostPlaceAction;"))
//    private RecipeBookMenu.PostPlaceAction handlePlaceRecipe(RecipeBookMenu instance, boolean useMaxItems, boolean isCreative, RecipeHolder<?> recipe, ServerLevel level, Inventory playerInventory) {
//        return RecipeBookMenu.PostPlaceAction.NOTHING;
//    }
//}
