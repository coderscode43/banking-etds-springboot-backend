package domain.in.rjsa.controller;

import domain.in.rjsa.dto.AuthStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apiAuth")
public class AuthStatusController {

    // private static final Logger logger = LoggerFactory.getLogger(AuthStatusController.class);

    @GetMapping("/getStatus")
    public ResponseEntity<AuthStatusResponse> authStatus(Authentication authentication) {
        AuthStatusResponse response;

        if (authentication != null && authentication.isAuthenticated()) {
            // Log the authenticated user detail
            // logger.info("User authenticated with username: {}", authentication.getName());
            response = new AuthStatusResponse("active", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Log the unauthenticated access attempt
            // logger.error("User is not authenticated");
            response = new AuthStatusResponse("inactive", false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

}
