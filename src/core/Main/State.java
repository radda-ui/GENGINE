package core.Main;

public class State {

	protected String id;

	public State() {
		id = getClass().getSimpleName();
	}

	public void update() {
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

}
