package particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class ParticleEmiter {
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	private Point maxVelocity, minVelocity;
	private int maxTimer, minTimer;
	private int maxParticles, minParticles;
	private static Random r = new Random();

	public ParticleEmiter(Point maxVelocity, Point minVelocity, int maxTimer,
			int minTimer, int maxParticles, int minParticles) {
		this.maxVelocity = maxVelocity;
		this.minVelocity = minVelocity;
		this.maxTimer = maxTimer;
		this.minTimer = minTimer;
		this.maxParticles = maxParticles;
		this.minParticles = minParticles;
	}

	public void emit() {
		int numberOfParticles = minParticles + r.nextInt(maxParticles);
		for (int i = 0; i < numberOfParticles; i++) {
			particles.add(new Particle(new Point(300 + r.nextInt(50), 300 + r
					.nextInt(50)), new Point(minVelocity.x
					+ r.nextInt(maxVelocity.x), minVelocity.y
					+ r.nextInt(maxVelocity.y)), 3000));
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

		if(particles.size() < minParticles){
			emit();
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);

		for (Particle particle : particles) {
			particle.draw(g2d);
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
