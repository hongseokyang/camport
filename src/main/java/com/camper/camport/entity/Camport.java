package com.camper.camport.entity;

import com.camper.camport.entity.enumerated.ApprovalStatusToCamport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Camport extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAMPORT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // default FetchType.EAGER
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CAMPLOCATION_ID")
    private CampLocation campLocation;

    @Lob
    private String caption;

    @Enumerated(EnumType.STRING)
    private ApprovalStatusToCamport status;

    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getCamports().remove(this);
        }
        this.member = member;
        this.member.getCamports().add(this);
    }

    @Override
    public String toString() {
        return "Camport{" +
                "camportId=" + id +
                ", member=" + member.getId() +
                ", campLocation=" + campLocation.getCampLocationId() +
                '}';
    }
}
