package com.cfm.mcr.materials;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public class SimpleLight extends Material{
	
	public SimpleLight(Vec3 emittance) {
		super(emittance);
	}

	@Override
	public Vec3 bounce(Ray r, Vec3 normal) {
		return null;
	}

	@Override
	public Vec3 reflectance(Ray r, Vec3 normal, Vec3 vOut) {
		return null;
	}

	@Override
	public boolean isLight() {
		return true;
	}
	
}
