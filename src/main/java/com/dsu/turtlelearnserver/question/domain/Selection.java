package com.dsu.turtlelearnserver.question.domain;

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
public class Selection {

    @Id
    @ManyToOne
    @JoinColumn
    private Question question;

    @Column(nullable = false)
    private String oneSelect;

    @Column(nullable = false)
    private String twoSelect;

    @Column(nullable = false)
    private String threeSelect;

    @Column(nullable = false)
    private String fourSelect;


}
