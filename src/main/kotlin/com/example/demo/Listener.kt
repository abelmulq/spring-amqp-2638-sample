package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.mono
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class Listener {
    @RabbitListener(
        queuesToDeclare = [Queue(name = "not_working_queue", durable = "false")],
        ackMode = "MANUAL"
    )
    suspend fun not_working(message: Message): String {
        return "hello"
    }

    @RabbitListener(
        queuesToDeclare = [Queue(name = "working_queue", durable = "false")],
        ackMode = "MANUAL"
    )
    suspend fun working(message: Message): Mono<String> = mono {
        "hello"
    }
}
