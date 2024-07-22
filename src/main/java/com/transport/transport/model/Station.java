package com.transport.transport.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@Data
@RedisHash("Station")
public class Station {
    @Id
    private Long id;
    @JsonProperty("name")
    private String name;
}
