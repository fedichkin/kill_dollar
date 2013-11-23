INSERT INTO moon_2040.state_resources (game, game_date, usr, resources, count_res, hide_res, show_count)
VALUES(?, ?, select id from moon_2040.usr where person_uid = ?, ?, ?, ?, ?)