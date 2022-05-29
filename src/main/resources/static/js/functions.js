
"use strict";

//se llama al DOM a traves del evento DOMContentLoaded
document.addEventListener('DOMContentLoaded', async () => {

    //Devuelve un listado de los turnos disponibles entre las fechas dadas en formato JSON
    async function turnosDisponiblesEntreFechas(from, to) {
        try {
            let obj = await fetch('https://testturnofacil.herokuapp.com/turno/turnosdisponibles/' + from + '/' + to);
            let data = await obj.json();
            //console.log(data)
            return data;
        }
        catch (error) {
            console.log(error)
        }
    }
    const turnos = await turnosDisponiblesEntreFechas("2022-05-13", "2022-05-17");

    //se ordenan los turnos de forma ascendente
    turnos.sort((a, b) => {
        const auxA = new Date(a.fecha);
        const auxB = new Date(b.fecha);
        if (auxA.getTime() > auxB.getTime()) return 1;
        if (auxA.getTime() < auxB.getTime()) return -1;
        return 0;
    })

    const modal = document.querySelector('#modal');
    const modalContainer = document.querySelector('#modal-container');

    let modalVisible = document.querySelector(".modal-visible");
    let btnVerProxTurnos = document.querySelector("#verTurnos");
    btnVerProxTurnos.addEventListener("click", ocultarModal);
    //Se oculta el modal "Ver próximos turnos" y se muestra el del listado de turnos
    function ocultarModal() {
        modalVisible.classList.toggle("ocultar");
        modalContainer.classList.remove('modal-hidden');
        modalContainer.classList.add('modal-show');
    }


    const btnCerrar = document.querySelector('#btn-cerrar');

    modalContainer.classList.remove('modal-show');
    modalContainer.classList.add('modal-hidden');

    //Si hace click en cerrar vuelve a la pantalla anterior
    btnCerrar.addEventListener('click', () => {
        modalContainer.classList.remove('modal-show');
        modalContainer.classList.add('modal-hidden');
        modalVisible.classList.toggle("ocultar");
    })

    //se recorren los turnos y para cada uno se agrega el boton seleccionar, para poder seleccionar
    //un turno disponible de la lista
    turnos.forEach((turno) => {
        const date = new Date(turno.fecha);
        const day = date.getDate();
        const time = date.getMinutes() >= 10 ? `${date.getHours()}:${date.getMinutes()}` : `${date.getHours()}:0${date.getMinutes()}`;
        const dayOfWeek = date.toLocaleDateString('es', { weekday: 'long' });
        const month = date.toLocaleDateString('es', { month: 'long' });
        const prunnedDay = dayOfWeek.charAt(0).toUpperCase() + dayOfWeek.slice(1);
        const ul = document.createElement('ul');
        const button = document.createElement('button');
        button.innerHTML = 'Seleccionar';
        button.classList.add('btn', 'btn-light', 'btn-sm', 'col-3', 'btn-select-modal');
        button.addEventListener('click', () => {
            modalContainer.classList.remove('modal-show');
            modalContainer.classList.add('modal-hidden');
        })
        const div = document.createElement('div');
        div.classList.add('container');
        const li = document.createElement('li');
        li.classList.add('row');
        const p = document.createElement('p');
        p.innerHTML = `${prunnedDay} ${day} de ${month} - ${time} hs`
        p.classList.add('col-9');
        li.append(p);
        li.append(button);
        div.appendChild(li);
        ul.appendChild(div);
        modal.appendChild(ul);
    })

})