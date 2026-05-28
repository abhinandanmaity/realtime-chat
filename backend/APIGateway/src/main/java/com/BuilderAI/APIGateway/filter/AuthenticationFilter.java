package com.BuilderAI.APIGateway.filter;

import com.BuilderAI.APIGateway.util.JwtUtil;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private final RouteValidator validator;
	private final JwtUtil jwtUtil;

	// 1. Use Constructor Injection instead of @Autowired fields
	public AuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil) {
		super(Config.class);
		this.validator = validator;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public GatewayFilter apply(Config config) {
		System.out.println("Abhinandan Maity");
		System.out.println("exchange.getRequest() >>>>>>>>>>>");

		return ((exchange, chain) -> {
			System.out.println("exchange.getRequest() ----------------------------------->>>>>>>>>>>>");
			System.out.println(exchange.getRequest());

			if (validator.isSecured.test(exchange.getRequest())) {

				// 1. Try to grab the header directly. If it's not there, Spring returns null.
				String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

				System.out.println(authHeader);
				// 2. Check if it's null (meaning the header is missing)
				if (authHeader == null) {
					return onError(exchange, "Missing authorization header", HttpStatus.UNAUTHORIZED);
				}

				// 3. Use getFirst() for safer retrieval
				if (authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				} else {
					return onError(exchange, "Invalid authorization header format", HttpStatus.UNAUTHORIZED);
				}

				try {
					jwtUtil.validateToken(authHeader);
				} catch (Exception e) {
					System.out.println("invalid access...!");
					// 4. Return standard 401 instead of throwing Exception
					return onError(exchange, "un-authorized access to application", HttpStatus.UNAUTHORIZED);
				}
			}
			return chain.filter(exchange);
		});

//		System.out.println("last");
	}

	// Helper method to gracefully handle reactive WebFlux errors
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		// Optional: You can also write the 'err' string to the response body here if needed
		return response.setComplete();
	}

	public static class Config {
	}
}
