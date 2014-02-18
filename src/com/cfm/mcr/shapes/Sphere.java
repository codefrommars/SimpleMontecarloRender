package com.cfm.mcr.shapes;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public class Sphere extends Shape{
	private Vec3 center;
	private float radius;
	
	
	public Sphere(Vec3 center, float radius) {
		super();
		this.center = center;
		this.radius = radius;
	}

	private float[] t = new float[2];

	public float intersect(Ray ray){
		Vec3 L = ray.origin.cpy().sub(center);
		float a = ray.direction.len2();
		float b = 2 * ray.direction.dot(L);
		float c = L.len2() - radius * radius;
		if(solveQuadratic(a, b, c, t))
			return Math.min(t[0], t[1]);

		//Fill the hit info here ?
		return -1;
	}
	
public static final float EPSILON = 1e-5f;
	
	public static boolean isZero(float f){
		return f < EPSILON && f > -EPSILON;
	}
	
	public static boolean isPositive(float f){
		return f > EPSILON;
	}
	
	public static boolean solveQuadratic(float a, float b, float c, float[] t){
		float d = b*b - 4*a*c;
		if( d < 0 )
			return false;
		
		if( isZero(d) ){
			t[0] = t[1] = -0.5f * b / a;
			return true;
		}
		
		float sqd = (float)Math.sqrt(d);
		float q = ( isPositive(b) ) ? 
						-0.5f * (b + sqd) 
					:
						-0.5f * (b - sqd);
					
		t[0] = q / a;
		t[1] = c / q;
		return true;
	}
	public Vec3 getNormal(Vec3 point){
		return point.sub(center).normalize();
	}
	
}
