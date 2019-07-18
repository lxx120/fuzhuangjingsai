package com.match.fastdfs;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.UploadStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class FastDFSUtils {
	
	@PostConstruct
	public void init() {
		try {
			ClientGlobal.initByProperties("fastdfs.properties");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public static String[] uploadFile(InputStream inStream, String uploadFileName,long fileLength) throws Exception {
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			throw new Exception(
					"Fail to upload file, because the format of filename is illegal.");
		}
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient1 client = new StorageClient1(trackerServer, storageServer);
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));
		try{
			UploadStream up=new UploadStream(inStream, fileLength);
			String[] result=client.upload_file(null, fileLength, up, fileExtName, metaList);
			return result;
		}catch(Exception e){
			throw e;			
		}finally{
			trackerServer.close();
		}		
	}
	
	public String uploadFile1(InputStream inStream, String uploadFileName,
			long fileLength) throws IOException, Exception {
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			throw new Exception(
					"Fail to upload file, because the format of filename is illegal.");
		}
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient1 client = new StorageClient1(trackerServer, storageServer);
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));
		try{
			UploadStream up=new UploadStream(inStream, fileLength);
			String result=client.upload_file1(null, fileLength, up, fileExtName, metaList);
			return result;
		}catch(Exception e){
			throw e;			
		}finally{
			trackerServer.close();
		}		
	}
	
	public String uploadFileBytes(byte[] bytes, String uploadFileName,
			long fileLength) throws IOException, Exception {
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			throw new Exception(
					"Fail to upload file, because the format of filename is illegal.");
		}
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient1 client = new StorageClient1(trackerServer, storageServer);
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));
		try{
			String result=client.upload_file1(bytes,uploadFileName,metaList);
			return result;
		}catch(Exception e){
			throw e;			
		}finally{
			trackerServer.close();
		}		
	}
}

 
