package com.kryptography.newworld.integration;

import net.neoforged.fml.ModList;

public enum Mods {
	BIOLITH,
	FARMERSDELIGHT,
	NOMANSLAND,
	BLOCKBOX,
	WOODWORKS,
	BOATLOAD;
	private final String id;

	Mods() {
		id = name().toLowerCase();
	}

	public boolean isLoaded() {
		return ModList.get().isLoaded(id);
	}
}