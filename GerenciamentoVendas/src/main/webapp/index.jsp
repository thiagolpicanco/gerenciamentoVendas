
<%
	String contextPath = ((HttpServletRequest) request).getContextPath();
	response.sendRedirect(contextPath + "/paginas/index.jsf");
%>