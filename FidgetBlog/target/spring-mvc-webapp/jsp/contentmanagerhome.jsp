<%--
    Document   : contentmanagerhome
    Created on : Jul 19, 2017, 12:07:24 PM
    Author     : vincentsiciliano
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jinja Home</title>
        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

    </head>
    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>





        <div class="container-fluid">



            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">My Posts</h3>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <a class="btn btn-sm btn-primary btn-create" href="${pageContext.request.contextPath}/newpostform">Create New Post</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                            <tr>
                                <th><em class="fa fa-cog"></em></th>
                                <th width="5%">ID</th>
                                <th width="15%">Author</th>
                                <th width="40%">Title</th>
                                <th width="15%">Start Date</th>
                                <th width="15%">End Date</th>
                                <th width="10%">Status</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentPost" items="${postList}">
                                <tr mycolor="${currentPost.colorStatus}" id="post-row-${currentPost.postId}">
                                    <td align="center">
                                        <a class="btn btn-default" href="${pageContext.request.contextPath}/contentmanagerpostform/${currentPost.postId}"><em class="fa fa-pencil"></em></a>
                                        <form method="POST" action="${pageContext.request.contextPath}/contentmanager-deletepost/${currentPost.postId}">
                                            <button type="submit" class="btn btn-danger">
                                                <em class="fa fa-trash"></em>
                                            </button>
                                        </form>
                                    </td>

                                    <td>${currentPost.postId}</td>
                                    <td>${currentPost.user.userName}</td>
                                    <td>${currentPost.title}</td>
                                    <td><tags:localDate date="${currentPost.startDate}"/></td>
                                    <td><tags:localDate date="${currentPost.endDate}"/></td>
                                    <td>${currentPost.approvalStatus}</td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col col-xs-4">
                        </div>
                        <div class="col col-xs-8">
                            <c:choose>
                                <c:when test="${postIndex gt 1 && fn: length(postList) le 20}">
                                    <ul class="pagination pagination-sm pull-right">
                                        <li><a href="${pageContext.request.contextPath}/contentmanager/update/pages/${postIndex - 1}">« back</a></li>
                                    </ul>
                                </c:when>
                                <c:when test="${postIndex ge 1}">
                                    <ul class="pagination pagination-sm pull-right">
                                        <li><a href="${pageContext.request.contextPath}/contentmanager/update/pages/${postIndex - 1}">« back</a></li>
                                        <li><a href="${pageContext.request.contextPath}/contentmanager/update/pages/${postIndex + 1}">next »</a></li>
                                    </ul>
                                </c:when>
                                <c:when test="${fn: length(postList) == 10}">

                                    <ul class="pagination pagination-sm pull-right">
                                        <li><a href="${pageContext.request.contextPath}/contentmanager/update/pages/1">next »</a></li>
                                    </ul>

                                </c:when>


                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>


        </div>



        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/cm-admin-home.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/js/adminhome.js"></script>
        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>
    </body>
</html>
