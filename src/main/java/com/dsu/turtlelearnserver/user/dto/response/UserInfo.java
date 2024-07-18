package com.dsu.turtlelearnserver.user.dto.response;

import com.dsu.turtlelearnserver.user.domain.Sex;
import com.dsu.turtlelearnserver.user.domain.User;
import lombok.Builder;

@Builder
public record UserInfo(
    String username,
    String name,
    int age,
    Sex sex,
    double answerAccuracy,
    int submissionCount
) {

    public static UserInfo of(User user, double answerAccuracy, int submissionCount) {
        return UserInfo.builder()
            .username(user.getUsername())
            .name(user.getName())
            .age(user.getAge())
            .sex(user.getSex())
            .answerAccuracy(answerAccuracy)
            .submissionCount(submissionCount)
            .build();
    }
}
