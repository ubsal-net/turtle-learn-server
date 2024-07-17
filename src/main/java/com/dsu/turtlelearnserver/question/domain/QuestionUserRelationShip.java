package com.dsu.turtlelearnserver.question.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import com.dsu.turtlelearnserver.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUserRelationShip extends BaseEntity {


    @ManyToOne
    @JoinColumn(nullable = false, name = "question")
    private Question question;


    @ManyToOne
    @JoinColumn(nullable = false, name = "user")
    private User user;


}
