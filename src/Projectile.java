// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Projectile extends Entity {

    public void trackTarget(int targetX, int targetY, int targetZ, int createTime) {
        if (!hasMoved) {
            double dx = targetX - x;
            double dy = targetY - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            startX = x + (dx * distanceFromSource) / distance;
            startY = y + (dy * distanceFromSource) / distance;
            startZ = heightStart;
        }
        double timeRemaining = (speed + 1) - createTime;
        velocityX = (targetX - startX) / timeRemaining;
        velocityY = (targetY - startY) / timeRemaining;
        horizontalVelocity = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        if (!hasMoved)
            velocityZ = -horizontalVelocity * Math.tan(initialSlope * 0.02454369D);
        gravity = (2D * (targetZ - startZ - velocityZ * timeRemaining)) / (timeRemaining * timeRemaining);
    }

    /**
     * Updates the projectiles position based on velocity and gravity.
     */
    public void updatePosition(int deltaTime) {
        hasMoved = true;
        startX += velocityX * deltaTime;
        startY += velocityY * deltaTime;
        startZ += velocityZ * deltaTime + 0.5D * gravity * deltaTime * deltaTime;
        velocityZ += gravity * deltaTime;

        // Calculate yaw (aka horizontal rotation)
        yaw = (int) (Math.atan2(velocityX, velocityY) * 325.949) + 1024 & 0x7ff;

        // Calculate pitch (aka vertical rotation)
        pitch = (int) (Math.atan2(velocityZ, horizontalVelocity) * 325.949) & 0x7ff;
        if (spotAnimation.animation != null)
            for (animationFrame += deltaTime; animationFrame > spotAnimation.animation.method205(0, currentFrame); ) {
                animationFrame -= spotAnimation.animation.method205(0, currentFrame);
                currentFrame++;
                if (currentFrame >= spotAnimation.animation.anInt294)
                    currentFrame = 0;
            }

    }

	/**
	 * Retrieves and prepares the 3D model of this projectile for rendering.
	 * Applies necessary transformations, animations, and lighting effects.
	 *
	 * @return The prepared Model for rendering, or null if the base model is not available
	 */
	@Override
	public Model getModel() {
		Model baseModel = spotAnimation.getModel();
		if (baseModel == null)
			return null;

		int currentAnimationFrame = -1;
		if (spotAnimation.animation != null) {
			currentAnimationFrame = spotAnimation.animation.anIntArray295[currentFrame];
		}

		Model projectileModel = new Model(false, false, true,
				baseModel, Class21.method239(currentAnimationFrame));

		if (currentAnimationFrame != -1) {
			projectileModel.method584(7); //TODO apply effects
			projectileModel.method585(currentAnimationFrame, (byte) 6); //TODO apply animations
			projectileModel.anIntArrayArray1679 = null;
			projectileModel.anIntArrayArray1678 = null;
		}

		// Apply scaling if needed
		int defaultScaling = 128;
		if (spotAnimation.anInt561 != defaultScaling || spotAnimation.anInt562 != defaultScaling)
			projectileModel.method593(spotAnimation.anInt562, spotAnimation.anInt561, 9,
					spotAnimation.anInt561);

		// Apply rotation (aka pitch) to the model
		projectileModel.method589(pitch, 341); // TODO investigate 341

		// Apply lighting and shading
		projectileModel.method594(
				64 + spotAnimation.anInt564, // ambient light
				850 + spotAnimation.anInt565, // contrast
				-30, -50, -30, // light direction vector
				true // Apply lighting
		);
		return projectileModel;
	}

	public Projectile(int plane, int heightEnd, int distanceFromSource, int y, int id, int speed, int initialSlope, int target, int heightStart, int x,
					  int createdTime) {
		aBoolean1561 = false;
		aBoolean1573 = true;
		spotAnimation = SpotAnimation.spotAnimations[id];
		this.plane = plane;
		this.x = x;
		this.y = y;
		this.heightStart = heightStart;
		this.createdTime = createdTime;
		this.speed = speed;
		this.initialSlope = initialSlope;
		this.distanceFromSource = distanceFromSource;
		this.target = target;
		this.heightEnd = heightEnd;
		hasMoved = false;
		return;
	}

	public SpotAnimation spotAnimation;
	public int plane;
	public double startX;
	public double startY;
	public double startZ;
	public int initialSlope;
	public int distanceFromSource;
	public int target;
	public boolean aBoolean1561;
	public int yaw;
	public int pitch;
	public int createdTime;
	public int speed;
	public int currentFrame;
	public int animationFrame;
	public double velocityX;
	public double velocityY;
	public double horizontalVelocity;
	public double velocityZ;
	public boolean aBoolean1573;
	public double gravity;
	public boolean hasMoved;
	public int x;
	public int y;
	public int heightStart;
	public int heightEnd;
}
