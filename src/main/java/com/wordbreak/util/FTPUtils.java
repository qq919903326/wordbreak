package com.wordbreak.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.UnknownHostException;


public class FTPUtils {
	static String url="192.168.10.116";
	static int port=21;
	static String userName = "ftp1";
	static String password = "ftp1";

	/**
	 * 上传文件到ftp
	 *
	 * @param ftpFileName 上传到ftp文件路径名称
	 * @param localFile   本地文件路径名称
	 */
	public static void upload(String ftpFileName, File localFile) throws IOException {
		if (!localFile.exists()) {
			throw new IOException("Can't upload '" + localFile.getAbsolutePath() + "'. This file doesn't exist.");
		}

		InputStream in = new BufferedInputStream(new FileInputStream(localFile));
		if (!storeFile("ftp1",ftpFileName, in)) {
			throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
		}
		in.close();
	}
	/**
	   * 上传文件至FTP服务器 
	   *
	   *   用户登录密码 
	   * @param storePath 
	   *   服务器文件存储路径 
	   * @param fileName 
	   *   服务器文件存储名称 
	   * @param is 
	   *   文件输入流 
	   * @return 
	   *   <b>true</b>：上传成功 
	   *   <br/> 
	   *   <b>false</b>：上传失败 
	   */
	  public static boolean storeFile ( String storePath, String fileName, InputStream is) {
	    boolean result = false;
	    FTPClient ftp = new FTPClient();
	    try { 
	      // 连接至服务器，端口默认为21时，可直接通过URL连接 
	      ftp.connect(url ,port); 
	      // 登录服务器 
	      ftp.login(userName, password);
	      // 判断返回码是否合法 
	      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
	        // 不合法时断开连接 
	        ftp.disconnect(); 
	        // 结束程序 
	        return result; 
	      } 
	      // 判断ftp目录是否存在，如果不存在则创建目录，包括创建多级目录 
	      String s = "/"+storePath; 
	      String[] dirs = s.split("/"); 
	      ftp.changeWorkingDirectory("/");       

	        //按顺序检查目录是否存在，不存在则创建目录  
	        for(int i=1; dirs!=null&&i<dirs.length; i++) {  
	          if(!ftp.changeWorkingDirectory(dirs[i])) {  
	            if(ftp.makeDirectory(new String(dirs[i].getBytes("UTF-8"),"iso-8859-1"))) {  
	              if(!ftp.changeWorkingDirectory(dirs[i])) {  
	                return false;  
	              }  
	            }else {  
	              return false;              
	            }  
	          }  
	        }  
	      // 设置文件操作目录 
	      ftp.changeWorkingDirectory(storePath); 
	      // 设置文件类型，二进制 
	      ftp.setFileType(FTPClient.BINARY_FILE_TYPE); 
	      // 设置缓冲区大小 
	      //ftp.setBufferSize(3072); 
	      // 上传文件 
	      ftp.enterLocalPassiveMode();  
	      ftp.setControlEncoding("GBK");
	      //ftp.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);  
	      result = ftp.storeFile(new String(fileName.getBytes("GBK"),"iso-8859-1"), is); 
	      
	      // 关闭输入流 
	      is.close(); 
	      // 登出服务器 
	      ftp.logout(); 
	    } catch (IOException e) { 
	      e.printStackTrace(); 
	    } finally { 
	      try { 
	        // 判断输入流是否存在 
	        if (null != is) { 
	          // 关闭输入流 
	          is.close(); 
	        } 
	        // 判断连接是否存在 
	        if (ftp.isConnected()) { 
	          // 断开连接 
	          ftp.disconnect(); 
	        } 
	      } catch (IOException e) { 
	        e.printStackTrace(); 
	      } 
	    } 
	    return result; 
	  } 
	  
	  public static InputStream getFTPFileInputStream(String remotePath) {
		    FTPClient ftp = new FTPClient(); 
		    try { 
		      // 连接至服务器，端口默认为21时，可直接通过URL连接 
		      ftp.connect(url ,port); 
		      // 登录服务器 
		      ftp.login(userName, password); 
		      // 判断返回码是否合法 
		      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) { 
		        // 不合法时断开连接 
		        ftp.disconnect(); 
		        // 结束程序 
		        return null; 
		      } 
		      InputStream inputStream = ftp.retrieveFileStream(remotePath);
		      return inputStream;
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally { 
		      try { 
			        // 判断连接是否存在 
			        if (ftp.isConnected()) { 
			          // 断开连接 
			          ftp.disconnect(); 
			        } 
			      } catch (IOException e) { 
			        e.printStackTrace(); 
			      } 
			}
			return null;
	  }
	  

	  /** 
	   * 从FTP服务器下载文件至本地 
	   *
	   * @param remotePath 
	   *   服务器文件存储路径 
	   * @param fileName 
	   *   服务器文件存储名称 
	   * @param localPath 
	   *   本地文件存储路径 
	   * @return 
	   *   <b>true</b>：下载成功 
	   *   <br/> 
	   *   <b>false</b>：下载失败 
	   */ 
	  public static boolean retrieveFile (String remotePath, String fileName, String localPath) {
	    boolean result = false;
	    FTPClient ftp = new FTPClient(); 
	    OutputStream os = null; 
	    try { 
	      // 连接至服务器，端口默认为21时，可直接通过URL连接 
	      ftp.connect(url ,port); 
	      // 登录服务器 
	      ftp.login(userName, password); 
	      // 判断返回码是否合法 
	      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) { 
	        // 不合法时断开连接 
	        ftp.disconnect(); 
	        // 结束程序 
	        return result; 
	      } 
	      // 设置文件操作目录 
	      ftp.changeWorkingDirectory(remotePath);
			System.out.println("ftp下载文件路径："+remotePath);
	      // 设置文件类型，二进制 
	      ftp.setFileType(FTPClient.BINARY_FILE_TYPE); 
	      // 设置缓冲区大小 
	      //ftp.setBufferSize(3072); 

	      ftp.enterLocalPassiveMode();  
	      // 设置字符编码 
	      ftp.setControlEncoding("GBK");
	      // 构造本地文件对象
	      File localFile = new File(localPath + fileName); 
	      // 获取文件操作目录下所有文件名称 
	      String[] remoteNames = ftp.listNames(); 
	      // 循环比对文件名称，判断是否含有当前要下载的文件名 
	      for (String remoteName: remoteNames) { 
	        if (fileName.equals(remoteName)) { 
	          result = true; 
	        } 
	      } 
	      // 文件名称比对成功时，进入下载流程 
	      if (result) { 
	        // 构造文件输出流 
	        os = new FileOutputStream(localFile); 
	        // 下载文件 
	        result = ftp.retrieveFile(new String(fileName.getBytes("GBK"),"iso-8859-1"), os); 
	        // 关闭输出流 
	        os.close(); 
	      } 
	      // 登出服务器 
	      ftp.logout(); 
	    } catch (IOException e) { 
	      e.printStackTrace(); 
	    } finally { 
	      try { 
	        // 判断输出流是否存在 
	        if (null != os) { 
	          // 关闭输出流 
	          os.close(); 
	        } 
	        // 判断连接是否存在 
	        if (ftp.isConnected()) { 
	          // 断开连接 
	          ftp.disconnect(); 
	        } 
	      } catch (IOException e) { 
	        e.printStackTrace(); 
	      } 
	    } 
	    return result; 
	  } 
	   
	  /** 
	   * 从FTP服务器删除文件 
	   *
	   * @param remotePath 
	   *   服务器文件存储路径 
	   * @param fileName 
	   *   服务器文件存储名称 
	   * @return 
	   *   <b>true</b>：删除成功 
	   *   <br/> 
	   *   <b>false</b>：删除失败 
	   */ 
	  public static boolean deleteFile (String remotePath, String fileName) {
	    boolean result = false;
	    FTPClient ftp = new FTPClient(); 
	    try { 
	      // 连接至服务器，端口默认为21时，可直接通过URL连接 
	      ftp.connect(url ,port); 
	      // 登录服务器 
	      ftp.login(userName, password); 
	      // 判断返回码是否合法 
	      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) { 
	        // 不合法时断开连接 
	        ftp.disconnect(); 
	        // 结束程序 
	        return result; 
	      } 
	      // 设置文件操作目录 
	      ftp.changeWorkingDirectory(remotePath); 
	      // 设置文件类型，二进制 
	      ftp.setFileType(FTPClient.BINARY_FILE_TYPE); 
	      // 设置缓冲区大小 
	      ftp.setBufferSize(3072); 
	      // 设置字符编码 
	      ftp.enterLocalPassiveMode();  
	      ftp.setControlEncoding("GBK"); 
	      // 获取文件操作目录下所有文件名称 
	      String[] remoteNames = ftp.listNames(); 
	      // 循环比对文件名称，判断是否含有当前要下载的文件名 
	      for (String remoteName: remoteNames) { 
	        if (fileName.equals(remoteName)) { 
	          result = true; 
	        } 
	      } 
	      // 文件名称比对成功时，进入删除流程 
	      if (result) { 
	        // 删除文件 
	        result = ftp.deleteFile(new String(fileName.getBytes("GBK"),"iso-8859-1")); 
	      } 
	      // 登出服务器 
	      ftp.logout(); 
	    } catch (IOException e) { 
	      e.printStackTrace(); 
	    } finally { 
	      try { 
	        // 判断连接是否存在 
	        if (ftp.isConnected()) { 
	          // 断开连接 
	          ftp.disconnect(); 
	        } 
	      } catch (IOException e) { 
	        e.printStackTrace(); 
	      } 
	    } 
	    return result; 
	  }   
}
