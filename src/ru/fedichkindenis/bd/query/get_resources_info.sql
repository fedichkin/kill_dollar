SELECT
  r.name                    RES_NAME,
  r.img                     RES_IMG,
  tr.name                   RES_TYPE,
  gr.name                   RES_GROUP,
  ifnull(ur.count_res, 0)   RES_COUNT,
  ifnull(ur.hide_res, 1)    RES_HIDE,
  ifnull(ur.show_count, -1) RES_SHOW,
  lr.parent_count           RES_PARENT_CNT,
  r.img_min                 RES_IMG_MIN

FROM resources r

     JOIN type_resources tr  ON tr.id = r.type_r
     JOIN group_resources gr ON gr.id = r.group_r
LEFT JOIN usr_resources ur   ON ur.resources = r.id
                             AND ur.usr = (SELECT id FROM usr WHERE login = ?)
LEFT JOIN link_resources lr  ON lr.parent = r.id

WHERE r.id = ?