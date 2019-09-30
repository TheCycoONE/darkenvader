package com.sealbaker.darkenvader;

import java.awt.Image;

class Actor {
	int x;
	int y;
	int spirit;
	int score;
	int attack;
	int defence;
	Image mapIcon;
	int xwidth;
	int ywidth;
	int speed;
	String mapPicURL;

	void setupActor(int X, int Y, String ActorType) {
		x = X;
		y = Y;
	}
}
