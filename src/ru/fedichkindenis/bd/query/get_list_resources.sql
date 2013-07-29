SELECT
  res.id                    ID,
  res.name                  RES_NAME,
  res.img                   IMG,
  gres.name                 GR,
  tres.name                 TYP,
  ifnull(ures.count_res, 0) CNT

FROM resources res

     JOIN group_resources gres ON gres.id = res.group_r
     JOIN type_resources tres  ON tres.id = res.type_r
LEFT JOIN usr_resources ures   ON ures.resources = res.id
                               AND ures.usr = (SELECT id FROM usr WHERE login = ?)

ORDER BY gres.name, res.name