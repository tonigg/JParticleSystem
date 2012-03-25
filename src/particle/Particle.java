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

	public Particle(Point position, Point velocity, int timer) {
		this.position = position;
		this.velocity = velocity;
		this.timer = timer;
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

	public void update() {
		if (position.x <= 0 || position.x >= ParticleSystemFrame.WIDTH) {
			velocity.x *= -1;
		}
		if (position.y <= 0 || position.y >= ParticleSystemFrame.HEIGHT) {
			velocity.y *= -1;
		}

		position.translate(velocity.x, velocity.y);

		long curTime = System.currentTimeMillis();
		timer -= (curTime - prevTime);
		prevTime = curTime;

		if (timer < 0) {
			alive = false;
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.fillOval(position.x, position.y, 4, 4);
	}
}
