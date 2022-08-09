var colors = ['Platinum','White','Yellow','Rose'];
var requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

fetch('http://localhost:8080/jewelries', requestOptions)
    .then(response => response.json())
    .then(result => addRows(result))
    .catch(error => console.log('error in findAll', error));

function addRows(response) {
    for (let i = 0; i < response.length; i++) {
        let tr = $('<tr>');

        let id = response[i].id;
        tr.append($('<td>').html(id));

        let tdImage = $('<td>');
        let src = 'data:image/png;base64,' + response[i].image;
        tdImage.html(`<img src="${src}" style="width:50px; height:50px">`);
        tr.append(tdImage);

        tr.append($('<td>').html(response[i].name));

        let price = response[i].price / 100;
        tr.append($('<td>').html('$' + price.toFixed(2).toLocaleString('en-US')));

        let priceNew = response[i].priceNew / 100;
        tr.append($('<td>').html('$' + priceNew.toFixed(2).toLocaleString('en-US')));

        tr.append($('<td>').html(response[i].color));

        let tdActions = $('<td>');
        tdActions.html(`<div class="container">
                <button type="button" class="btn btn-outline-primary btn-sm updateJewelry" data-bs-toggle="modal"
            data-bs-target="#modalUpdate" id="${id}">Change</button>
                <button type="button" class="btn btn-outline-danger btn-sm deleteJewelry" data-bs-toggle="modal"
            data-bs-target="#modalDelete" id="${id}">Delete</button>
                </div>
`);
        tr.append(tdActions);
        $('#data').append(tr);
    }
}