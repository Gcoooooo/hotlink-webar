package com.hotlink.business.util;

import org.apache.commons.lang.StringUtils;
import org.wechatvr.framework.log.Log;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtility {
    private static final Log LOG = Log.getLog(FileUtility.class);
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static void copyFolder(File src, File dest) throws IOException {  
        if (src.isDirectory()) {  
            if (!dest.exists()) {  
                dest.mkdir();  
            } 

            String files[] = src.list();  
            for (String file : files) {  
                File srcFile = new File(src, file);  
                File destFile = new File(dest, file);  
                // 递归复制  
                copyFolder(srcFile, destFile);  
            }  
        } else {  
            InputStream in = new FileInputStream(src);  
            OutputStream out = new FileOutputStream(dest);  

            byte[] buffer = new byte[1024];  

            int length;  

            while ((length = in.read(buffer)) > 0) {  
                out.write(buffer, 0, length);  
            }

            in.close();  
            out.close();  
        }  
    }  

    public static String readText(File file) throws IOException {
        BufferedReader br = null;

        try {
            String currentLine = null;
            br = new BufferedReader(new FileReader(file));
            while ((currentLine = br.readLine()) != null) {         

                if (StringUtils.isNotBlank(currentLine)) {  
                    break;
                } else {
                    currentLine = null;
                }
            }

            return currentLine;
        } finally { 
            if (br != null) {
                br.close();
            }

        }
    }

    public static void writeText(File file, String content) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            
            fw = new FileWriter(file.getAbsoluteFile(), false);  //false表示不追加新内容  
            
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } finally { 
            if (bw != null) {
                bw.close();
            }
        }

    }   

    public static void unzip(String file, String outputFolder) { 
        
        ZipFile zipFile = null;
        
        try {
            zipFile = new ZipFile(file); 
            Enumeration<?> emu = zipFile.entries();
            
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry)emu.nextElement();

                //建立目录
                if (entry.isDirectory()) {
                    new File(outputFolder + FILE_SEPARATOR + entry.getName()).mkdirs();
                    continue;
                }
                
                //文件拷贝
                InputStream is = zipFile.getInputStream(entry);
                File tmpFile = new File(outputFolder + FILE_SEPARATOR +  entry.getName());
                
                //注意：zipfile读取文件是随机读取的，可能先读取一个文件，再读取文件夹，所以可能要先创建目录
                File parent = tmpFile.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                
                OutputStream out = new FileOutputStream(tmpFile);
                byte[] buffer = new byte[1024];  

                int length;  

                while ((length = is.read(buffer)) > 0) {  
                    out.write(buffer, 0, length);  
                }
                
                is.close();
                out.close();
            } 
        } catch(IOException e) {
            LOG.error("Catch an exception!", e);
        } finally {
            if(zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    LOG.error("Catch an exception!", e);
                }
            }
        } 
    }

    public static void main(String[] args) throws IOException {
        unzip("d:/jre_1.8_145.zip", "d:/tmp");
    }
}