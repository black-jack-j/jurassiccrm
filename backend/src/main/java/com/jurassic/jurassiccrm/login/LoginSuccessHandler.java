package com.jurassic.jurassiccrm.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        List<String> authorityNames = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Map<String, String> redirectMap = getRoleToURLMappings();
        Optional<String> redirectTarget = getRedirectTarget(authorityNames, redirectMap);

        if (redirectTarget.isPresent()) {
            redirectStrategy.sendRedirect(request, response, redirectTarget.get());
        } else {
            logger.error("role not found. authorities: {}", String.join(", ", authorityNames));
            throw new IllegalStateException("CANT FIND VALID DEFAULT PAGE");
        }
    }


    private Optional<String> getRedirectTarget(List<String> authorityNames, Map<String, String> redirectMap) {
        return authorityNames.stream().filter(redirectMap::containsKey).findFirst().map(redirectMap::get);
    }

    protected Map<String, String> getRoleToURLMappings() {
        Map<String, String> redirectMap = new HashMap<>();
        redirectMap.put("ROLE_ADMIN", "/admin");
        redirectMap.put("ROLE_SECURITY_READER", "/security");
        redirectMap.put("ROLE_DOCUMENT_READER", "/document");
        redirectMap.put("ROLE_TASK_READER", "/task");
        return redirectMap;
    }
}
