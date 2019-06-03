package com.martiply.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;

public class S3AdminUtil extends S3Util{
	
    final static AmazonS3Client client;
    
    static{
		client = new AmazonS3Client(new BasicAWSCredentials(AWSCredentials.ACCESS_KEY, AWSCredentials.SECRET_KEY));
    }
    
    public static void createFolder(String folderName){
    	

 		ObjectMetadata metadata = new ObjectMetadata();
 		metadata.setContentLength(0);

 		// Create empty content
 		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

 		// Create a PutObjectRequest passing the foldername suffixed by /
 		client.putObject(new PutObjectRequest(BUCKET, folderName, emptyContent, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
     }
     
     

     /**
      * Store file with default ACL PublicRead
      * @param f
      * @param bucket
      * @param key
      */
     public static void storeFile(File f, String key){	
 		storeFile(f, key, CannedAccessControlList.PublicRead);
     }
     
     public static void storeFile(File f, String key, CannedAccessControlList acl){
 		client.putObject(new PutObjectRequest(BUCKET, key, f).withCannedAcl(acl));	  		
     }
     
     /**
      * 
      * @param keyFolder must end with slash
      */
     public static void deleteFolder(String keyFolder){
         final ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(BUCKET).withPrefix(keyFolder);
         ObjectListing objects = client.listObjects(listObjectsRequest);
         ArrayList<KeyVersion> keyVersions = new ArrayList<DeleteObjectsRequest.KeyVersion>();
         do {
             for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
             	keyVersions.add(new KeyVersion(objectSummary.getKey()));
             }
             objects = client.listNextBatchOfObjects(objects);
         } while (objects.isTruncated());      
     	deleteObjects(keyVersions);    	
     }
     
     public static void deleteObject(String key){
     	client.deleteObject(BUCKET, key);	
     	
     }
     
     public static void deleteObjects(ArrayList<KeyVersion> keys){
     	DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(BUCKET);
     	multiObjectDeleteRequest.setKeys(keys);
 	    client.deleteObjects(multiObjectDeleteRequest);    	    			
     }
     
     public static boolean isValidFile(String key) {
         boolean isValidFile = true;
         try {
             client.getObjectMetadata(BUCKET, key);
         } catch (AmazonS3Exception s3e) {
             if (s3e.getStatusCode() == 404) {
             // i.e. 404: NoSuchKey - The specified key does not exist
                 isValidFile = false;
             }
             else {
                 throw s3e;    // rethrow all S3 exceptions other than 404   
             }
         }

         return isValidFile;
     }

}
