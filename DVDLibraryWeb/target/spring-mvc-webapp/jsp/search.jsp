<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library Search</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">-->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


        <style>
            th {
                background-color: #449D44;
                color: #E7FFE1;
            }

            #datePickerDiv {
                padding: 15px 0 0 15px;
                display: none;
            }

            #searchBar {
                visibility: visible;
            }

        </style>



    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/dvd/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div>

            <div class="row">

                <div class="col-md-6">

                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Release Date</th>
                                <th><span class="glyphicon glyphicon-edit"></span> Edit</th>
                                <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${searchResults}" var="dvd">
                            <tr>
                                <td><a href="show/${dvd.id}">${dvd.title}</a></td>
                                <td><fmt:formatDate type="date" value="${dvd.releaseDate}" /></td>
                                <td><a href="edit/${dvd.id}">Edit</a></td>
                                <td><a href="delete/${dvd.id}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>   

                <div class="col-md-6">

                    <form method="POST" action="search" class="form-horizontal">


                        <div class="row">

                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Search by:</span>
                                    <select name="searchBy" class="form-control" id="searchBy">
                                        <option value="title">DVD title</option>
                                        <option value="releaseDate">Release date</option>
                                        <option value="director">Director</option>
                                        <option value="rating">Rating</option>
                                        <option value="studio">Studio</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="input-group" id="searchBar">
                                    <input name="search" type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-secondary btn-success" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                    </span>
                                </div>
                            </div>

                        </div>



                        <div class="row">

                            <div class="col-md-6">

                                <div id="datePickerDiv">
                                    <h5>Find all DVDs from date to present:</h5>

                                    <!--<p>Date: <input type="date" ></p>-->
                                    <div class="input-group" id="searchBar">
                                        <input name="dateSearch" type="text" id="datepicker" class="form-control">
                                        <span class="input-group-btn">
                                            <button class="btn btn-secondary btn-success" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                        </span>                        </div>
                                </div>

                            </div>

                            <div class="col-md-6">

                            </div>

                        </div>


                    </form>

                </div>

            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!--<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>-->
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>


            $(function () {
                $("#datepicker").datepicker();
            });


            var ddl = document.getElementById("searchBy");
            ddl.onchange = newCustomerType;
            function newCustomerType()
            {
                var ddl = document.getElementById("searchBy");
                var selectedValue = ddl.options[ddl.selectedIndex].value;


                if (selectedValue === "releaseDate")
                {
                    document.getElementById("datePickerDiv").style.display = "block";
                    document.getElementById("searchBar").style.visibility = "hidden";
                } else
                {
                    document.getElementById("datePickerDiv").style.display = "none";
                    document.getElementById("searchBar").style.visibility = "visible";
                }

            }

        </script>
    </body>
</html>

