package dev.hoon.deepdive.heavytraffic.flitter.core.exception

enum class FlitterErrorCode(
    val code: Int,
    val message: String,
) {
    MEMBER_NOT_FOUND(10000, "존재하지 않는 회원입니다."),
    CANNOT_JOIN(10001, "회원가입에 실패했습니다."),
    CANNOT_LEAVE(10002, "회원탈퇴에 실패했습니다."),
    CANNOT_CHANGE_NICKNAME(10003, "닉네임을 변경할 수 없습니다."),

    CANNOT_FOLLOW(20000, "팔로우하지 못했습니다."),
    CANNOT_UNFOLLOW(25000, "언팔로우하지 못했습니다."),

    POST_NOT_FOUND(30000, "존재하지 않는 포스트입니다."),
    CANNOT_WRITE_POST(30001, "포스트를 작성하지 못했습니다."),
    CANNOT_LIKE_POST(30002, "포스트에 좋아요를 못했습니다."),
    CANNOT_UNLIKE_POST(30003, "포스트에 좋아요를 취소하지 못했습니다."),
    CANNOT_DELETE_POST(30004, "포스트를 삭제하지 못했습니다."),

    TIMELINE_NOT_FOUND(40000, "타입라인을 찾지 못했습니다."),
    CANNOT_CREATE_TIMELINE(40001, "타입라인을 생성하지 못했습니다."),
    CANNOT_DELETE_TIMELINE(40002, "타입라인을 삭제하지 못했습니다.");

    companion object {
        fun valueOf(code: Int): FlitterErrorCode = values().first { it.code == code }
    }
}