<jsp:include page="/includes/includeUpperPart.jsp" />

<!-- begin content column -->

<div id="content">
    <span class="font1">Login:</span><br/>
    Please enter a username and password to continue.
    <%  String message = (String)request.getAttribute("messageContent");
        if(message!=null){%>
            <p style="color: red; margin: 0px;"><%=message%></p>
        <%}%>
    <form action="/Hw4/user/login" method="POST">
        <br/>
        Username<br/> <input type="text" name="username" required="required"/><br/>
        Password<br/> <input type="password" name="password" required="required"/><br/>
        <input type="submit" class="button" value="submit">
    </form>
</div>

<!-- end content column -->

<jsp:include page="/includes/includeBottomPart.jsp" />