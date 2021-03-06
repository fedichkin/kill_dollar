-- --------------------------------------------------------
-- Хост:                         localhost
-- Версия сервера:               5.5.31-MariaDB - mariadb.org binary distribution
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных moon_2040
DROP DATABASE IF EXISTS `moon_2040`;
CREATE DATABASE IF NOT EXISTS `moon_2040` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `moon_2040`;


-- Дамп структуры для таблица moon_2040.game
DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование игры',
  `max_player` int(11) NOT NULL COMMENT 'Максимальное количество игроков (-1 ограничений нет)',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата начала игры',
  `finish_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Дата окончания игры',
  `step` time NOT NULL DEFAULT '24:00:00' COMMENT 'Шаг игры',
  `count_ppl` int(10) unsigned NOT NULL COMMENT 'Количество колонистов в начале игры',
  `credit_ppl` int(10) unsigned NOT NULL COMMENT 'Количество денег у колонистов в начале игры',
  `credit_user` int(10) unsigned NOT NULL COMMENT 'Количество денег у игрока в начале игры',
  `life_out_flat` int(10) unsigned NOT NULL COMMENT 'Количество ходов которые колонисты могут прожить без жилья в начале игры',
  `description` longtext COMMENT 'Описание игры',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Игры';

-- Дамп данных таблицы moon_2040.game: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` (`id`, `name`, `max_player`, `start_date`, `finish_date`, `step`, `count_ppl`, `credit_ppl`, `credit_user`, `life_out_flat`, `description`) VALUES
	(1, 'Тестовая игра №1', -1, '2013-10-25 00:00:00', '0000-00-00 00:00:00', '00:05:00', 20, 20, 50, 3, 'Это первая тестовая игра');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game_statistics
DROP TABLE IF EXISTS `game_statistics`;
CREATE TABLE IF NOT EXISTS `game_statistics` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `game_date` datetime NOT NULL COMMENT 'Дата и время игры',
  `count_ppl` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'общее количество колонистов',
  `change_count_ppl` int(10) NOT NULL DEFAULT '0' COMMENT 'изменение количества колонистов',
  `summ_max_ppl` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'сумма самого богатого колониста',
  `summ_min_ppl` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'сумма самого бедного колониста',
  `summ_avg_ppl` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'среднее количество кредитов у одного колониста',
  `workless_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'количество безработных',
  `parazit_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'количество тунеядцев',
  `flat_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'количество жилых мест',
  `flat_count_empty` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'количество свободных жилых мест',
  `price_max_flat` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'максимальная цена за жильё',
  `price_min_flat` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'минимальная цена на жильё',
  `price_avg_flat` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'средняя цена на жильё',
  `res_statistics` bigint(19) unsigned NOT NULL DEFAULT '0' COMMENT 'ссылка на статистику ресурсов',
  `salary_max` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'максимальная зарплата',
  `salary_min` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'минимальная зарплата',
  `salary_avg` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'средняя зарплата',
  PRIMARY KEY (`game`,`game_date`),
  KEY `fk$game_stat$res_stat$res_stat` (`res_statistics`),
  CONSTRAINT `fk$game_stat$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$game_stat$res_stat$res_stat` FOREIGN KEY (`res_statistics`) REFERENCES `resources_statistics` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Статистика игры по каждому ходу, генерируется перед началом нового хода';

-- Дамп данных таблицы moon_2040.game_statistics: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_statistics` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.goal_game
DROP TABLE IF EXISTS `goal_game`;
CREATE TABLE IF NOT EXISTS `goal_game` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `resources` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор ресурса',
  `type_function` bigint(19) unsigned NOT NULL COMMENT 'тип функции',
  `value_func` int(11) DEFAULT NULL COMMENT 'Значение используемое функцией',
  `win` int(10) unsigned NOT NULL COMMENT 'Условие на победу (1) или поражение (0)',
  PRIMARY KEY (`game`,`resources`,`win`),
  KEY `k$goal_game$type_function` (`type_function`),
  KEY `fk$goal_game$res$res_idx` (`resources`),
  CONSTRAINT `fk$goal_game$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$goal_game$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$goal_game&func&func` FOREIGN KEY (`type_function`) REFERENCES `type_function` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Цель игры, какие ресурсы и чему должны быть равны в случае победы и в случае поражения';

