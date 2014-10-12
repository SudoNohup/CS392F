package LApp;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity> {
	@Override
	public int compare(Entity e1, Entity e2) {
		return e1.name.compareTo(e2.name);
	}
}