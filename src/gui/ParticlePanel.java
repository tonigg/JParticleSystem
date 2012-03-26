package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import particle.Particle;
import particle.ParticleEmiter;

public class ParticlePanel extends JPanel {

	ParticleEmiter pe1 = new ParticleEmiter(new Point(150, 250),new Point(-3, -3), new Point(3, 3),
			1200, 2000, 1000, 1500, Color.GREEN, 50, 2000,this);
	ParticleEmiter pe2 = new ParticleEmiter(new Point(120, 560),new Point(-6, -6), new Point(6, 6),
			300, 5000, 1000, 1500, Color.CYAN, 50, 6000, this);

	public ParticlePanel() {
		super();
		setBackground(Color.BLACK);
	}

	private static final int FPS = 60;
	private static final long REFRESH_TIMER = 1000000000 / FPS;

	public void run() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {

					long beginTime, timeTaken, timeLeft;
					beginTime = System.nanoTime();

					pe1.update();
					pe2.update();

					repaint();

					timeTaken = System.nanoTime() - beginTime;
					timeLeft = (REFRESH_TIMER - timeTaken) / 1000000;

					if (timeLeft < 0) {
						timeLeft = 5;
					}

					try {
						Thread.sleep(timeLeft);
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
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		pe1.draw(g2d);
		pe2.draw(g2d);
	}
}
