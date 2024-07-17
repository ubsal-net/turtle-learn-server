package com.dsu.turtlelearnserver.question.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
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
