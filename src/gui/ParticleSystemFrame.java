package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ParticleSystemFrame extends JFrame{

	
	public ParticleSystemFrame() {
		super("Particle System");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ParticlePanel p = new ParticlePanel(); 
		add(p, BorderLayout.CENTER);
		
		setVisible(true);
		
		p.run();
	}
	
}
