package core;

import Game.rpg;
import core.Main.Engine;

public class run {

	public static void main(String[] args) {
		Engine engine = new Engine();
		Engine.setGame(new rpg());
		engine.start();
	}

}
