package com.martiply.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import com.martiply.dao.CouponDAO;
import com.martiply.dao.InventoryDAO;
import com.martiply.dao.StoreDAO;
import com.martiply.model.Coupon;
import com.martiply.model.Store;

public class Flow {

	
	public static int newStore(StoreDAO storeDAO, InventoryDAO inventoryDAO, int ownerId, Store store){			
		int storeId = storeDAO.newStore(ownerId, store);    
		String storeFolder = S3Util.STORE + "/" + storeId + "/"; // this is symbolic. Even without folder ready, S3 can still save file in a folder even when the folder is not yet created
		S3AdminUtil.createFolder(storeFolder);
		return storeId;
	}
	
	public static void newCoupon(final StoreDAO storeDAO, final CouponDAO couponDAO, final Coupon coupon, final int ownerId, final int[] storeIds, final File imgTemp){
		
		ExecutorService executorService = Executors.newFixedThreadPool(4);		
		executorService.execute(new Runnable() {
			
			@Override
			public void run() { // make new catalog
				couponDAO.newCouponSafe(coupon, ownerId);
				
			}
		});
		

		
		executorService.execute(new Runnable() { //update stores
			
			@Override
			public void run() {
				storeDAO.insertNewCoupon2LookupTable(storeIds, coupon.getCpId());				
			}
		});
		
		executorService.execute(new Runnable() { //move img 
			
			@Override
			public void run() {
				BufferedImage bi;
				try {
					bi = ImageIO.read(imgTemp);
			        File large = File.createTempFile("s3-temp-img-mainLarge", ".jpg");
			        large.deleteOnExit();
			        ImageIO.write(ImageUtils.largeImage(bi), "jpg", large);	       
			        
			        File normal = File.createTempFile("s3-temp-img-mainNormal", ".jpg");
			        normal.deleteOnExit();
			        ImageIO.write(ImageUtils.normalImage(bi), "jpg", normal);	
			        
			        File small = File.createTempFile("s3-temp-img-small", ".jpg");
			        small.deleteOnExit();
			        ImageIO.write(ImageUtils.smallImage(bi), "jpg", small);	   
			        
			        File xsmall = File.createTempFile("s3-temp-img-xsmall", ".jpg");
			        xsmall.deleteOnExit();
			        ImageIO.write(ImageUtils.xsmallImage(bi), "jpg", xsmall);	   

		
			        S3AdminUtil.storeFile(imgTemp, S3Util.getImageCouponKey(coupon.getCpId(), S3Util.SIZE_SOURCE));
			        S3AdminUtil.storeFile(large,  S3Util.getImageCouponKey(coupon.getCpId(), S3Util.SIZE_LARGE));
			        S3AdminUtil.storeFile(normal, S3Util.getImageCouponKey(coupon.getCpId(), S3Util.SIZE_NORMAL));  
			        S3AdminUtil.storeFile(small, S3Util.getImageCouponKey(coupon.getCpId(), S3Util.SIZE_SMALL));  
			        S3AdminUtil.storeFile(xsmall, S3Util.getImageCouponKey(coupon.getCpId(), S3Util.SIZE_XSMALL));  	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

//    	String catalogFolder = S3Util.CATALOG + "/" + newCatalogId + "/";
//    	S3Util.createFolder(catalogFolder);
    	
	}
	
	public static void deleteCatalog(CouponDAO couponDAO, long catalogId){
		couponDAO.delete(catalogId);
		deleteCatalogResources(catalogId);
	}
	
	public static void deleteCatalogResources(long catalogId){
    	String catalogFolder = S3Util.COUPON + "/" + catalogId + "/";
    	S3AdminUtil.deleteFolder(catalogFolder);		
	}
	
	public static void deleteStore(StoreDAO storeDAO, CouponDAO couponDAO, int storeId, int ownerId){
		
		Store store = storeDAO.getStore(storeId);
//		if (store.getCatalogId() > 0){			
//			deleteCatalog(catalogDAO, store.getCatalogId());				
//		}
		storeDAO.deleteStore(storeId);
		
		String storeFolder = S3Util.STORE + "/" + storeId + "/";
		
		S3AdminUtil.deleteFolder(storeFolder);
	}

	
}
