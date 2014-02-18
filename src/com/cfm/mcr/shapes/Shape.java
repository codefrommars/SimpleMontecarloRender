package com.cfm.mcr.shapes;

import com.cfm.mcr.Ray;
import com.cfm.mcr.math.Vec3;

public abstract class Shape {
	
	public abstract float intersect(Ray ray);
	public abstract Vec3 getNormal(Vec3 point);
	
}