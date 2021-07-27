function executarExemplo12jQuery( event ) {
    
    let q = prompt( "Quantidade de pessoas:" );
    
    $.ajax( "listarPessoas", {
        data: {
            quantidade: q
        },
        dataType: "json"
    }).done( ( data, textStatus ) =>{
        
        let $div = $( "#divExemplo12" );
        $div.html( "" );
        
        data.forEach( pessoa => {
            $div.append( 
                    `<div class="dadosPessoa">Pessoa:<p>Nome: ${pessoa.nome}</p>` +
                    `<p>Data de Nascimento: ${pessoa.dataNasc}</p>` +
                    `<p>Salário: R$ ${pessoa.salario}</p></div>` );
        });
        
    }).fail( ( jqXHR, textStatus, errorThrown ) => {
        alert( "Erro: " + errorThrown + "\n" +
               "Status: " + textStatus );
    });
    
}

function executarExemplo12Fetch( event ) {
    
    let n = prompt( "Calcular a tabuada de:" );
    
    let parametros = new URLSearchParams();
    parametros.append( "quantidade", n );
    
    fetch( "listarPessoas", {
        method: "POST",
        body: parametros
    }).then( response => {
        return response.json();
    }).then( data => {
        
        let $div = $( "#divExemplo12" );
        $div.html( "" );
        
        data.forEach( pessoa => {
            $div.append( 
                    `<div class="dadosPessoa">Pessoa:<p>Nome: ${pessoa.nome}</p>` +
                    `<p>Data de Nascimento: ${pessoa.dataNasc}</p>` +
                    `<p>Salário: R$ ${pessoa.salario}</p></div>` );
        });
        
    }).catch( error => {
        alert( "Erro: " + error );
    });
    
}