package dev.scrythe.unlockallrecipes.mixin.client;

import dev.scrythe.unlockallrecipes.client.IClientRecipeBookMixin;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Debug(export = true)
@Mixin(ClientRecipeBook.class)
public abstract class ClientRecipeBookMixin implements IClientRecipeBookMixin {
    @Unique
    private final Map<RecipeDisplayId, RecipeDisplayEntry> unlockedRecipes = new HashMap<>();
    @Unique
    private List<RecipeManager.ServerDisplayInfo> allRecipes;
    @Shadow
    @Final
    private Map<RecipeDisplayId, RecipeDisplayEntry> known;

    @Shadow
    @Final
    private Set<RecipeDisplayId> highlight;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        this.allRecipes = getAllRecipes();
        allRecipes.forEach(serverDisplayInfo -> {
            RecipeDisplayEntry recipeDisplayEntry = serverDisplayInfo.display();
            known.put(recipeDisplayEntry.id(), recipeDisplayEntry);
        });
    }

    @Inject(method = "add", at = @At("HEAD"))
    private void addUnlocked(RecipeDisplayEntry recipe, CallbackInfo ci) {
        unlockedRecipes.put(recipe.id(), recipe);
    }

    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    public void removeUnlocked(RecipeDisplayId recipe, CallbackInfo ci) {
        unlockedRecipes.remove(recipe);
        highlight.remove(recipe);
        ci.cancel();
    }

    @Inject(method = "clear", at = @At("HEAD"), cancellable = true)
    public void clearUnlocked(CallbackInfo ci) {
        unlockedRecipes.clear();
        highlight.clear();
        ci.cancel();
    }

    @Unique
    public List<RecipeDisplayEntry> unlockAllRecipes$getAllRecipeDisplayEntries() {
        return allRecipes.stream().map((RecipeManager.ServerDisplayInfo::display)).toList();
    }

    @Unique
    public Map<RecipeDisplayId, RecipeDisplayEntry> unlockAllRecipes$getUnlockedRecipes() {
        return unlockedRecipes;
    }

    public Map<RecipeDisplayId, RecipeDisplayEntry> unlockAllRecipes$getKnownRecipes() {
        return known;
    }

    @Unique
    private List<RecipeManager.ServerDisplayInfo> getAllRecipes() {
        PackRepository packRepository = Minecraft.getInstance().getResourcePackRepository();
        List<PackResources> list = packRepository.openAllSelected();
        CloseableResourceManager closeableResourceManager = new MultiPackResourceManager(PackType.SERVER_DATA, list);
        RecipeManager recipeManager = new RecipeManager(Minecraft.getInstance().level.registryAccess());
        RecipeManagerInvoker recipeManagerInvoker = (RecipeManagerInvoker) recipeManager;
        RecipeMap object = recipeManagerInvoker.invokePrepare(closeableResourceManager, Profiler.get());
        recipeManagerInvoker.invokeApply(object, closeableResourceManager, Profiler.get());
        FeatureFlagSet featureFlags = Minecraft.getInstance().level.enabledFeatures();
        recipeManager.finalizeRecipeLoading(featureFlags);
        return recipeManagerInvoker.getAllDisplays();
    }
}
