package com.snare;

import io.rsocket.*;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.time.Instant;

@SpringBootApplication
@Slf4j
public class RsocketApplication implements ApplicationListener<ApplicationReadyEvent> {
  public static void main(String[] args) {
    SpringApplication.run(RsocketApplication.class, args);
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    RSocketFactory.receive()
        .acceptor(new PayloadAcceptor())
        .transport(TcpServerTransport.create("localhost", 7000))
        .start()
        .onTerminateDetach().subscribe(nettyContextCloseable -> log.info("Server started @" + Instant.now().toString()));
  }
}
