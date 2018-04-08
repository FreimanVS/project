var ip = location.hostname;
var port = location.port;
var contextPath = $('#contextPathHolder').attr('data-contextPath');

function validAge(age) {
    return age.match(/^[1-9][\d]{0,2}$/);
}

function validPhoneNumber(phoneNumber) {
    return phoneNumber.match(/^[+][1-9][\d]{5,10}$/);
}

function validSalary(salary) {
    return !isNaN(salary);
}

function validFivePlusSymbols(login) {
    return login.match(/.{5,}/);
}

function validTwoPlusSymbols(login) {
    return login.match(/.{2,}/);
}

function validEmail(email) {
    return email.match(/^(.+)@(.+)[.](.+)$/);
}

function getAll() {
    $.getJSON(contextPath + "/employees", function(data) {
        var appendJson ="<table><caption><b>РАБОТНИКИ</b></caption>" +
            "<thead><tr>" +
            "<th>id</th>" +
            "<th>логин</th>" +
            "<th>пароль</th>" +
            "<th>ФИО</th>" +
            "<th>Отдел</th>" +
            "<th>Город</th>" +
            "<th>Зарплата</th>" +
            "<th>Телефон</th>" +
            "<th>email</th>" +
            "<th>Возраст</th>" +
            "</tr></thead>" +
            "<tbody>";

        appendJson = appendJson + "<tr>"
            + "<td align=\"center\">новый</td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='логин'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='3' minlength='5' placeholder='пароль'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='ФИО'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='отдел'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='город'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='зарплата'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='телефон'></td>"
            + "<td align=\"center\"><input name='add' type=\"text\" size='7' minlength='5' placeholder='email'></td>"
            + "<td align=\"center\"><input name='add' type=\"number\"  min='1' step='1' max='999' placeholder='возраст'></td>"
            + "<td><button id='add-button'>добавить</button>"
            + "</tr>";

        for (var i = 0; i < data.length; i++) {
            appendJson = appendJson + "<tr>"
                + "<td align=\"center\">" + data[i].id + "</td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].login + "\"/></td>"
                + "<td align=\"center\">" +
                "</td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].fio + "\"/></td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].department + "\"/></td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].city + "\"/></td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].salary + "\"/></td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].phoneNumber + "\"/></td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"text\" size='7' value=\"" + data[i].email + "\"/></td>"
                + "<td align=\"center\"><input name='" + data[i].id + "' type=\"number\"  min='1' step='1' max='999' value=\"" + data[i].age + "\"/></td>"

                + "<td><button class='update-button' value=\"" + data[i].id + "\">изменить</button>"
                + "|"
                + "<button class='delete-button' value=\"" + data[i].id + "\">удалить</button></td>"
                + "</tr>"
        }
        appendJson = appendJson + "</tbody>" +
            "<tfoot>" +
            "<tr>" +
            "<th colspan=\"10\">Информация обо всех работниках</th>" +
            "</tr>" +
            "</tfoot>" +
            "</table>";
        $("#allEmployees").html(appendJson);

        //add-button
        $("#add-button").click(function () {
            var employee = {id:0,"age":0,"city":null,"department":null,
                "email":null,"fio":null,"id":0,
                "login":null,"password":null,
                "phoneNumber":null,"positions":[],
                "roles":[],"salary":0.0};

            var valid = true;

            $("input[name='add']").each(function (index) {
                //login
                if (index == 0) {
                    if (validFivePlusSymbols($(this).val())) {
                        employee.login = $(this).val();
                    } else {
                        alert("not valid login [5+ symbols]");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //password
                if (index == 1) {
                    if (validFivePlusSymbols($(this).val())) {
                        employee.password = $(this).val();
                    } else {
                        alert("not valid city [5+ symbols]");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //fio
                if (index == 2) {
                    if (validFivePlusSymbols($(this).val())) {
                        employee.fio = $(this).val();
                    } else {
                        alert("not valid FIO [5+ symbols]");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //department
                if (index == 3) {
                    if (validTwoPlusSymbols($(this).val())) {
                        employee.department = $(this).val();
                    } else {
                        alert("not valid department [2+ symbols]");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //city
                if (index == 4) {
                    if (validFivePlusSymbols($(this).val())) {
                        employee.city = $(this).val();
                    } else {
                        alert("not valid city [5+ symbols]");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //salary
                if (index == 5) {
                    if (validSalary($(this).val())) {
                        employee.salary = $(this).val();
                    } else {
                        alert("not valid salary (only number)");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //phone number
                if (index == 6) {
                    if (validPhoneNumber($(this).val())) {
                        employee.phoneNumber = $(this).val();
                    } else {
                        alert("not valid phone number (+ and [6-11]numbers)");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //e-mail
                if (index == 7) {
                    if (validEmail($(this).val())) {
                        employee.email = $(this).val();
                    } else {
                        alert("not valid email");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
                //age
                if (index == 8) {
                    if (validAge($(this).val())) {
                        employee.age = $(this).val();
                    } else {
                        alert("not valid age");
                        getAll();
                        valid = false;
                        return false;
                    }
                }
            });

            if (!valid) {
                return false;
            }

            var json_employee = JSON.stringify(employee);

            $.ajax({
                url: contextPath + "/employees",
                type: "POST",
                data: json_employee,
                dataType: "JSON",
                contentType: "application/json",
                beforeSend: function() {
                },
                success: function(data2) {
                    getAll();
                },
                error: function () {
                    getAll();
                }
            });
        });

        //delete-button
        $(".delete-button").click(function () {
            if (!(confirm('Вы уверены, что хотите удалить?'))) {
                return false;
            }
            var id = this.value;
            $.ajax({
                url: contextPath + "/employees?id=" + id,
                type: "DELETE",
                data: ({}),
                dataType: "html",
                beforeSend: function() {
                },
                success: function(data) {
                    getAll();
                },
                error: function () {
                    getAll();
                }
            });
        });

        //update-button
        $(".update-button").click(function () {
            var id = this.value;

            var this_data = null;
            for (var j = 0; j < data.length; j++) {
                if (data[j].id == id) {
                    this_data = data[j];
                }
            }

            $("input[name='" + id + "']").each(function (index) {
                //login
                if (index == 0) {
                    if (validFivePlusSymbols($(this).val())) {
                        this_data.login = $(this).val();
                    } else {
                        alert("not valid login [5+ symbols]");
                    }
                }
                //fio
                if (index == 1) {
                    if (validFivePlusSymbols($(this).val())) {
                        this_data.fio = $(this).val();
                    } else {
                        alert("not valid FIO [5+ symbols]");
                    }
                }
                //department
                if (index == 2) {
                    if (validTwoPlusSymbols($(this).val())) {
                        this_data.department = $(this).val();
                    } else {
                        alert("not valid department [2+ symbols]");
                    }
                }
                //city
                if (index == 3) {
                    if (validFivePlusSymbols($(this).val())) {
                        this_data.city = $(this).val();
                    } else {
                        alert("not valid city [5+ symbols]");
                    }
                }
                //salary
                if (index == 4) {
                    if (validSalary($(this).val())) {
                        this_data.salary = $(this).val();
                    } else {
                        alert("not valid salary (only number)");
                    }
                }
                //phone number
                if (index == 5) {
                    if (validPhoneNumber($(this).val())) {
                        this_data.phoneNumber = $(this).val();
                    } else {
                        alert("not valid phone number (+ and [6-11]numbers)");
                    }
                }
                //e-mail
                if (index == 6) {
                    if (validEmail($(this).val())) {
                        this_data.email = $(this).val();
                    } else {
                        alert("not valid email");
                    }
                }
                //age
                if (index == 7) {
                    if (validAge($(this).val())) {
                        this_data.age = $(this).val();
                    } else {
                        alert("not valid age");
                    }
                }
            });

            //send JSON
            var json_data = JSON.stringify(this_data);

            $.ajax({
                url: contextPath + "/employees?id=" + id,
                type: "PUT",
                data: json_data,
                dataType: "JSON",
                contentType: "application/json",
                beforeSend: function() {
                },
                success: function(data) {
                    getAll();
                },
                error: function () {
                    getAll();
                }
            });
        });
    });
}

$(document).ready(function() {
    getAll();
});