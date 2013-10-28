/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.10.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
$( document ).ready(function() {
    load_listgame();
});

load_listgame = function(){
    $.get('/GetListGames', function(data){
        if(data.success){
            for(var i = 0;i < data.games.length;i++){
                var game = data.games[i];
                var el = $('<div />',{class: 'span12'}).appendTo('<div />',{class: 'row-fluid'}).appendTo('body');
                $('<p>Игра: ' + game.name + '</p>').appendTo(el);
                $('<p>Максимальное количество игроков: ' + game.max_player + '</p>').appendTo(el);
                $('<p>Дата и время начала: ' + game.start_date + '</p>').appendTo(el);
                $('<p>Шаг игры в часах: ' + game.step + '</p>').appendTo(el);
            }
        }
    }, 'json');
}