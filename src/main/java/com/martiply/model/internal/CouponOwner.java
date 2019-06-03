package com.martiply.model.internal;

import com.martiply.model.Coupon;
import com.martiply.model.Owner;

public class CouponOwner {

	private Coupon coupon;
	private Owner owner;
	private BidAndCurrency bidAndCurrency;
	
	
	public CouponOwner(Coupon coupon, Owner owner, BidAndCurrency bidAndCurrency) {
		this.coupon = coupon;
		this.owner = owner;
		this.bidAndCurrency = bidAndCurrency;
	}
	
	
	public Coupon getcoupon() {
		return coupon;
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public BidAndCurrency getBidAndCurrency() {
		return bidAndCurrency;
	}
}
