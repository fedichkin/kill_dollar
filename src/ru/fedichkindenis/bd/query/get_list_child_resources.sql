SELECT
  r.name                  CHILD_NAME,
  r.img_min               CHILD_IMG_MIN,
  ifnull(ur.count_res, 0) CHILD_COUNT,
  lr.child_count          CHILD_F_CNT

FROM resources r

     JOIN link_resources lr ON lr.child = r.id
LEFT JOIN usr_resources ur  ON ur.resources = r.id
                            AND ur.usr = (SELECT id FROM usr WHERE login = ?)

WHERE lr.parent = ?