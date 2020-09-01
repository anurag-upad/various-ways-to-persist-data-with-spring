package com.anurag.springbootapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {
	
	@Autowired
	private SortAlgorithm sortAlgorithm;
	
	public int binarySearch(int[] numbersToSort, int numberToSearch){
		return sortAlgorithm.binarySearch(numbersToSort, numberToSearch);
	}

}
