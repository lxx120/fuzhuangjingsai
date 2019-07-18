
package com.match.common.ParamFileter;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;


/** 
 * Function: 实现文件的简单处理,复制和移动文件、目录等    <br/> 
 * Date:     2016年4月19日 上午10:54:24 <br/> 
 * @author   guolq        
 */
public class Fileoperation { 
	   
	   /**   
	     *  新建目录   
	     *  @param  folderPath  String  如  c:/fqf   
	     *  @return  boolean   
	     */    
	   public static  void  newFolder(String  folderPath)  {    
	       try  {    
	           String  filePath  =  folderPath;  
	           Path p=Paths.get(filePath);
	           Files.createDirectories(p);
	       }    
	       catch  (Exception  e)  {    
	           e.printStackTrace();    
	       }    
	   }    
	   
	   /**   
	     *  新建文件   
	     *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt   
	     *  @param  fileContent  String  文件内容   
	     *  @return  boolean   
	     */    
	   public static  void  newFile(String  filePathAndName,  String  fileContent)  {    
	   
	       try  {    
	           String  filePath  =  filePathAndName;    
	           filePath  =  filePath.toString();  //取的路径及文件名  
	           Path p=Paths.get(filePath);
	           Files.write(p, fileContent.getBytes(), StandardOpenOption.APPEND,StandardOpenOption.CREATE_NEW);
	       }    
	       catch  (Exception  e)  {    
	           e.printStackTrace();    
	   
	       }    
	   
	   }    
	   
	   /**   
	     *  删除文件   
	     *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt   
	     *  @param  fileContent  String   
	     *  @return  boolean   
	     */    
	   public  static void  delFile(String  filePathAndName)  {    
	       try  {    
	           String  filePath  =  filePathAndName;    
	           filePath  =  filePath.toString();    
	           Path p=Paths.get(filePath);
	           if(Files.isDirectory(p))
	        	   return ;
	           Files.deleteIfExists(p);
	       }   catch  (Exception  e)  {    
	           e.printStackTrace();    
	   
	       }    
	   
	   }    
	   
	   /**   
	     *  删除文件夹   
	     *  @param  filePathAndName  String  文件夹路径及名称  如c:/fqf   
	     *  @param  fileContent  String   
	     *  @return  boolean   
	     */    
	   public static void  delFolder(Path p)  {    
	       try  {   
	    	   Files.walkFileTree(p,new SimpleFileVisitor<Path>(){
	    		   @Override
	                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	                    Files.delete(file);
	                    return FileVisitResult.CONTINUE;
	                }

	                @Override
	                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
	                    Files.delete(dir);
	                    return super.postVisitDirectory(dir, exc);
	                }
	    	   });
	       }    
	       catch  (Exception  e)  {    
	           e.printStackTrace();    
	   
	       }    
	   
	   }    
	   
	   
	   
	   /**   
	     *  复制单个文件   
	     *  @param  oldPath  String  原文件路径  如：c:/fqf.txt   
	     *  @param  newPath  String  复制后路径  如：f:/fqf.txt   
	     *  @return  boolean   
	     */    
	   public static  void  copyFile(String  oldPath,  String  newPath)  {    
	       try  {    
	          Files.copy(Paths.get(oldPath), Paths.get(newPath),StandardCopyOption.REPLACE_EXISTING)  ;
	       }    
	       catch  (Exception  e)  {    
	           e.printStackTrace();    
	       }    
	   
	   }    
	   
	    
}
