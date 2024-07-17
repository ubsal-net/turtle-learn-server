package com.dsu.turtlelearnserver.question.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Selection extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Question question;

    @Column(nullable = false)
    private Boolean answer;

    @Column(nullable = false)
    private String content;
}
