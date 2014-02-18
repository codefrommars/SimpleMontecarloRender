package com.cfm.mcr.materials;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public class Diffuse extends Material{

	private Vec3 color;
	
	public Diffuse(Vec3 color) {
		this.color = color;
	}
	
	public Vec3 bounce(Ray r, Vec3 normal){
		return Vec3.randomVector(normal);
	}
	
	public Vec3 reflectance(Ray r, Vec3 normal, Vec3 vOut){
		float cosTheta = vOut.dot(normal);
		return color.cpy().mulScalar(2 * cosTheta);
	}

}