-- Дамп данных таблицы moon_2040.goal_game: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `goal_game` DISABLE KEYS */;
INSERT INTO `goal_game` (`game`, `resources`, `type_function`, `value_func`, `win`) VALUES
	(1, 1, 1, 0, 0),
	(1, 1, 1, 100, 1);
/*!40000 ALTER TABLE `goal_game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.group_resources
DROP TABLE IF EXISTS `group_resources`;
CREATE TABLE IF NOT EXISTS `group_resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование группы ресурсов',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Группы ресурсов (сырье, производящие и т.п.)';

-- Дамп данных таблицы moon_2040.group_resources: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `group_resources` DISABLE KEYS */;
INSERT INTO `group_resources` (`id`, `name`) VALUES
	(1, 'сырьё'),
	(2, 'производящие'),
	(3, 'колонисты');
/*!40000 ALTER TABLE `group_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.link_resources
DROP TABLE IF EXISTS `link_resources`;
CREATE TABLE IF NOT EXISTS `link_resources` (
  `parent` bigint(19) unsigned NOT NULL COMMENT 'Производимый ресурс',
  `parent_count` int(10) unsigned NOT NULL COMMENT 'Количество производимого ресурса',
  `child` bigint(19) unsigned NOT NULL COMMENT 'Производящий ресурс',
  `child_count` int(10) unsigned NOT NULL COMMENT 'Количество производящего ресурса',
  PRIMARY KEY (`parent`,`child`),
  KEY `fk$link_res$res$child_idx` (`child`),
  CONSTRAINT `fk$link_res$res$child` FOREIGN KEY (`child`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$link_res$res$parent` FOREIGN KEY (`parent`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Связь ресурсов в производстве других ресурсов';

-- Дамп данных таблицы moon_2040.link_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `link_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `link_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.operation_game
DROP TABLE IF EXISTS `operation_game`;
CREATE TABLE IF NOT EXISTS `operation_game` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `game_date` datetime NOT NULL COMMENT 'Дата игры',
  `usr` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор пользователя',
  `type_operation` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор типа операции',
  `object` bigint(19) unsigned DEFAULT NULL COMMENT 'Ресурс над которым производится операция (если есть)',
  `object_count` int(10) unsigned DEFAULT NULL COMMENT 'Количество ресурса над которым производится операция',
  `partner` bigint(19) unsigned DEFAULT NULL COMMENT 'Игрок с которым производится операция (если есть)',
  `object_goal` bigint(19) unsigned NOT NULL COMMENT 'Ресур являющийся целью операции',
  `object_goal_count` int(10) unsigned NOT NULL COMMENT 'Количество ресурса являющегося целью операции',
  `operation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Время свершения операции (смена статуса)',
  `status_operation` bigint(19) unsigned NOT NULL COMMENT 'Текущий статус операции',
  PRIMARY KEY (`game`,`game_date`,`usr`,`operation_time`),
  KEY `k$oper_game$type_oper$type_oper` (`type_operation`),
  KEY `k$oper_game$res$object` (`object`),
  KEY `k$oper_game$usr$patner` (`partner`),
  KEY `k$oper_game$res_object_goal` (`object_goal`),
  KEY `k$oper_game$st_oper$st_oper` (`status_operation`),
  KEY `fk$oper_game$usr$usr_idx` (`usr`),
  CONSTRAINT `fk$oper_game$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$oper_game$res$object` FOREIGN KEY (`object`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$oper_game$res$object_goal` FOREIGN KEY (`object_goal`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$oper_game$st_oper$st_oper` FOREIGN KEY (`status_operation`) REFERENCES `status_operation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$oper_game$type_oper$type_oper` FOREIGN KEY (`type_operation`) REFERENCES `type_operation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$oper_game$usr$partner` FOREIGN KEY (`partner`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$oper_game$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Операции производимые в игре';

-- Дамп данных таблицы moon_2040.operation_game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `operation_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation_game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.queue_resources
DROP TABLE IF EXISTS `queue_resources`;
CREATE TABLE IF NOT EXISTS `queue_resources` (
  `game` bigint(20) unsigned NOT NULL,
  `sort_res` int(10) unsigned NOT NULL,
  `resources` bigint(20) unsigned NOT NULL,
  `queue` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`game`,`sort_res`),
  KEY `key$queue_res$res` (`resources`),
  CONSTRAINT `fk$queue_res$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$queue_res$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Очередь ресурс ов для каждой игры, для определения последовательности раздачи ресурсов игрокам. На данный момент учитывается что раздаётся только один ресурс (не считая кредитов, они фиксированы)';

-- Дамп данных таблицы moon_2040.queue_resources: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `queue_resources` DISABLE KEYS */;
INSERT INTO `queue_resources` (`game`, `sort_res`, `resources`, `queue`) VALUES
	(1, 1, 15, 0),
	(1, 2, 16, 0),
	(1, 3, 9, 0),
	(1, 4, 10, 0),
	(1, 5, 11, 0),
	(1, 6, 12, 0),
	(1, 7, 13, 0),
	(1, 8, 14, 0);
