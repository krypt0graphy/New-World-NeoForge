package com.kryptography.newworld.common.entity;

import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.NWItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class FirChestBoatEntity extends ChestBoat {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(FirChestBoatEntity.class, EntityDataSerializers.INT);

    public FirChestBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.entityData.set(DATA_ID_TYPE, FirBoatEntity.Type.FIR.ordinal());
    }

    public FirChestBoatEntity(Level level, double pX, double pY, double pZ) {
        this(NWEntityTypes.FIR_CHEST_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case FIR -> NWItems.FIR_CHEST_BOAT.get();
        };
    }

    public void setVariant(FirBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public FirBoatEntity.Type getModVariant() {
        return FirBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, 0);
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(FirBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

}
