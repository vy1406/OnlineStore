package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class EditSettings_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <div style=\"text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;\">Settings Edit</div>\n");
      out.write("    <div style=\"text-align: center; margin-top: 15px;\">\n");
      out.write("        ");
      model.Library librarySettings = null;
      librarySettings = (model.Library) _jspx_page_context.getAttribute("librarySettings", PageContext.REQUEST_SCOPE);
      if (librarySettings == null){
        throw new java.lang.InstantiationException("bean librarySettings not found within scope");
      }
      out.write("\n");
      out.write("        \n");
      out.write("        <form action=\"/Hw4/admin/update\">\n");
      out.write("            <TABLE BORDER=1 ALIGN=CENTER>\n");
      out.write("                <TR BGCOLOR=\"#FFAD00\">\n");
      out.write("                    <TH>Parameter Name<TH>Parameter Value(s)\n");
      out.write("                </TR>\n");
      out.write("                <TR>\n");
      out.write("                    <TD>Max num of loans</TD>                \n");
      out.write("                    <TD><input type=\"text\" name=\"numOfLoans\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((model.Library)_jspx_page_context.findAttribute("librarySettings")).getNumOfLoans())));
      out.write("\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>\n");
      out.write("                </TR>\n");
      out.write("                <TR>\n");
      out.write("                    <TD>Damage fine</TD>                \n");
      out.write("                    <TD><input type=\"text\" name=\"damageFine\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((model.Library)_jspx_page_context.findAttribute("librarySettings")).getDamageFine())));
      out.write("\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>\n");
      out.write("                </TR>\n");
      out.write("                <TR>\n");
      out.write("                    <TD>Late fine</TD>                \n");
      out.write("                    <TD><input type=\"text\" name=\"lateFine\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((model.Library)_jspx_page_context.findAttribute("librarySettings")).getLateFine())));
      out.write("\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>\n");
      out.write("                </TR>\n");
      out.write("                <TR>\n");
      out.write("                    <TD>Max fine</TD>                \n");
      out.write("                    <TD><input type=\"text\" name=\"maxFine\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((model.Library)_jspx_page_context.findAttribute("librarySettings")).getMaxFine())));
      out.write("\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>\n");
      out.write("                </TR>\n");
      out.write("            </TABLE>\n");
      out.write("            <input type=\"submit\" name=\"submitValue\" value=\"update\" style=\"float: left; margin-left: 310px;\" />\n");
      out.write("        </form>      \n");
      out.write("        <a href=\"/Hw4/welcomePage.jsp\"><input type=\"submit\" value=\"cancel\" style=\"float: left\"/></a>\n");
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
