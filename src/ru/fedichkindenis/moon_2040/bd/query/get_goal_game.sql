select r.name resources, tf.name type_function, gg.value_func, gg.win 
from moon_2040.goal_game gg
join moon_2040.resources r on r.id = gg.resources
join moon_2040.type_function tf on tf.id = gg.type_function 
where game = ?