package com.sitrica.sourholograms.objects;

/**
 * Represents a position which grabs the x, y and z coordinates used for packets.
 */
public class Position {

	private final float yaw, pitch;
	private final double x, y, z;

	/**
	 * Constructs a position with an x, y and z coordinate.
	 * 
	 * @param x represents the x-coordinate of the position.
	 * @param y represents the y-coordinate of the position.
	 * @param z represents the z-coordinate of the position.
	 */
	public Position(double x, double y, double z) {
		this.pitch = 0;
		this.yaw = 0;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructs a position with an x, y and z coordinate with yaw and pitch.
	 * 
	 * @param x represents the x-coordinate of the position.
	 * @param y represents the y-coordinate of the position.
	 * @param z represents the z-coordinate of the position.
	 * @param yaw the yaw of this position.
	 * @param pitch the pitch of this position.
	 */
	public Position(double x, double y, double z, float yaw, float pitch) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (!(object instanceof Position))
			return false;
		Position other = (Position) object;
		if (other.getX() != x)
			return false;
		if (other.getY() != y)
			return false;
		if (other.getZ() != z)
			return false;
		return true;
	}

}
