 $(function () {

    $('.filtro').click(function () {

        id = $(this).attr('id');

        $.ajax({
            url: "filtragem",
            data: "id=" + id,
            dataType: "json",
            success: function (lista) {
                cards = $(".cards");

                cards.html("");

                for (let index = 0; index < lista.length; index++) {

                    consulta = `<div class="business-card">
        <div class="card-left  ${lista[index].situacaoConsulta}  ">
            <div class="logo-area">
                <div class="logo-icon">FVC</div>
            </div>
        </div>

        <div class="card-right">
            <div class="name-position">
                <h1 class="name">Consulta</h1>
            </div>

            <div class="contact-info">
                <div class="info-item">
                    <p>${lista[index].dataAgendada}</p>
                    <i class="fas fa-map-marker-alt"></i>
                </div>
                <div class="info-item">
                    <p>${lista[index].pet?.nome}</p>
                    <i class="fas fa-globe"></i>
                </div>
                <div class="info-item">
                    <p>${lista[index].situacaoConsulta}</p>
                    <i class="fas fa-envelope"></i>
                </div>
            </div>

            <div class="iconeAgenda">
                <i class="fas fa-calendar-alt"></i>
            </div>

             <div class="btns">
                <a href="agendar?id=${lista[index].id}">Agendar</a>
                <a href="finalizarConsulta?id=${lista[index].id}">Finalizar</a>
                <a href="desmarcar?id=${lista[index].id}" class="desmarcar">Desmarcar</a>

            </div>

        </div>
    </div>`;

         cards.append(consulta);
                }
            }
        })


    })

}
)