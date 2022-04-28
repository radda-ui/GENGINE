package core.gameObject;

public class gameobject {
	// Global Unique identity;
	protected int guid = 0;

	public gameobject() {
		guid++;
	}

	public final int getGuid() {
		return guid;
	}
}
