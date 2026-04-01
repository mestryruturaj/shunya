package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.dao.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateEmailAndMobileNumberUniqueness(String email, String mobileNumber) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new IllegalArgumentException("Mobile number already exists");
        }
    }
}
