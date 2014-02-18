package com.cfm.mcr;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.cfm.mcr.materials.Chrome;
import com.cfm.mcr.materials.Diffuse;
import com.cfm.mcr.materials.Glass;
import com.cfm.mcr.materials.SimpleLight;
import com.cfm.mcr.math.Vec3;
import com.cfm.mcr.shapes.Plane;
import com.cfm.mcr.shapes.Sphere;

@SuppressWarnings("serial")
public class TestRendering extends JComponent{
	private RenderTarget rt;
	private Renderer renderer;
	private BufferedImage img;
	private PointholeCamera cam;
	private Scene scene;
	
	public TestRendering(int w, int h){
		setSize(w, h);
		setPreferredSize(getSize());
		img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		createScene2(w, h);
		rt = new RenderTarget(scene.width, scene.height);
		renderer = new Renderer(scene, rt);
	}
	
	public void createScene3(int w, int h){
		scene = new Scene();
		scene.width = w;
		scene.height = h;
		
		cam = new PointholeCamera(w, h, 45);
		cam.eye = new Vec3(0, -1, 15);
		cam.focalPoint = new Vec3(0, -1, 0);
		cam.up = new Vec3(0, 1, 0);
		cam.update();
		
		Body light = new Body(new Sphere(new Vec3(0, 2, 0), 2f), new SimpleLight(new Vec3(2.5f, 2.5f, 2.0f)));
		scene.objects.add(light);
		
		Body opaqueBall = new Body(new Sphere(new Vec3(0, -2, 0), 2f), new Diffuse(new Vec3(0.9f, 0.9f, 0.9f)));
		scene.objects.add(opaqueBall);
		
		Body floor = new Body(new Plane(new Vec3(0, -4, 0), new Vec3(0, 1, 0)), new Diffuse(new Vec3(0.9f, 0.9f, 0.9f)));
		scene.objects.add(floor);
		
	}
	
	public void createScene2(int w, int h){
		scene = new Scene();
		scene.width = w;
		scene.height = h;
		
		cam = new PointholeCamera(w, h, 45);
		cam.eye = new Vec3(0, 0, 15);
		cam.focalPoint = new Vec3(0, 0, -15);
		cam.up = new Vec3(0, 1, 0);
		cam.update();
		
		Body roof = new Body(new Plane(new Vec3(0, 10, 0), new Vec3(0, -1, 0)), new SimpleLight(new Vec3(1.54f, 1.47f, 1.2f)));
		scene.objects.add(roof);

		Body back = new Body(new Plane(new Vec3(0, 0, -20), new Vec3(0, 0, 1)), new Diffuse(new Vec3(0.9f, 0.9f, 0.4f)));
		scene.objects.add(back);

		Body right = new Body(new Plane(new Vec3(10, 0, 0), new Vec3(-1, 0, 0)), new Diffuse(new Vec3(0.3f, 0.3f, 0.9f)));
		scene.objects.add(right);

		Body left = new Body(new Plane(new Vec3(-10, 0, 0), new Vec3(1, 0, 0)), new Diffuse(new Vec3(0.9f, 0.3f, 0.3f)));
		scene.objects.add(left);

		Body floor = new Body(new Plane(new Vec3(0, -5, 0), new Vec3(0, 1, 0)), new Diffuse(new Vec3(0.3f, 0.9f, 0.3f)));
		scene.objects.add(floor);
		
		Body centralGlass = new Body(new Sphere(new Vec3(0, -2.7f, 0), 2f),  new Glass(new Vec3(1f, 1f, 1f), 1.5f, 0.01f));//new Diffuse(new Vec3(0.9f, 0.75f, 0.75f)));
		scene.objects.add(centralGlass);
		
		Body metallic = new Body(new Sphere(new Vec3(-2, 2, 0), 1f), new Chrome(new Vec3(0.8f, 0.8f, 0.8f)));
		scene.objects.add(metallic);
		
		Body topGlass = new Body(new Sphere(new Vec3(2, 2, 4.5f), 1f), new Glass(new Vec3(1f, 1f, 1f), 3.0f, 0.001f));
		scene.objects.add(topGlass);
		
		Body grayBall = new Body(new Sphere(new Vec3(-7.0f, -1, -12.5f), 3f),  new Diffuse(new Vec3(0.75f, 0.75f, 0.75f)));
		scene.objects.add(grayBall);
		
	}
	
	public void render(){
		int samples = 10000;
		for (int i = 0; i < samples; i++) {
			renderer.iterate(scene, cam, 7);
			onEndSample(i);
		}
	}
	
	public void onEndSample(int sample){
		
		if( sample % 10 != 0 )
			return;
			
		int c = 0;
		for (int j = 0; j < scene.height; j++) 
			for (int i = 0; i < scene.width; i++) 
				img.setRGB(i, j, rt.getPix(c++).mulScalar(1f / sample).asInt() );
		
		repaint();
		System.out.println("Sample " + sample + " ended.");
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TestRendering tr = new TestRendering(400, 400);
		
		frame.add(tr);
		frame.pack();
		
		frame.setVisible(true);
		tr.render();
		
	}

}
