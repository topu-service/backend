package jp.co.topu.backend.user.application.port.in;

import java.util.List;

public record SignupCommand(
        String nickname,
        String duty,
        Integer career,
        List<String> favoriteTechnologies
) {
}
