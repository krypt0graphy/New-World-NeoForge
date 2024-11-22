package com.kryptography.newworld.common.blocks.entity;

import com.kryptography.newworld.common.blocks.TombstoneBlock;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.NWItems;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

public class TombstoneBlockEntity extends RandomizableContainerBlockEntity {

    private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);

    public TombstoneBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NWBlockEntityTypes.TOMBSTONE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.items, pRegistries);
        }
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag)) {
            ContainerHelper.loadAllItems(pTag, this.items, pRegistries);
        }
        super.loadAdditional(pTag, pRegistries);
    }

    @Override
    public int getContainerSize() {
        return 45;
    }

    protected NonNullList<ItemStack> getInventory() {
        return this.items;
    }


    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.tombstone");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return ChestMenu.threeRows(pId, pPlayer, this);
    }

    public int getEmptySlot() {
        for(int i = 0; i < getContainerSize(); ++i) {
            if ((items.get(i)).isEmpty()) {
                return i;
            }
        }

        return -1;
    }

    public int getCompatibleSlot(ItemStack stack) {
        for(int i = 0; i < getContainerSize(); ++i) {
            ItemStack currentStack = items.get(i);
            if (currentStack.isEmpty() || (ItemStack.isSameItemSameComponents(currentStack, stack) && currentStack.getCount() < currentStack.getMaxStackSize())) {
                return i;
            }
        }
        return -1;
    }



    void playSound(BlockState pState, SoundEvent pSound) {
        Vec3i vec3i = pState.getValue(TombstoneBlock.FACING).getNormal();
        double d0 = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double d1 = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double d2 = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        this.level.playSound(null, d0, d1, d2, pSound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
    

}

