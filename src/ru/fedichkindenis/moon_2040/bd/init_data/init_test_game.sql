insert into moon_2040.game (id, name, max_player, start_date, finish_date, step, count_ppl,
                            credit_ppl, credit_user, description)
values(1, 'Тестовая игра №1', -1, '2013-10-25 00:00:00', '0000-00-00 00:00:00', '00:05:00',
	     20, 20, 50, 'Это первая тестовая игра');

insert into moon_2040.goal_game (game, resources, type_function, value_func, win)
values (1, 1, 1, 100, 1), (1, 1, 1, 0, 0);