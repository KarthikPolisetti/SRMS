package com.srms.student_result_management.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService; // NOTE: We will build this next!

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Look for the HTTP Header React sent us
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // 2. Check if the header exists and starts with the magic word "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Cut off "Bearer " (7 characters) to get just the token
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                System.out.println("Invalid or Expired Wristband!");
            }
        }

        // 3. If we found a username in the token, let's verify them
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Go to the database and get this user's details (Roles, Password, etc.)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 4. Ask the Wristband Machine if the math checks out and it isn't expired
            if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {

                // 5. The Bouncer tells Spring Security: "This person is legit, open the door!"
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. IMPORTANT: Move to the next step
        filterChain.doFilter(request, response);
    }
}