$(document).ready(
    loadData()
)

function loadData() {
    getModels();
    getEngine();
}

function getModels() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/carModels',
        dataType: 'text'
    }).done(function (data) {
        let json = $.parseJSON(data);
        console.log(json);
        for (let model of json) {
            if (!$('#model option:contains(' + model.name + ')').length) {
                $('#model').append(`<option value="${model.id}">${model.name}</option>`);
            }
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function getEngine() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/engine',
        dataType: 'text'
    }).done(function (data) {
        let json = $.parseJSON(data);
        console.log(json);
        for (let engine of json) {
            if (!$('#engine option:contains(' + engine.name + ')').length) {
                $('#engine').append(`<option value="${engine.id}">${engine.name}</option>`);
            }
        }
    }).fail(function (err) {
        console.log(err);
    });
}