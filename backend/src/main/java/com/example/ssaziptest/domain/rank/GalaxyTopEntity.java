package com.example.ssaziptest.domain.rank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity(name = "galaxytop")
@Table(name = "galaxytop")
public class GalaxyTopEntity {
    @Id
    private String galaxytopEmail;

    //유저 네임
    @Column(name = "galaxytop_name")
    private String galaxytopName;

    @Column(name = "galaxytop_weektime")
    private int galaxytopWeektime;

    @Builder
    public GalaxyTopEntity(String galaxytopEmail, String galaxytopName, int galaxyWeektime) {
        this.galaxytopEmail = galaxytopEmail;
        this.galaxytopName = galaxytopName;
        this.galaxytopWeektime = galaxyWeektime;
    }
}
