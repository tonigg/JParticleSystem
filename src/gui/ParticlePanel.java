package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import particle.Particle;
import particle.ParticleEmiter;

public class ParticlePanel extends JPanel {

	ParticleEmiter pe1 = new ParticleEmiter(new Point(100, 250),new Point(-6, -6), new Point(6, 6),
			1200, 5000, 3000, 6000, Color.GREEN);
//	ParticleEmiter pe2 = new ParticleEmiter(new Point(120, 560),new Point(-6, -6), new Point(6, 6),
//			300, 5000, 1000, 1000, Color.CYAN);

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
//					pe2.update();

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
		super.paintComponent(g);
		pe1.draw(g2d);
//		pe2.draw(g2d);
	}
}
