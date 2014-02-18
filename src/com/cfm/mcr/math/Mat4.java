package com.cfm.mcr.math;

public class Mat4 {
	
	/**
	 * 0  4  8 12
	 * 1  5  9 13
	 * 2  6 10 14
	 * 3  7 11 15
	 */
	public float[] m = new float[16];
	
	public Mat4(){
		m[0] = 1;
		m[5] = 1;
		m[10] = 1;
		m[15] = 1;
	}
	
	public static void multiply(Mat4 a, Mat4 b, Mat4 o){
		float o00 = a.m[0] * b.m[0] + a.m[4] * b.m[1] + a.m[8] * b.m[2] + a.m[12] * b.m[3]; 
		float o10 = a.m[1] * b.m[0] + a.m[5] * b.m[1] + a.m[9] * b.m[2] + a.m[13] * b.m[3]; 
		float o20 = a.m[2] * b.m[0] + a.m[6] * b.m[1] + a.m[10] * b.m[2] + a.m[14] * b.m[3]; 
		float o30 = a.m[3] * b.m[0] + a.m[7] * b.m[1] + a.m[11] * b.m[2] + a.m[15] * b.m[3]; 

		float o01 = a.m[0] * b.m[4] + a.m[4] * b.m[5] + a.m[8] * b.m[6] + a.m[12] * b.m[7]; 
		float o11 = a.m[1] * b.m[4] + a.m[5] * b.m[5] + a.m[9] * b.m[6] + a.m[13] * b.m[7]; 
		float o21 = a.m[2] * b.m[4] + a.m[6] * b.m[5] + a.m[10] * b.m[6] + a.m[14] * b.m[7]; 
		float o31 = a.m[3] * b.m[4] + a.m[7] * b.m[5] + a.m[11] * b.m[6] + a.m[15] * b.m[7];
		
		float o02 = a.m[0] * b.m[8] + a.m[4] * b.m[9] + a.m[8] * b.m[10] + a.m[12] * b.m[11]; 
		float o12 = a.m[1] * b.m[8] + a.m[5] * b.m[9] + a.m[9] * b.m[10] + a.m[13] * b.m[11]; 
		float o22 = a.m[2] * b.m[8] + a.m[6] * b.m[9] + a.m[10] * b.m[10] + a.m[14] * b.m[11]; 
		float o32 = a.m[3] * b.m[8] + a.m[7] * b.m[9] + a.m[11] * b.m[10] + a.m[15] * b.m[11];
		
		float o03 = a.m[0] * b.m[12] + a.m[4] * b.m[13] + a.m[8] * b.m[14] + a.m[12] * b.m[15]; 
		float o13 = a.m[1] * b.m[12] + a.m[5] * b.m[13] + a.m[9] * b.m[14] + a.m[13] * b.m[15]; 
		float o23 = a.m[2] * b.m[12] + a.m[6] * b.m[13] + a.m[10] * b.m[14] + a.m[14] * b.m[15]; 
		float o33 = a.m[3] * b.m[12] + a.m[7] * b.m[13] + a.m[11] * b.m[14] + a.m[15] * b.m[15];
		
		o.m[0] = o00;
		o.m[1] = o10;
		o.m[2] = o20;
		o.m[3] = o30;

		o.m[4] = o01;
		o.m[5] = o11;
		o.m[6] = o21;
		o.m[7] = o31;
		
		o.m[8] = o02;
		o.m[9] = o12;
		o.m[10] = o22;
		o.m[11] = o32;
		
		o.m[12] = o03;
		o.m[13] = o13;
		o.m[14] = o23;
		o.m[15] = o33;
	}
	
	public static void setFromAngleAxis(float a, float x, float y, float z, Mat4 o){
		float cosA = (float)Math.cos(a);
		float oneMcosA = 1 - cosA;
		float sinA = (float)Math.sin(a);
		
		o.m[0] = cosA + x*x*oneMcosA;
		o.m[1] = x*y*oneMcosA + z*sinA;
		o.m[2] = x*z*oneMcosA - y*sinA;
		o.m[3] = 0;
		
		o.m[4] = x*y*oneMcosA - z*sinA;
		o.m[5] = cosA + y*y*oneMcosA;
		o.m[6] = y*z*oneMcosA + x*sinA;
		o.m[7] = 0;
		
		o.m[8] = x*z*oneMcosA + y*sinA;
		o.m[9] = y*z*oneMcosA - x*sinA;
		o.m[10] = cosA + z*z*oneMcosA;
		o.m[11] = 0;
		
		o.m[12] = 0;
		o.m[13] = 0;
		o.m[14] = 0;
		o.m[15] = 1;
	}
	
	/**
	 * 0  4  8 12
	 * 1  5  9 13
	 * 2  6 10 14
	 * 3  7 11 15
	 */
	public static float determinant(Mat4 m){
		float d1 = m.m[0] * (m.m[5] * m.m[10] - m.m[6] * m.m[9]);
		float d2 = m.m[4] * (m.m[1] * m.m[10] - m.m[2] * m.m[9]);
		float d3 = m.m[8] * (m.m[1] * m.m[6] - m.m[2] * m.m[5]);
		
		return d1 - d2 + d3;
	}
	
	
	public static boolean invertMatrix(Mat4 m, Mat4 out){
		return invertMatrix(m.m, out.m);
	}
	
