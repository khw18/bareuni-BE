package com.umc.BareuniBE.entities;

import com.umc.BareuniBE.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Getter
//@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="communityIdx")
    private Long communityIdx;
}
