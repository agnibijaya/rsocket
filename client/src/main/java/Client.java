import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

public class Client {
  public static void main(String[] args) {
    RSocket socket = RSocketFactory.connect()
        .transport(TcpClientTransport.create("localhost", 7000))
        .start()
        .block();
    socket.requestResponse(DefaultPayload.create("Hello again"))
        .map(Payload::getDataUtf8)
        .onErrorReturn("error")
        .doOnNext(System.out::println)
        .block();
    socket.dispose();
  }
}
