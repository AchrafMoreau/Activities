package com.activity.Activity.security;

import com.activity.Activity.service.UserDetailsImpl;
import com.activity.Activity.service.UserDetailsServiceImpl;
import com.activity.Activity.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtTokenFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{

            String jwt = jwtParser(request);
            logger.info("Token ==> {}", jwt);
            if(jwt != null && jwtUtil.validateToken(jwt)){
                String email = jwtUtil.parserEmail(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            logger.error("Can not Set User Auth {} ", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    public String jwtParser(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if(bearer != null && bearer.startsWith("Bearer ") ){
            return bearer.substring(7);
        }
        return null;
    }
}
