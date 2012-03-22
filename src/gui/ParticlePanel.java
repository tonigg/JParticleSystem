package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import particle.Particle;
import particle.ParticleEmiter;

public class ParticlePanel extends JPanel {

	ParticleEmiter pe = new ParticleEmiter(new Point(15, 15), new Point(-5, -5), 250, 100, 250, 100);
	
	public ParticlePanel() {
		super();
		setBackground(Color.BLACK);
	}
	
	public void run(){
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(true){
					pe.update();
					
					repaint();
					
					try {
						Thread.sleep(13);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}			
			}
		});
		
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		pe.draw(g2d);
	}
}
