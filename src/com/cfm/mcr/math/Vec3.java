package com.cfm.mcr.math;


public class Vec3 {
	public float x, y, z;

	public static Vec3 ZERO = new Vec3(0,0,0);
	
	public Vec3(){
		set(ZERO);
	}
	
	public Vec3(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3(Vec3 other) {
		set(other);
	}

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(Vec3 v){
		set(v.x, v.y, v.z);
	}
	
	public Vec3 cpy(){
		return new Vec3(this);
	}
	
	public Vec3 add(Vec3 o){
		return new Vec3(x + o.x, y + o.y, z + o.z);
	}
	public void innerAdd(Vec3 o){
		x += o.x;
		y += o.y;
		z += o.z;
	}
	
	public Vec3 addScl(float scl, Vec3 o){
		return new Vec3(x + scl * o.x, y + scl * o.y, z + scl * o.z);
	}
	
	public Vec3 sub(Vec3 o){
		return new Vec3(x - o.x, y - o.y, z - o.z);
	}
	
	public Vec3 mul(Vec3 o){
		return new Vec3(x * o.x, y * o.y, z * o.z);
	}
	
	public Vec3 mulScalar(float k){
		return new Vec3(x * k, y * k, z * k);
	}
	
	public Vec3 crs(Vec3 r){
		return new Vec3(y*r.z-z*r.y,z*r.x-x*r.z,x*r.y-y*r.x);
	}
	
	public float dot(Vec3 v){
		return x*v.x + y*v.y + z*v.z;
	}
	
	public float len2(){
		return dot(this);
	}
	
	public float len(){
		return (float)Math.sqrt(len2());
	}
	
	public Vec3 normalize(){
		return mulScalar( 1 / len() );
	}
	
	public Vec3 transform(Mat4 m){
		float v0 = m.m[0] * x + m.m[4] * y + m.m[8] * z + m.m[12]; 
		float v1 = m.m[1] * x + m.m[5] * y + m.m[9] * z + m.m[13]; 
		float v2 = m.m[2] * x + m.m[6] * y + m.m[10] * z + m.m[14];
		
		x = v0;
		y = v1;
		z = v2;
		
		return this;
	}
	
	public int asInt() {
		
		float xx = Math.max(Math.min(x, 1.0f), 0.0f);
		float yy = Math.max(Math.min(y, 1.0f), 0.0f);
		float zz = Math.max(Math.min(z, 1.0f), 0.0f);
		
		int pr = (int)(xx * 255)<<16;
		int pg = (int)(yy * 255)<<8;
		int pb = (int)(zz * 255);
		
		return pr | pg | pb;
	}
	@Override
	public String toString() {
		return "Vector3 [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	public static Vec3 randomVector(Vec3 n){
		Vec3 v = Vec3.ZERO.cpy();
		do{
			float f1 = 2 * (float)Math.random() - 1;
			float f2 = 2 * (float)Math.random() - 1;
			float f3 = 2 * (float)Math.random() - 1;
			v.set(f1, f2, f3);
		}while(v.len2() > 1.0f);
		v = v.normalize();
		if( v.dot(n) < 0.0f )
			v = v.mulScalar(-1);
		return v;
	}
}
