package com.dsm.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 名称:过滤器 作用:统一字符集,解决乱码问题
 * 
 * 
 */
public class MyFilter implements Filter {

	private String characterEncoding = "utf-8";

	/**
	 * 初始化
	 */
	public void init(FilterConfig config) throws ServletException {
		//如果上下文参数不为空,则将上下文参数的值值给当前属性characterEncoding
		if (config.getInitParameter("characterEncoding") != null
				&& !config.getInitParameter("characterEncoding").equals("")) {
			this.characterEncoding = config
					.getInitParameter("characterEncoding");
		}

	}

	/**
	 * 过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 设置字符集
		request.setCharacterEncoding(this.characterEncoding);
		response.setCharacterEncoding(this.characterEncoding);
		// 让request和response穿过过滤器
		chain.doFilter(request, response);
	}

	/**
	 * 销毁
	 */
	public void destroy() {
	}
}
