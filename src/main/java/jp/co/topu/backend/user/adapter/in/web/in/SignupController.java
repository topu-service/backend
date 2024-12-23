package jp.co.topu.backend.user.adapter.in.web.in;

import jp.co.topu.backend.user.application.port.in.SignupUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignupController {

    private final SignupUseCase signupUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signup(@Validated @RequestBody SignupRequest signupRequest) {
        log.info("signup request: {}", signupRequest);


    }
}
