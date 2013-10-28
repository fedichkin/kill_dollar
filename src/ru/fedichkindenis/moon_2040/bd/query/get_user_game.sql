select u.person_uid
from moon_2040.usr_game ug
join moon_2040.usr u on u.id = ug.usr
where ug.game = ? and del_date = '0000-00-00 00:00:00'