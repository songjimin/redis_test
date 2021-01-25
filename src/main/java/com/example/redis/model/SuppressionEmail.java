package com.example.redis.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;
import java.util.Date;

@RedisHash("suppressionEmail")
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class SuppressionEmail {
    @Id
    private String email;
    @TimeToLive
    private long timeToLive;
}
