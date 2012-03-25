package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ParticleSystemFrame extends JFrame{

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public ParticleSystemFrame() {
		super("Particle System");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ParticlePanel p = new ParticlePanel(); 
		add(p, BorderLayout.CENTER);
		
		setVisible(true);
		
		p.run();
	}
	
}
