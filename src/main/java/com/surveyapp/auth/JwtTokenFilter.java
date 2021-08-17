package com.surveyapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    @Autowired
    public JwtTokenFilter(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = httpServletRequest.getHeader("Authorization");

        String token;
        String userMail=null;
        if(authHeader != null && authHeader.contains("Bearer")){
            token = authHeader.substring(7);

            try{
                userMail = tokenManager.getUserMailToken(token);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if (userMail != null && token != null && SecurityContextHolder.getContext().getAuthentication()==null) {
                if (tokenManager.tokenValidate(token)){
                    UsernamePasswordAuthenticationToken upassToken =
                            new UsernamePasswordAuthenticationToken(userMail,null,new ArrayList<>());
                    upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(upassToken);
                }
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
