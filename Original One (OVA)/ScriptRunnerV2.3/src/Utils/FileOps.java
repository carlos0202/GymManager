/*
 * 
 * Responsable:
 * Carlos Antonio González Canario
 * 
 */

package Utils;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;

public class FileOps {
    
    public static void copyFile(String src, String dest)
    {
        try{
              File source = new File(src);
              File destination = new File(dest);
              FileChannel in = (new FileInputStream(source)).getChannel();
              FileChannel out = (new FileOutputStream(destination)).getChannel();
              in.transferTo(0, source.length(), out);
              in.close();
              out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void copyStream(InputStream is, String outputDest) throws Exception{
        File destination = new File(outputDest);
        InputStream inputStream = is;
        OutputStream os = new FileOutputStream(outputDest);
        
        byte[] buffer = new byte[4096];
        int length;
        
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.close();
        is.close();

    }
    
}
