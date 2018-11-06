package org.apache.jsp.studentPages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addStudent_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<div id=\"content\">\n");
      out.write("    <span class=\"font1\">Add new student:</span>\n");
      out.write("    <form action=\"/Hw4/student/add\" method=\"POST\">\n");
      out.write("        <br/>\n");
      out.write("        Name:<br/> <input type=\"text\" name=\"studentName\" required=\"required\"/><br/>\n");
      out.write("        Surname:<br/> <input type=\"text\" name=\"studentSurname\" required=\"required\"/><br/>\n");
      out.write("        ID:<br/> <input type=\"text\" name=\"studentID\" required=\"required\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>\n");
      out.write("        Gender:<br/>\n");
      out.write("        <input type=\"radio\" name=\"gender\" value=\"male\" checked=\"checked\"/>Male<br/>\n");
      out.write("        <input type=\"radio\" name=\"gender\" value=\"female\"/>Female<br/>\n");
      out.write("        <br/>\n");
      out.write("        Email:<br/> <input type=\"email\" name=\"sEmail\" required=\"required\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}$\" title=\"email@example.com\"/><br/>\n");
      out.write("        Phone number:<br/> <input type=\"tel\" name=\"phoneNumber\" required=\"required\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>\n");
      out.write("        Create user: <input type=\"checkbox\" name=\"user\" value=\"yes\"><br/>\n");
      out.write("        <input type=\"submit\" class=\"button\" value=\"Add\">\n");
      out.write("    </form>\n");
      out.write("</div>\n");
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
