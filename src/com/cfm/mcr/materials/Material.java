package com.cfm.mcr.materials;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public abstract class Material {
	private Vec3 emittance;
	
	public Material(){
		this(Vec3.ZERO.cpy());
	}
	
	public Material(Vec3 emittance) {
		this.emittance = emittance;
	}
	
	public abstract Vec3 bounce(Ray r, Vec3 normal);
	
	public abstract Vec3 reflectance(Ray r, Vec3 normal, Vec3 vOut);
	
	public boolean isLight(){
		return false;
	}

	public Vec3 getEmittance() {
		return emittance.cpy();
	}

}
