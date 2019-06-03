package com.martiply.utils;

public class S3Util {

	//store dir structure
	//bucket - store - storeId - img - normal
	//                               - large
	
	

    
	public static final String SIZE_SOURCE = "source";
	public static final String SIZE_LARGE = "large";
	public static final String SIZE_NORMAL = "normal";
	public static final String SIZE_SMALL = "small";
	public static final String SIZE_XSMALL = "xsmall";
	
	
	public static final String BUCKET = "martiply";
	
	public static final String STORE = "store";
	public static final String COUPON = "coupon";
	public static final String ITEM = "i";
	public static final String OWNER = "owner";
	

	
	public static final String JPG = "jpg";
	
	/*
	 * Rules:
	 * 1. Image file in stores name starts with type prefix and ends with size suffix i.e. mainlarge for main large
	 * 2. Image files in coupon and i are the sizes i.e large.jpg
	 * 
	 * Warning:
	 * 1. Put all files in a same folder without subdirs because S3 folders cannot be deleted if not empty. 
	 */
	
	
	public static String getS3Url(String key){
		return "http://" + BUCKET + ".s3.amazonaws.com/" + key;		
	}
	
	public static String getImageItemKey(String itemId, int which, String size){
		return String.format(ITEM + "/%s/%d%s.%s",
				itemId,
				which,
				size,
				JPG);
	}
	
	public static String getImageCouponKey(long cpId, String size){
		return String.format(COUPON + "/%d/%s.%s",
				cpId,
				size,
				JPG
				);
		
	}
	
	public static String getImageStoreKey(int storeId, int which, String size){
		return String.format(STORE + "/%d/%d%s.%s",
				storeId,
				which,
				size,
				JPG);
	}
	
	public static String makeImageFolderKey(String itemId){
		return String.format(ITEM, "/%s/");
	}
	
	
	public static String getOwnerTempFileKey(int ownerId, String stuff){
		return String.format(OWNER + "/%d/%s.%s",
				ownerId,
				stuff,
				JPG
				);
		
	}
    
    
    /**
     * Create a folder in S3 bucket
     * @param folderName MUST end with backlash
     */
 
    
    
}
