package com.face.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.face.dao.ConnectionUtils;
import com.face.dao.MyUtils;
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter
{
	
    public JDBCFilter() 
    {
    	
    }
    public void init(FilterConfig arg0) throws ServletException {			//initialize filter
	}
    public void destroy() {
		// TODO Auto-generated method stub
		
	}
public  boolean needJDBC(HttpServletRequest request)
{
	System.out.println("needJDBC in JDBCfilter class");					//storing values
String servletPath = request.getServletPath();
System.out.println("Servlet path :"+servletPath);
String pathInfo=request.getPathInfo();
System.out.println("path info :"+pathInfo);
String urlPattern = servletPath;
System.out.println("urlPattern : :"+urlPattern);
if(pathInfo!=null)
{
	System.out.println("setting url pattern as servletpath+/*");
urlPattern=servletPath+"/*"	;
System.out.println("urlPattern :"+urlPattern);
}
																//key: servletName
																// value : servletRegistration
Map<String, ? extends ServletRegistration> servletRegistrations=request.getServletContext().getServletRegistrations();
Collection<? extends ServletRegistration> values=servletRegistrations.values();
for(ServletRegistration sr : values) {
	Collection<String>mappings = sr.getMappings();
	if(mappings.contains(urlPattern))
	{
		return true;
	}
}
return false;
}
public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain)
        throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
															    // Only open connections for the special requests.
															    // (For example, the path to the servlet, JSP, ..)
															    //
															    // Avoid open connection for commons request.
															    // (For example: image, css, javascript,... )
    System.out.println("dofilter in JDBCfiter class");
    if (this.needJDBC(req)) {
        System.out.println("Open Connection for: " + req.getServletPath());
        Connection conn = null;
        try {
        																	// Create a Connection.
            conn = ConnectionUtils.getConnection();
            														// Set auto commit to false.
            conn.setAutoCommit(false);
            												// Store Connection object in attribute of request.
            MyUtils.storeConnection(request, conn);
            															// Allow request to go forward
            												// (Go to the next filter or target)
            chain.doFilter(request, response);
            															// Invoke the commit() method to complete the transaction with the DB.
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ConnectionUtils.rollbackQuietly(conn);
            throw new ServletException();
        } finally {
        										// ConnectionUtils.closeQuietly(conn);
        }
    }
	else
	{
		chain.doFilter(request, response);						//forward to servlet
	}
}

}
	
