(function ($) {

    //form validate++++++++++++++++++++++++++++++++++++++
    jQuery.validator.setDefaults({
        debug: true,
        success: function (label) {
            label.attr('id', 'valid');
        },
    });
    $("#register_form").validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: "required",
            comfirm_password: {
                equalTo: "#password"
            }
        },
        messages: {
            username: {
                required: "Please enter an username"
            },
            email: {
                required: "Please provide an email"
            },
            phonenumber: {
                required: "Please provide a phonenumber"
            },
            password: {
                required: "Please provide a password"
            },
            comfirm_password: {
                required: "Please provide a password",
                equalTo: "Wrong Password"
            }
        }
    });

    //form submit+++++++++++++++++++++++++++++++++++++
    $("#submit").on('click', function(){
        var form =  $("#notification-section");
        var data ={
            username: $("#username").val(),
            email : $("#email").val(),
            phoneNumber:$("#phonenumber").val(),
            password : $("#password").val()
        }
        // send ajax
        console.log(JSON.stringify(data));
        $.ajax({
            url: 'Http://localhost:9191/register',
            type : "POST",
            dataType : 'json',
            data : JSON.stringify(data),
            contentType:"application/json;charset=UTF-8",
            success : function(result) {
                $("#register_form")[0].reset();
                form.html("Registered successfully").addClass("form-row validate-input alert alert-success")
            },
            error: function(xhr, resp, text) {
                form.html("Error during registration");
            }
        });
    });

    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function () {
        $(this).on('blur', function () {
            if ($(this).val().trim() != "") {
                $(this).addClass('has-val');
            } else {
                $(this).removeClass('has-val');
            }
        })
    })


    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit', function () {
        var check = true;

        for (var i = 0; i < input.length; i++) {
            if (validate(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });

    function validate(input) {
        if ($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        } else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }


})(jQuery);