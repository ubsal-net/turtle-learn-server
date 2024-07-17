package com.dsu.turtlelearnserver.question.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import com.dsu.turtlelearnserver.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnswerSubmission extends BaseEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "question")
    private Question question;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Selection selection;


}
