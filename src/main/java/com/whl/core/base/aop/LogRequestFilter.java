package com.whl.core.base.aop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

@Component
public class LogRequestFilter extends OncePerRequestFilter implements Ordered {

	private final static Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

	// put filter at the end of all other filters to make sure we are processing
	// after all others
	private int order = Ordered.LOWEST_PRECEDENCE - 8;

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

		filterChain.doFilter(wrappedRequest, response);

		String method = request.getMethod();
		String path = request.getRequestURI();
		String query = request.getQueryString();
		String body = getBody(wrappedRequest);
		int status = response.getStatus();

		if (status != HttpStatus.OK.value()) {
			LOGGER.warn("method: {}, path: {}, query: {}, body: {}, status: {}", method, path, query, body, status);
		} else {
			LOGGER.debug("method: {}, path: {}, query: {}, body: {}, status: {}", method, path, query, body, status);
		}
	}

	private String getBody(ContentCachingRequestWrapper request) {
		String payload = null;
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				try {
					payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
			}
		}

		return payload;
	}

}
