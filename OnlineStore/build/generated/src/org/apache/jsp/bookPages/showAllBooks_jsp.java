package org.apache.jsp.bookPages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class showAllBooks_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/includeUpperPart.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"content\">\n");
      out.write("    <div style=\"text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;\">Show all books</div>\n");
      out.write("    <div style=\"text-align: center; margin-top: 15px;\">\n");
      out.write("        <TABLE BORDER=1 ALIGN=CENTER>\n");
      out.write("            <TR BGCOLOR=\"#FFAD00\">\n");
      out.write("                <TH>Book Picture<TH>Book name<TH>Author<TH>ISBN<TH>Category<TH>Release date<TH>copies available<TH>Loan\n");
      out.write("            </TR>\n");
      out.write("            ");
      ArrayList<model.Book> bookList = null;
      bookList = (ArrayList<model.Book>) _jspx_page_context.getAttribute("bookList", PageContext.REQUEST_SCOPE);
      if (bookList == null){
        throw new java.lang.InstantiationException("bean bookList not found within scope");
      }
      out.write("\n");
      out.write("            ");
for(model.Book b : bookList) {
      out.write("\n");
      out.write("            <TR>\n");
      out.write("                <TD><img src=\"");
      out.print(b.getBookPic());
      out.write("\" alt=\"\" height=\"100\" width=\"100\">n</TD>\n");
      out.write("                <TD>");
      out.print(b.getBookName());
      out.write("</TD>\n");
      out.write("                <TD>");
      out.print(b.getAuthor());
      out.write("</TD>\n");
      out.write("                <TD>");
      out.print(b.getIsbn());
      out.write("</TD>\n");
      out.write("                <TD>");
      out.print(b.getCategory());
      out.write("</TD>\n");
      out.write("                <TD>");
      out.print(b.getReleaseDate());
      out.write("</TD>\n");
      out.write("                ");
if(true){
      out.write("\n");
      out.write("                    <TD>\n");
      out.write("                        <form action=\"\">\n");
      out.write("                            <input type=\"hidden\" name=\"isbnFromSearch\" value=\"\">\n");
      out.write("                            <input type=\"submit\" value=\"loan\" />\n");
      out.write("                        </form>\n");
      out.write("                    </TD>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </TR>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </TABLE>\n");
      out.write("        <form action=\"/Hw4/welcomePage.jsp\">\n");
      out.write("               <input type=\"submit\" value=\"HOME\" />\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
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
