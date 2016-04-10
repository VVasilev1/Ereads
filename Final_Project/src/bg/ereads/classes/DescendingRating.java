package bg.ereads.classes;

import java.util.Comparator;

public class DescendingRating implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		double first =  ((Book) o1).getRating();
		double second = ((Book)o2).getRating();
		if(first > second) {
			return 1;
		}
		if(first < second) {
			return -1;
		}
		if(first == second) {
			return 0;
		}
		return 0;
	}

}
