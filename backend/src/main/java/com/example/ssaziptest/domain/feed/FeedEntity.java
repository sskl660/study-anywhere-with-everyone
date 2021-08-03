package com.example.ssaziptest.domain.feed;

import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "feed")
@Table(name = "feed")
@EntityListeners(AuditingEntityListener.class)
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedNo;

    @Column(name = "feed_type")
    private int feedType;

    //피드 주인
    @Column(name = "feed_owner")
    private String feedOwner;

    //이벤트 발생시킨 사람
    @ManyToOne
    @JoinColumn(name = "fk_user_feed_email")
    private UserEntity feedUserEntity;

    //challenge, follow, galaxy no든 email이든 가지고있기
    @Column(name = "feed_info")
    private String feedInfo;

//    @ElementCollection
////    @Column(name = "feed_info")
////    private List<String> feedInfo;

    @CreatedDate
    @Column(name = "feed_venttime")
    private LocalDateTime feedEventtime;

    //1: 챌린지 가입, 2. 챌린지 완주, 3. 랜각공 참여 4. 팔로우

    @Builder
    public FeedEntity(int feedNo, int feedType, String feedOwner, UserEntity feedUserEntity, String feedInfo, LocalDateTime feedEventtime) {
        this.feedNo = feedNo;
        this.feedType = feedType;
        this.feedOwner = feedOwner;
        this.feedUserEntity = feedUserEntity;
        this.feedInfo = feedInfo;
        this.feedEventtime = feedEventtime;
    }
}
