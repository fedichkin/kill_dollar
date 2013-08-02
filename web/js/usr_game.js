$( document ).ready(function() {
    load_resources();
});

load_resources = function(){
    $.get('/GetListResources', {game_id: getUrlVars()['game_id']} , function(data){
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
        else{

           // $(".modal").slideToggle("slow");
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
            $('#comm_check').empty();
            $('#comm_check').append('Отображение: ');
            $('#comm_check').append('<div class="btn-group" data-toggle="buttons-checkbox" id="res_hide"></div>');
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
            $('#img_f').empty();
            if(data.child.length > 0){
                for(var i = 0;i < data.child.length;i++){
                    menuForChildRes('#img_f', data.child[i].img_min, data.child[i].count_f, data.child[i].count_usr);
                    //$('#img_f').append('<img src="' + data.child[i].img_min + '" class="img-rounded" />(' + data.child[i].count_f + ')');
                    if(i !=  (data.child.length - 1)){
                        $('#img_f').append('<img src="/img/resources/plus.png" class="img-rounded" />');
                    }
                }
                $('#img_f').append('<img src="/img/resources/equally.png" class="img-rounded" />');
                $('#img_f').append('<img src="' + data.res_img_min + '" class="img-rounded" />(' + data.res_parent_cnt + ')');
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

getUrlVars = function() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&#]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

menuForChildRes = function(selector, img, countF, count){

    idChild = (new Date()).getTime();

    $(selector).append('<div class="btn-group" id="' + idChild + '"></div>');

    $('#' + idChild).append('<a class="btn dropdown-toggle" data-toggle="dropdown" href="#"><img src="' +
        img + '" class="img-rounded" />(' + countF + ') ' + count + '<span class="caret"></span></a>');

    if(count >= countF){
        $('#' + idChild + ' > a').addClass('btn-success');
    }
    else if(count == 0){
        $('#' + idChild + ' > a').addClass('btn-danger');
    }
    else{
        $('#' + idChild + ' > a').addClass('btn-warning');
    }

    $('#' + idChild).append('<ul class="dropdown-menu"></ul>');

    $('#' + idChild + ' > ul').append('<li><a href="#">Попросить в другой стране</a></li>');
    $('#' + idChild + ' > ul').append('<li><a href="#">Произвести</a></li>');
}
