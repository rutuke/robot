package rpbot;


import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Jelly_Beans extends Robot {

	double dist = 30;
	boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move

	/**
	 * run: Move around the walls
	 */
	public void run() {
		
		//setAdjustGunForRobotTurn(true);
		//setAdjustRadarForGunTurn(true);

		//setTurnRadarRight(Double.POSITIVE_INFINITY);		

		setBodyColor(Color.pink);
		setGunColor(Color.pink);
		setRadarColor(Color.yellow);
		setBulletColor(Color.orange);
		setScanColor(Color.yellow);
		
		

		moveAmount = Math.max(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2);
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(90);
		turnRight(90);

		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			//turnGunRight((this.getHeading() - this.getGunHeading()) + e.getBearing());
			turnRight(90);
		}
	}

	/**
	 * onHitRobot:  Move away a bit.
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			//fire(3);
			back(2500);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(250);
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		turnGunRight((this.getHeading() - this.getGunHeading()) + e.getBearing());
		fire(3);
		/*if (e.getDistance() < 50 && getEnergy() > 50) {
			fire(3);
		}
		else {
			fire(1);
		}
		if (peek) {
			scan();
		}
		if (e.getBearing() > -90 && e.getBearing() < 90) 
			fire(3);
	*/
		}
}

