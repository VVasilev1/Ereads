package bg.ereads.classes;

import java.util.Comparator;

public class AsendingRating implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		double first = ((Book) arg0).getRating();
		double second = ((Book) arg1).getRating();
		if (first > second) {
			return -1;
		}
		if (first < second) {
			return +1;
		}
		if (first == second) {
			return 0;
		}
		return 0;

	}

}
