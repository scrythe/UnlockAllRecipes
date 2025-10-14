
package dev.scrythe.unlockallrecipes.mixin.client;

import com.mojang.serialization.Dynamic;
import net.minecraft.server.Main;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.server.packs.repository.PackRepository;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Main.class)
public class MainMixin2 {
    @Shadow
    static WorldLoader.InitConfig loadOrCreateConfig(DedicatedServerProperties dedicatedServerProperties, @Nullable Dynamic<?> dynamic, boolean safeMode, PackRepository packRepository) {
        return null;
    }
}
