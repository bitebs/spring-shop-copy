//При нажатии на ссылку "update" вызвать диалоговое окно и передать ему book
$(document).on("click", ".updateJewelry", function(){
    let id = $(this).attr("id");

//console.log(id)

    fetch(`http://127.0.0.1:8080/jewelries/${id}`, {
        method: 'GET',
        redirect: 'follow'
    })
        .then(response => response.json())
        .then(result => {
            $("#modalUpdate").val(id);
            $("#modalUpdate #name").val(
                result.name);

            let selectColor =  $("#modalUpdate #color");
            selectColor.val(result.color);
            for (let i=0; i<colors.length; i++){
                let option = $('<option>');
                option.html(colors[i]);
                selectColor.append(option);
            }
            $("#modalUpdate #price").val(result.price);
            $("#modalUpdate #priceNew").val(result.priceNew);

//console.log(result)

        })
        .catch(error => console.log('error', error));
})

//При подтверждении изменений (нажатии на кнопку OK)
$(document).on("click", "#buttonUpdate", async function(){
    let id = $("#modalUpdate").val();
    let name = $("#modalUpdate #name").val();
    let color = $("#modalUpdate #color").val();
    let price = $("#modalUpdate #price").val();
    let priceNew = $("#modalUpdate #priceNew").val();
    let file = $('#file').prop('files')[0];
    const toBase64 = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });

    var image = await toBase64(file);

//console.log(image);


    //let s = JSON.stringify({id, name, color, price, priceNew, image});
    //console.log(s);



    const res = await fetch(`http://localhost:8080/api/jewelries/${id}`,
        {
            method:"PUT",
            headers:{'Content-Type':'application/json'},
            body: JSON.stringify({id, name, color, price, priceNew, image})
        });

    $("#modalUpdate").modal().hide();
    location.reload();
    //$("#modalUpdate #cancel").click()

})