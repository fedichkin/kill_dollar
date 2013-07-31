SELECT
COUNT(*) CNT

FROM usr_game

WHERE
	game = ?
	AND
	usr = (SELECT id FROM usr WHERE login = ?)
	AND
	DATE_FORMAT(del_date,'%Y-%m-%d') = '0000-00-00';