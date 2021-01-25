package com.example.redis.listener;

import com.example.redis.model.SuppressionEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SuppressionEmailListener {

    /*
	https://stackoverflow.com/questions/62122538/how-to-acess-spring-data-redis-stored-object-at-the-expiration-event
	 */
    // need to enable redis notifications, inside redis-cli type:
    // config set notify-keyspace-events KEA
    @EventListener
    public void anything(RedisKeyExpiredEvent<SuppressionEmail> expiredCart) {
        SuppressionEmail suppressionEmail = (SuppressionEmail) expiredCart.getValue();
        log.info("RedisKeyExpired Listener Got: {}", suppressionEmail.getEmail());
    }
}
