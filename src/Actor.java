// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public abstract class Actor extends Entity {

	public void resetWalkingQueue() {
		walkingQueueSize = 0;
		anInt1613 = 0;
	}

	public boolean isVisible() {
		return false;
	}

	public void addStep(int direction, boolean running) {
		int x = walkingQueueX[0];
		int y = walkingQueueY[0];
		if (direction == 0) {
			x--;
			y++;
		}
		if (direction == 1)
			y++;
		if (direction == 2) {
			x++;
			y++;
		}
		if (direction == 3)
			x--;
		if (direction == 4)
			x++;
		if (direction == 5) {
			x--;
			y--;
		}
		if (direction == 6)
			y--;
		if (direction == 7) {
			x++;
			y--;
		}
		if (currentAnimation != -1 && Animation.animations[currentAnimation].anInt306 == 1)
			currentAnimation = -1;
		if (walkingQueueSize < 9)
			walkingQueueSize++;
		for (int pos = walkingQueueSize; pos > 0; pos--) {
			walkingQueueX[pos] = walkingQueueX[pos - 1];
			walkingQueueY[pos] = walkingQueueY[pos - 1];
			runningQueue[pos] = runningQueue[pos - 1];
		}

		walkingQueueX[0] = x;
		walkingQueueY[0] = y;
		runningQueue[0] = running;
	}

	public void method567(int i, boolean flag, int j, int k) {
		for (int l = 0; l < 4; l++)
			if (anIntArray1632[l] <= i) {
				anIntArray1630[l] = j;
				anIntArray1631[l] = k;
				anIntArray1632[l] = i + 70;
				return;
			}

		if (flag)
			anInt1581 = -52;
	}

	public void teleport(int x, int y, boolean discard) {
		if (currentAnimation != -1 && Animation.animations[currentAnimation].anInt306 == 1)
			currentAnimation = -1;
		if (!discard) {
			int k = x - walkingQueueX[0];
			int i1 = y - walkingQueueY[0];
			if (k >= -8 && k <= 8 && i1 >= -8 && i1 <= 8) {
				if (walkingQueueSize < 9)
					walkingQueueSize++;
				for (int j1 = walkingQueueSize; j1 > 0; j1--) {
					walkingQueueX[j1] = walkingQueueX[j1 - 1];
					walkingQueueY[j1] = walkingQueueY[j1 - 1];
					runningQueue[j1] = runningQueue[j1 - 1];
				}

				walkingQueueX[0] = x;
				walkingQueueY[0] = y;
				runningQueue[0] = false;
				return;
			}
		}
		walkingQueueSize = 0;
		anInt1613 = 0;
		anInt1623 = 0;
		walkingQueueX[0] = x;
		walkingQueueY[0] = y;
		unitX = walkingQueueX[0] * 128 + size * 64;
		unitY = walkingQueueY[0] * 128 + size * 64;
	}

	public Actor() {
		anInt1581 = -89;
		anInt1582 = 100;
		walkingQueueX = new int[10];
		walkingQueueY = new int[10];
		anInt1588 = -1;
		runningQueue = new boolean[10];
		aBoolean1592 = false;
		anInt1594 = 200;
		anInt1595 = -1000;
		walkAnimation = 32;
		size = 1;
		anInt1609 = -1;
		graphicAnimation = -1;
		turnAnimation = -1;
		standAnimation = -1;
		deathAnimation = -1;
		combatLevel = -1;
		currentAnimation = -1;
		anInt1629 = -1;
		anIntArray1630 = new int[4];
		anIntArray1631 = new int[4];
		anIntArray1632 = new int[4];
		anInt1634 = -1;
		anInt1635 = -1;
	}

	public String aString1580;
	public int anInt1581;
	public int anInt1582;
	public int anInt1583;
	public int anInt1584;
	public int pulseCycle;
	public int walkingQueueX[];
	public int walkingQueueY[];
	public int anInt1588;
	public int anInt1589;
	public int anInt1590;
	public boolean runningQueue[];
	public boolean aBoolean1592;
	public int anInt1593;
	public int anInt1594;
	public int anInt1595;
	public int anInt1596;
	public int anInt1597;
	public int anInt1598;
	public int anInt1599;
	public int walkAnimation;
	public int size;
	public int anInt1602;
	public int anInt1603;
	public int anInt1604;
	public int anInt1605;
	public int anInt1606;
	public int anInt1607;
	public int anInt1608;
	public int anInt1609;
	public int unitX;
	public int unitY;
	public int anInt1612;
	public int anInt1613;

	// Spot animation (aka Gfx)
	public int graphicAnimation;
	public int graphicAnimationFrame;
	public int graphicAnimationCycle;
	public int graphicAnimationCycleEnd;
	public int graphicAnimationHeight;
	public int turnAnimation;
	public int standAnimation;
	public int deathAnimation;
	public int combatLevel;
	public int anInt1623;

	// Animation and state
	public int currentAnimation;
	public int animationFrame;
	public int animationFrameCycle;
	public int animationDelay;
	public int animationCycleReset;
	public int anInt1629;
	public int anIntArray1630[];
	public int anIntArray1631[];
	public int anIntArray1632[];
	public int walkingQueueSize;
	public int anInt1634;
	public int anInt1635;
}
