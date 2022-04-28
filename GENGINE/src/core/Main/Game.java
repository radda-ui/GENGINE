package core.Main;

import java.util.ArrayList;
import java.util.List;

import core.gameObject.Entity;
import core.map.GameMap;

public abstract class Game {

	protected boolean initiated;
	protected GameMap map;
	protected List<Entity> entities = new ArrayList<>();
	protected List<State> states = new ArrayList<>();
	protected State current;

	public abstract void init();

	public void update() {
		current.update();
	}

	public void addState(State s) {
		states.forEach(state -> {
			if (state.getId().equals(s.getId()))
				return;
			else
				states.add(s);
		});
	}

	public State getCurrentState() {
		return current;
	}

	public State getState(String id) {
		for( State state :states){
			if(state.getId().equals(id))
				return state;
		}
		return null;
		
	}

	public final boolean isInitiated() {
		return initiated;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public List<State> getStates(){
		return states;
	}

}
