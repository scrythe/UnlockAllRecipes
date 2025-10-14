package dev.scrythe.unlockallrecipes.mixin.client;

import com.mojang.serialization.Dynamic;
import net.minecraft.server.Main;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.server.packs.repository.PackRepository;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Main.class)
public interface MainMixin {
    @Invoker
    static WorldLoader.InitConfig invokeLoadOrCreateConfig(DedicatedServerProperties dedicatedServerProperties, @Nullable Dynamic<?> dynamic, boolean safeMode, PackRepository packRepository) {
        return null;
    }
}