/*!40000 ALTER TABLE `queue_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.resources
DROP TABLE IF EXISTS `resources`;
CREATE TABLE IF NOT EXISTS `resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование ресурса',
  `group_r` bigint(19) unsigned NOT NULL COMMENT 'Группа ресурса',
  `type_r` bigint(19) unsigned NOT NULL COMMENT 'Тип ресурса',
  `img` varchar(256) DEFAULT NULL COMMENT 'Путь к изображению ресурса',
  `img_min` varchar(256) DEFAULT NULL COMMENT 'Путь к изображению миниатюре ресурса',
  PRIMARY KEY (`id`),
  KEY `k$resources$group_r` (`group_r`),
  KEY `k$resources$type_r` (`type_r`),
  CONSTRAINT `fk$resources$gr_res$group_r` FOREIGN KEY (`group_r`) REFERENCES `group_resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$resources$tp_res$type_r` FOREIGN KEY (`type_r`) REFERENCES `type_resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='Ресурсы';

-- Дамп данных таблицы moon_2040.resources: ~16 rows (приблизительно)
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` (`id`, `name`, `group_r`, `type_r`, `img`, `img_min`) VALUES
	(1, 'Колонисты', 3, 1, '/img/resources/PPL.png', '/img/resources/min/PPL.png'),
	(2, 'Кредиты', 1, 1, '/img/resources/Cr.png', '/img/resources/min/Cr.png'),
	(3, 'Еда', 1, 1, '/img/resources/Food.png', '/img/resources/min/Food.png'),
	(4, 'Кислород', 1, 1, '/img/resources/O2.png', '/img/resources/min/O2.png'),
	(5, 'Гелий-3', 1, 1, '/img/resources/He3.png', '/img/resources/min/He3.png'),
	(6, 'Минералы', 1, 1, '/img/resources/Min.png', '/img/resources/min/Min.png'),
	(7, 'Производственные материалы', 1, 1, '/img/resources/PMt.png', '/img/resources/min/PMt.png'),
	(8, 'Энергия', 1, 1, '/img/resources/En.png', '/img/resources/min/En.png'),
	(9, 'Ферма', 2, 2, '/img/resources/Ferm.png', '/img/resources/min/Ferm.png'),
	(10, 'Оксинизатор', 2, 2, '/img/resources/Ox.png', '/img/resources/min/Ox.png'),
	(11, 'Электростанция', 2, 2, '/img/resources/El.png', '/img/resources/min/El.png'),
	(12, 'Добывающая станция', 2, 2, '/img/resources/Ds.png', '/img/resources/min/Ds.png'),
	(13, 'Минеральная шахта', 2, 2, '/img/resources/Msh.png', '/img/resources/min/Msh.png'),
	(14, 'Завод материалов', 2, 2, '/img/resources/PPL.png', '/img/resources/min/PPL.png'),
	(15, 'Строительная артель', 2, 2, '/img/resources/Zmt.png', '/img/resources/min/Zmt.png'),
	(16, 'Жилой комплекс', 2, 2, '/img/resources/Gil.png', '/img/resources/min/Gil.png');
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.resources_statistics
DROP TABLE IF EXISTS `resources_statistics`;
CREATE TABLE IF NOT EXISTS `resources_statistics` (
  `id` bigint(19) unsigned NOT NULL,
  `resources` bigint(19) unsigned NOT NULL COMMENT 'идентификатор ресурса',
  `count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'сумарное количество конкретного ресурса',
  `add` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'добыто ресурсов за последний ход',
  `del` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'потрачено ресурсов за последний ход',
  `price` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'цена на ресурс на Земле',
  `price_change` int(10) NOT NULL DEFAULT '0' COMMENT 'изменение цены на ресурс',
  PRIMARY KEY (`id`,`resources`),
  KEY `key$res_stat$res` (`resources`),
  CONSTRAINT `fk$res_stat$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Статистика по ресурсам за каждый ход игры, вычисляется перед началом каждого хода';

-- Дамп данных таблицы moon_2040.resources_statistics: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `resources_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `resources_statistics` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Роли';

-- Дамп данных таблицы moon_2040.role: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Дамп структуры для процедура moon_2040.start_game
DROP PROCEDURE IF EXISTS `start_game`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `start_game`(IN `_game` BIGINT)
    DETERMINISTIC
    COMMENT 'Процедура инициализации начала игры'
BEGIN
	DECLARE res BIGINT;
	DECLARE i, u INT;
	DECLARE g_date TIMESTAMP;
	DECLARE cr_ppl, cr_user, cnt_ppl INT;
	DECLARE usrs CURSOR FOR SELECT DISTINCT usr FROM moon_2040.usr_game WHERE game = _game and del_date < NOW();
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET i = 1;
	
	SELECT start_date INTO g_date FROM moon_2040.game WHERE id = _game;
	SELECT credit_ppl INTO cr_ppl FROM moon_2040.game WHERE id = _game;
	SELECT credit_user INTO cr_user FROM moon_2040.game WHERE id = _game;
	SELECT count_ppl INTO cnt_ppl FROM moon_2040.game WHERE id = _game;
	
	OPEN usrs;
	
	SET i = 0;
	
	WHILE i = 0 DO
		FETCH usrs INTO u;
		
		SELECT resources INTO res 
		FROM moon_2040.queue_resources
		WHERE game = _game AND sort_res = (
			SELECT MIN(sort_res) 
			FROM moon_2040.queue_resources 
			WHERE game = _game AND queue = (
				SELECT MIN(queue) 
				FROM moon_2040.queue_resources
				WHERE game = _game
			)
		);
		
    INSERT INTO moon_2040.state_resources (game, game_date, usr, resources, count_res, hide_res, show_count)
	  VALUES (_game, g_date, u, 2, cr_user, 1, null);	
		
		INSERT INTO moon_2040.state_resources (game, game_date, usr, resources, count_res, hide_res, show_count)
		VALUES (_game, g_date, u, res, 1, 1, null);
		
	END WHILE;
	
	CLOSE usrs;
	
	SET i = 0;
	
	WHILE i < cnt_ppl DO
	
		INSERT INTO moon_2040.state_resources_ppl (game, game_date, id_ppl, credit, pay_house, salary, parasit, parasit_step)
		VALUES (_game, g_date, i, cr_ppl, 0, 0, 0, 0);
	
		SET i = i + 1;
	
	END WHILE;
	
END//
DELIMITER ;


-- Дамп структуры для таблица moon_2040.state_resources
DROP TABLE IF EXISTS `state_resources`;
CREATE TABLE IF NOT EXISTS `state_resources` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `game_date` datetime NOT NULL COMMENT 'День игры',
  `usr` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор пользователя',
  `resources` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор ресурса',
  `count_res` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Количество ресурсов у пользователя',
  `hide_res` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'Ключ, позволяет скрывать ресурсы от других пользователей (1 - скрыто, 0 - видно)',
  `show_count` int(11) DEFAULT NULL COMMENT 'Если ресурсы видны то равно количеству ресурсов, если скрыты то это значение задаёт пользователь',
  PRIMARY KEY (`game`,`usr`,`game_date`,`resources`),
  KEY `fk$st_res$usr$usr_idx` (`usr`),
  KEY `fk$st_res$res$res_idx` (`resources`),
  CONSTRAINT `fk$st_res$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$st_res$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$st_res$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Состояние ресурсов на игровой день по игрокам';

-- Дамп данных таблицы moon_2040.state_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `state_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `state_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.state_resources_ppl
DROP TABLE IF EXISTS `state_resources_ppl`;
CREATE TABLE IF NOT EXISTS `state_resources_ppl` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `game_date` datetime NOT NULL COMMENT 'День игры',
  `id_ppl` int(10) unsigned NOT NULL COMMENT 'Коллонист',
  `credit` bigint(19) unsigned NOT NULL DEFAULT '0' COMMENT 'Кредиты',
  `pay_house` bigint(19) unsigned NOT NULL DEFAULT '0' COMMENT 'Плата за жильё',
  `salary` bigint(19) unsigned NOT NULL DEFAULT '0' COMMENT 'Зарплата',
  `parasit` int(1) unsigned NOT NULL DEFAULT '0' COMMENT 'Тунеядец (0 - нет, 1 - да)',
  `parasit_step` int(2) unsigned NOT NULL DEFAULT '0' COMMENT 'Шаг тунеядца (не меньше двух)',
  PRIMARY KEY (`game`,`game_date`,`id_ppl`),
  KEY `fk$st_res_ppl$res$res_ppl` (`id_ppl`),
  CONSTRAINT `fk$st_res_ppl$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Состояние колонистов на каждом ходе игры';

-- Дамп данных таблицы moon_2040.state_resources_ppl: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `state_resources_ppl` DISABLE KEYS */;
/*!40000 ALTER TABLE `state_resources_ppl` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.status_operation
DROP TABLE IF EXISTS `status_operation`;
CREATE TABLE IF NOT EXISTS `status_operation` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование статуса операции',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы moon_2040.status_operation: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `status_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_operation` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.type_function
DROP TABLE IF EXISTS `type_function`;
CREATE TABLE IF NOT EXISTS `type_function` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование функции',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Тип функции (больше, меньше, равно и т.п.)';

-- Дамп данных таблицы moon_2040.type_function: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `type_function` DISABLE KEYS */;
INSERT INTO `type_function` (`id`, `name`) VALUES
	(1, 'равенство');
