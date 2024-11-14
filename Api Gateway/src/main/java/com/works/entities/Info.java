package com.works.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spanId;
    private String traceId;
    private String username;
    private String roles;
    private String userAgent;
    private String ip;
    private String url;
    private String time;
    private String sessionID;

}
