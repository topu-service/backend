package jp.co.topu.backend.user.adapter.in.web.in;

import java.util.List;

public record SignupRequest(
        String nickname,
        String duty,
        Integer career,
        List<String> favoriteTechnologies
) {

}
