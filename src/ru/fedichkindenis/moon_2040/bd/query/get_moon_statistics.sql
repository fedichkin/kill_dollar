select *
from moon_2040.game_statistics gs
where gs.game = ?
having gs.game_date = max(gs.game_date)