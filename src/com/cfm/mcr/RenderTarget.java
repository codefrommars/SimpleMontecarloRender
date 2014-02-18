package com.cfm.mcr;

import com.cfm.mcr.math.Vec3;

public class RenderTarget {
	public Vec3[] buffer;
	
	public RenderTarget(int width, int height){
		buffer = new Vec3[width * height];
		for (int i = 0; i < buffer.length; i++)
			buffer[i] = Vec3.ZERO.cpy();
	}
	
	public Vec3 getPix(int index){
		return buffer[index];
	}

}
