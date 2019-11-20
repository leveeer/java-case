package com.jxau.web.filter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * ????session
 * @author Administrator
 *
 */
public class HibernateFilter implements Filter {
	
	private static ThreadLocal hibernateHolder = new ThreadLocal();
	
	private static SessionFactory factory = null;
	
	@Override
    public void destroy() {
	}

	@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
		try {
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			Session session = (Session)hibernateHolder.get();
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
				hibernateHolder.remove();
			}
		}
	}

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
		try {
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}	
	}
	
	public static Session getSession() {
		Session session = (Session)hibernateHolder.get();
		if (session == null) {
			session = factory.openSession();
			hibernateHolder.set(session);
		}
		return session;
	}
}
