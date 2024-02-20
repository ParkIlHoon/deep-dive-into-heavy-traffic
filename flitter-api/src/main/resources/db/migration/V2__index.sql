-- member 테이블 유니크키 조건
CREATE UNIQUE INDEX `uk_member_nickname` ON `member` (`nickname`);
CREATE UNIQUE INDEX `uk_member_email` ON `member` (`email`);

-- 특정 회원의 타임라인 탐색/정렬을 위한 인덱스
CREATE INDEX `idx_timeline_member` ON `timeline` (`member_id`, `posted_at`);

-- 작성자의 포스트 목록 탐색을 위한 인덱스
CREATE INDEX `idx_post_writer_id` ON `post` (`writer_id`);

-- 포스트 작성 시 타임라인 생성을 위한 팔로워 탐색을 위한 인덱스
CREATE INDEX `idx_follow_member_id` ON `follow` (`member_id`);