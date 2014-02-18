package com.cfm.mcr;

import com.cfm.mcr.materials.Material;
import com.cfm.mcr.shapes.Shape;

public class Body {
	public Shape shape;
	public Material material;
	
	public Body(Shape shape, Material material) {
		super();
		this.shape = shape;
		this.material = material;
	}
	
}
