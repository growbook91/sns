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
// 여기서 \"를 붙이는 이유는 postgreSQL이 이미 user table을 가지고 있기 떄문이래.
@Table(name = "\"user\"")
@Getter
@Setter
// 이건 delete를 할 때 처리할 코드
@SQLDelete(sql = "UPDATED \"user\" SET deleted_at = NOW() where id=?")
// delete된 코드는 검색 안되게 하나 보다.
@Where(clause = "deleted_at is NULL")
public class UserEntity {
    @Id
    // 자동으로 id 값 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    // user의 권한을 저장하는 것.
    @Column(name = "role")
    // 이건 enum 관련 코드
    @Enumerated(EnumType.STRING)
    // 여기에 뭘 넣어주면 default 값인가보다.
    private UserRole role = UserRole.USER;

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

    // user entity를 반환하는 method
    // 우리는 User라는 class도 있고 userEntity라는 실제 db에 mapping되는 클래스도 있는데 이것은 구붇되는 게 좋다.
    // user는 dto. user정보를 서비스 단에서 사용할 때 dto를 사용.
    // 흠...뭔말인지 제대로 이해 안됨. 6강 12:44 나중에 다시 보기
    // 변환을 위한 메소드를 작성할 것이다.
    // 아 서비스에서 이 함수를 사용해서 userentity를 만드는 군. 즉, constructor 같은 것.
    public static UserEntity of(String userName, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        return userEntity;
    }

}
