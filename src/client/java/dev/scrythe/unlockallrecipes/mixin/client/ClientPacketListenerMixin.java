package dev.scrythe.unlockallrecipes.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.scrythe.unlockallrecipes.client.IClientRecipeBookMixin;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @Shadow
    public abstract RegistryAccess.Frozen registryAccess();

    @Inject(method = "handleRecipeBookAdd", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;refreshRecipeBook(Lnet/minecraft/client/ClientRecipeBook;)V"))
    private void handleRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci, @Local ClientRecipeBook clientRecipeBook) {
        if (packet.replace()) {
            List<RecipeDisplayEntry> allRecipeDisplayEntries = ((IClientRecipeBookMixin) clientRecipeBook).unlockAllRecipes$getAllRecipeDisplayEntries();
            allRecipeDisplayEntries.forEach(clientRecipeBook::add);
        }
    }
}
