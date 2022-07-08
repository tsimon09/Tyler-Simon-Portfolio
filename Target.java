/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		GOval gOval = new GOval(300, 150, 144, 144);
		gOval.setFillColor(Color.RED);
		gOval.setFilled(true);
		add(gOval);
		
		GOval GOval = new GOval(325, 175, 93.6, 93.6);
		GOval.setFillColor(Color.WHITE);
		GOval.setFilled(true);
		add(GOval);
		
		GOval GOVal = new GOval(350, 200, 43.2, 43.2);
		GOVal.setFillColor(Color.RED);
		GOVal.setFilled(true);
		add(GOVal);
	}
}
