<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <style>
            th {
                background-color: #449D44;
                color: #E7FFE1;
            }

        </style>

    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <h3>Display Details</h3>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div>

            <div class="row">

                <div class="col-md-12">

                    <table class="table table-bordered">
                        <tr>
                            <th>DVD Title</th>
                            <th>Release Date</th>
                            <th>Rating</th>
                            <th>Director</th>
                            <th>Studio</th>
                        </tr>
                        <tr>
                            <td>${dvd.title}</td>
                            <td><fmt:formatDate type="date" value="${dvd.releaseDate}" /></td>
                            <td>${dvd.rating}</td>
                            <td>${dvd.director}</td>
                            <td>${dvd.studio}</td>

                        </tr>
                    </table>
                </div>   


            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

