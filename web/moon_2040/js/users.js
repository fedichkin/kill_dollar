/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 18.10.13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
$( document ).ready(function() {
    load_infouser();
});

load_infouser = function(){
    $.get('/GetInfoUser', function(data){
        if(data.success){
            $("#us img").attr("src", "http://test.bizcontacts.net/" + data.person_uid + "/picture");
        }
    }, 'json');
 }