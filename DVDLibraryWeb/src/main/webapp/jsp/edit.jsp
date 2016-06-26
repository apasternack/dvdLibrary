<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello Controller Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div> 

            <div class="row">

                <div class="col-md-6">

                    <form method="POST" action="./" class="form-horizontal">
                        <input type="hidden" name="id" value="${dvd.id}" />
                        <div class="form-group">
                            <label for="title" class="col-md-4 control-lable">DVD Title:</label>
                            <div class="col-md-8">
                                <input name="title" id="title" value="${dvd.title}"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="release-date" class="col-md-4 control-lable">Release Date:</label>
                            <div class="col-md-8">
                                <input name="releaseDate" id="release-date" value="<fmt:formatDate pattern="MM/dd/YYYY" value="${dvd.releaseDate}" />" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="rating" class="col-md-4 control-lable">Rating:</label>
                            <div class="col-md-8">
                                <input name="rating" id="rating" value="${dvd.rating}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="director" class="col-md-4 control-lable">Director:</label>
                            <div class="col-md-8">
                                <input name="director" id="director" value="${dvd.director}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="studio" class="col-md-4 control-lable">Studio:</label>
                            <div class="col-md-8">
                                <input name="studio" id="studio" value="${dvd.studio}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label"></label>
                            <div class="col-md-8">
                                <input type="submit" class="btn pull-right btn-success"/>
                            </div>
                        </div>

                    </form>

                </div>

            </div>



        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

