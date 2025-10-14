package dev.scrythe.unlockallrecipes.mixin.client;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(RecipeManager.class)
public interface RecipeManagerInvoker {
    @Invoker
    RecipeMap invokePrepare(ResourceManager resourceManager, ProfilerFiller profiler);
    @Invoker
    void invokeApply(RecipeMap object, ResourceManager resourceManager, ProfilerFiller profiler);
    @Accessor
    List<RecipeManager.ServerDisplayInfo> getAllDisplays();
}
