package com.transport.transport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Path")
public class Path {
    @Id
    private Long id;
    @JsonProperty("sourceId")
    private Long sourceId;
    @JsonProperty("destinationId")
    private Long destinationId;
    @JsonProperty("cost")
    private double cost;
}
