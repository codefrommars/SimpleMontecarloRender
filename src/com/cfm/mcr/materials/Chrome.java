package com.cfm.mcr.materials;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public class Chrome extends Material{
	
	private Vec3 power;
	
	public Chrome(Vec3 power) {
		this.power = power;
	}

	@Override
	public Vec3 bounce(Ray r, Vec3 normal) {
		//Always out ?
		float a = Math.abs(r.direction.dot(normal));
		return normal.mulScalar(a * 2).add(r.direction);
	}

	@Override
	public Vec3 reflectance(Ray r, Vec3 normal, Vec3 vOut) {
		return this.power.cpy();
	}

}
