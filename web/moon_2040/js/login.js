/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 16.10.13
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
$( document ).ready(function() {
    load_congig();
});

load_congig = function(){
    $.get('/GetConfig', {file: 'bizcontacts.xml'} , function(data){
        if(data.success){
            $('#biz-moon').attr('data-redirect', data.redirect_page);
        }
    }, 'json');
}