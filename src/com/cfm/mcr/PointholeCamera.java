package com.cfm.mcr;

import com.cfm.mcr.math.Vec3;


public class PointholeCamera extends Camera {

	private float tanA2;
	private int w, h;
	private float aspect;
	public PointholeCamera(int w, int h, float fov){
		tanA2 = (float)Math.tan(Math.toRadians(fov) / 2);
		this.w = w;
		this.h = h;
		aspect = (float)w / h;
	}
	
	@Override
	public Ray castRay(float x, float y) {
		float xRemap = (2 * x - 1) * tanA2 * aspect;
		float yRemap = (1 - 2 * y) * tanA2;
		
		Ray ray = new Ray();
		ray.origin = new Vec3();
		ray.origin.transform(camToWorld);
		
		Vec3 p = new Vec3(xRemap, yRemap, -1);
		p.transform(camToWorld);
		ray.direction = p.sub(ray.origin).normalize();
		
		return ray;
	}
	
}
