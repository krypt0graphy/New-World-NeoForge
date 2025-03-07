package com.kryptography.newworld.integration;

import net.neoforged.fml.ModList;

import java.util.Optional;
import java.util.function.Supplier;


//Author: Tazer https://github.com/tazercopter
public enum Mods {
	BIOLITH,
	FARMERSDELIGHT,
	NOMANSLAND;
	private final String id;

	Mods() {
		id = name().toLowerCase();
	}

	public boolean isLoaded() {
		return ModList.get().isLoaded(id);
	}


	public <T> Optional<T> runIfInstalled(Supplier<T> toRun) {
		if (isLoaded())
			return Optional.of(toRun.get());
		return Optional.empty();
	}
	public void executeIfInstalled(Supplier<Runnable> toExecute) {
		if (isLoaded()) {
			toExecute.get().run();
		}
	}

}
