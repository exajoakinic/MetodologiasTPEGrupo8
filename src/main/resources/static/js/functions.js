"use strict";

//se llama al DOM a traves del evento DOMContentLoaded
document.addEventListener('DOMContentLoaded', async () => {

    //Devuelve en formato JSON un listado de los turnos disponibles entre las fechas dadas y medico indicado
    //Listado ordenado por fecha ascendente
    async function turnosDisponiblesEntreFechas(id_medico, from, to) {
        try {
            let obj = await fetch('https://testturnofacil.herokuapp.com/turno/turnosdisponibles/' + id_medico + '/' + from + '/' + to);
            let data = await obj.json();
            console.log(data);
            return data;
        }
        catch (error) {
            console.log(error)
        }
    }

    //Devuelve en formato JSON listado de los médicos
    async function getMedicos() {
        try {
            let obj = await fetch('https://testturnofacil.herokuapp.com/medico/');
            let data = await obj.json();
            return data;
        }
        catch (error) {
            console.log(error)
        }
    }
    let formFechasMedicos = document.querySelector("#form_fechas_y_medico");
    cargarListaMedicos()

    //Carga en el input del forumlario el listado de médicos
    async function cargarListaMedicos() {
        let medicos = await getMedicos();
        for (let medico of medicos) {
            formFechasMedicos.medico.innerHTML += "<option value=" + medico.id + ">" + medico.apellido + ", " + medico.nombre + "</option>";
        }
    }

    //inicializa las fechas iniciales (toda la semana empezando desde hoy)
    formFechasMedicos.desde.value = fechaToStringForInputDate(Date.now());
    formFechasMedicos.hasta.value = fechaToStringForInputDate(sumarAFecha(Date.now(), 6));

    //Transforma una fecha a String que admite el Input Type Date
    function fechaToStringForInputDate(f) {
        let fecha = new Date(f);
        fecha.setDate(fecha.getDate());
        let mes = (fecha.getUTCMonth()+1<10?'0':'') + (fecha.getUTCMonth()+1);
        let dia = (fecha.getUTCDate()<10?'0':'') + fecha.getUTCDate();
        return fecha.getFullYear() + "-" + mes + "-" + dia;
    }
    //Transforma un valor localDateTime fecha String que admite el Input Type Date
    function sumarAFecha(f, sumarDias) {
        let fecha = new Date(f);
        fecha.setDate(fecha.getDate() + sumarDias);
        return fecha;
    }

    //Comportamiento del boton Buscar Turnos
    //Se toma el submit del formulario para que se validen las fechas automáticamente
    formFechasMedicos.addEventListener("submit", function(e) {
        e.preventDefault();
        buscarTurnos();
    })
    
    //esta funcion no deja avanzar en la aplicacion sin seleccionar medico antes
    async function buscarTurnos() {
        let id = formFechasMedicos.medico.value
        if (id == "none") {
            console.log("no se seleccionó médico")
            return; //no se selccionó médico, no hace nada
        }
        let desde = formFechasMedicos.desde.value;
        let hasta = formFechasMedicos.hasta.value;

        let turnos = await turnosDisponiblesEntreFechas(id, desde, hasta);
        
        //Se filtran los turnos por mañana o tarde según se seleccionó
        let turnosFiltrados = new Array();
        let turnoManana = formFechasMedicos.manana.checked;
        let turnoTarde = formFechasMedicos.tarde.checked;
        console.log("Mañana: " + turnoManana, "Tarde: " + turnoTarde)
        for (let turno of turnos) {
            const date = new Date(turno.fecha);
            const hora = date.getHours();
            console.log(((hora < 13) && turnoManana), 
                        (hora >= 13) && turnoTarde, 
                        (((hora < 13) && turnoManana) || ((hora >= 13) && turnoTarde)), 
                        hora)
            if (((hora < 13) && turnoManana) || ((hora >= 13) && turnoTarde)) {
                console.log(hora)
                turnosFiltrados.push(turno);
            }
        }

        //Si no hay turnos disponibles se muestra el modal de proximos turnos
        if (turnosFiltrados.length == 0) {
            document.querySelector("#rango_fechas_proximos_turnos").innerHTML = desde + " a " + hasta
            modalProximosTurnos.classList.remove("ocultar")
        } else {
            mostrarTurnos(turnosFiltrados);
            ocultarModal();
         }
    }


    const modal = document.querySelector('#modal');
    const modalContainer = document.querySelector('#modal-container');
    const modalProximosTurnos = document.querySelector('#modal-prox-turnos');

    let modalVisible = document.querySelector(".modal-visible");
    let btnVerProxTurnos = document.querySelector("#ver-proximos-turnos");
    let btnAtrasProxTurnos = document.querySelector("#atras-proximos-turnos");
    //esta funcion muestra los proximos turnos de la semana siguiente
    btnVerProxTurnos.addEventListener("click", function() {
        console.log(formFechasMedicos.desde.value);
        let s = formFechasMedicos.hasta.value.split("-");
        let fecha =  new Date();
        fecha.setDate(s[2]);
        fecha.setDate(fecha.getDate() + 1);
        fecha.setMonth(s[1] - 1);
        fecha.setFullYear(s[0]);
        console.log(fecha);
        formFechasMedicos.desde.value = fechaToStringForInputDate(fecha);
        fecha.setDate(fecha.getDate() + 6);
        formFechasMedicos.hasta.value = fechaToStringForInputDate(fecha);
        buscarTurnos();
        modalProximosTurnos.classList.add("ocultar")
    });

    //Se regresa al menu principal "Buscar Turnos"
    btnAtrasProxTurnos.addEventListener("click", function() {
        modalProximosTurnos.classList.add("ocultar")
    });

    //Se oculta el modal "Ver próximos turnos" y se muestra el del listado de turnos
    function ocultarModal() {
        modalVisible.classList.toggle("ocultar");
        modalContainer.classList.remove('modal-hidden');
        modalContainer.classList.add('modal-show');
    }


    const btnCerrar = document.querySelector('#btn-cerrar');

    //Si hace click en cerrar muestra la pantalla anterior principal
    btnCerrar.addEventListener('click', () => {
        modalContainer.classList.remove('modal-show');
        modalContainer.classList.add('modal-hidden');
        modalVisible.classList.toggle("ocultar");
    })

    //Se visualizan los turnos pasados por parámetro
    function mostrarTurnos(turnos) {
        modal.innerHTML = "";
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
                modalVisible.classList.remove("ocultar");
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
    }
})