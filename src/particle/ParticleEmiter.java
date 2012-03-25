package particle;

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
	private Color color;
	private static Random r = new Random();

	public ParticleEmiter(Point position, Point minVelocity, Point maxVelocity,
			int minTimer, int maxTimer, int minParticles, int maxParticles,
			Color color) {
		this.position = position;
		this.maxVelocity = maxVelocity;
		this.minVelocity = minVelocity;

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

		this.color = color;
	}

	private static int rDir() {
		if (r.nextBoolean()) {
			return 1;
		} else {
			return -1;
		}
	}

	public void emit() {
		r.setSeed(System.currentTimeMillis());

		int numberOfParticles = minParticles
				+ (int)(r.nextDouble() * maxParticles);
		for (int i = 0; i < numberOfParticles; i++) {
			particles
					.add(new Particle(
							new Point(position.x + (int) (r.nextDouble() * 50),
									position.y + (int) (r.nextDouble() * 50)),
							new Point(
									rDir()
											* (minVelocity.x + (int) (r
													.nextDouble() * maxVelocity.x)),
									rDir()
											* (minVelocity.y + (int) (r
													.nextDouble() * maxVelocity.y))),
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
		for (int i = particles.size() - 1; i >= 0; i--) {
			if (particles.get(i).isAlive()) {
				particles.get(i).update();
			} else {
				particles.remove(i);
			}
		}

		if (particles.size() < minParticles) {
			emit();
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(color);

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(g2d);
		}
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
