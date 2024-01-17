package com.example.QuizPlatformApplication.security.config;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.security.jwttoken.JwtService;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter class in order to prevent an unauthenticated user to make calls to certain
 *  endpoints from the app
 *  Checks if the Bearer token attached to the HTTP request is valid and not expired, or if it's present
 */
@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    /**
     * Parameter used to manipulate the tokens and validate/generate them
     */
    @Autowired
    private JwtService jwtService;

    /**
     * User service, from which we can obtain details about the validity of the token and generate new ones
     */
    @Autowired
    private UserServiceInterface userService;

    /**
     * The filter method
     * @param request the HTTP request which comes to the app
     * @param response the response of the app to the request
     * @param filterChain the chains of filter which still need to be executed
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        try{
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                userName = jwtService.extractUserName(token);
            }

            User userDetails = null;
            try {
                userName = jwtService.extractUserName(token);
                userDetails = userService.getUserByUsername(userName);
                if(!jwtService.isTokenValid(token,userDetails)){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                }
            }
            catch(ExpiredJwtException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }



        filterChain.doFilter(request, response);

    }

    /**
     * Method which marks the endpoins which don't need to be checked such as /login
     * @param request current HTTP request
     * @return true if the endpoint is in the path, else false
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.contains("/login") || path.contains("/quiz") || path.contains("/userStats");
    }
}
