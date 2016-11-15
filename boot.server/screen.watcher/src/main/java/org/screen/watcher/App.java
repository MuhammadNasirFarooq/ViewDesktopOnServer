package org.screen.watcher;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, AWTException, InterruptedException
    {
        System.out.println( "Hello World!" );
        
//        for (int i = 0; i < 5; i++) {
//        	Thread.sleep(50);
//            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//            BufferedImage capture = new Robot().createScreenCapture(screenRect);
//            ImageIO.write(capture, "bmp", new File("C:\\Users\\nasir\\Desktop\\screenshoot-"+i+".bmp"));
//		}
        
        
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice[] screens = ge.getScreenDevices();
//
//        int i = 0;
//        for (GraphicsDevice screen : screens) {
//        	
//            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
//            BufferedImage capture = new Robot().createScreenCapture(screenBounds);
//            ImageIO.write(capture, "bmp", new File("/home/sces122/abc/abc-"+i+".bmp"));
//            i++;
//        }

        
        requestMultiPartData();
    }
    
    public static void requestMultiPartData() throws ClientProtocolException, IOException{
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost uploadFile = new HttpPost("http://localhost:8080/uploadFile");
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create();

    	// This attaches the file to the POST:
    	File f = new File("C:\\Users\\nasir\\Desktop\\screenshoot.bmp");
    	builder.addBinaryBody("uploadfile", new FileInputStream(f), ContentType.APPLICATION_OCTET_STREAM, f.getName() );

    	HttpEntity multipart = builder.build();
    	uploadFile.setEntity(multipart);
    	CloseableHttpResponse response = httpClient.execute(uploadFile);
    	HttpEntity responseEntity = response.getEntity();
    	
    }
}
