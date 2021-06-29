package com.camper.camport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME")
    private String name;

    public Member() {}
    public Member(Long memberId) {
        this.id = memberId;
    }

    // default FetchType.LAZY
    // 영속성 전이(자식도 함께 영속상태로) 설정
    // 고아객체(자식연결 제거시 DELETE 쿼리 실행) 설정
    @OneToMany(mappedBy = "member") // , cascade = CascadeType.ALL
    List<Camport> camports = new ArrayList<Camport>();

    public void camportsSetter(Camport camport) {
        this.camports.add(camport);
    }
}
