package org.springside.modules.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class CasForInvokeContextFilter implements Filter {
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) {
	    HttpServletRequest httpRequest = (HttpServletRequest)request;
	    HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = ((HttpServletRequest) request).getSession();
		if(session!=null){
			String cas_account = session.getAttribute("edu.yale.its.tp.cas.client.filter.user") == null ? "" : (String)httpRequest.getSession().getAttribute("edu.yale.its.tp.cas.client.filter.user");
			if (session.getAttribute("username") == null) {
				
				try {
					System.out.println("dsfaf"+cas_account);
					session.setAttribute("username", cas_account);
					httpResponse.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/JSWeb/QueryWage");
				    
				    return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		    session.setAttribute("username", cas_account);
		    System.out.println("dsfaf"+cas_account);
			try {
				chain.doFilter(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}
}
