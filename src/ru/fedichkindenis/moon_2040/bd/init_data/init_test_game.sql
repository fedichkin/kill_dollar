insert into moon_2040.game (id, name, max_player, start_date, finish_date, step, count_ppl,
                            credit_ppl, credit_user, description, life_out_flat)
values(1, 'Тестовая игра №1', -1, '2013-10-25 00:00:00', '0000-00-00 00:00:00', '00:05:00',
	     20, 20, 50, 'Это первая тестовая игра', 3);

insert into moon_2040.goal_game (game, resources, type_function, value_func, win)
values (1, 1, 1, 100, 1), (1, 1, 1, 0, 0);

insert into moon_2040.queue_resources (game, sort_res, resources, queue) values
	(1, 1, 15, 0),
	(1, 2, 16, 0),
	(1, 3, 9, 0),
	(1, 4, 10, 0),
	(1, 5, 11, 0),
	(1, 6, 12, 0),
	(1, 7, 13, 0),
	(1, 8, 14, 0);