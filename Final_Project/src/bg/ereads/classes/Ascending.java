package bg.ereads.classes;

import java.util.Comparator;

public class Ascending implements Comparator {
	@Override
	public int compare(Object arg0, Object arg1) {
		// String first = (String) arg0;
		// String second = (String) arg1;

		String first = ((Book) arg0).getName();
		String second = ((Book) arg1).getName();

		if (first.compareToIgnoreCase(second) > 0) {
			return 1;
		}
		if (first.compareToIgnoreCase(second) < 0) {
			return -1;
		}
		if (first.compareToIgnoreCase(second) == 0) {
			return 0;
		}
		return 0;
	}
}
