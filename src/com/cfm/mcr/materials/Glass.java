package com.cfm.mcr.materials;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public class Glass extends Material{
	
	Vec3 color;
	float ior, reflection;
	
	public Glass(Vec3 color, float ior, float reflection){
		this.color = color;
		this.ior = ior;
		this.reflection = reflection;
	}
	
	@Override
	public Vec3 bounce(Ray r, Vec3 normal) {
		
		float tetha1 = Math.abs(r.direction.dot(normal) );
		
		float internalIndex = ior;
		float externalIndex = 1.0f;
		
		if( tetha1 < 0.0 ){
			internalIndex = 1.0f;
			externalIndex = ior;
		}
		
		float eta = externalIndex / internalIndex;
		float tetha2 = (float)Math.sqrt(1 - (eta * eta) * (1 - tetha1 * tetha1));
		float rs = (externalIndex * tetha1 - internalIndex * tetha2) / 
					(externalIndex * tetha1 + internalIndex * tetha2);
		float rp = (internalIndex * tetha1 - externalIndex * tetha2) / (internalIndex * tetha1 + externalIndex * tetha2);
		
		float reflectance = (rs * rs + rp * rp);
		
		//Reflection
		if( Math.random() < reflectance + reflection ){
			return normal.mulScalar(tetha1 * 2.0f).add(r.direction);
		}
		
		//Refraction
		return normal.mulScalar(tetha1).add(r.direction).mulScalar(eta).add(normal.mulScalar(-tetha2));
	}

	@Override
	public Vec3 reflectance(Ray r, Vec3 normal, Vec3 vOut) {
		return color;
	}

}
