package cholog.auth.infrastructure;

import cholog.auth.dto.AuthInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;

public class BasicAuthorizationExtractor implements AuthorizationExtractor<AuthInfo> {
    private static final String BASIC_TYPE = "Basic";
    private static final String DELIMITER = ":";

    @Override
    public AuthInfo extract(final HttpServletRequest request) {
        final String header = request.getHeader(AUTHORIZATION);

        if (header == null) {
            return null;
        }

        if ((header.toLowerCase().startsWith(BASIC_TYPE.toLowerCase()))) {
            final String authHeaderValue = header.substring(BASIC_TYPE.length()).trim();
            final byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
            final String decodedString = new String(decodedBytes);

            final String[] credentials = decodedString.split(DELIMITER);
            final String email = credentials[0];
            final String password = credentials[1];

            return new AuthInfo(email, password);
        }

        return null;
    }
}
