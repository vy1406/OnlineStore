
<%@page import="util.*"%>
<%@page import="model.*"%>
<%
    String currentUser = UserUtil.resolveUser((User)request.getSession().getAttribute("user"));  
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="/Hw4/style.css" rel="stylesheet" type="text/css"/>
        <title>Netanya Library</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="stas and alex">
        <meta name="description" content="Netanya college library">
        <meta name="keywords" content="Netanya,library,Books,Study">
    </head>
    <body style="background-color: #E2DCC4; margin: 0px;">
        <div id="firstD">
            <a href="http://www.netanya.ac.il/Pages/default.aspx"><img src="/Hw4/pics/capture4.png" alt=\"\" height="30"/></a>
            <a href="http://www.netanya.ac.il/Pages/default.aspx"><img src="/Hw4/pics/font3.png" alt="" height="30"/></a>
        </div>
        <div id="mainDiv" style="width: 1000px; margin-left: 400px;">
            <div id="header"> 
                <%if(request.getSession().getAttribute("sessionLoan")!=null){%>
                    <div style="float: left; margin-top: 120px;">                 
                         <a href="/Hw4/loanPages/showCart.jsp">Loan in progress</a>
                    </div>
                    <div style="float: left; margin-left: 222px;">
                      <a href="/Hw4/welcomePage.jsp"><img src="/Hw4/pics/lib5.png" alt="" /></a>
                    </div>
                <%}else{%>
                    <div style="float: left; margin-left: 330px;">
                      <a href="/Hw4/welcomePage.jsp"><img src="/Hw4/pics/lib5.png" alt="" /></a>
                    </div>
                <%}%>
                <div style="float: right">
                    <div style="margin-top: 5px; float: right;">
                        <% if(currentUser.equals("guest")){%>
                            <a href="/Hw4/mixPages/loginPage.jsp">Login</a>
                        <%}else{%>
                        Hello <%= currentUser%>/<a href="/Hw4/user/logout">Logout</a>
                        <%}%> 
                    </div>
                    <br/>
                    <div>
                        <form action="/Hw4/book/search" method="POST" style="margin-top: 80px;">
                            <input type="search" name="bookName" />
                            <input type="submit" value="search book" />
                        </form>
                        <span style="float: right;"><a href="/Hw4/bookPages/searchBook.jsp">Advanced</a></span>
                    </div>
                </div>
            </div>
            <div id="menu_content">
                <div id="menu">
                    <%  if(currentUser.equals("guest")){%>
                            <jsp:include page="/includes/menus/guestMenu.jsp" />
                    <%  }else if(currentUser.equals("admin")){%>
                            <jsp:include page="/includes/menus/adminMenu.jsp" />
                    <%  }else if(currentUser.equals("librarian")){%>
                            <jsp:include page="/includes/menus/librarianMenu.jsp" />
                    <%  }else{%>
                            <jsp:include page="/includes/menus/studentMenu.jsp" />
                    <%}%>
                </div>
                <div id="contentDiv" class="contentDiv">