/*!40000 ALTER TABLE `type_function` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.type_operation
DROP TABLE IF EXISTS `type_operation`;
CREATE TABLE IF NOT EXISTS `type_operation` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование операции',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Тип производимых операций';

-- Дамп данных таблицы moon_2040.type_operation: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `type_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_operation` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.type_resources
DROP TABLE IF EXISTS `type_resources`;
CREATE TABLE IF NOT EXISTS `type_resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование типа ресурсов',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Типы ресурсов (иссекаемые, не иссекаемы)';

-- Дамп данных таблицы moon_2040.type_resources: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `type_resources` DISABLE KEYS */;
INSERT INTO `type_resources` (`id`, `name`) VALUES
	(1, 'иссекаемые'),
	(2, 'не иссекаемые');
/*!40000 ALTER TABLE `type_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.usr
DROP TABLE IF EXISTS `usr`;
CREATE TABLE IF NOT EXISTS `usr` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `person_uid` varchar(32) NOT NULL,
  `email` varchar(128) NOT NULL COMMENT 'Электроная почта пользователя',
  `first_name` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk$usr$uid` (`person_uid`),
  UNIQUE KEY `uk$usr$email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Пользователи';

-- Дамп данных таблицы moon_2040.usr: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `usr` DISABLE KEYS */;
INSERT INTO `usr` (`id`, `person_uid`, `email`, `first_name`, `last_name`) VALUES
	(1, '5260171e6501377a0c000002', 'fd@fedichkindenis.ru', 'Денис', 'Федичкин');
