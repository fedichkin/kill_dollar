SELECT
  res.id                    ID,
  res.name                  RES_NAME,
  res.img                   IMG,
  gres.name                 GR,
  tres.name                 TYP,
  ifnull(sres.count_res, 0) CNT

FROM resources res

     JOIN group_resources gres ON gres.id = res.group_r
     JOIN type_resources tres  ON tres.id = res.type_r
LEFT JOIN state_resources sres ON sres.resources = res.id
										           AND sres.game = ?
										           AND DATE_FORMAT(sres.game_date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')
                               AND sres.usr = (SELECT id FROM usr WHERE login = ?)

ORDER BY gres.name, res.name