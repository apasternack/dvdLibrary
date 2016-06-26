/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {            //JQuery function that runs when the DOM has been fully built in memory

    $('#create-submit').on('click', function (e) {                 //any object with a css selector that matches in ' ' make an array out of it
        e.preventDefault();

        var dvdData = JSON.stringify({
            title: $('#title').val(),
            releaseDate: $('#release-date').val(),
            rating: $('#rating').val(),
            director: $('#director').val(),
            studio: $('#studio').val()

        });
        $.ajax({
            url: contextRoot + "/dvd/",
            type: "POST",
            data: dvdData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");            //setting the accept header because we are sending a JSON file
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success:
                    function (data, status) {
                        console.log(data);
                        var tableRow = buildDvdRow(data);
                        $('#dvd-table').append($(tableRow));
                        $('#title').val(''); //clears user inputed values from our form
                        $('#release-date').val('');
                        $('#rating').val('');
                        $('#director').val('');
                        $('#studio').val('');

                        $('#dvd-title-div').removeClass('has-error');
                        $('#dvd-title-error-message').hide();
                        $('#dvd-rating-div').removeClass('has-error');
                        $('#dvd-rating-error-message').hide();
                        $('#dvd-director-div').removeClass('has-error');
                        $('#dvd-director-error-message').hide();
                        $('#dvd-studio-div').removeClass('has-error');
                        $('#dvd-studio-error-message').hide();
                    },
            error: function (data, status) {
                console.log('error here!');

                var errors = data.responseJSON.errors;

                console.log(errors);

                //clears out validation error messages and css
                $('#dvd-title-div').removeClass('has-error');
                $('#dvd-title-error-message').hide();
                $('#dvd-rating-div').removeClass('has-error');
                $('#dvd-rating-error-message').hide();
                $('#dvd-director-div').removeClass('has-error');
                $('#dvd-director-error-message').hide();
                $('#dvd-studio-div').removeClass('has-error');
                $('#dvd-studio-error-message').hide();
                var errors = data.responseJSON.errors;
                console.log(errors);
                //if validation error change styling for matching field to indicate to user
                $.each(errors, function (index, error) {

                    if (error.fieldName === "title") {
                        $('#dvd-title-div').addClass('has-error');
                        $('#dvd-title-error-message').show();
                    }
                    if (error.fieldName === "rating") {
                        $('#dvd-rating-div').addClass('has-error');
                        $('#dvd-rating-error-message').show();
                    }
                    if (error.fieldName === "director") {
                        $('#dvd-director-div').addClass('has-error');
                        $('#dvd-director-error-message').show();
                    }
                    if (error.fieldName === "studio") {
                        $('#dvd-studio-div').addClass('has-error');
                        $('#dvd-studio-error-message').show();
                    }

                });



//                $.each(errors, function (index, error) {
//
//                    $('#add-dvd-validation-errors').append(error.fieldName + ": " + error.message + "<br />");
//
//
//                });

            }



        });

//        alert("alert after ajax");

    });

    $('#showDvdModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);
        var dvdId = link.data('dvd-id');
        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#dvd-title').text(data.title);
                console.log(data.releaseDate);
                if (data.releaseDate === null) {
                    $('#dvd-release-date').text("");
                } else {
                    $('#dvd-release-date').text(data.releaseDate);
                }
                $('#dvd-rating').text(data.rating);
                $('#dvd-director').text(data.director);
                $('#dvd-studio').text(data.studio);
            },
            error: function (data, status) {
                console.log(data);


            }


        });
    });

    $('#editDvdModal').on('show.bs.modal', function (e) {

//        $("#ui-edit-dvd-release-date-div").css("z-index", "9999");

        var link = $(e.relatedTarget);
        var dvdId = link.data('dvd-id');
        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#edit-dvd-title').val(data.title);
                $('#edit-dvd-release-date').val(dateFormatter2(data.releaseDate));
                $('#edit-dvd-rating').val(data.rating);
                $('#edit-dvd-director').val(data.director);
                $('#edit-dvd-studio').val(data.studio);
                $('#edit-id').val(data.id);

            },
            error: function (data, status) {
                console.log(data);
            }


        });
    });

    $('#edit-dvd-button').on('click', function (e) {

        var dvdData = JSON.stringify({
            id: $('#edit-id').val(),
            title: $('#edit-dvd-title').val(),
            releaseDate: $('#edit-dvd-release-date').val(),
            rating: $('#edit-dvd-rating').val(),
            director: $('#edit-dvd-director').val(),
            studio: $('#edit-dvd-studio').val()

        });
        $.ajax({
            url: contextRoot + "/dvd/",
            type: "PUT",
            data: dvdData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json"); //setting the accept header because we are sending a JSON file
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                console.log(data);

                $('#editDvdModal').modal('hide');

                var tableRow = buildDvdRow(data);

                $('#dvd-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {

                var errors = data.responseJSON.errors;

                console.log(errors);

                $.each(errors, function (index, error) {

                    $('#add-dvd-edit-validation-errors').append(error.fieldName + ": " + error.message + "<br />");

                });

            }



        });
//        alert("alert after ajax");

    });


    $(document).on('click', '.delete-link', function (e) {     //we need to do this because '.delete-link' is NOT in the DOM....created via JS, SOOO here we bind to the document instead of '.delete-link' and then pass '.delete-link' as a parameter to .on which delegates, meaning, tells the DOM to communicate to '.delete-link' which is not part of the DOM

        e.preventDefault();

        var dvdId = $(e.target).data('dvd-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/dvd/" + dvdId,
            success: function (data, status) {
                console.log(dvdId);
                $('#dvd-row-' + dvdId).remove();
            },
            error: function (data, status) {

            }




        });

    });


    function buildDvdRow(data) {

        var releaseDateString = "";

        if (data.releaseDate !== null) {
            releaseDateString = "<fmt:formatDate type='date' value='" + data.releaseDate + "' />";
        }

        return "<tr id='dvd-row-" + data.id + "'>  \n\
                    <td><a data-dvd-id='" + data.id + "' data-toggle='modal' data-target='#showDvdModal'>" + data.title + "</a></td>  \n\
                    <td>" + dateFormatter(data.releaseDate) + "</td>  \n\
                    <td> <a data-dvd-id='" + data.id + "' data-toggle='modal' data-target='#editDvdModal'>Edit</a>  </td>   \n\
                    <td> <a data-dvd-id='" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";
    }

    function dateFormatter(date) {
        var month = date.substring(0, 2);
        var day = date.substring(3, 5);
        var year = date.substring(6, 10);

        var months = [
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        ];

        return months[month - 1] + " " + day + ", " + year;

    }
    
    function dateFormatter2(date) {
        var month = date.substring(5, 7);
        var day = date.substring(8, 10);
        var year = date.substring(0, 4);
        
        return month + "/" + day + "/" + year;
         
    }

});
