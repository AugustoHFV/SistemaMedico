

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body onload = 'loaded();'><%
           String m=(String) session.getAttribute("mensaje");
           if(m=="null"){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body onload = 'loaded();'></body>");
                out.println("<script>");
                out.println("function loaded() {");
                out.println("location.href ='index.jsp';");
                out.println("}");
                out.println("</script>");
                out.println("</html>");
            }%>
           </body>
    <script>
    function loaded() {
        <%String l=(String) session.getAttribute("link");%>
        <%session.setAttribute("mensaje",null);%>
        <%session.setAttribute("link",null);%>
        alert('<%=m%>');
        location.href ='<%=l%>';
    }
    </script>
</html>
