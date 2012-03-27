package particle;

import gui.ParticleSystemFrame;

import java.awt.Graphics2D;
import java.awt.Point;

public class Particle {
	private Point position;
	private Point velocity;
	private int timer;
	private boolean alive = true;
	private long prevTime;
	private int radius;

	public Particle(Point position, Point velocity, int timer, int radius) {
		this.position = position;
		this.velocity = velocity;
		this.timer = timer;
		this.radius = radius;
		prevTime = System.currentTimeMillis();
	}

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @return the velocity
	 */
	public Point getVelocity() {
		return velocity;
	}

	/**
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	public void update(int width, int height) {
		updateCollisions(width, height);

		position.translate(velocity.x, velocity.y);

		long curTime = System.currentTimeMillis();
		timer -= (curTime - prevTime);
		prevTime = curTime;

		if (timer < 0) {
			alive = false;
		}
	}

	private void updateCollisions(int width, int height) {
		int offset = radius/2;
		
		if (position.x <= 0 + offset || position.x +offset>= width) {
			velocity.x *= -1;
		}
		if (position.y <= 0 +offset || position.y +offset >= height) {
			velocity.y *= -1;
		}

		if (Math.pow((position.x  + offset - 300), 2) + Math.pow(position.y + offset  - 150, 2) <= Math.pow(
				50, 2)) {
			velocity.x *= -1;
			velocity.y *= -1;
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.fillOval(position.x, position.y, radius, radius);
	}
}
