package com.hotlink.business.util;

import java.io.*;

public class JNAUtility {
    public static String extractLibraries(String libName, String[] dlls) {
        String nativeTempDir = System.getProperty("java.io.tmpdir");   

        if (nativeTempDir.endsWith(File.separator)) {
            nativeTempDir = nativeTempDir.substring(0, nativeTempDir.length() - 1);
        }
        
        String extractedLibName = nativeTempDir + File.separator + "nsfc_c1dd7bb8_" + libName;
        
        File extractedLibDir = new File(extractedLibName);
        if (extractedLibDir.exists()) {
            FileUtility.deleteDir(extractedLibDir);
        }
        
        extractedLibDir.mkdir();
        
        for (String dll : dlls) {
            InputStream in = null;   
            BufferedInputStream reader = null;   
            FileOutputStream writer = null;   
            
            File extractedDllFile = new File(extractedLibName + File.separator + dll);
            
            try {   
                in = Thread.currentThread().getContextClassLoader().getResourceAsStream(dll);   
    
                reader = new BufferedInputStream(in);   
                writer = new FileOutputStream(extractedDllFile);   
    
                byte[] buffer = new byte[1024];   
    
                while (reader.read(buffer) > 0) {   
                    writer.write(buffer);   
                    buffer = new byte[1024];   
                }
            } catch (IOException e){   
                e.printStackTrace();   
            } finally {   
                try {
                    if (in != null) {   
                        in.close(); 
                    }
    
                    if (writer!=null) {   
                        writer.close();
                    }
                } catch(IOException e) {
    
                }
            }   
        }
        
        return extractedLibName;
    }
    
    public static void main(String argv[]) {
        String tmpLibDir = JNAUtility.extractLibraries("zbarlib", new String[] {
                "libiconv-2.dll",
                "libjpeg-7.dll",
                "libMagickCore-2.dll",
                "libMagickWand-2.dll",
                "libpng12-0.dll",
                "libtiff-3.dll",
                "libxml2-2.dll",
                "libzbar-0.dll"
        });  
        
        System.out.println(tmpLibDir);
    }
}
