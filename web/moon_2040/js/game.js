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

    var arrCount = [ 6, 25 ];

    var arrData = [ ['change_count_ppl', 'count_ppl', 'goal_ppl'],
                    ['count_free_flat', 'count_all_flat'],
                    ['sum_min_ppl', 'sum_avg_ppl', 'sum_max_ppl'],
                    ['price_min_flat', 'price_avg_flat', 'price_max_flat'],
                    ['workless_count', 'all_workless_count', 'parazit_count'],
                    ['salary_min', 'salary_avg', 'salary_max'],
                    ['count_food', 'add_food', 'del_food'],
                    ['count_oxygen', 'add_oxygen', 'del_oxygen'],
                    ['count_helium3', 'add_helium3', 'del_helium3'],
                    ['count_energy', 'add_energy', 'del_energy'],
                    ['count_ilmenite', 'add_ilmenite', 'del_ilmenite'],
                    ['count_building_materials', 'add_building_materials', 'del_building_materials'],
                    ['count_farm'],
                    ['count_greenhouse'],
                    ['count_mining_station'],
                    ['count_powerhouse'],
                    ['count_mining_complex'],
                    ['count_metallurgical_complex'],
                    ['count_construction_gang'],
                    ['buy_food', 'buy_change_food'],
                    ['buy_oxygen', 'buy_change_oxygen'],
                    ['buy_helium3', 'buy_change_helium3'],
                    ['buy_energy', 'buy_change_energy'],
                    ['buy_ilmenite', 'buy_change_ilmenite'],
                    ['buy_building_materials', 'buy_change_building_materials']
                  ];

    var arrName = [
                    ['Изменение численности за последний месяц', 'Общее число колонистов', 'Требуемое число колонистов для успешнеой колонизации'],
                    ['Свободные жилые места', 'Общее число жилых мест'],
                    ['Состояние самого бедного колониста', 'Средний достаток колонистов', 'Состояние самого богатого колониста'],
                    ['Самое доступное жилье за последний месяц', 'Средняя стоимость жилья за последний месяц', 'Самое дорогое жилье за последний месяц'],
                    ['Вынужденно безработные', 'Общее число безработных', 'Тунеядцы'],
                    ['Минимальная заработная плата за последний месяц', 'Средняя заработная плата за последний месяц', 'Максимальная заработная плата за последний месяц'],
                    ['Общие запасы еды', 'Производство еды за последний месяц', 'Потребление еды за последний месяц'],
                    ['Общие запасы кислорода', 'Производство кислорода за последний месяц', 'Потребление кислорода за последний месяц'],
                    ['Общие запасы гелия-3', 'Добыча гелия-3 за последний месяц', 'Расход гелия-3 за последний месяц'],
                    ['Общие запасы электроэнергии', 'Производство электроэнергии за последний месяц', 'Расход электроэнергии за последний месяц'],
                    ['Общие запасы минералов', 'Добыча минералов за последний месяц', 'Расход минералов за последний месяц'],
                    ['Общие запасы материалов', 'Производство материалов за последний месяц', 'Расход материалов за последний месяц'],
                    ['Фермы'],
                    ['Оранжереи'],
                    ['Гелиевые шахты'],
                    ['Электростанции'],
                    ['Минеральные шахты'],
                    ['Заводы материалов'],
                    ['Строительные бригады'],
                    ['Закупочная цена на еду в этом месяце', 'Изменение цены по сравнению с прошлым месяцем'],
                    ['Закупочная цена на кислород в этом месяце', 'Изменение цены по сравнению с прошлым месяцем'],
                    ['Закупочная цена на гелий-3 в этом месяце', 'Изменение цены по сравнению с прошлым месяцем'],
                    ['Закупочная цена на электроэнергию в этом месяце', 'Изменение цены по сравнению с прошлым месяцем'],
                    ['Закупочная цена на минералы в этом месяце', 'Изменение цены по сравнению с прошлым месяцем'],
                    ['Закупочная цена на материалы в этом месяце', 'Изменение цены по сравнению с прошлым месяцем']
                  ];

    var arrImg = [
                    'img/population-summary.png',
                    'img/city.png',
                    'img/wallet.png',
                    'img/price.png',
                    'img/chair.png',
                    'img/work.png'
                 ];

    var arrTitle = [
                    'Колонисты',
                    'Жилое пространство',
                    'Богатство населения',
                    'Стоимость проживания',
                    'Уровень безработицы',
                    'Оплата труда колонистов'
                   ];

    var arrSel = [
                    '#ppl',
                    '#flat',
                    '#summ_ppl',
                    '#price_flat',
                    '#workless',
                    '#salary',
                    '#food',
                    '#oxygen',
                    '#helium3',
                    '#energy',
                    '#ilmenite',
                    '#building_materials',
                    '#farm',
                    '#greenhouse',
                    '#mining_station',
                    '#powerhouse',
                    '#mining_complex',
                    '#metallurgical_complex',
                    '#construction_gang',
                    '#buy_food',
                    '#buy_oxygen',
                    '#buy_helium3',
                    '#buy_energy',
                    '#buy_ilmenite',
                    '#buy_building_materials'
                 ];

    $.post('/GetStatistics', {gameId: getUrlVars()['gameId']} , function(data){
        if(data.success){

            for(var i = 0;i < arrCount[0];i++){

                putStatElementColony(data, arrSel[i], arrImg[i], arrTitle[i], arrData[i], arrName[i]);
            }

            for(var i = arrCount[0];i < arrCount[1];i++){

                putStatElementIndustry(data, arrSel[i], arrData[i], arrName[i]);
            }

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

putStatElementColony = function (data, selector, imgUrl, title, arrData, arrName) {

    $(selector).empty();
    $(selector).append('<a class="tip"><img alt="" src="' + imgUrl + '" class="tip"/><span class="hint">' + title + '</span></a>');

    for(var i = 0;i < arrData.length;i++){
        $(selector).append('<a class="tip"><span class="">' + data[arrData[i]] + '</span><span class="hint">' + arrName[i] + '</span></a>');

        if(i < arrData.length - 1){
            $(selector).append(' / ');
        }
    }
}

putStatElementIndustry = function (data, selector, arrData, arrName) {

    $(selector).empty();

    for(var i = 0;i < arrData.length;i++){

        $(selector).append('<span class="tip">' + data[arrData[i]] + '<span class="hint">' + arrName[i] + '</span></span>');

        if(i < arrData.length - 1){
            $(selector).append('<br/>');
        }
    }
}