package me.roundaround.elytraswap.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.roundaround.elytraswap.SwapHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Mixin(ElytraItem.class)
public abstract class ElytraItemMixin {
  @Inject(method = "use", at = @At("HEAD"), cancellable = true)
  private void onUse(
      World world,
      PlayerEntity user,
      Hand hand,
      CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
    info.setReturnValue(SwapHelper.equipAndSwap((ElytraItem) (Object) this, world, user, hand));
  }
}
