-- flitter.follow definition

CREATE TABLE `follow` (
                          `id` binary(16) NOT NULL COMMENT '아이디',
                          `member_id` binary(16) NOT NULL COMMENT '회원 아이디',
                          `follower_member_id` binary(16) NOT NULL COMMENT '팔로워 회원 아이디',
                          `created_at` datetime(6) NOT NULL COMMENT '생성일시',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='팔로우';


-- flitter.`member` definition

CREATE TABLE `member` (
                          `birthday` date NOT NULL COMMENT '생일',
                          `created_at` datetime(6) NOT NULL COMMENT '생성일시',
                          `updated_at` datetime(6) NOT NULL COMMENT '수정일시',
                          `id` binary(16) NOT NULL COMMENT '아이디',
                          `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '이메일',
                          `nickname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '닉네임',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='회원';


-- flitter.post definition

CREATE TABLE `post` (
                        `created_at` datetime(6) NOT NULL COMMENT '생성일시',
                        `like_count` bigint NOT NULL COMMENT '좋아요 수',
                        `updated_at` datetime(6) NOT NULL COMMENT '수정일시',
                        `id` binary(16) NOT NULL COMMENT '아이디',
                        `writer_id` binary(16) NOT NULL COMMENT '작성자 아이디',
                        `contents` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '내용',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='포스트';


-- flitter.timeline definition

CREATE TABLE `timeline` (
                            `created_at` datetime(6) NOT NULL COMMENT '생성일시',
                            `posted_at` datetime(6) NOT NULL COMMENT '포스트 일시',
                            `id` binary(16) NOT NULL COMMENT '아이디',
                            `member_id` binary(16) NOT NULL COMMENT '회원 아이디',
                            `post_id` binary(16) NOT NULL COMMENT '포스트 아이디',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='타임라인';


-- flitter.member_nickname_history definition

CREATE TABLE `member_nickname_history` (
                                           `created_at` datetime(6) NOT NULL COMMENT '생성일시',
                                           `id` binary(16) NOT NULL COMMENT '아이디',
                                           `member_id` binary(16) NOT NULL COMMENT '회원 아이디',
                                           `nickname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '닉네임',
                                           PRIMARY KEY (`id`),
                                           KEY `fk_member_nickname_history_member` (`member_id`),
                                           CONSTRAINT `fk_member_nickname_history_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='회원 닉네임 이력';


-- flitter.post_like definition

CREATE TABLE `post_like` (
                             `created_at` datetime(6) NOT NULL COMMENT '생성일시',
                             `id` binary(16) NOT NULL COMMENT '아이디',
                             `member_id` binary(16) NOT NULL COMMENT '좋아요를 누른 회원 아이디',
                             `post_id` binary(16) NOT NULL COMMENT '포스트 아이디',
                             PRIMARY KEY (`id`),
                             KEY `fk_post_like_post` (`post_id`),
                             CONSTRAINT `fk_post_like_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='포스트 좋아요';