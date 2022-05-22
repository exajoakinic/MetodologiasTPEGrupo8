async function turnosDisponiblesEntreFechas(from, to) {
    try {
        let obj = await fetch('/turno/turnosdisponibles/' + from + '/' + to);
        let data = await obj.json();
        //console.log(data)
        return data;
    }
    catch (error) {
       console.log(error)
    }
}
}