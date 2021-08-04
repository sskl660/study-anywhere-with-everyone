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
@Entity(name = "challengetop")
@Table(name = "challengetop")
public class ChallengeTopEntity {
    @Id
    private String challengetopEmail;

    //유저 네임
    @Column(name = "challengetop_name")
    private String challengetopName;

    @Column(name = "challengetop_weekcnt")
    private int challengetopWeekcnt;

    @Builder
    public ChallengeTopEntity(String challengetopEmail, String challengetopName, int challengetopWeekcnt) {
        this.challengetopEmail = challengetopEmail;
        this.challengetopName = challengetopName;
        this.challengetopWeekcnt = challengetopWeekcnt;
    }
}
