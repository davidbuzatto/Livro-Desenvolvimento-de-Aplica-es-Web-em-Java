function cancelarVenda( event, cp ) {
          
    if ( confirm( "Deseja mesmo cancelar essa venda?" ) ) {

        let id = $( event.target ).data( "id" );

        let parametros = new URLSearchParams();
        parametros.append( "acao", "cancelar" );
        parametros.append( "id", id );

        fetch( `${cp}/processaVendas`, {
            method: "POST",
            body: parametros
        }).then( response => {
            return response.json();
        }).then( data => {

            if ( data.status ) {
                $( event.target ).parent().html( "Cancelada" );
            } else {
                alert( "Ocorreu um erro na sua requisição!" );
            }

        }).catch( error => {
            alert( "Erro: " + error );
        });
    
        // jQuery
        /*$.ajax( `${cp}/processaVendas`, {
            data: {
                acao: "cancelar",
                id: id
            },
            dataType: "json"
        }).done( ( data, textStatus ) =>{

            if ( data.status ) {
                $( event.target ).parent().html( "Cancelada" );
            } else {
                alert( "Ocorreu um erro na sua requisição!" );
            }

        }).fail( ( jqXHR, textStatus, errorThrown ) => {
            alert( "Erro: " + errorThrown + "\n" +
                   "Status: " + textStatus );
        });*/

    }

}