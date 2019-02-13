package com.snare;

import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

public class IHandler implements PayloadHandler {
  @Override
  public Mono<Payload> handle(Payload payload) {
    return Mono.just(DefaultPayload.create("Hello from handler"));
  }
}
