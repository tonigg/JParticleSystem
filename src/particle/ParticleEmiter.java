package particle;

import gui.ParticlePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class ParticleEmiter {
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	private Point maxVelocity, minVelocity;
	private Point position;
	private int maxTimer, minTimer;
	private int maxParticles, minParticles;
	private int radius;
	private Color color;
	private long emitTimer;
	private long copyTime;
	
	private long prevTime;
	
	private static Random r = new Random();

	private ParticlePanel panel;
	
	public ParticleEmiter(Point position, Point minVelocity, Point maxVelocity,
			int minTimer, int maxTimer, int minParticles, int maxParticles,
			Color color, int radius, long time,ParticlePanel panel) {
		this.position = position;
		this.radius = radius;
		this.emitTimer = time;
		this.maxVelocity = maxVelocity;
		this.minVelocity = minVelocity;
		this.panel = panel;

		if (maxTimer < minTimer) {
			this.maxTimer = minTimer;
		} else {
			this.maxTimer = maxTimer;
		}
		this.minTimer = minTimer;

		if (maxParticles < minParticles) {
			this.maxParticles = this.minParticles;
		} else {
			this.maxParticles = maxParticles;
		}
		this.minParticles = minParticles;

		prevTime = System.currentTimeMillis();
		copyTime = emitTimer;
		
		this.color = color;
	}

	private static int rDir() {
		if (r.nextBoolean()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	private static double linearInterpolate(double x, double y, double amount){
		return x*amount + y*(1 - amount);
	}

	public void emit() {
		r.setSeed(System.currentTimeMillis());

		int numberOfParticles = minParticles
				+ (int) (r.nextDouble() * maxParticles);
		for (int i = 0; i < numberOfParticles; i++) {
			particles
					.add(new Particle(
							new Point(
									position.x
											+ (int) (rDir() * r.nextDouble() * radius),
									position.y
											+ (int) (rDir() * r.nextDouble() * radius)),
							new Point(
									rDir()
											* ((int)linearInterpolate(minVelocity.x, maxVelocity.x, r.nextDouble())),
									rDir()
											* ((int)linearInterpolate(minVelocity.y, maxVelocity.y, r.nextDouble()))),
							minTimer + (int) (r.nextDouble() * maxTimer)));

			}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).getVelocity().x == 0) {
				particles.get(i).getVelocity().x = 1 * rDir();
			}
			if (particles.get(i).getVelocity().y == 0) {
				particles.get(i).getVelocity().y = 1 * rDir();
			}
		}
	}

	public void update() {
		int width = panel.getWidth(), height = panel.getHeight();
		
		for (int i = particles.size() - 1; i >= 0; i--) {
			if (particles.get(i).isAlive()) {
				particles.get(i).update(width, height);
			} else {
				particles.remove(i);
			}
		}

		long currentTime = System.currentTimeMillis();
		
		emitTimer -= (currentTime - prevTime);
		prevTime = currentTime;
		
		if (emitTimer < 0) {
			emitTimer = copyTime;
			emit();
		}
		
		System.out.println("Number of particles: " + particles.size());
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(color);

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(g2d);
		}

		g2d.setColor(Color.red);

		g2d.drawOval(position.x - radius, position.y - radius, 2*radius, 2*radius);
		
		g2d.setColor(Color.BLUE);
		
		g2d.drawOval(275, 125, 50, 50);
		
	}

	/**
	 * @return the maxVelocity
	 */
	public Point getMaxVelocity() {
		return maxVelocity;
	}

	/**
	 * @return the minVelocity
	 */
	public Point getMinVelocity() {
		return minVelocity;
	}

	/**
	 * @return the maxTimer
	 */
	public int getMaxTimer() {
		return maxTimer;
	}

	/**
	 * @return the minTimer
	 */
	public int getMinTimer() {
		return minTimer;
	}

	/**
	 * @return the maxParticles
	 */
	public int getMaxParticles() {
		return maxParticles;
	}

	/**
	 * @return the minParticles
	 */
	public int getMinParticles() {
		return minParticles;
	}

	/**
	 * @param maxVelocity
	 *            the maxVelocity to set
	 */
	public void setMaxVelocity(Point maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	/**
	 * @param minVelocity
	 *            the minVelocity to set
	 */
	public void setMinVelocity(Point minVelocity) {
		this.minVelocity = minVelocity;
	}

	/**
	 * @param maxTimer
	 *            the maxTimer to set
	 */
	public void setMaxTimer(int maxTimer) {
		this.maxTimer = maxTimer;
	}

	/**
	 * @param minTimer
	 *            the minTimer to set
	 */
	public void setMinTimer(int minTimer) {
		this.minTimer = minTimer;
	}

	/**
	 * @param maxParticles
	 *            the maxParticles to set
	 */
	public void setMaxParticles(int maxParticles) {
		this.maxParticles = maxParticles;
	}

	/**
	 * @param minParticles
	 *            the minParticles to set
	 */
	public void setMinParticles(int minParticles) {
		this.minParticles = minParticles;
	}

}
