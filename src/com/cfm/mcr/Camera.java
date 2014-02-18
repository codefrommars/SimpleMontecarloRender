package com.cfm.mcr;

import com.cfm.mcr.math.Mat4;
import com.cfm.mcr.math.Vec3;

public abstract class Camera {
	public Vec3 eye;
	public Vec3 focalPoint;
	public Vec3 up;
	protected Mat4 camToWorld = new Mat4();
	protected Mat4 worldToCam = new Mat4();
	
	public void update(){
		Vec3 z = focalPoint.cpy().sub(eye).normalize();
		up.normalize();
		Vec3 x = z.crs(up).normalize();
		Vec3 y = x.crs(z).normalize();
		
		worldToCam.m[0] = 	x.x;
		worldToCam.m[1] = 	y.x;
		worldToCam.m[2] = 	-z.x;
		worldToCam.m[3] = 	0;
		
		worldToCam.m[4] = 	x.y;
		worldToCam.m[5] = 	y.y;
		worldToCam.m[6] = 	-z.y;
		worldToCam.m[7] = 	0;
		
		worldToCam.m[8] = 	x.z;
		worldToCam.m[9] = 	y.z;
		worldToCam.m[10] = 	-z.z;
		worldToCam.m[11] = 	0;
		
		
		worldToCam.m[12] = -eye.x * x.x - eye.y * x.y - eye.z * x.z;
		worldToCam.m[13] = -eye.x * y.x - eye.y * y.y - eye.z * y.z;
		worldToCam.m[14] = eye.x * z.x + eye.y * z.y + eye.z * z.z;
		worldToCam.m[15] = 1;
		
		Mat4.invertMatrix(worldToCam, camToWorld);
		Mat4 out = new Mat4();
		Mat4.multiply(worldToCam, camToWorld, out);
	}
	
	public abstract Ray castRay(float x, float y);
	
}
