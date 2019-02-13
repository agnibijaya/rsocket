package com.snare;

import io.rsocket.*;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Slf4j
public class PayloadAcceptor implements SocketAcceptor {
  @Override
  public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) {
    return Mono.just(new AbstractRSocket() {
      @Override
      public Mono<Payload> requestResponse(Payload payload) {
        log.info("Received from client :"+payload.getDataUtf8());
        return Mono.just(DefaultPayload.create("Hello from my first rsocket app @" + Instant.now()));
      }
    });
  }
}