	private static boolean invertMatrix(float m[], float invOut[])
	{
	    float inv[] = new float[16];
	    float det;
	    int i;

	    inv[0] = m[5]  * m[10] * m[15] - 
	             m[5]  * m[11] * m[14] - 
	             m[9]  * m[6]  * m[15] + 
	             m[9]  * m[7]  * m[14] +
	             m[13] * m[6]  * m[11] - 
	             m[13] * m[7]  * m[10];

	    inv[4] = -m[4]  * m[10] * m[15] + 
	              m[4]  * m[11] * m[14] + 
	              m[8]  * m[6]  * m[15] - 
	              m[8]  * m[7]  * m[14] - 
	              m[12] * m[6]  * m[11] + 
	              m[12] * m[7]  * m[10];

	    inv[8] = m[4]  * m[9] * m[15] - 
	             m[4]  * m[11] * m[13] - 
	             m[8]  * m[5] * m[15] + 
	             m[8]  * m[7] * m[13] + 
	             m[12] * m[5] * m[11] - 
	             m[12] * m[7] * m[9];

	    inv[12] = -m[4]  * m[9] * m[14] + 
	               m[4]  * m[10] * m[13] +
	               m[8]  * m[5] * m[14] - 
	               m[8]  * m[6] * m[13] - 
	               m[12] * m[5] * m[10] + 
	               m[12] * m[6] * m[9];

	    inv[1] = -m[1]  * m[10] * m[15] + 
	              m[1]  * m[11] * m[14] + 
	              m[9]  * m[2] * m[15] - 
	              m[9]  * m[3] * m[14] - 
	              m[13] * m[2] * m[11] + 
	              m[13] * m[3] * m[10];

	    inv[5] = m[0]  * m[10] * m[15] - 
	             m[0]  * m[11] * m[14] - 
	             m[8]  * m[2] * m[15] + 
	             m[8]  * m[3] * m[14] + 
	             m[12] * m[2] * m[11] - 
	             m[12] * m[3] * m[10];

	    inv[9] = -m[0]  * m[9] * m[15] + 
	              m[0]  * m[11] * m[13] + 
	              m[8]  * m[1] * m[15] - 
	              m[8]  * m[3] * m[13] - 
	              m[12] * m[1] * m[11] + 
	              m[12] * m[3] * m[9];

	    inv[13] = m[0]  * m[9] * m[14] - 
	              m[0]  * m[10] * m[13] - 
	              m[8]  * m[1] * m[14] + 
	              m[8]  * m[2] * m[13] + 
	              m[12] * m[1] * m[10] - 
	              m[12] * m[2] * m[9];

	    inv[2] = m[1]  * m[6] * m[15] - 
	             m[1]  * m[7] * m[14] - 
	             m[5]  * m[2] * m[15] + 
	             m[5]  * m[3] * m[14] + 
	             m[13] * m[2] * m[7] - 
	             m[13] * m[3] * m[6];

	    inv[6] = -m[0]  * m[6] * m[15] + 
	              m[0]  * m[7] * m[14] + 
	              m[4]  * m[2] * m[15] - 
	              m[4]  * m[3] * m[14] - 
	              m[12] * m[2] * m[7] + 
	              m[12] * m[3] * m[6];

	    inv[10] = m[0]  * m[5] * m[15] - 
	              m[0]  * m[7] * m[13] - 
	              m[4]  * m[1] * m[15] + 
	              m[4]  * m[3] * m[13] + 
	              m[12] * m[1] * m[7] - 
	              m[12] * m[3] * m[5];

	    inv[14] = -m[0]  * m[5] * m[14] + 
	               m[0]  * m[6] * m[13] + 
	               m[4]  * m[1] * m[14] - 
	               m[4]  * m[2] * m[13] - 
	               m[12] * m[1] * m[6] + 
	               m[12] * m[2] * m[5];

	    inv[3] = -m[1] * m[6] * m[11] + 
	              m[1] * m[7] * m[10] + 
	              m[5] * m[2] * m[11] - 
	              m[5] * m[3] * m[10] - 
	              m[9] * m[2] * m[7] + 
	              m[9] * m[3] * m[6];

	    inv[7] = m[0] * m[6] * m[11] - 
	             m[0] * m[7] * m[10] - 
	             m[4] * m[2] * m[11] + 
	             m[4] * m[3] * m[10] + 
	             m[8] * m[2] * m[7] - 
	             m[8] * m[3] * m[6];

	    inv[11] = -m[0] * m[5] * m[11] + 
	               m[0] * m[7] * m[9] + 
	               m[4] * m[1] * m[11] - 
	               m[4] * m[3] * m[9] - 
	               m[8] * m[1] * m[7] + 
	               m[8] * m[3] * m[5];

	    inv[15] = m[0] * m[5] * m[10] - 
	              m[0] * m[6] * m[9] - 
	              m[4] * m[1] * m[10] + 
	              m[4] * m[2] * m[9] + 
	              m[8] * m[1] * m[6] - 
	              m[8] * m[2] * m[5];

	    det = m[0] * inv[0] + m[1] * inv[4] + m[2] * inv[8] + m[3] * inv[12];

	    if (det == 0)
	        return false;

	    det = 1.0f / det;

	    for (i = 0; i < 16; i++)
	        invOut[i] = inv[i] * det;

	    return true;
	}
	
	public void idt(){
		for (int i = 0; i < m.length; i++)
			m[i] = 0;
		
		m[0] = 1; 
		m[5] = 1;
		m[10] = 1;
		m[15] = 1;
	}
	
	public void translate(float dx, float dy, float dz){
		m[12] += dx;
		m[13] += dy;
		m[14] += dz;
	}
	
	public void rotate(float angle, float x, float y, float z){
		Mat4 r = new Mat4();
		setFromAngleAxis(angle, x, y, z, r);
		mult(r);
	}
	
	public void mult(Mat4 other){
		multiply(this, other, this);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for(int j = 0; j < 4; j++){
			for(int i = 0; i < 4; i++){
				sb.append(m[index++]).append("\t");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
