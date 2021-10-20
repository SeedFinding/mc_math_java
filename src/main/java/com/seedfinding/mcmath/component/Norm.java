package com.seedfinding.mcmath.component;

@FunctionalInterface
public interface Norm<C, R> {

	R get(C component);

}
