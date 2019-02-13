package com.snare;

import io.rsocket.Payload;
import reactor.core.publisher.Mono;

public interface PayloadHandler {
  Mono<Payload> handle(Payload payload);
}
