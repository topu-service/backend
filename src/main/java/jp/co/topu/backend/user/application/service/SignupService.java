package jp.co.topu.backend.user.application.service;

import jp.co.topu.backend.user.application.port.in.SignupCommand;
import jp.co.topu.backend.user.application.port.in.SignupUseCase;
import org.springframework.stereotype.Service;

@Service
public class SignupService implements SignupUseCase {
    @Override
    public void signup(SignupCommand command) {

    }
}
