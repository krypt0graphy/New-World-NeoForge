package com.kryptography.newworld.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;

import static com.kryptography.newworld.core.registry.NWBlocks.*;

public class NWCompat {
	public static void register() {
		registerFlammables();
	}

	private static void registerFlammables() {
		DataUtil.registerFlammable(FIR_LOG.get(), 5, 5);
		DataUtil.registerFlammable(FIR_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(STRIPPED_FIR_LOG.get(), 5, 5);
		DataUtil.registerFlammable(STRIPPED_FIR_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(FIR_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(FIR_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(FIR_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(FIR_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(FIR_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(FIR_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(FIR_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(FIR_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(FIR_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(FIR_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(TRIMMED_FIR_PLANKS.get(), 5, 20);
	}
}