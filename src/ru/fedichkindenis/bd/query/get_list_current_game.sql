SELECT
  g.id          ID,
  g.name        NAME_GAME,
  g.max_player  MAX_PLAYER,
  count(ug.usr) CURRENT_USR,
  g.start_date  SD,
  g.finish_date FD,
  'curr'        TIME_STATUS

FROM game g

LEFT JOIN usr_game ug on ug.game = g.id

WHERE now() BETWEEN g.start_date AND g.finish_date

GROUP BY g.id, g.name, g.max_player, g.start_date, g.finish_date

ORDER BY g.start_date