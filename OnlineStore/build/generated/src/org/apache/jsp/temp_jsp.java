package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class temp_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <title>Netanya Library</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <meta name=\"author\" content=\"stas and alex\">\n");
      out.write("        <meta name=\"description\" content=\"Netanya college library\">\n");
      out.write("        <meta name=\"keywords\" content=\"Netanya,library,Books,Study\">\n");
      out.write("    </head>\n");
      out.write("    <body style=\"background-color: #E2DCC4; margin: 0px;\">\n");
      out.write("        <div id=\"firstD\">\n");
      out.write("            <a href=\"http://www.netanya.ac.il/Pages/default.aspx\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/pics/capture4.png\" alt=\\\"\\\" height=\"30\"/></a>\n");
      out.write("            <a href=\"http://www.netanya.ac.il/Pages/default.aspx\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/pics/font3.png\" alt=\"\" height=\"30\"/></a>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"mainDiv\" style=\"width: 1000px; margin-left: 400px;\">\n");
      out.write("            <div id=\"header\"> \n");
      out.write("                <div style=\"float: left; margin-top: 120px;\">                 \n");
      out.write("                     <a href=\"AddLoanCheckServlet\">Loan in progress</a>\n");
      out.write("                </div>\n");
      out.write("                <div style=\"float: left; margin-left: 222px;\">\n");
      out.write("                  <a href=\"WelcomePageServlet\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/pics/lib5.png\" alt=\"\" /></a>\n");
      out.write("                </div>\n");
      out.write("                <div style=\"float: right\">\n");
      out.write("                    <div style=\"margin-top: 5px; margin-left: 150px;\">\n");
      out.write("                        <a href=\"index.html\">Login</a> / <a href=\"index.html\">Sign Up</a>\n");
      out.write("                    </div>\n");
      out.write("                    <br/>\n");
      out.write("                    <div>\n");
      out.write("                        <form action=\"SearchBookServlet\" method=\"POST\" style=\"margin-top: 60px;\">\n");
      out.write("                            <input type=\"search\" name=\"bookName\" />\n");
      out.write("                            <input type=\"submit\" value=\"search book\" />\n");
      out.write("                        </form>\n");
      out.write("                        <span style=\"float: right;\"><a href=\"SearchBookPageServlet\">Advanced</a></span>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"menu_content\">\n");
      out.write("                <div id=\"menu\">\n");
      out.write("                    <p style=\"text-align: center; font-weight: bold; text-decoration: underline; margin-top: 10px;\">Book Menu</p>\n");
      out.write("                    <ul style=\"\">\n");
      out.write("                        <li><a href=\"AddBookPageServlet\">Add Book</a></li>\n");
      out.write("                        <li><a href=\"removeBookPageServlet\">Remove Book</a></li>\n");
      out.write("                        <li><a href=\"AddCategoryPageServlet\">Add Category</a></li>\n");
      out.write("                        <li><a href=\"SearchBookPageServlet\">Search Book</a></li>\n");
      out.write("                        <li><a href=\"ShowAllBookServlet\">Show all books</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                    <hr>\n");
      out.write("                    <p style=\"text-align: center; font-weight: bold; text-decoration: underline; margin-top: 10px;\">Student Menu</p>                \n");
      out.write("                    <ul style=\"\">\n");
      out.write("                        <li><a href=\"AddStudentPageServlet\">Add student</a></li>\n");
      out.write("                        <li><a href=\"RemoveStudentPageServlet\">Remove student</a></li>\n");
      out.write("                        <li><a href=\"ShowAllStudentsServlet\">Show all students</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                    <hr>\n");
      out.write("                    <p style=\"text-align: center; font-weight: bold; text-decoration: underline; margin-top: 10px;\">Loan Menu</p>                \n");
      out.write("                    <ul style=\"\">\n");
      out.write("                        <li><a href=\"AddLoanPageServlet\">Add Loan</a></li>\n");
      out.write("                       <li><a href=\"ReturnLoanPageServlet\">Return Loan</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"contentDiv\" class=\"contentDiv\">\n");
      out.write("                    <div id=\"content\">\n");
      out.write("                        <div style=\"text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;\">Welcome to Netanya online library</div>\n");
      out.write("                        <div style=\"text-align: center; margin-top: 15px;\">\n");
      out.write("                            <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/pics/netanya.jpg\" alt=\"\"/>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"footer\">\n");
      out.write("                <div style=\"margin-top: 30px;\">&COPY;Stas & Alex</div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
