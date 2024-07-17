package com.dsu.turtlelearnserver.question.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseEntity {


    @Column(nullable = false, unique = false)
    private Long number;

    @Column(nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;


}
