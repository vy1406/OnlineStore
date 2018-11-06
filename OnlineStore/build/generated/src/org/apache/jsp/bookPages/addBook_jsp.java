package org.apache.jsp.bookPages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Category;
import java.util.ArrayList;
import DB.CategoryDB;

public final class addBook_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/includeUpperPart.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"content\">\n");
      out.write("    <span class=\"font1\">Add new book to the library</span>\n");
      out.write("    <form action=\"/Hw4/book/add\" method=\"POST\">\n");
      out.write("        <br/>\n");
      out.write("        Book name:<br/> <input type=\"text\" name=\"bookName\" required=\"required\" /><br/>\n");
      out.write("        Author:<br/> <input type=\"text\" name=\"authorName\" required=\"required\"/><br/>\n");
      out.write("        Category:<br/>\n");
      out.write("        ");
      out.print(util.TagUtil.selectCategory("null") );
      out.write("\n");
      out.write("        ISBN:<br/> <input type=\"text\" name=\"bIsbn\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required=\"required\"/><br/>\n");
      out.write("        Release date:<br/> <input type=\"number\" name=\"releaseDate\" required=\"required\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' min=\"1900\" max=\"2016\"/><br/>\n");
      out.write("        Number of copies:<br/> <input type=\"number\" name=\"numOfCopies\" required=\"required\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style=\"width: 40px;\" min=\"1\" max=\"50\"/><br/>\n");
      out.write("        Book url:<br/> <input type=\"text\" name=\"pic\" pattern=\"https?://.+\" title=\"Include http://\"><br/>\n");
      out.write("        <input type=\"submit\" class=\"button\" value=\"Add\">\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("        \n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/includeBottomPart.jsp", out, false);
      out.write('\n');
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
