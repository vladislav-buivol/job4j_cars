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
        for (let model of json) {
            $('#model').append(` <li class="list-group-item">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" value="${model.id}" name="model">
                                                        ${model.name}
                                                    </label>
                                                </div>
                                            </li>`);
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
            $('#engine').append(` <li class="list-group-item">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" value="${engine.id}" name="engine">
                                                     ${engine.name}
                                                    </label>
                                                </div>
                                            </li>`);
        }
    }).fail(function (err) {
        console.log(err);
    });
}