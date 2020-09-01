package com.anurag.springbootapp;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class QuickSortAlgorithm implements SortAlgorithm{

	@Override
	public int binarySearch(int[] numbersToSort, int numberToSearch) {
		System.out.println("inside QuickSortAlgorithm class");
		return 3;
	}

}