/*!40000 ALTER TABLE `usr` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.usr_game
DROP TABLE IF EXISTS `usr_game`;
CREATE TABLE IF NOT EXISTS `usr_game` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `usr` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор пользователя',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата и время регисрации пользователя в игре',
  `del_date` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Время и дата удаления пользователя из игры',
  PRIMARY KEY (`game`,`usr`,`reg_date`),
  KEY `fk$usr_game$usr$usr_idx` (`usr`),
  CONSTRAINT `fk$usr_game$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$usr_game$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Связь пользователя и игры';

-- Дамп данных таблицы moon_2040.usr_game: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `usr_game` DISABLE KEYS */;
INSERT INTO `usr_game` (`game`, `usr`, `reg_date`, `del_date`) VALUES
	(1, 1, '2013-11-05 22:53:05', '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `usr_game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.usr_role
DROP TABLE IF EXISTS `usr_role`;
CREATE TABLE IF NOT EXISTS `usr_role` (
  `usr` bigint(19) unsigned NOT NULL,
  `role` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`usr`,`role`),
  KEY `fk$usr_role$role$role_idx` (`role`),
  CONSTRAINT `fk$usr_role$role$role` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk$usr_role$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Связь пользователей и ролей';

-- Дамп данных таблицы moon_2040.usr_role: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `usr_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `usr_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
