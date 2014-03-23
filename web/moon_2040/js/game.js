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
    $.post('/GetStatistics', {gameId: getUrlVars()['gameId']} , function(data){
        if(data.success){
            /*var stat = $('#statistic').add('div').addClass('row-fluid');
            var stat1 = stat.add('div').addClass('span3');
            var stat2 = stat.add('div').addClass('span3');
            var stat3 = stat.add('div').addClass('span3');*/
            $('#ppl').empty();
            $('#ppl').append('<a class="tip"><img alt="Колонисты" src="img/population-summary.png" class="tip"/><span class="hint">Колонисты</span></a>');
            $('#ppl').append('<a class="tip">' + data.change_count_ppl + '<span class="hint">Изменение численности за последний месяц</span></a>');
            $('#ppl').append(' / ');
            $('#ppl').append('<a class="tip">' + data.count_ppl + '<span class="hint">Общее число колонистов</span></a>');
            $('#ppl').append(' / ');
            $('#ppl').append('<a class="tip">' + data.goal_ppl + '<span class="hint">Требуемое число колонистов для успешнеой колонизации</span></a>');
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