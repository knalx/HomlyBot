import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.server.tjws.TJWSServletServer;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by knalx on 02.05.16.
 *         //de6a7fa7-01ff-4f53-8dec-10238714b5b0

 */
public class RestTest {
//    @Test
    public void testGet(){
        String reguestString = "https://tts.voicetech.yandex.net/generate";
         //       +"text=\"Наш%20текст%20гот+ов\"&format=mp3&lang=ru-RU&speaker=zahar&emotion=good&;
        Client client;
        client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(reguestString)
                .queryParam("text","Привет милый")
                .queryParam("format","mp3")
                .queryParam("lang=ru-RU")
                .queryParam("key","de6a7fa7-01ff-4f53-8dec-10238714b5b0");
        Response response = target.request().get();


        Byte by = response.readEntity(Byte.class);
    //    Byte response.readEntity(Byte.class);
        //System.out.println(value);
        response.close();  // You should close connections!

    }

    public void test2(){
            String fileServiceUrl = "http://rama-local:8081/RESTfulDemoApplication/files";
        ClientBuilder.newBuilder().build();
    }


}
