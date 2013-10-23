select g.*, ug.*
from moon_2040.game g
left join moon_2040.usr_game ug on ug.game = g.id and ug.reg_date is not null and ug.del_date < ug.reg_date