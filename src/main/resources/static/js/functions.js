
function test() {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
     "from": "2022-05-13",
     "to": "2022-05-13"
    });

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        data: raw,
        redirect: 'follow'
    };

    fetch("localhost:8080/turno/entrefechas", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

async function listarTurnosDisponiblesEntreFechas(fechaInicio, fechaFin) {
 try {
    let url = '/turno/entrefechas';
    let raw = JSON.stringify({"from": fechaInicio, "to": fechaFin})
    console.log(raw)
    let obj = await fetch(url,{
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          method: "GET",
          body: raw
      }
    );
    let data = await obj.json();
    let turnosDisp = [];
    let domTurnos = document.querySelector("#divTurnosDisponibles");
    domTurnos.innerHTML = "";
    for (let i = 0; i < data.length; i++) {
        domTurnos.innerHTML += data[i];
    }
 }
 catch (error) {
    console.log(error)
}
}