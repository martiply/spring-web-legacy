package com.martiply.utils;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class ImageUtils {
	
	public static int IMG_MIN_WIDTH = 750;
	public static int IMG_MIN_HEIGHT = 750;
	
	public static int IMG_MIN_WIDTH_LARGE = 650;
	public static int IMG_MIN_HEIGHT_LARGE = 650;
	
	public static int IMG_MIN_WIDTH_NORMAL = 450;
	public static int IMG_MIN_WIDTH_SMALL = 250;
	public static int IMG_MIN_WIDTH_XSMALL = 150;
	
	public static boolean verifySize(BufferedImage bimg){
		int width          = bimg.getWidth();
		int height         = bimg.getHeight();
		
		if (width >= IMG_MIN_WIDTH_LARGE && height >= IMG_MIN_HEIGHT_LARGE){
			return true;
		}else{
			return false;
		}								
	}
	
    public static BufferedImage sourceImage(BufferedImage originalImage){  	
    	return Scalr.resize(originalImage, Scalr.Method.SPEED, originalImage.getWidth() >= originalImage.getHeight() ? Mode.FIT_TO_HEIGHT : Mode.FIT_TO_WIDTH,
    			IMG_MIN_WIDTH_NORMAL, IMG_MIN_WIDTH_NORMAL, Scalr.OP_ANTIALIAS);
    }
	
    public static BufferedImage largeImage(BufferedImage originalImage){   	
    	return Scalr.resize(originalImage, Scalr.Method.SPEED, originalImage.getWidth() >= originalImage.getHeight() ? Mode.FIT_TO_HEIGHT : Mode.FIT_TO_WIDTH,
    			IMG_MIN_WIDTH_LARGE, IMG_MIN_HEIGHT_LARGE, Scalr.OP_ANTIALIAS);
    }	 
    
    public static BufferedImage normalImage(BufferedImage originalImage){  	
    	return Scalr.resize(originalImage, Scalr.Method.SPEED, originalImage.getWidth() >= originalImage.getHeight() ? Mode.FIT_TO_HEIGHT : Mode.FIT_TO_WIDTH,
    			IMG_MIN_WIDTH_NORMAL, IMG_MIN_WIDTH_NORMAL, Scalr.OP_ANTIALIAS);
    }
    
    public static BufferedImage smallImage(BufferedImage originalImage){  	
    	return Scalr.resize(originalImage, Scalr.Method.SPEED, originalImage.getWidth() >= originalImage.getHeight() ? Mode.FIT_TO_HEIGHT : Mode.FIT_TO_WIDTH,
    			IMG_MIN_WIDTH_SMALL, IMG_MIN_WIDTH_SMALL, Scalr.OP_ANTIALIAS);
    }
    
    public static BufferedImage xsmallImage(BufferedImage originalImage){  	
    	return Scalr.resize(originalImage, Scalr.Method.SPEED, originalImage.getWidth() >= originalImage.getHeight() ? Mode.FIT_TO_HEIGHT : Mode.FIT_TO_WIDTH,
    			IMG_MIN_WIDTH_XSMALL, IMG_MIN_WIDTH_XSMALL, Scalr.OP_ANTIALIAS);
    }

}
