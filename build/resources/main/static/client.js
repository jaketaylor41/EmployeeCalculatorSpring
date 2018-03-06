$(document).ready(function () {
    init();
});

function init() {
    enable();
    getEmployees();
}

function enable() {
$("#btnSubmit").on("click", postEmployee);
$("#btnSearch").on("click", searchEmployee);
$("#btnPosition").on("click", searchPosition);
}

function getEmployees() {
    $.ajax({
        type: "GET",
        url: "/getAllEmployees",
        success: function (response) {
            appendEmployee(response)
        }
    });
}

var total = 0;
var salaryNumber = 0;

function postEmployee(event) {
    event.preventDefault();

    var formId = $("#textId").val();
    var formFName = $("#textFName").val();
    var formLName = $("#textLName").val();
    var formSalary = $("#salary").val();
    var formPosition = $("#position").val();

    salaryNumber = (parseFloat((formSalary)));
    total += salaryNumber;

    $("#totalSalary").text("Salary Total for all Employees: $" + total);
    $("#monthlySalary").text("Monthly Salary Spend for the Company: $" + total / 12);
    $("#biWeeklySalary").text("Bi-Weekly Salary for the Company: $" + total / 26);
    $("#twiceAMonthSalary").text("Twice a Month Salary Spend for the Company: $" + total / 24);


    var newEmployee = {
        id: formId,
        firstName: formFName,
        lastName: formLName,
        salary: formSalary,
        position: formPosition
    };


    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(newEmployee),
        url: "/add/employee",
        success: function (response) {
            console.log(response);
            getEmployees();
        }
    });
}

function searchEmployee(event) {
    event.preventDefault();

    var searchId = $("#txtSearch").val();
    $.ajax({
        type: "GET",
        url: "/get/" + searchId,
        success: function (response) {
            appendEmployee(response);
        }
    });
}

function searchPosition(event) {
    event.preventDefault();

    var searchPos = $("#txtPosition").val();
    $.ajax({
        type: "GET",
        url: "/findEmployeeByPosition/" + searchPos,
        success: function (response) {
            appendEmployee(response);
        }
    })
}

function appendEmployee(responseArray) {
    $("#container").empty();

    for (var i = 0; i < responseArray.length; i++){
        var person = responseArray[i];

        $("#container").append("<div></div>");
        var el = $("#container").children().last();
        el.append("<p>" + person.id + " " +
                          person.firstName + " " +
                          person.lastName + " " +
                          person.position + " " +
                          person.salary + "</p>");
    }
}


