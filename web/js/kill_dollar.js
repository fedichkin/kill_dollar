$( document ).ready(function() {
    load_resources();
});

load_resources = function(){
    $.get('/GetListResources', function(data){
        if(data.success){
            var temp = '';
            for(var i = 0;i < data.data.length;i++){

                if(data.data[i].res_group != temp){
                    $('#resources').append('<h3>' + data.data[i].res_group + '</h3>');
                    temp = data.data[i].res_group;
                }

                $('#resources').append('<a href="#" class="btn btn-success btn-large btn-res" onClick="load_detail_res(' + data.data[i].res_id + ')"><img src="'
                    + data.data[i].res_img
                    + '" class="img-rounded" alt="' + data.data[i].res_name + '" />' + data.data[i].res_count + '</a>');
            }
        }
    }, 'json');
}

load_detail_res = function(res_id){
    $.get('/GetResourceInfo?id_res=' + res_id, function(data){
        if(data.success){
            $('#res_group').empty();
            $('#res_group').append(data.res_group);
            $('#res_img').empty();
            $('#res_img').append('<img src="' + data.res_img + '" class="img-rounded" />');
            $('#res_name').empty();
            $('#res_name').append(data.res_name);
            $('#res_type').empty();
            $('#res_type').append(data.res_type);
            $('#res_count').empty();
            $('#res_count').append('Количество: ' + data.res_count);
            if(data.res_hide == 1){
                $('#res_hide').empty();
                $('#res_hide').append('<button type="button" class="btn btn-primary active" onClick="click_ide_btn()">Скрыт</button>');
            }
            else if(data.res_hide == 0){
                $('#res_hide').empty();
                $('#res_hide').append('<button type="button" class="btn btn-primary active" onClick="click_ide_btn()">Видно всем</button>');
            }
            $('#res_show').empty();
            if(data.res_show == -1){
                $('#res_show').append('Видно для всех: нет данных');
            }
            else{
                $('#res_show').append('Видно для всех: ' + data.res_show);
            }
        }
    });
}

click_ide_btn = function(){
    if($('#res_hide > button').hasClass('active')){
        $('#res_hide > button').empty();
        $('#res_hide > button').append('Видно всем');
    }
    else{
        $('#res_hide > button').empty();
        $('#res_hide > button').append('Скрыт');
    }
}
