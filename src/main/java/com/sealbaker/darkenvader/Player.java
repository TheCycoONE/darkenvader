package com.sealbaker.darkenvader;

class Player extends Actor {
	int score;

	void reset(int dfclty) {
		x = 700;
		y = 700;
		speed = 4;
		score = 0;
		switch (dfclty) {
		case 1:
			spirit = 100;
			attack = 20;
			defence = 20;
			break;
		case 2:
			spirit = 50;
			attack = 15;
			defence = 15;
			break;
		case 3:
			spirit = 25;
			attack = 10;
			defence = 10;
			break;
		default:
			spirit = 100;
			attack = 100;
			defence = 100;
			break;
		}
		mapPicURL = "PCD0.gif";
	}
}
