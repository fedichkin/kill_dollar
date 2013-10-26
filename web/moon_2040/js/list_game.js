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

        }
    }, 'json');
}