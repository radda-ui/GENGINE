package core.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.gameObject.Entity;
import core.map.GameMap;

public abstract class Game {

	protected boolean initiated;
	protected GameMap map;
	protected List<Entity> entities = new ArrayList<>();
	protected HashMap<String, State> states = new HashMap<String, State>();
	protected State current;

	public abstract void init();

	public abstract void update();

	public void addState(State s) {
		states.forEach((id, state) -> {
			if (s.getId().equals(id))
				return;
			else
				states.put(s.getId(), s);
		});
	}

	public State getCurrentState() {
		return current;
	}

	public State getState(String id) {
		states.containsKey(id);
		return states.get(id);
	}

	public final boolean isInitiated() {
		return initiated;
	}

	protected GameMap getMap() {
		return map;
	}

}
