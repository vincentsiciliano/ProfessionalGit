<%--
    Document   : adminhome
    Created on : Jul 19, 2017, 12:07:24 PM
    Author     : vincentsiciliano
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <style>
            #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }

            #sortable li span { position: absolute; margin-left: -1.3em; }
        </style>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#sortable").sortable();
                $("#sortable").disableSelection();
            });
        </script>


    </head>
    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>

        <div class="container">




            <div class="row" id="postTable">


                <div class="panel panel-default panel-table">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col col-xs-6">
                                <h3 class="panel-title">Posts</h3>
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
                                            <a class="btn btn-default" href="${pageContext.request.contextPath}/admineditpostform/${currentPost.postId}"><em class="fa fa-pencil"></em></a>
                                            <form method="POST" action="${pageContext.request.contextPath}/deletepost/${currentPost.postId}">
                                                <button type="submit" class="btn btn-danger">
                                                    <em class="fa fa-trash"></em>
                                                </button>
                                            </form>
                                            <form method="POST" action="${pageContext.request.contextPath}/restorepost/${currentPost.postId}">
                                                <button style="display: none" type="submit" class="btn btn-success">
                                                    <em class="fa fa-window-restore"></em>
                                                </button>
                                            </form>
                                        </td>

                                        <td>${currentPost.postId}</td>
                                        <td>${currentPost.user.userName}</td>
                                        <td><a href="${pageContext.request.contextPath}/viewpost/${currentPost.postId}">${currentPost.title}</a></td>
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
                                    <c:when test="${postIndex ge 1 && fn: length(postList) le 10}">
                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex - 1}/${staticIndex}#postTable">« back</a></li>
                                        </ul>
                                    </c:when>
                                    <c:when test="${postIndex ge 1}">
                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex - 1}/${staticIndex}#postTable">« back</a></li>
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex + 1}/${staticIndex}#postTable">next »</a></li>
                                        </ul>
                                    </c:when>
                                    <c:when test="${fn: length(postList) == 10}">

                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/1/${staticIndex}#postTable">next »</a></li>
                                        </ul>

                                    </c:when>


                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>


            </div>


            <div class="row" id="userTable">


                <div class="panel panel-default panel-table">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col col-xs-6">
                                <h3 class="panel-title">Jinja Users</h3>
                            </div>
                            <div class="col col-xs-6 text-right">
                                <a class="btn btn-sm btn-primary btn-create" href="${pageContext.request.contextPath}/admincreateuserform">Create New User</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                                <tr>
                                    <th><em class="fa fa-cog"></em></th>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Authority</th>
                                    <th>Number of Comments</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="entry" items="${userCommentsMap}">
                                    <tr mycolor="${entry.key.colorStatus}" id="user-${entry.key.userId}">
                                        <td align="center">
                                            <form method="POST" action="${pageContext.request.contextPath}/deactivateuser/${entry.key.userId}">
                                                <button type="submit" class="btn btn-danger">
                                                    <em class="fa fa-trash"></em>
                                                </button>
                                            </form>
                                            <form method="POST" action="${pageContext.request.contextPath}/restoreuser/${entry.key.userId}">
                                                <button style="display: none" type="submit" class="btn btn-success">
                                                    <em class="fa fa-window-restore"></em>
                                                </button>
                                            </form>
                                        </td>


                                        <td>${entry.key.userId}</td>
                                        <td>${entry.key.userName}</td>





                                        <td><c:forEach var="authorityList" items="${entry.key.authorities}">

                                                <c:forEach var="authority" items="${authorityList}">
                                                    ${authority}
                                                </c:forEach>

                                            </c:forEach></td>
                                        <td>
                                            ${entry.value}
                                        </td>



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
                                    <c:when test="${userIndex ge 1 && fn: length(userList) le 20}">
                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex -1}/${postIndex}/${staticIndex}#userTable">« back</a></li>
                                        </ul>
                                    </c:when>
                                    <c:when test="${userIndex ge 1}">
                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex -1}/${postIndex}/${staticIndex}#userTable">« back</a></li>
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex +1}/${postIndex}/${staticIndex}#userTable">next »</a></li>
                                        </ul>
                                    </c:when>
                                    <c:when test="${fn: length(userList) == 10}">

                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/1/${postIndex}/${staticIndex}#userTable">next »</a></li>
                                        </ul>

                                    </c:when>


                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

            <div class="row" id="staticPageTable">

                <div class="panel panel-default panel-table">
                    <div class="panel-heading">
                        <div class="row" id="staticPageTable">
                            <div class="col col-xs-6">
                                <h3 class="panel-title">Static Pages</h3>
                            </div>
                            <div class="col col-xs-6 text-right">
                                <a class="btn btn-sm btn-primary btn-create" href="${pageContext.request.contextPath}/staticpageform">Create Static Page</a>
                            </div>
                        </div>
                    </div>

                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                                <tr>
                                    <th width="5%"><em class="fa fa-cog"></em></th>
                                    <th width="5%">Index</th>
                                    <th width="90%">Title</th>



                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="currentStaticPage" items="${staticPageList}">
                                    <tr mycolor = "${currentStaticPage.colorStatus}">
                                        <td align="center">
                                            <a class="btn btn-default" href="${pageContext.request.contextPath}/editstaticpageform/${currentStaticPage.staticPageId}"><em class="fa fa-pencil"></em></a>
                                            <form method="POST" action="${pageContext.request.contextPath}/removestaticpage/${currentStaticPage.staticPageId}">
                                                <button type="submit" class="btn btn-danger"><em class="fa fa-trash"></em>
                                                </button>
                                            </form>
                                            <form method="POST" action="${pageContext.request.contextPath}/restorestaticpage/${currentStaticPage.staticPageId}">
                                                <button style="display: none" type="submit" class="btn btn-success">
                                                    <em class="fa fa-window-restore"></em>
                                                </button>
                                            </form>
                                        </td>

                                        <td>${currentStaticPage.staticPageIndex}</td>
                                        <td><a href="${pageContext.request.contextPath}/static/${currentStaticPage.staticPageId}">${currentStaticPage.staticPageTitle}</a></td>


                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>









                        <form method="POST" action="${pageContext.request.contextPath}/changestaticpageorder">
                            <div class="col-md-2">

                                <input type="submit" class="btn btn-default btn-sm" value="Re-Arrange"/>


                            </div>
                            <div class="col-md-8"
                                 <ul id="sortable">
                                    <c:forEach var="currentStaticPage" items="${staticPageList}">
                                        <li class="ui-state-default" draggable="true">
                                        <td>${currentStaticPage.staticPageTitle}</td>
                                        <input name="staticPageOrderList" type="hidden" value="${currentStaticPage.staticPageId}"/>

                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <div class="col-md-2">

                            </div>
                        </form>


                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col col-xs-4">
                            </div>
                            <div class="col col-xs-8">
                                <c:choose>
                                    <c:when test="${staticIndex gt 1 && fn: length(staticList) le 10}">
                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex}/${staticIndex - 1}#staticPageTable">« back</a></li>
                                        </ul>
                                    </c:when>
                                    <c:when test="${staticIndex ge 1}">
                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex}/${staticIndex - 1}#staticPageTable">« back</a></li>
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex}/${staticIndex + 1}#staticPageTable">next »</a></li>
                                        </ul>
                                    </c:when>
                                    <c:when test="${fn: length(staticList) == 10}">

                                        <ul class="pagination pagination-sm pull-right">
                                            <li><a href="${pageContext.request.contextPath}/admin/update/pages/${userIndex}/${postIndex}/1#staticPageTable">next »</a></li>
                                        </ul>

                                    </c:when>


                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>


            </div>






        </div>




        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>


        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/cm-admin-home.js"></script>
        <script src="${pageContext.request.contextPath}/js/adminhome.js"></script>
    </body>
</html>
