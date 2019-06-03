package com.martiply.dao;

public class Config {

	public static final double SEARCH_RADIUS = 0.01 * 5; // (0.01 degree = 1 km
															// but 0.05 = 7.5 km
															// in reality )
	/*
	 * 
	 * Each degree of latitude is approximately 69 miles (111 kilometers) apart.
	 * The range varies (due to the earth's slightly ellipsoid shape) from
	 * 68.703 miles (110.567 km) at the equator to 69.407 (111.699 km) at the
	 * poles. This is convenient because each minute (1/60th of a degree) is
	 * approximately one mile.
	 * 
	 * A degree of longitude is widest at the equator at 69.172 miles (111.321)
	 * and gradually shrinks to zero at the poles. At 40° north or south the
	 * distance between a degree of longitude is 53 miles (85 km).
	 */



	public static final int MAX_ROWS_RETURNED = 20; 

}
