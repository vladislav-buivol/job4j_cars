$(document).ready(
    requestAdvData(setData, logError)
)

function logError(err) {
    console.log(err);
}

function requestAdvData(successCallback, unSuccessCallback) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4j_cars/myAdv.jsp',
        dataType: 'json'
    }).done(function (response) {
        console.log(response)
        successCallback(response)
    }).fail(function (err) {
        console.log(err);
    });
}

function setData(advertisements) {
    let divs = '';

    function getCondition(adv) {
        let carCondition = 'New vehicle';
        if (adv.car.isUsed) {
            carCondition = 'Used vehicle';
        }
        return carCondition;
    }

    function getImgDAta(adv) {
        let imageSrc = '../img/cars/empty.jpg';
        if (adv.images.length > 0) {
            let image = adv.images[0];
            imageSrc = "data:image/jpeg;base64,"
                + btoa(new Uint8Array(image.imgData).reduce(function (data, byte) {
                    return data + String.fromCharCode(byte);
                }, ''));
        }
        return imageSrc;
    }

    for (let i in advertisements) {
        let adJson = advertisements[i];
        let key = Object.keys(adJson)[0];
        let adv = adJson[key];
        let imageSrc = getImgDAta(adv);
        let carCondition = getCondition(adv);
        let status = '';
        let editUrl = getContextPath() + '/edit.jsp?id=' + adv.id;
        if (adv.status) {
            status = '<img src="../img/cars/sold.png" class="img-responsive sold" alt="">';
        }

        let adDiv = `<div class="col-md-4 col-sm-4">
                <div class="courses-thumb courses-thumb-secondary">
                    <div class="courses-top">
                        <div class="courses-image">
                            <img src='${imageSrc}' class="img-responsive parent" alt="">
                            ${status}
                        </div>
                        <div class="courses-date">
                            <span title="Millage"><i class="fa fa-dashboard"></i> ${adv.car.mileage}km</span>
                            <span title="Engine"><i class="fa fa-cube"></i> ${adv.car.engine.name}L</span>
                            <span title="Transmission"><i class="fa fa-cog"></i> ${adv.car.gearbox}</span>
                        </div>
                    </div>

                    <div class="courses-detail" id="1">
                        <h3><a href="car-details.html">${adv.car.model.name}</a></h3>

                        <p class="lead"><small>
                        </small> <strong>$${adv.price}</strong></p>

                        <p>${adv.car.fuel} &nbsp;&nbsp;/&nbsp;&nbsp; ${carCondition}</p>
                    </div>

                    <div class="courses-info">
                        <a href="${editUrl}" class="section-btn btn btn-primary btn-block">View More / Edit</a>
                    </div>
                </div>
            </div>`
        divs += adDiv;
    }
    $("#advs").html(divs)
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}