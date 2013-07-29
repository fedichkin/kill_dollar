$( document ).ready(function() {
    load_lists_game();
});

load_lists_game = function(){
    $.get('/GetListGame', {listType: "short_lists_game"}, function(data){
        if(data.success){
            for(var i = 0;i < data.data.length;i++){

            }
        }
    }, 'json');
}