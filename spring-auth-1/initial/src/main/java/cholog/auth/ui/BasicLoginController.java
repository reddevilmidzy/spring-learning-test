package cholog.auth.ui;

import cholog.auth.application.AuthService;
import cholog.auth.application.AuthorizationException;
import cholog.auth.dto.AuthInfo;
import cholog.auth.dto.MemberResponse;
import cholog.auth.infrastructure.AuthorizationExtractor;
import cholog.auth.infrastructure.BasicAuthorizationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicLoginController {
    private final AuthService authService;
    private final AuthorizationExtractor<AuthInfo> authorizationExtractor;

    public BasicLoginController(final AuthService authService) {
        this.authService = authService;
        this.authorizationExtractor = new BasicAuthorizationExtractor();
    }

    /**
     * ex) request sample
     * <p>
     * GET /members/me/basic HTTP/1.1
     * authorization: Basic ZW1haWxAZW1haWwuY29tOjEyMzQ=
     * accept: application/json
     */
    @GetMapping("/members/me/basic")
    public ResponseEntity<MemberResponse> findMyInfo(final HttpServletRequest request) {
        final String email = authorizationExtractor.extract(request).getEmail();
        final String password = authorizationExtractor.extract(request).getPassword();

        if (authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        final MemberResponse member = authService.findMember(email);
        return ResponseEntity.ok().body(member);
    }
}
