$(document).ready(
    function() {

        // SUBMIT FORM
        $("#registerBuddyForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                name : $("#buddyName").val(),
                address : $("#buddyAddress").val(),
                number : $("#buddyNumber").val(),
                addressBookName: $("#addressBookName").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/api/v0/addressbook/abm1/buddies",
                data : JSON.stringify(formData),
                dataType : 'json'
        }).then(function(data) {
                console.log(data);
            })
        }
    });

$(document).ready(
    function() {

        // SUBMIT FORM
        $("#updateBuddyForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var name = $("#existingBuddyName").val();
            var formData = {
                newAddress : $("#newBuddyAddress").val()
            }

            // DO PUT
            $.ajax({
                type : "PUT",
                contentType : "application/json",
                url : "/api/v0/addressbook/abm1/buddies/" + name,
                data : JSON.stringify(formData),
                dataType : 'json'
            }).then(function(data) {
                console.log(data);
            })
        }
    });

$(document).ready(
    function() {

        // SUBMIT FORM
        $("#deleteBuddyForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var name = $("#buddyToDelete").val();

            // DO DELETE
            $.ajax({
                type : "DELETE",
                contentType : "application/json",
                url : "/api/v0/addressbook/abm1/buddies/" + name,
                dataType : 'json'
            }).then(function(data) {
                console.log(data);
            })
        }
    });