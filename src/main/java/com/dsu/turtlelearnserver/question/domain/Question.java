package com.dsu.turtlelearnserver.question.domain;

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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private Long number;

    @Column(nullable = false)
    private String question;

    @ManyToOne
    @JoinColumn(nullable = false , name = "answer")
    private Selection answer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;


}
