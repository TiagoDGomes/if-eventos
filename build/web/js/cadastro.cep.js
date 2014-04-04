    var ultimoCep;

    
    function checkCEP(preffix){ 
        if (ultimoCep != $('#cep').val() ){
            if ($("#"+preffix+"cep").val().length == 8){
               // $('#cepLoading').fadeIn(); 
                $.ajax({
                    url: '/eventos/cep?cep=' + $("#"+preffix+"cep").val(),
                    type: 'GET',
                    success: function(xml) {
                        $.xmlDOM(xml).find('webservicecep').each(function(){
                            var tipo_logradouro = $(this).find('tipo_logradouro').text();
                            var logradouro = $(this).find('logradouro').text();
                            $("#"+preffix+"endereco").val(tipo_logradouro + " " + logradouro);                        
                            $("#"+preffix+"cidade").val($(this).find('cidade').text());
                            $("#"+preffix+"bairro").val($(this).find('bairro').text());
                            var uf = $(this).find('uf').text();
                            $('#'+preffix+'uf option[value="' + uf + '"]').attr({ selected : "selected" });
                            //$("#enderecamento").fadeIn();
                            //$('#cepLoading').fadeOut();
                            var resultado = $(this).find('resultado').text();
                            switch (resultado){                            
                                case '1':
                                   // document.cadastro.bairro.disabled = 1;                                
                                  //  document.cadastro.endereco.disabled = 1;
                                    break;
                                case '2':
                                  //  document.cadastro.bairro.disabled = 0;
                                  //  document.cadastro.endereco.disabled = 0;
                                    break;                                  
                            }
                        }); 
                    },
                    error: function () {
                       
                    }
                }); 
             
            } else {
               // $("#enderecamento").fadeOut(); 
            }
            ultimoCep = $('#'+preffix+'cep').val();
        } 
        
                
    }