package edu.miu.ticket_system;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordMatch {

    @Test
    public void testPasswordMatch() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "securepass456";
        String encodedPassword = "$2a$10$fXDEiwY5A1.QTCz7H9N39.eHmTSnIDPcVcIOr/g1ZAKh46BDvtLei";

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + matches);  // Should print 'true'
    }

}
