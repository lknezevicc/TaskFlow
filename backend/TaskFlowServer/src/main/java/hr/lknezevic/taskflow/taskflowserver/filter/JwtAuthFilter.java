package hr.lknezevic.taskflow.taskflowserver.filter;

import hr.lknezevic.taskflow.taskflowserver.model.User;
import hr.lknezevic.taskflow.taskflowserver.repository.UserRepository;
import hr.lknezevic.taskflow.taskflowserver.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if ("/api/auth/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1️⃣ Dohvati Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Dohvati token iz headera
        String token = authHeader.substring(7);

        // 3️⃣ Dohvati korisničko ime iz tokena
        String username = jwtService.extractUsername(token);

        // 4️⃣ Provjeri da korisnik nije već autentificiran
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 5️⃣ Dohvati korisnika iz baze
            User userDetails = userRepository.findByUsername(username)
                    .orElse(null);

            if (userDetails != null && jwtService.validateToken(token, userDetails)) {
                // 6️⃣ Kreiraj autentifikaciju i postavi u Spring Security kontekst
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        System.out.println("Current user authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());

        // 7️⃣ Nastavi s ostalim filterima
        filterChain.doFilter(request, response);
    }
}
