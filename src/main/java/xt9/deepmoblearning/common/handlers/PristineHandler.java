package xt9.deepmoblearning.common.handlers;

import net.minecraft.item.ItemStack;
import xt9.deepmoblearning.common.items.ItemPristineMatter;

import javax.annotation.Nonnull;

/**
 * Created by xt9 on 2018-01-08.
 */
public class PristineHandler extends BaseItemHandler {
    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if(stack.getItem() instanceof ItemPristineMatter) {
            return super.insertItem(slot, stack, simulate);
        } else {
            return stack;
        }
    }
}
