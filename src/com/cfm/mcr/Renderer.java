package com.cfm.mcr;

import com.cfm.mcr.materials.SimpleLight;
import com.cfm.mcr.math.Vec3;

public class Renderer {
	private RenderTarget rt;
	
	public Renderer(Scene scene, RenderTarget rt){
		this.rt = rt;
	}
	
	public void iterate(Scene scene, Camera cam, int depth){
		float w = scene.width;
		float h = scene.height;
		int pix = 0;
		
		//Jitter for antialiasing
		float ystep = 1/h;
		float xstep = 1/w;

		for(int j = 0; j < h; j++){
			float r0 = (float)Math.random()/h;
			float y = r0 + ystep * j;
				for(int i = 0; i < w; i++){
					float r1 = (float)Math.random()/w;
					float x = r1 + xstep * i;
					Ray ray = cam.castRay(x, y);
					Vec3 color = trace(scene, cam, ray, depth);
					rt.getPix(pix++).innerAdd(color);
				}
		}
	}
	
	public Vec3 trace(Scene scene, Camera cam, Ray ray, int depth){
		float mint = Float.POSITIVE_INFINITY;
		
		if( depth == 0 ){
			return Vec3.ZERO.cpy();
		}
		
		Body hit = null;
		for (int i = 0; i < scene.objects.size(); i++) {
			Body o = scene.objects.get(i);
			float t = o.shape.intersect(ray);
			if( t > 0 && t <= mint ){
				mint = t;
				hit = o;
			}
		}
		
		if( hit == null ){
			return Vec3.ZERO.cpy();
		}
		
		//Don't bounce light. (Suppose 100% emittive)
		if( hit.material.isLight() )
			return ((SimpleLight)hit.material).getEmittance();
		
		Vec3 point = ray.getPointAt(mint);
		Vec3 normal = hit.shape.getNormal(point);
		Vec3 dir = hit.material.bounce(ray, normal);
		Vec3 bdrf = hit.material.reflectance(ray, normal, dir);
		
		Ray newray = new Ray(point, dir);
		
		Vec3 newTrace = trace(scene, cam, newray, depth - 1);
		return newTrace.mul(bdrf).add(hit.material.getEmittance());
	}
}
