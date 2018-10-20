$("#categoriaUno").change(function(){
            $.ajax({
                type: 'POST',
                url: '/rest/incidencia/getCategoriasDos',
                data:  {id:$('#categoriaUno').val()},
                success: function(){
                    $.each(response, function(index){
                    $('#categoriaDos').append('<option>',{
                        value: response[index].valueYouWant,
                        text: response[index].valueYouWant
                    });
                });
            });
        });