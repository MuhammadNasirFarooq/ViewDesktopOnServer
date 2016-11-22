package org.screen.watcher;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
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
        
//            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//            BufferedImage capture = new Robot().createScreenCapture(screenRect);
//            ImageIO.write(capture, "bmp", new File("C:\\Users\\nasir\\Desktop\\screenshoot-"+i+".bmp"));
        
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        int i = 0;
        for (GraphicsDevice screen : screens) {
        	
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
            BufferedImage capture = new Robot().createScreenCapture(screenBounds);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(capture, "bmp", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            requestMultiPartData(is,"ScreenShot-"+i+".bmp");
            i++;
        }
    }
    
    public static void requestMultiPartData(  InputStream is, String name) throws ClientProtocolException, IOException{
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost uploadFile = new HttpPost("http://localhost:8080/uploadFile");
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create();

    	// This attaches the file to the POST:
    	builder.addBinaryBody("uploadfile", is, ContentType.APPLICATION_OCTET_STREAM, name );

    	HttpEntity multipart = builder.build();
    	uploadFile.setEntity(multipart);
    	CloseableHttpResponse response = httpClient.execute(uploadFile);
    	HttpEntity responseEntity = response.getEntity();
    	
    }
}
