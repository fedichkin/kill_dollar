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
                var el = $('body').add('div').addClass('row-fluid').add('div').addClass('span12');
                var game = data.games[i];
                el.add('p').text = 'sdfsd';
            }
        }
    }, 'json');
}