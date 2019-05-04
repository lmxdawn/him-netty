package com.lmxdawn.him.api.filter;

import com.lmxdawn.him.api.config.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域拦截设置
 */
@WebFilter(urlPatterns = "/*", filterName = "corsFilter")
public class CorsFilter implements Filter {

	@Autowired
	private CorsConfig corsConfig;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", corsConfig.getAllowedOrigins());
		response.setHeader("Access-Control-Allow-Methods", corsConfig.getAllowedMethods());
		 
		response.setHeader("Access-Control-Allow-Headers", corsConfig.getAllowedHeaders());
		if ("true".equals(corsConfig.getAllowedCredentials())) {
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
