/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 07.10.13
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
$( document ).ready(function() {
    load_statistics();
});

load_statistics = function(){
    $.get('/GetMoonStatistics', {stat: 'moon_stat', game: 1} , function(data){
        if(data.success){
            /*var stat = $('#statistic').add('div').addClass('row-fluid');
            var stat1 = stat.add('div').addClass('span3');
            var stat2 = stat.add('div').addClass('span3');
            var stat3 = stat.add('div').addClass('span3');*/
        }
    }, 'json');
}

getUrlVars = function() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&#]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}