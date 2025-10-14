package dev.scrythe.unlockallrecipes.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
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
            PackRepository packRepository = Minecraft.getInstance().getResourcePackRepository();
            List<PackResources> list = packRepository.openAllSelected();
            CloseableResourceManager closeableResourceManager = new MultiPackResourceManager(PackType.SERVER_DATA, list);
//            FileToIdConverter RECIPE_LISTER = FileToIdConverter.registry(Registries.RECIPE);
//            RegistryOps<JsonElement> ops = this.registryAccess().createSerializationContext(JsonOps.INSTANCE);
//            SortedMap<ResourceLocation, Recipe<?>> sortedMap = new TreeMap<>();
//            SimpleJsonResourceReloadListener.scanDirectory(closeableResourceManager, RECIPE_LISTER, ops, Recipe.CODEC, sortedMap);

            RecipeManager recipeManager = new RecipeManager(this.registryAccess());
            RecipeMap object = ((RecipeManagerInvoker) recipeManager).invokePrepare(closeableResourceManager, Profiler.get());
            ((RecipeManagerInvoker) recipeManager).invokeApply(object, closeableResourceManager, Profiler.get());

            FeatureFlagSet featureFlags = Minecraft.getInstance().level.enabledFeatures();
            recipeManager.finalizeRecipeLoading(featureFlags);

            ((RecipeManagerInvoker) recipeManager).getAllDisplays()
                    .forEach(serverDisplayInfo -> clientRecipeBook.add(serverDisplayInfo.display()));

        }
    }

//    private void finalizeRecipeLoading() {
//        this.recipes.values()
//    }
}
