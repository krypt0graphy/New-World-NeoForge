package com.kryptography.newworld.common.blocks.entity;

import com.kryptography.newworld.init.NWBlockEntityTypes;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
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

    public int getCompatibleSlot(ItemStack stack) {
        for(int i = 0; i < getContainerSize(); ++i) {
            ItemStack currentStack = items.get(i);
            if (currentStack.isEmpty() || (ItemStack.isSameItemSameComponents(currentStack, stack) && currentStack.getCount() < currentStack.getMaxStackSize())) {
                return i;
            }
        }
        return -1;
    }
}

