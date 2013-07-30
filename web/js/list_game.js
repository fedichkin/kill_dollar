$( document ).ready(function() {
    load_lists_game();
    //alert(window.location.href);
});

load_lists_game = function(){
    $.get('/GetListGame', {listType: "short_lists_game"}, function(data){
        if(data.success){
            for(var i = 0;i < data.data.length;i++){
                var el = data.data[i];
                var sel, sel_child;
                var text_link;

                if(el.time_status == "curr"){
                    sel = $('#current_game');
                    text_link = "Играть";
                }
                else if(el.time_status == "future"){
                    sel = $('#future_game');
                    text_link = "Присоедениться";
                }
                else if(el.time_status == "old"){
                    sel = $('#old_game');
                    text_link = "Просмотреть результаты";
                }

                sel.append('<div class="form-actions fon_game"></div>');

                sel_child = $(sel.selector + ' > .fon_game:last');
                sel_child.append('<div class="row-fluid unit1"></div>');
                sel_child.append('<div class="row-fluid unit2"></div>');
                sel_child.append('<div class="row-fluid unit3"></div>');

                sel_child = $(sel.selector + ' > .fon_game:last > .unit1');
                sel_child.append('<div class="span9"></div>');
                sel_child.append('<div class="span3"></div>');

                sel_child = $(sel.selector + ' > .fon_game:last > .unit2');
                sel_child.append('<div class="span4"></div>');
                sel_child.append('<div class="span4"></div>');

                sel_child = $(sel.selector + ' > .fon_game:last > .unit3');
                sel_child.append('<div class="span4"></div>');
                sel_child.append('<div class="span4"></div>');
                sel_child.append('<div class="span4"></div>');

                sel_child = $(sel.selector + ' > .fon_game:last > .unit1');
                $(sel_child.selector + ' > .span9').append('<h3>' + el.game_name + '</h3>');
                $(sel_child.selector + ' > .span3').append('<h4 class="pull-right"><a href="#" class="link_game">' + text_link + '</a></h4>');

                sel_child =  $(sel.selector + ' > .fon_game:last > .unit2 > .span4');
                $(sel_child.selector + ':eq(0)').append('<h6>Текущее количество игроков: ' + el.current_usr + '</h6>');
                $(sel_child.selector + ':eq(1)').append('<h6>Максимальное количество игроков: ' + (el.max_player != -1 ? el.max_player : 'не ограниченно') + '</h6>');

                sel_child =  $(sel.selector + ' > .fon_game:last > .unit3 > .span4');
                $(sel_child.selector + ':eq(0)').append('<h6>Дата начала: ' + el.sd + '</h6>');
                $(sel_child.selector + ':eq(1)').append('<h6>Дата окончания: ' + el.fd + '</h6>');
                $(sel_child.selector + ':eq(2)').append('<h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>');
            }
        }
    }, 'json');
}