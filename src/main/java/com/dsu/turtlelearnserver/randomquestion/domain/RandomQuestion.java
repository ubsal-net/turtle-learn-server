package com.dsu.turtlelearnserver.randomquestion.domain;

import com.dsu.turtlelearnserver.common.global.BaseEntity;
import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RandomQuestion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User user;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "randomQuestion")
    @ToString.Exclude
    private List<RandomQuestionSelection> randomQuestionSelections = new ArrayList<>();

    public String getCategoryName() {
        return category.getName();
    }
}
