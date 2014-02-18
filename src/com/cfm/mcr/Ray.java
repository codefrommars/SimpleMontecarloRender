package com.cfm.mcr;

import com.cfm.mcr.math.Vec3;

public class Ray {
	public Vec3 origin, direction;
	
	public Ray() {
		super();
	}

	public Ray(Vec3 origin, Vec3 direction) {
		super();
		this.origin = origin;
		this.direction = direction;
	}

	public Vec3 pointInT(float t){
		return origin.addScl(t, direction);
	}

	public void set(Ray ray) {
		this.origin.set(ray.origin);
		this.direction.set(ray.direction);
	}

	public Vec3 getPointAt(float t) {
		return direction.mulScalar(t).add(origin);
	}
}
