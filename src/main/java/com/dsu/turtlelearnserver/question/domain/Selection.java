package com.dsu.turtlelearnserver.question.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Selection extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Question question;


    @Column(nullable = false, length = 45, unique = false)
    private String select_answer;


    @Column(nullable = false)
    private Boolean answer;



}
