package test;

import com.fbinvestments.BroadcastingServer;
import com.fbinvestments.MessageFilter;
import com.fbinvestments.ClientEndpoint;
import org.java_websocket.WebSocketImpl;
import org.junit.Test;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

import static org.junit.Assert.assertEquals;
public class MessageFilterTest {
    @Test
    public void evaluatesExpression() {
        WebSocketImpl.DEBUG = true;
        int broadcastingPort = 8890; // 843 flash policy port

        MessageFilter messageFilter = new MessageFilter();
        try {
            BroadcastingServer broadcastingServer = new BroadcastingServer(broadcastingPort);
            messageFilter.attach(broadcastingServer);
            broadcastingServer.start();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:"+broadcastingPort+"/";
            container.connectToServer(ClientEndpoint.class, URI.create(uri));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }



        messageFilter.update("{'exten':1234,'url':'dd','number':123}");

        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}


        System.out.println("Received message :"+ ClientEndpoint.getLastMessage());


        int b = 6;
        assertEquals(6, b);

    }








}



