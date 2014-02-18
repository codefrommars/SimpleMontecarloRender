package com.cfm.mcr.shapes;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;


public class Plane extends Shape {

	protected Vec3 p, n;
	
	public Plane(Vec3 p, Vec3 n){
		this.p = p;
		this.n = n;
	}
	
	@Override
	public float intersect(Ray ray) {
		float num = p.sub(ray.origin).dot(n);
		float denom = ray.direction.dot(n);
		
		if( denom == 0 )
			return -1;
		
		float t = num / denom;
		
		if( t < 0 )
			return -1;
		
		return t;
	}

	@Override
	public Vec3 getNormal(Vec3 point) {
		return n.cpy();
	}

}
