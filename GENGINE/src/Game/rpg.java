package Game;

import core.Main.Engine;
import core.Main.Game;
import core.Main.State;
import core.map.GameMap;

public class rpg extends Game {

	private int next = 15;

	public rpg() {
		super();
	}

	@Override
	public void init() {
		current = new State();

		map = new GameMap().Set("map.tmx");

		initiated = true;
	}

	@Override
	public void update() {
		if (Engine.time.asSeconds() == next) {
			System.out.println("map should be updated");
			map.update();
			next = Engine.time.asSeconds() + 15;
		}
	}

}
