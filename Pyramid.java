/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		
		double x = ((getWidth()/2) + (BRICK_WIDTH * BRICKS_IN_BASE)/2);
		double y = (getHeight()-BRICK_HEIGHT);

		
		for (int i=0;i<BRICKS_IN_BASE;i++) {
			for (int a=0;a<BRICKS_IN_BASE-i;a++) {
				add (new GRect (x - BRICK_WIDTH * a, y, BRICK_WIDTH, BRICK_HEIGHT));
			}
			x = x-BRICK_WIDTH/2;
			y = y - BRICK_HEIGHT;
		}
	}
}
