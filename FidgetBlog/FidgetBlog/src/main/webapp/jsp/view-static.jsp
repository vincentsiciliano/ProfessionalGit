<%--
    Document   : viewpost{id}
    Created on : Jul 19, 2017, 12:07:24 PM
    Author     : vincentsiciliano
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${post.title}</title>
        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">


        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/navbar.js"></script>

        <style>
            .commentTitle{
                font-weight: bold;
                padding-top:16px;
            }

            .username {
                font-weight: bold;
                color:darkblue;
            }

            .comments{
                margin-top:0px;
            }

            hr{
                margin-top:10px;
                margin-bottom:10px;
            }
            .seperator {
                margin-top: 15px;
            }
            textarea.form-control {
                margin-top:10px;
                height:100px;
            }
            .submit-btn{
                float:right;
                margin-top:8px;
            }
            .comment-delete{
                float:right;
                background:none!important;
                border:none;
                padding:0!important;
            }
            .category{
                border: #07a 2px solid;
                padding: 6px;
                background: #07a;
                border-radius: 12px;
                color: white;

            }
            a.category {
                text-decoration: none;
                display:inline-block;
                margin: 10px 2px 0px 2px;

            }
            .category:hover{
                background:white;
                border: #204d74 2px solid;
            }
            
            img{
                width: auto;
                max-width: 100%;
                height: auto;
            }

        </style>

    </head>
    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>

        <div class="container-fluid">
            <article>
                <div class="row">
                    <div class="col-md-2">
                    </div>

                    <div class="feed col-md-8">



                        <div class="col-md-12">
                            <h1><c:out value="${staticPageTitle}"></c:out></h1>

                 


                            <br>

                            <div name="postBody" id="postBody"><c:out value="${staticPageBody}" escapeXml="false">   </c:out></div>
                                <div class="col-md-12">

                                <br/><br/>
                            </div>

                        </div>

                    </div>







            </article>

        </div>









        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
    </body>
</html>
