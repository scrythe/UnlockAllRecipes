package dev.scrythe.unlockallrecipes.mixin.client;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(Connection.class)
//public class ConnectionMixin {
//    private boolean print = false;
//
//    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/protocol/Packet;)V", at = @At("HEAD"))
//    private void channelRead0(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
//        if (print) {
//            System.out.println(packet);
//        }
//    }
//
//    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;)V", at = @At("HEAD"))
//    private void send(Packet<?> packet, CallbackInfo ci) {
//        if (packet instanceof ServerboundPlaceRecipePacket) {
//            print = !print;
//        }
//        if (print) {
//            System.out.println(packet);
//        }
//    }
//}
