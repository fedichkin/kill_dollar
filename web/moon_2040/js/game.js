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

            $('#flat').empty();
            $('#flat').append('<a class="tip"><img alt="" src="img/city.png" class="tip"/><span class="hint">Жилое пространство</span></a>');
            $('#flat').append('<a class="tip">' + data.count_free_flat + '<span class="hint">Свободные жилые места</span></a>');
            $('#flat').append(' / ');
            $('#flat').append('<a class="tip">' + data.count_all_flat + '<span class="hint">Общее число жилых мест</span></a>');

            $('#summ_ppl').empty();
            $('#summ_ppl').append('<a class="tip"><img alt="" src="img/wallet.png" class="tip"/><span class="hint">Богатство населения</span></a>');
            $('#summ_ppl').append('<a class="tip"><span class="red">' + data.sum_min_ppl + '</span><span class="hint">Состояние самого бедного колониста</span></a>');
            $('#summ_ppl').append(' / ');
            $('#summ_ppl').append('<a class="tip"><span class="">' + data.sum_avg_ppl + '</span><span class="hint">Средний достаток колонистов</span></a>');
            $('#summ_ppl').append(' / ');
            $('#summ_ppl').append('<a class="tip"><span class="yellow">' + data.sum_max_ppl + '</span><span class="hint">Состояние самого богатого колониста</span></a>');

            $('#price_flat').empty();
            $('#price_flat').append('<a class="tip"><img alt="" src="img/price.png" class="tip"/><span class="hint">Стоимость проживания</span></a>');
            $('#price_flat').append('<a class="tip"><span class="green">' + data.price_min_flat + '</span><span class="hint">Самое доступное жилье за последний месяц</span></a>');
            $('#price_flat').append(' / ');
            $('#price_flat').append('<a class="tip"><span class="">' + data.price_avg_flat + '</span><span class="hint">Средняя стоимость жилья за последний месяц</span></a>');
            $('#price_flat').append(' / ');
            $('#price_flat').append('<a class="tip"><span class="yellow">' + data.price_max_flat + '</span><span class="hint">Самое дорогое жилье за последний месяц</span></a>');

            $('#workless').empty();
            $('#workless').append('<a class="tip"><img alt="" src="img/chair.png" class="tip"/><span class="hint">Уровень безработицы</span></a>');
            $('#workless').append('<a class="tip"><span class="red">' + data.workless_count + '</span><span class="hint">Вынужденно безработные</span></a>');
            $('#workless').append(' / ');
            $('#workless').append('<a class="tip"><span class="">' + data.all_workless_count + '</span><span class="hint">Общее число безработных</span></a>');
            $('#workless').append(' / ');
            $('#workless').append('<a class="tip"><span class="yellow">' + data.parazit_count + '</span><span class="hint">Тунеядцы</span></a>');

            $('#salary').empty();
            $('#salary').append('<a class="tip"><img alt="" src="img/work.png" class="tip"/><span class="hint">Оплата труда колонистов</span></a>');
            $('#salary').append('<a class="tip"><span class="red">' + data.salary_min + '</span><span class="hint">Минимальная заработная плата за последний месяц</span></a>');
            $('#salary').append(' / ');
            $('#salary').append('<a class="tip"><span class="">' + data.salary_avg + '</span><span class="hint">Средняя заработная плата за последний месяц</span></a>');
            $('#salary').append(' / ');
            $('#salary').append('<a class="tip"><span class="green">' + data.salary_max + '</span><span class="hint">Максимальная заработная плата за последний месяц</span></a>');
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