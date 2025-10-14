package dev.scrythe.unlockallrecipes.client;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagLoader;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class UnlockAllRecipesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
//        FileToIdConverter RECIPE_LISTER = FileToIdConverter.registry(Registries.RECIPE);
//        FileToIdConverter.registry(Registries.RECIPE);
//
//
//
//
////        RegistryAccess.fromRegistryOfRegistries(net.minecraft.core.registries.Registries.R)
//        RegistryOps<JsonElement> ops = Minecraft.getInstance().level.registryAccess().freeze().createSerializationContext(JsonOps.INSTANCE);
//
////        List<HolderLookup.RegistryLookup<?>> list = TagLoader.buildUpdatedLookups(registryAccess.getAccessForLoading(RegistryLayer.RELOADABLE), postponedTags);
////        HolderLookup.Provider.create(list.stream());
//        SortedMap<ResourceLocation, Recipe<?>> sortedMap = new TreeMap<>();
//        SimpleJsonResourceReloadListener.scanDirectory(resourceManager, RECIPE_LISTER, ops, Recipe.CODEC, sortedMap);
//        System.out.println(sortedMap);
    }

//    private LayeredRegistryAccess<RegistryLayer> getLayeredRegistryAccess2(ResourceManager resourceManager) {
//        LayeredRegistryAccess<RegistryLayer>  layeredRegistryAccess= RegistryLayer.createRegistryAccess();
//        List<Registry.PendingTags<?>> list = TagLoader.loadTagsForExistingRegistries(resourceManager, layeredRegistryAccess.getLayer(RegistryLayer.STATIC));
//        RegistryAccess.Frozen frozen = layeredRegistryAccess.getAccessForLoading(RegistryLayer.WORLDGEN);
//        List<HolderLookup.RegistryLookup<?>> list2 = TagLoader.buildUpdatedLookups(frozen, list);
//        RegistryAccess.Frozen frozen2 = RegistryDataLoader.load(resourceManager, list2, RegistryDataLoader.WORLDGEN_REGISTRIES);
//        WorldLoader.DataLoadOutput<D> dataLoadOutput = worldDataSupplier.get(
//                new WorldLoader.DataLoadContext(closeableResourceManager, worldDataConfiguration, provider, frozen3)
//        );
//        LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess2 = layeredRegistryAccess.replaceFrom(RegistryLayer.WORLDGEN,frozen2,dataLoudOut)
//    }
}
