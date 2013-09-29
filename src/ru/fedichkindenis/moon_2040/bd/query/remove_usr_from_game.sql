update moon_2040.usr_game ug
join (select game, usr, max(reg_date) sd from moon_2040.usr_game group by game, usr) tug on
tug.usr = ug.usr and tug.game = ug.game and tug.sd = ug.reg_date
set ug.del_date = CURRENT_TIMESTAMP
where ug.game = ? and ug.usr = ?