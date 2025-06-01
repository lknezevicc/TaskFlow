package hr.lknezevic.taskflow.taskflowserver.config;

import hr.lknezevic.taskflow.taskflowserver.util.PasswordUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtil.hashPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordUtil.verifyPassword(rawPassword.toString(), encodedPassword);
    }
}
