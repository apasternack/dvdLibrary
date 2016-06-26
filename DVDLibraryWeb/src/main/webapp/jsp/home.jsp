<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   <!-- this tag allows you to do validation-->
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
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <style>
            th {
                background-color: #449D44;
                color: #E7FFE1;
            }

            #dvd-title-error-message,
            #dvd-rating-error-message,
            #dvd-director-error-message,
            #dvd-studio-error-message
            {
                display: none;
            }

        </style>

    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div>

            <div class="row">

                <div class="col-md-6">

                    <table class="table table-bordered table-hover" id="dvd-table">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Rating</th>
                                <!--<th>Rating</th>-->
                                <th><span class="glyphicon glyphicon-edit"></span> Edit</th>
                                <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${dvds}" var="dvd">
                            <tr id="dvd-row-${dvd.id}">
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target=#showDvdModal>${dvd.title}</a></td>
                                    <c:if test="${empty dvd.releaseDate}">
                                    <td></td>
                                </c:if>
                                <c:if test="${not empty dvd.releaseDate}">
                                    <td><fmt:formatDate type="date" value="${dvd.releaseDate}" /></td>
                                </c:if>
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target=#editDvdModal>Edit</a></td>
                                <td><a data-dvd-id="${dvd.id}" class="delete-link">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>   

                <div class="col-md-6">

                    <form method="POST" class="form-horizontal">

                        <div class="form-group" id="dvd-title-div">
                            <label for="title" class="col-md-4 control-label">DVD Title:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="title" />
                                <div id="dvd-title-error-message" class="text-danger">You must supply a dvd title</div>
                            </div>
                        </div>

                        <div class="form-group" id="dvd-release-date-div">
                            <label for="release-date" class="col-md-4 control-label">Release Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="release-date" />
                            </div>
                        </div>

                        <div class="form-group" id="dvd-rating-div">
                            <label for="rating" class="col-md-4 control-label">Rating:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="rating" />
                                <div id="dvd-rating-error-message" class="text-danger">You must supply a rating</div>
                            </div>
                        </div>

                        <div class="form-group" id="dvd-director-div">
                            <label for="director" class="col-md-4 control-label">Director:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="director" />
                                <div id="dvd-director-error-message" class="text-danger">You must supply a director name</div>

                            </div>
                        </div>


                        <div class="form-group" id="dvd-studio-div">
                            <label for="studio" class="col-md-4 control-label">Studio:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="studio" />    
                                <div id="dvd-studio-error-message" class="text-danger">You must supply a studio name</div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label"></label>
                            <div class="col-md-8">
                                <input type="submit" class="btn pull-right btn-success" id="create-submit"/>
                            </div>
                        </div>

                    </form>

                </div>

            </div>

        </div>

        <div id="showDvdModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Contact Details</h4>
              </div>
              <div class="modal-body">
                        <table class="table table-bordered" id="show-dvd-table">
                            <tr>
                                <th>Title:</th>
                                <td id="dvd-title"></td>
                            </tr>

                            <tr>
                                <th>Release Date:</th>
                                <td id="dvd-release-date"></td>
                            </tr>

                            <tr>
                                <th>Rating:</th>
                                <td id="dvd-rating"></td>
                            </tr>

                            <tr>
                                <th>Director:</th>
                                <td id="dvd-director"></td>
                            </tr>

                            <tr>
                                <th>Studio</th>
                                <td id="dvd-studio"></td>
                            </tr>
                        </table>
                    </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>

          </div>
        </div>

        <div id="editDvdModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Contact Details</h4>
              </div>
              <div class="modal-body">
                        <table class="table table-bordered" id="edit-dvd-table">
                            <input type="hidden" id="edit-id" />
                            <tr>
                                <th>Title:</th>
                                <td>
                                    <input type="text" id="edit-dvd-title" />
                                </td>
                            </tr>

                            <tr>
                                <th>Release Date:</th>
                                <td>
                                    <input type="text" id="edit-dvd-release-date" />
                                </td>
                            </tr>

                            <tr>
                                <th>Rating:</th>
                                <td>
                                    <input type="text" id="edit-dvd-rating" />
                                </td>
                            </tr>

                            <tr>
                                <th>Director:</th>
                                <td>
                                    <input type="text" id="edit-dvd-director" />
                                </td>
                            </tr>

                            <tr>
                                <th>Studio</th>
                                <td>
                                    <input type="text" id="edit-dvd-studio" />
                                </td>
                            </tr>
                        </table>
                    </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" id="edit-dvd-button"  >Save</button>
              </div>
                    <div id="add-dvd-edit-validation-errors">
                    </div>
            </div>

          </div>
        </div>


        <script>

            var contextRoot = '${pageContext.request.contextPath}';


            $(function () {
                $("#release-date").datepicker();
            });

            $(function () {
                $("#edit-dvd-release-date").datepicker();
            });


        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>-->

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>

