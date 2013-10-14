select *
from moon_2040.resources_statistics rs
join moon_2040.resources r on r.id = rs.resources
where rs.id = ?