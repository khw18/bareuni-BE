package com.umc.BareuniBE.dto;

import com.umc.BareuniBE.entities.Review;
import com.umc.BareuniBE.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewRes {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ReviewCreateRes extends Review {
        private Review review;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class ReviewListRes {
        private Long reviewIdx;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private String content;
        private int totalScore;
        private boolean receipt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class ReviewStatisticsRes {
        private Long reviewIdx;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private String content;
        private int totalScore;
        private boolean receipt;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class ReviewDetailRes {
        private Long reviewIdx;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private String content;
        private int totalScore;
        private boolean receipt;
        private Long payment;
    }

    public static class ReviewListRes {


        private Object reviewIdx;
        private Object createdAt;
        private Object updatedAt;
        private User user;
        private Object content;
        private Object equipmentScore;
        private Object receipt;
        private Object serviceScore;
        private Object payment;
        private Object totalScore;
        private Object treatmentScore;
        private Object hospital;

    }
}
