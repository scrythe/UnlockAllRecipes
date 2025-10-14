package dev.scrythe.unlockallrecipes.mixin.client;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Main;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.*;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @Shadow public abstract RegistryAccess.Frozen registryAccess();

    @Inject(method = "handleRecipeBookAdd", at = @At("HEAD"))
    private void handleRecipeBookAdd(ClientboundRecipeBookAddPacket packet, CallbackInfo ci) {
//        if (packet.replace()) {
////            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
////            FileToIdConverter RECIPE_LISTER = FileToIdConverter.registry(Registries.RECIPE);
////            FileToIdConverter.registry(Registries.RECIPE);
////
////
////            RegistryOps<JsonElement> ops = this.registryAccess().freeze().createSerializationContext(JsonOps.INSTANCE);
//////        RegistryAccess.fromRegistryOfRegistries(net.minecraft.core.registries.Registries.R)
//////            RegistryOps<JsonElement> ops = Minecraft.getInstance().player.registryAccess().freeze().createSerializationContext(JsonOps.INSTANCE);
////
//////        List<HolderLookup.RegistryLookup<?>> list = TagLoader.buildUpdatedLookups(registryAccess.getAccessForLoading(RegistryLayer.RELOADABLE), postponedTags);
//////        HolderLookup.Provider.create(list.stream());
////            SortedMap<ResourceLocation, Recipe<?>> sortedMap = new TreeMap<>();
////            SimpleJsonResourceReloadListener.scanDirectory(resourceManager, RECIPE_LISTER, ops, Recipe.CODEC, sortedMap);
////            System.out.println(sortedMap);
////        }
////        packet.entries().clear();

        if (packet.replace()) {
//            WorldLoader.InitConfig initConfig = MainMixin2.loadOrCreateConfig(dedicatedServerSettings.getProperties(), dynamic2, bl, packRepository);

//            DirectoryValidator directoryValidator = Minecraft.getInstance().directoryValidator();
//            PackRepository packRepository = ServerPacksSource.createPackRepository(Path.of("D:\\projects\\UnlockAllRecipes\\run\\saves\\New World\\datapacks"),directoryValidator);
            PackRepository packRepository = Minecraft.getInstance().getResourcePackRepository();
//            Collection<PackResources> collection = packRepository.openAllSelected();
            List<PackResources> list = packRepository.openAllSelected();
//            ImmutableList<PackResources> immutableList = collection.stream().map(packRepository::getPack).filter(Objects::nonNull).map(Pack::open).collect(ImmutableList.toImmutableList());
            CloseableResourceManager closeableResourceManager = new MultiPackResourceManager(PackType.SERVER_DATA, list);

////            PackRepository packRepository = ServerPacksSource.createPackRepository(levelStorageAccess);
//            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
//            RecipeManager recipeManager = new RecipeManager(this.registryAccess());
//            RecipeMap object = ((RecipeManagerInvoker) recipeManager).invokePrepare(closeableResourceManager, Profiler.get());
//            ((RecipeManagerInvoker) recipeManager).invokeApply(object, closeableResourceManager, Profiler.get());
//            System.out.println(recipeManager.getRecipes());
        }
    }

//    @Inject(method = "handleRecipeBookAdd", at = @At("TAIL"))
//    private void handleRecipeBookAdd2(ClientboundRecipeBookAddPacket packet, CallbackInfo ci) {
//        if (!packet.replace()) {
//            var bla = Minecraft.getInstance().level.registryAccess();
//            System.out.println("hm");
//        }
//    }
}
