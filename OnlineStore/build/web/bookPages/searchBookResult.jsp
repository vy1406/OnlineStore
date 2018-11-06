<jsp:include page="/includes/includeUpperPart.jsp" />
<% model.User currentUser = (model.User)request.getSession().getAttribute("user");%>
<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Book search result</div>
    <div style="text-align: center; margin-top: 15px;">
        <% if(currentUser==null){%>
            <jsp:include page="/includes/tables/peasantBookSearch.jsp" />
        <%}else if(currentUser.getPrivilege().equals("admin") || currentUser.getPrivilege().equals("librarian")){%>
            <jsp:include page="/includes/tables/adminBookSearch.jsp" />
        <%}else{%>
            <jsp:include page="/includes/tables/studentBookSearch.jsp" />
        <%}%>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />