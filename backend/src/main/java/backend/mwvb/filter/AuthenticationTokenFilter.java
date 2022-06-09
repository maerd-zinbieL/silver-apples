package backend.mwvb.filter;

import backend.mwvb.entity.AuthUser;
import backend.mwvb.exception.UserAuthenticationException;
import backend.mwvb.service.AuthUserCacheService;
import backend.mwvb.util.CommonJWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private final AuthUserCacheService authUserCacheService;
    @Value("${com.maerd_zinbiel.silver-apples.jwt.password}")
    private String jwtKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Claims claims = CommonJWTUtils.parse(token, jwtKey);
            String userId = claims.getSubject();
            AuthUser cachedAuthUser = authUserCacheService.getCachedAuthUser(userId);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(cachedAuthUser, null, cachedAuthUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JwtException ex) {
            throw new UserAuthenticationException("Bad Token");
        }
        filterChain.doFilter(request, response);
    }
}
