package com.fastcampus.sns.model.entity;

import com.fastcampus.sns.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "\"post\"")
@Getter
@Setter
// 이건 delete를 할 때 처리할 코드
@SQLDelete(sql = "UPDATED \"post\" SET deleted_at = NOW() where id=?")
// delete된 코드는 검색 안되게 하나 보다.
@Where(clause = "deleted_at is NULL")
public class PostEntity {
    @Id
    // 자동으로 id 값 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    // string으로 하면 짧아져서..! TEXT로
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;


    // userEntity를 가지고 와서 유저가 로그인되어 있는지 확인
    @ManyToOne
    //얘는 뭐지ㅣ..?
    // 얘를 적으면, userEntity를 가지고 올 때, user_id로 join을 해서 가지고 오게 된다.
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 실제 유저가 저장된 시각을 저장하는 필드
    // 디버깅할 때 이런 시간들이 있어야 편하다.
    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    // 특히 삭제된 시각은 중요하다는 것..
    // 실제 row를 삭제하지 않고 그냥 삭제된 것처럼 한다.
    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    // 등록될 때 자동으로 되게 이런 코드를 작성하는 것.
    @PrePersist
    void registerdAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
