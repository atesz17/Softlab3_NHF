package com.newl.calendar;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		
		if (t1.compareTo(t2) < 0)
			return -1;
		else if (t1.compareTo(t2) == 0)
			return 0;
		else
			return 1;
	}

}
