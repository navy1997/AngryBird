package com.birds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickLog {
    private Integer id;
    private String ip;
    private String header;
    private String city;
    private Long createAt = System.currentTimeMillis();
}
