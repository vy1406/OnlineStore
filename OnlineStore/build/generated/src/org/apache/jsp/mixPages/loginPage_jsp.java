package org.apache.jsp.mixPages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loginPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/includeUpperPart.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!-- begin content column -->\n");
      out.write("\n");
      out.write("<div id=\"content\">\n");
      out.write("    <span class=\"font1\">Login:</span><br/>\n");
      out.write("    Please enter a username and password to continue.\n");
      out.write("    ");
  String message = (String)request.getAttribute("messageContent");
        if(message!=null){
      out.write("\n");
      out.write("            <p style=\"color: red; margin: 0px;\">");
      out.print(message);
      out.write("</p>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    <form action=\"/Hw4/user/login\" method=\"POST\">\n");
      out.write("        <br/>\n");
      out.write("        Username<br/> <input type=\"text\" name=\"username\" required=\"required\"/><br/>\n");
      out.write("        Password<br/> <input type=\"password\" name=\"password\" required=\"required\"/><br/>\n");
      out.write("        <input type=\"submit\" class=\"button\" value=\"submit\">\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!-- end content column -->\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/includeBottomPart.jsp", out, false);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
