/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.10.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
$( document ).ready(function() {
    $('#my').click(function (e){load_listgame('my'); $(this).tab('show');})
    $('#curr').click(function (e){load_listgame('curr'); $(this).tab('show');})
    $('#new').click(function (e){load_listgame('new'); $(this).tab('show');})
    $('#old').click(function (e){load_listgame('old'); $(this).tab('show');})
});

load_listgame = function(type){
    $.get('/GetListGames', {type: type}, function(data){
        if(data.success){
            if($('#list-g').length){
                $('#list-g').remove();
            }
            var cont = $('<div />', {id: 'list-g'}).appendTo('body');
            for(var i = 0;i < data.games.length;i++){
                var game = data.games[i];
                var el = $('<div />',{class: 'span12'}).appendTo('<div />',{class: 'row-fluid'}).appendTo(cont);
                $('<p>Игра: ' + game.name + '</p>').appendTo(el);
                $('<p>Максимальное количество игроков: ' + game.max_player + '</p>').appendTo(el);
                $('<p>Дата и время начала: ' + game.start_date + '</p>').appendTo(el);
                $('<p>Шаг игры в часах: ' + game.step + '</p>').appendTo(el);
            }
        }
    }, 'json');
}