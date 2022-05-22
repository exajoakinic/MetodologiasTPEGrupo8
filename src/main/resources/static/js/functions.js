"use strict";

//se llama al DOM a traves del evento DOMContentLoaded
document.addEventListener('DOMContentLoaded', async () => {
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
    const turnos = await turnosDisponiblesEntreFechas("2022-05-13", "2022-05-17");

    // const turnos = [
    //     {
    //         id: 1,
    //         "fecha": "2022-05-13 14:30:00",
    //         "disponible": true
    //     },
    //     {
    //         "id": 2,
    //         "fecha": "2022-05-13 16:30:00",
    //         "disponible": true
    //     },
    //     {
    //         "id": 3,
    //         "fecha": "2022-05-15 10:30:00",
    //         "disponible": true
    //     }
    // ]

    const modal = document.querySelector('#modal');
    const modalContainer = document.querySelector('#modal-container')

    //se recorren los turnos y para cada uno se agrega el boton seleccionar, para poder seleccionar
    //un turno disponible de la lista
    const btnCerrar = document.querySelector('#btn-cerrar');
    btnCerrar.addEventListener('click', () => {
        modalContainer.classList.remove('modal-show');
        modalContainer.classList.add('modal-hidden');
    })
    turnos.forEach((turno) => {
        const date = new Date(turno.fecha);
        const day = date.getDate();
        const time = `${date.getHours()}:${date.getMinutes()}`
        const dayOfWeek = date.toLocaleDateString('es', { weekday: 'long' });
        const month = date.toLocaleDateString('es', { month: 'long' });
        const prunnedDay = dayOfWeek.charAt(0).toUpperCase() + dayOfWeek.slice(1);
        const ul = document.createElement('ul');
        const button = document.createElement('button');
        button.innerHTML = 'Seleccionar';
        button.classList.add('btn', 'btn-light', 'btn-sm', 'col-3');
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
