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


-- Дамп структуры для таблица moon_2040.function
DROP TABLE IF EXISTS `function`;
CREATE TABLE IF NOT EXISTS `function` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `is_if` bit(1) NOT NULL,
  `type_if` int(10) unsigned DEFAULT NULL,
  `el_function` int(10) unsigned DEFAULT NULL,
  `operand` int(10) unsigned DEFAULT NULL,
  `const_operand` decimal(10,2) unsigned DEFAULT NULL,
  `func_operand` bigint(19) unsigned DEFAULT NULL,
  `next_step` bigint(19) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk$func$type_if$type_if` (`type_if`),
  KEY `fk$func$el_func$el_func` (`el_function`),
  KEY `fk$func$operand$operand` (`operand`),
  KEY `fk$func$func$next_step` (`next_step`),
  KEY `fk#func$func$operand` (`func_operand`),
  CONSTRAINT `fk#func$func$operand` FOREIGN KEY (`func_operand`) REFERENCES `function` (`id`),
  CONSTRAINT `fk$func$func$next_step` FOREIGN KEY (`next_step`) REFERENCES `function` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='Функция, можест представлять как формулу так и условие';

-- Дамп данных таблицы moon_2040.function: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
INSERT INTO `function` (`id`, `is_if`, `type_if`, `el_function`, `operand`, `const_operand`, `func_operand`, `next_step`) VALUES
	(1, b'00000000', NULL, NULL, 5, NULL, NULL, NULL),
	(2, b'00000000', NULL, 0, NULL, NULL, NULL, 1),
	(3, b'00000000', NULL, NULL, NULL, 1.00, NULL, 2),
	(4, b'00000000', NULL, 0, NULL, NULL, NULL, 3),
	(5, b'00000000', NULL, NULL, NULL, 0.40, NULL, 4),
	(6, b'00000000', NULL, 2, NULL, NULL, NULL, 5),
	(7, b'00000000', NULL, NULL, 5, NULL, NULL, 6),
	(8, b'00000000', NULL, NULL, 3, NULL, NULL, NULL),
	(9, b'00000000', NULL, 2, NULL, NULL, NULL, 8),
	(10, b'00000000', NULL, 5, NULL, NULL, NULL, 9),
	(11, b'00000000', NULL, NULL, 4, NULL, NULL, 10),
	(12, b'00000000', NULL, 3, NULL, NULL, NULL, 11),
	(13, b'00000000', NULL, NULL, 1, NULL, NULL, 12),
	(14, b'00000000', NULL, 1, NULL, NULL, NULL, 13),
	(15, b'00000000', NULL, NULL, 7, NULL, NULL, 14),
	(16, b'00000000', NULL, 4, NULL, NULL, NULL, 15),
	(17, b'00000000', NULL, 1, NULL, NULL, NULL, 16),
	(18, b'00000000', NULL, NULL, 7, NULL, NULL, 17),
	(19, b'00000000', NULL, 5, NULL, NULL, NULL, NULL),
	(20, b'00000000', NULL, NULL, NULL, NULL, 18, 19),
	(21, b'00000000', NULL, 3, NULL, NULL, NULL, 20),
	(22, b'00000000', NULL, NULL, 1, NULL, NULL, 21),
	(23, b'00000000', NULL, 4, NULL, NULL, NULL, 22),
	(24, b'00000000', NULL, 2, NULL, NULL, NULL, 23),
	(25, b'00000000', NULL, NULL, 0, NULL, NULL, 24),
	(26, b'10000000', 5, NULL, NULL, 2000.00, NULL, NULL),
	(27, b'10000000', 2, NULL, NULL, 0.00, NULL, NULL);
/*!40000 ALTER TABLE `function` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game
DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT 'Наименование игры',
  `max_player` int(11) NOT NULL COMMENT 'Максимальное количество игроков (-1 ограничений нет)',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата начала игры',
  `finish_date` timestamp NULL DEFAULT NULL COMMENT 'Дата окончания игры',
  `step` time NOT NULL DEFAULT '24:00:00' COMMENT 'Шаг игры',
  `count_ppl` int(10) unsigned NOT NULL COMMENT 'Количество колонистов в начале игры',
  `credit_ppl` int(10) unsigned NOT NULL COMMENT 'Количество денег у колонистов в начале игры',
  `credit_user` int(10) unsigned NOT NULL COMMENT 'Количество денег у игрока в начале игры',
  `life_out_flat` int(10) unsigned NOT NULL COMMENT 'Количество ходов которые колонисты могут прожить без жилья в начале игры',
  `description` longtext COMMENT 'Описание игры',
  `status` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Игры';

-- Дамп данных таблицы moon_2040.game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` (`id`, `name`, `max_player`, `start_date`, `finish_date`, `step`, `count_ppl`, `credit_ppl`, `credit_user`, `life_out_flat`, `description`, `status`) VALUES
	(1, 'Тестовая игра №1', 50, '2014-03-24 21:55:12', NULL, '03:00:00', 100, 20, 500, 5, 'Игра формируется через сервлет', 1);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game_day
DROP TABLE IF EXISTS `game_day`;
CREATE TABLE IF NOT EXISTS `game_day` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `number_day` int(10) unsigned NOT NULL,
  `svd` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fvd` timestamp NULL DEFAULT NULL,
  `game` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='день игры';

-- Дамп данных таблицы moon_2040.game_day: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_day` DISABLE KEYS */;
INSERT INTO `game_day` (`id`, `number_day`, `svd`, `fvd`, `game`) VALUES
	(2, 1, '2014-03-24 21:55:10', NULL, 1);
/*!40000 ALTER TABLE `game_day` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game_functions
DROP TABLE IF EXISTS `game_functions`;
CREATE TABLE IF NOT EXISTS `game_functions` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `name_func` int(10) unsigned NOT NULL,
  `function` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk$game_func$game$game` (`game`),
  KEY `fk$game_func$func$func` (`function`),
  CONSTRAINT `fk$game_func$func$func` FOREIGN KEY (`function`) REFERENCES `function` (`id`),
  CONSTRAINT `fk$game_func$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Функции используемые в игре для расчёта';

-- Дамп данных таблицы moon_2040.game_functions: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_functions` DISABLE KEYS */;
INSERT INTO `game_functions` (`id`, `game`, `name_func`, `function`) VALUES
	(1, 1, 0, 7),
	(2, 1, 1, 25),
	(3, 1, 2, 18);
/*!40000 ALTER TABLE `game_functions` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game_link_resources
DROP TABLE IF EXISTS `game_link_resources`;
CREATE TABLE IF NOT EXISTS `game_link_resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `resources` bigint(19) unsigned NOT NULL,
  `count_up` int(10) unsigned DEFAULT NULL,
  `count_down` int(10) unsigned DEFAULT NULL,
  `link_resources` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk$game_lr$game$game` (`game`),
  KEY `fk$game_lr$res$res` (`resources`),
  KEY `fk$game_lr$lr$lr` (`link_resources`),
  CONSTRAINT `fk$game_lr$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$game_lr$lr$lr` FOREIGN KEY (`link_resources`) REFERENCES `link_resources` (`id`),
  CONSTRAINT `fk$game_lr$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='Связь игры и формул для ресурсов';

-- Дамп данных таблицы moon_2040.game_link_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_link_resources` DISABLE KEYS */;
INSERT INTO `game_link_resources` (`id`, `game`, `resources`, `count_up`, `count_down`, `link_resources`) VALUES
	(1, 1, 3, 8, 12, 4),
	(2, 1, 4, 8, 12, 8),
	(3, 1, 8, 8, 12, 12),
	(4, 1, 5, 4, 6, 16),
	(5, 1, 7, 1, 1, 25),
	(6, 1, 7, 2, 2, 30),
	(7, 1, 7, 3, 3, 35),
	(8, 1, 7, 4, 4, 40),
	(9, 1, 7, 5, 5, 45),
	(10, 1, 9, 1, 1, 50),
	(11, 1, 10, 1, 1, 50),
	(12, 1, 11, 1, 1, 50),
	(13, 1, 12, 1, 1, 50),
	(14, 1, 13, 1, 1, 50),
	(15, 1, 14, 1, 1, 50),
	(16, 1, 15, 1, 1, 50),
	(17, 1, 16, 1, 1, 50),
	(18, 1, 17, 10, 10, 52),
	(19, 1, 2, NULL, NULL, 56);
/*!40000 ALTER TABLE `game_link_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game_res_stat
DROP TABLE IF EXISTS `game_res_stat`;
CREATE TABLE IF NOT EXISTS `game_res_stat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `game_stat` bigint(20) unsigned NOT NULL DEFAULT '0',
  `res_stat` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk$game_res_stat$game_stat` (`game_stat`),
  KEY `fk$game_res_stat$res_stat` (`res_stat`),
  CONSTRAINT `fk$game_res_stat$game_stat` FOREIGN KEY (`game_stat`) REFERENCES `game_statistics` (`id`),
  CONSTRAINT `fk$game_res_stat$res_stat` FOREIGN KEY (`res_stat`) REFERENCES `resources_statistics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='Связь статистики ресурсов с статистикой игры';

-- Дамп данных таблицы moon_2040.game_res_stat: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_res_stat` DISABLE KEYS */;
INSERT INTO `game_res_stat` (`id`, `game_stat`, `res_stat`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 1, 4),
	(5, 1, 5),
	(6, 1, 6),
	(7, 1, 7),
	(8, 1, 8),
	(9, 1, 9),
	(10, 1, 10),
	(11, 1, 11),
	(12, 1, 12),
	(13, 1, 13),
	(14, 1, 14),
	(15, 1, 15),
	(16, 1, 16),
	(17, 1, 17);
/*!40000 ALTER TABLE `game_res_stat` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.game_statistics
DROP TABLE IF EXISTS `game_statistics`;
CREATE TABLE IF NOT EXISTS `game_statistics` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `game_date` bigint(19) unsigned NOT NULL COMMENT 'Дата и время игры',
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
  `salary_max` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'максимальная зарплата',
  `salary_min` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'минимальная зарплата',
  `salary_avg` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'средняя зарплата',
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk$game_stat` (`game`,`game_date`),
  KEY `fk$game_stat$sd$sd` (`game_date`),
  CONSTRAINT `fk$game_stat$sd$sd` FOREIGN KEY (`game_date`) REFERENCES `game_day` (`id`),
  CONSTRAINT `fk$game_stat$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Статистика игры по каждому ходу, генерируется перед началом нового хода';

-- Дамп данных таблицы moon_2040.game_statistics: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_statistics` DISABLE KEYS */;
INSERT INTO `game_statistics` (`game`, `game_date`, `count_ppl`, `change_count_ppl`, `summ_max_ppl`, `summ_min_ppl`, `summ_avg_ppl`, `workless_count`, `parazit_count`, `flat_count`, `flat_count_empty`, `price_max_flat`, `price_min_flat`, `price_avg_flat`, `salary_max`, `salary_min`, `salary_avg`, `id`) VALUES
	(1, 2, 100, 0, 20, 20, 20, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
/*!40000 ALTER TABLE `game_statistics` ENABLE KEYS */;


-- Дамп структуры для функция moon_2040.get_res_queue
DROP FUNCTION IF EXISTS `get_res_queue`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `get_res_queue`(`_game` BIGINT) RETURNS bigint(20)
    COMMENT 'Распределяет ресурсы по пользователям при старте игры'
BEGIN
	DECLARE res BIGINT;
	
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
	
	UPDATE moon_2040.queue_resources SET queue = queue + 1
	WHERE game = _game and resources = res;
	
	RETURN res;
END//
DELIMITER ;


-- Дамп структуры для таблица moon_2040.goal_game
DROP TABLE IF EXISTS `goal_game`;
CREATE TABLE IF NOT EXISTS `goal_game` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `resources` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор ресурса',
  `function` bigint(19) unsigned NOT NULL COMMENT 'тип функции',
  `win` bit(1) NOT NULL COMMENT 'Условие на победу (1) или поражение (0)',
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk$goal_game` (`game`,`resources`,`win`),
  KEY `fk$goal_game$res$res_idx` (`resources`),
  CONSTRAINT `fk$goal_game$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$goal_game$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Цель игры, какие ресурсы и чему должны быть равны в случае победы и в случае поражения';

-- Дамп данных таблицы moon_2040.goal_game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `goal_game` DISABLE KEYS */;
INSERT INTO `goal_game` (`game`, `resources`, `function`, `win`, `id`) VALUES
	(1, 1, 26, b'10000000', 1),
	(1, 1, 27, b'00000000', 2);
/*!40000 ALTER TABLE `goal_game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.init_market_earth
DROP TABLE IF EXISTS `init_market_earth`;
CREATE TABLE IF NOT EXISTS `init_market_earth` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `resources` bigint(19) unsigned NOT NULL,
  `start_cost` int(10) unsigned NOT NULL COMMENT 'стартовая цена',
  `const_value` int(10) unsigned NOT NULL COMMENT 'константа количества',
  `start_value` int(10) unsigned NOT NULL COMMENT 'стартовое количество',
  `const_consum` decimal(4,2) unsigned NOT NULL COMMENT 'константа потребления',
  `multi_price_incr` int(10) unsigned NOT NULL COMMENT 'мультипликатор роста цен',
  PRIMARY KEY (`id`),
  KEY `fk$imarket$game$game` (`game`),
  KEY `fk$imarket$res$res` (`resources`),
  CONSTRAINT `fk$imarket$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$imarket$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Данные для инициализации рынка Земли';

-- Дамп данных таблицы moon_2040.init_market_earth: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `init_market_earth` DISABLE KEYS */;
INSERT INTO `init_market_earth` (`id`, `game`, `resources`, `start_cost`, `const_value`, `start_value`, `const_consum`, `multi_price_incr`) VALUES
	(1, 1, 3, 20, 1000, 3000, 0.20, 3),
	(2, 1, 4, 20, 1000, 7000, 0.15, 3),
	(3, 1, 8, 20, 500, 2500, 0.18, 5),
	(4, 1, 5, 20, 300, 100, 0.05, 5),
	(5, 1, 6, 20, 300, 100, 0.05, 4),
	(6, 1, 7, 20, 750, 1200, 0.15, 4);
/*!40000 ALTER TABLE `init_market_earth` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.link_resources
DROP TABLE IF EXISTS `link_resources`;
CREATE TABLE IF NOT EXISTS `link_resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `resources` bigint(19) unsigned NOT NULL COMMENT 'Производимый ресурс',
  `count_res` int(10) unsigned DEFAULT NULL COMMENT 'Количество производимого ресурса',
  `next` bigint(19) unsigned DEFAULT NULL COMMENT 'Производящий ресурс',
  PRIMARY KEY (`id`),
  KEY `fk$link_res$res$res` (`resources`),
  KEY `fk$link_res$link_res$next` (`next`),
  CONSTRAINT `fk$link_res$link_res$next` FOREIGN KEY (`next`) REFERENCES `link_resources` (`id`),
  CONSTRAINT `fk$link_res$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='Связь ресурсов в производстве других ресурсов';

-- Дамп данных таблицы moon_2040.link_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `link_resources` DISABLE KEYS */;
INSERT INTO `link_resources` (`id`, `resources`, `count_res`, `next`) VALUES
	(1, 9, 1, NULL),
	(2, 8, 1, 1),
	(3, 1, 1, 2),
	(4, 2, NULL, 3),
	(5, 10, 1, NULL),
	(6, 8, 1, 5),
	(7, 1, 1, 6),
	(8, 2, NULL, 7),
	(9, 11, 1, NULL),
	(10, 5, 2, 9),
	(11, 1, 1, 10),
	(12, 2, NULL, 11),
	(13, 12, 1, NULL),
	(14, 8, 1, 13),
	(15, 1, 2, 14),
	(16, 2, NULL, 15),
	(17, 13, 1, NULL),
	(18, 8, 2, 17),
	(19, 1, 2, 18),
	(20, 2, NULL, 19),
	(21, 14, 1, NULL),
	(22, 8, 1, 21),
	(23, 6, 1, 22),
	(24, 1, 2, 23),
	(25, 2, NULL, 24),
	(26, 14, 1, NULL),
	(27, 8, 1, 26),
	(28, 6, 2, 27),
	(29, 1, 2, 28),
	(30, 2, NULL, 29),
	(31, 14, 1, NULL),
	(32, 8, 1, 31),
	(33, 6, 3, 32),
	(34, 1, 2, 33),
	(35, 2, NULL, 34),
	(36, 14, 1, NULL),
	(37, 8, 1, 36),
	(38, 6, 4, 37),
	(39, 1, 2, 38),
	(40, 2, NULL, 39),
	(41, 14, 1, NULL),
	(42, 8, 1, 41),
	(43, 6, 5, 42),
	(44, 1, 2, 43),
	(45, 2, NULL, 44),
	(46, 15, 1, NULL),
	(47, 8, 1, 46),
	(48, 7, 2, 47),
	(49, 1, 2, 48),
	(50, 2, NULL, 49),
	(51, 16, 1, NULL),
	(52, 8, 1, 51),
	(53, 17, 1, NULL),
	(54, 4, 1, 53),
	(55, 3, 1, 54),
	(56, 1, 1, 55);
/*!40000 ALTER TABLE `link_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.market_earth
DROP TABLE IF EXISTS `market_earth`;
CREATE TABLE IF NOT EXISTS `market_earth` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `game_date` bigint(19) unsigned NOT NULL,
  `resources` bigint(19) unsigned NOT NULL,
  `sale_cost` int(10) unsigned NOT NULL,
  `val` int(10) unsigned NOT NULL,
  `buy_cost` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk$market$game$game` (`game`),
  KEY `fk$market$res$res` (`resources`),
  KEY `fk$market$sd$sd` (`game_date`),
  CONSTRAINT `fk$market$sd$sd` FOREIGN KEY (`game_date`) REFERENCES `game_day` (`id`),
  CONSTRAINT `fk$market$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$market$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Рынок Земли';

-- Дамп данных таблицы moon_2040.market_earth: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `market_earth` DISABLE KEYS */;
INSERT INTO `market_earth` (`id`, `game`, `game_date`, `resources`, `sale_cost`, `val`, `buy_cost`) VALUES
	(1, 1, 2, 3, 20, 3000, 29),
	(2, 1, 2, 4, 20, 7000, 29),
	(3, 1, 2, 8, 20, 2500, 29),
	(4, 1, 2, 5, 20, 100, 29),
	(5, 1, 2, 6, 20, 100, 29),
	(6, 1, 2, 7, 20, 1200, 29);
/*!40000 ALTER TABLE `market_earth` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.operation_game
DROP TABLE IF EXISTS `operation_game`;
CREATE TABLE IF NOT EXISTS `operation_game` (
  `id` bigint(19) unsigned NOT NULL,
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `game_date` bigint(19) unsigned NOT NULL COMMENT 'Дата игры',
  `usr` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор пользователя',
  `type_operation` int(10) unsigned NOT NULL COMMENT 'Идентификатор типа операции',
  `object` bigint(19) unsigned DEFAULT NULL COMMENT 'Ресурс над которым производится операция (если есть)',
  `object_count` int(10) unsigned DEFAULT NULL COMMENT 'Количество ресурса над которым производится операция',
  `partner` bigint(19) unsigned DEFAULT NULL COMMENT 'Игрок с которым производится операция (если есть)',
  `object_goal` bigint(19) unsigned NOT NULL COMMENT 'Ресур являющийся целью операции',
  `object_goal_count` int(10) unsigned NOT NULL COMMENT 'Количество ресурса являющегося целью операции',
  `operation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Время свершения операции (смена статуса)',
  `status_operation` int(10) unsigned NOT NULL COMMENT 'Текущий статус операции',
  PRIMARY KEY (`id`),
  KEY `fk$oper_game$game$game` (`game`),
  KEY `fk$oper_game$res$object` (`object`),
  KEY `fk$oper_game$res$object_goal` (`object_goal`),
  KEY `fk$oper_game$st_oper$st_oper` (`status_operation`),
  KEY `fk$oper_game$type_oper$type_oper` (`type_operation`),
  KEY `fk$oper_game$usr$partner` (`partner`),
  KEY `fk$oper_game$usr$usr` (`usr`),
  KEY `fk$oper_game$sd$sd` (`game_date`),
  CONSTRAINT `fk$oper_game$sd$sd` FOREIGN KEY (`game_date`) REFERENCES `game_day` (`id`),
  CONSTRAINT `fk$oper_game$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$oper_game$res$object` FOREIGN KEY (`object`) REFERENCES `resources` (`id`),
  CONSTRAINT `fk$oper_game$res$object_goal` FOREIGN KEY (`object_goal`) REFERENCES `resources` (`id`),
  CONSTRAINT `fk$oper_game$usr$partner` FOREIGN KEY (`partner`) REFERENCES `usr` (`id`),
  CONSTRAINT `fk$oper_game$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Операции производимые в игре';

-- Дамп данных таблицы moon_2040.operation_game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `operation_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation_game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.ppl
DROP TABLE IF EXISTS `ppl`;
CREATE TABLE IF NOT EXISTS `ppl` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор колониста',
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `days_capsule` int(10) unsigned NOT NULL COMMENT 'количество дней проведёных в капсуле',
  `stat` bit(1) NOT NULL COMMENT 'статус (0 - в игре, 1 - умер, 2 - покинул Луну)',
  `add_date` bigint(19) unsigned NOT NULL COMMENT 'дата прилёта на Луну (день игры)',
  `del_date` bigint(19) unsigned DEFAULT NULL COMMENT 'дата смерти или покидания Луны (день игры)',
  PRIMARY KEY (`id`),
  KEY `fk$state_ppl$game$game` (`game`),
  KEY `fk$state_ppl$sd$add` (`add_date`),
  KEY `fk$state_ppl$sd$del` (`del_date`),
  CONSTRAINT `fk$state_ppl$sd$del` FOREIGN KEY (`del_date`) REFERENCES `game_day` (`id`),
  CONSTRAINT `fk$state_ppl$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$state_ppl$sd$add` FOREIGN KEY (`add_date`) REFERENCES `game_day` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='Общее состояние каждого колониста';

-- Дамп данных таблицы moon_2040.ppl: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `ppl` DISABLE KEYS */;
INSERT INTO `ppl` (`id`, `game`, `days_capsule`, `stat`, `add_date`, `del_date`) VALUES
	(1, 1, 1, b'00000000', 2, NULL),
	(2, 1, 1, b'00000000', 2, NULL),
	(3, 1, 1, b'00000000', 2, NULL),
	(4, 1, 1, b'00000000', 2, NULL),
	(5, 1, 1, b'00000000', 2, NULL),
	(6, 1, 1, b'00000000', 2, NULL),
	(7, 1, 1, b'00000000', 2, NULL),
	(8, 1, 1, b'00000000', 2, NULL),
	(9, 1, 1, b'00000000', 2, NULL),
	(10, 1, 1, b'00000000', 2, NULL),
	(11, 1, 1, b'00000000', 2, NULL),
	(12, 1, 1, b'00000000', 2, NULL),
	(13, 1, 1, b'00000000', 2, NULL),
	(14, 1, 1, b'00000000', 2, NULL),
	(15, 1, 1, b'00000000', 2, NULL),
	(16, 1, 1, b'00000000', 2, NULL),
	(17, 1, 1, b'00000000', 2, NULL),
	(18, 1, 1, b'00000000', 2, NULL),
	(19, 1, 1, b'00000000', 2, NULL),
	(20, 1, 1, b'00000000', 2, NULL),
	(21, 1, 1, b'00000000', 2, NULL),
	(22, 1, 1, b'00000000', 2, NULL),
	(23, 1, 1, b'00000000', 2, NULL),
	(24, 1, 1, b'00000000', 2, NULL),
	(25, 1, 1, b'00000000', 2, NULL),
	(26, 1, 1, b'00000000', 2, NULL),
	(27, 1, 1, b'00000000', 2, NULL),
	(28, 1, 1, b'00000000', 2, NULL),
	(29, 1, 1, b'00000000', 2, NULL),
	(30, 1, 1, b'00000000', 2, NULL),
	(31, 1, 1, b'00000000', 2, NULL),
	(32, 1, 1, b'00000000', 2, NULL),
	(33, 1, 1, b'00000000', 2, NULL),
	(34, 1, 1, b'00000000', 2, NULL),
	(35, 1, 1, b'00000000', 2, NULL),
	(36, 1, 1, b'00000000', 2, NULL),
	(37, 1, 1, b'00000000', 2, NULL),
	(38, 1, 1, b'00000000', 2, NULL),
	(39, 1, 1, b'00000000', 2, NULL),
	(40, 1, 1, b'00000000', 2, NULL),
	(41, 1, 1, b'00000000', 2, NULL),
	(42, 1, 1, b'00000000', 2, NULL),
	(43, 1, 1, b'00000000', 2, NULL),
	(44, 1, 1, b'00000000', 2, NULL),
	(45, 1, 1, b'00000000', 2, NULL),
	(46, 1, 1, b'00000000', 2, NULL),
	(47, 1, 1, b'00000000', 2, NULL),
	(48, 1, 1, b'00000000', 2, NULL),
	(49, 1, 1, b'00000000', 2, NULL),
	(50, 1, 1, b'00000000', 2, NULL),
	(51, 1, 1, b'00000000', 2, NULL),
	(52, 1, 1, b'00000000', 2, NULL),
	(53, 1, 1, b'00000000', 2, NULL),
	(54, 1, 1, b'00000000', 2, NULL),
	(55, 1, 1, b'00000000', 2, NULL),
	(56, 1, 1, b'00000000', 2, NULL),
	(57, 1, 1, b'00000000', 2, NULL),
	(58, 1, 1, b'00000000', 2, NULL),
	(59, 1, 1, b'00000000', 2, NULL),
	(60, 1, 1, b'00000000', 2, NULL),
	(61, 1, 1, b'00000000', 2, NULL),
	(62, 1, 1, b'00000000', 2, NULL),
	(63, 1, 1, b'00000000', 2, NULL),
	(64, 1, 1, b'00000000', 2, NULL),
	(65, 1, 1, b'00000000', 2, NULL),
	(66, 1, 1, b'00000000', 2, NULL),
	(67, 1, 1, b'00000000', 2, NULL),
	(68, 1, 1, b'00000000', 2, NULL),
	(69, 1, 1, b'00000000', 2, NULL),
	(70, 1, 1, b'00000000', 2, NULL),
	(71, 1, 1, b'00000000', 2, NULL),
	(72, 1, 1, b'00000000', 2, NULL),
	(73, 1, 1, b'00000000', 2, NULL),
	(74, 1, 1, b'00000000', 2, NULL),
	(75, 1, 1, b'00000000', 2, NULL),
	(76, 1, 1, b'00000000', 2, NULL),
	(77, 1, 1, b'00000000', 2, NULL),
	(78, 1, 1, b'00000000', 2, NULL),
	(79, 1, 1, b'00000000', 2, NULL),
	(80, 1, 1, b'00000000', 2, NULL),
	(81, 1, 1, b'00000000', 2, NULL),
	(82, 1, 1, b'00000000', 2, NULL),
	(83, 1, 1, b'00000000', 2, NULL),
	(84, 1, 1, b'00000000', 2, NULL),
	(85, 1, 1, b'00000000', 2, NULL),
	(86, 1, 1, b'00000000', 2, NULL),
	(87, 1, 1, b'00000000', 2, NULL),
	(88, 1, 1, b'00000000', 2, NULL),
	(89, 1, 1, b'00000000', 2, NULL),
	(90, 1, 1, b'00000000', 2, NULL),
	(91, 1, 1, b'00000000', 2, NULL),
	(92, 1, 1, b'00000000', 2, NULL),
	(93, 1, 1, b'00000000', 2, NULL),
	(94, 1, 1, b'00000000', 2, NULL),
	(95, 1, 1, b'00000000', 2, NULL),
	(96, 1, 1, b'00000000', 2, NULL),
	(97, 1, 1, b'00000000', 2, NULL),
	(98, 1, 1, b'00000000', 2, NULL),
	(99, 1, 1, b'00000000', 2, NULL),
	(100, 1, 1, b'00000000', 2, NULL);
/*!40000 ALTER TABLE `ppl` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.queue_resources
DROP TABLE IF EXISTS `queue_resources`;
CREATE TABLE IF NOT EXISTS `queue_resources` (
  `game` bigint(20) unsigned NOT NULL,
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sort_res` int(10) unsigned NOT NULL,
  `resources` bigint(20) unsigned NOT NULL,
  `queue` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `key$queue_res$res` (`resources`),
  KEY `fk$queue_res$game$game` (`game`),
  CONSTRAINT `fk$queue_res$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$queue_res$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Очередь ресурс ов для каждой игры, для определения последовательности раздачи ресурсов игрокам. На данный момент учитывается что раздаётся только один ресурс (не считая кредитов, они фиксированы)';

-- Дамп данных таблицы moon_2040.queue_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `queue_resources` DISABLE KEYS */;
INSERT INTO `queue_resources` (`game`, `id`, `sort_res`, `resources`, `queue`) VALUES
	(1, 1, 1, 15, 1),
	(1, 2, 2, 16, 0),
	(1, 3, 3, 9, 0),
	(1, 4, 4, 10, 0),
	(1, 5, 5, 11, 0),
	(1, 6, 6, 12, 0),
	(1, 7, 7, 13, 0),
	(1, 8, 8, 14, 0);
/*!40000 ALTER TABLE `queue_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.resources
DROP TABLE IF EXISTS `resources`;
CREATE TABLE IF NOT EXISTS `resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `id_enum` int(10) unsigned NOT NULL,
  `name` varchar(128) NOT NULL COMMENT 'Наименование ресурса',
  `group_r` int(10) unsigned NOT NULL COMMENT 'Группа ресурса',
  `type_r` int(10) unsigned NOT NULL COMMENT 'Тип ресурса',
  `img` varchar(256) DEFAULT NULL COMMENT 'Путь к изображению ресурса',
  `img_min` varchar(256) DEFAULT NULL COMMENT 'Путь к изображению миниатюре ресурса',
  PRIMARY KEY (`id`),
  KEY `k$resources$group_r` (`group_r`),
  KEY `k$resources$type_r` (`type_r`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='Ресурсы';

-- Дамп данных таблицы moon_2040.resources: ~17 rows (приблизительно)
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` (`id`, `id_enum`, `name`, `group_r`, `type_r`, `img`, `img_min`) VALUES
	(1, 0, 'Колонисты', 2, 0, '/img/resources/PPL.png', '/img/resources/min/PPL.png'),
	(2, 1, 'Кредиты', 0, 0, '/img/resources/Cr.png', '/img/resources/min/Cr.png'),
	(3, 2, 'Еда', 0, 0, '/img/resources/Food.png', '/img/resources/min/Food.png'),
	(4, 3, 'Кислород', 0, 0, '/img/resources/O2.png', '/img/resources/min/O2.png'),
	(5, 4, 'Гелий-3', 0, 0, '/img/resources/He3.png', '/img/resources/min/He3.png'),
	(6, 5, 'Минералы', 0, 0, '/img/resources/Min.png', '/img/resources/min/Min.png'),
	(7, 6, 'Производственные материалы', 0, 0, '/img/resources/PMt.png', '/img/resources/min/PMt.png'),
	(8, 7, 'Энергия', 0, 0, '/img/resources/En.png', '/img/resources/min/En.png'),
	(9, 8, 'Ферма', 1, 1, '/img/resources/Ferm.png', '/img/resources/min/Ferm.png'),
	(10, 9, 'Оксинизатор', 1, 1, '/img/resources/Ox.png', '/img/resources/min/Ox.png'),
	(11, 10, 'Электростанция', 1, 1, '/img/resources/El.png', '/img/resources/min/El.png'),
	(12, 11, 'Добывающая станция', 1, 1, '/img/resources/Ds.png', '/img/resources/min/Ds.png'),
	(13, 12, 'Минеральная шахта', 1, 1, '/img/resources/Msh.png', '/img/resources/min/Msh.png'),
	(14, 13, 'Завод материалов', 1, 1, '/img/resources/PPL.png', '/img/resources/min/PPL.png'),
	(15, 14, 'Строительная артель', 1, 1, '/img/resources/Zmt.png', '/img/resources/min/Zmt.png'),
	(16, 15, 'Жилой комплекс', 1, 1, '/img/resources/Gil.png', '/img/resources/min/Gil.png'),
	(17, 16, 'Квартира', 1, 1, NULL, NULL);
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.resources_statistics
DROP TABLE IF EXISTS `resources_statistics`;
CREATE TABLE IF NOT EXISTS `resources_statistics` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `resources` bigint(19) unsigned NOT NULL COMMENT 'идентификатор ресурса',
  `count_r` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'сумарное количество конкретного ресурса',
  `add_r` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'добыто ресурсов за последний ход',
  `del_r` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'потрачено ресурсов за последний ход',
  `sale_price` int(10) unsigned DEFAULT '0' COMMENT 'цена на ресурс на Земле (продажа)',
  `sale_price_change` int(10) DEFAULT '0' COMMENT 'изменение цены на ресурс (продажа)',
  `buy_price` int(10) unsigned DEFAULT '0' COMMENT 'цена на ресурс на Земле (покупка)',
  `buy_price_change` int(10) DEFAULT '0' COMMENT 'изменение цены на ресурс (покупка)',
  PRIMARY KEY (`id`),
  KEY `key$res_stat$res` (`resources`),
  CONSTRAINT `fk$res_stat$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='Статистика по ресурсам за каждый ход игры, вычисляется перед началом каждого хода';

-- Дамп данных таблицы moon_2040.resources_statistics: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `resources_statistics` DISABLE KEYS */;
INSERT INTO `resources_statistics` (`id`, `resources`, `count_r`, `add_r`, `del_r`, `sale_price`, `sale_price_change`, `buy_price`, `buy_price_change`) VALUES
	(1, 1, 0, 0, 0, NULL, 0, NULL, 0),
	(2, 2, 500, 0, 0, NULL, 0, NULL, 0),
	(3, 3, 0, 0, 0, 20, 0, 29, 0),
	(4, 4, 0, 0, 0, 20, 0, 29, 0),
	(5, 5, 0, 0, 0, 20, 0, 29, 0),
	(6, 6, 0, 0, 0, 20, 0, 29, 0),
	(7, 7, 0, 0, 0, 20, 0, 29, 0),
	(8, 8, 0, 0, 0, 20, 0, 29, 0),
	(9, 9, 0, 0, 0, NULL, 0, NULL, 0),
	(10, 10, 0, 0, 0, NULL, 0, NULL, 0),
	(11, 11, 0, 0, 0, NULL, 0, NULL, 0),
	(12, 12, 0, 0, 0, NULL, 0, NULL, 0),
	(13, 13, 0, 0, 0, NULL, 0, NULL, 0),
	(14, 14, 0, 0, 0, NULL, 0, NULL, 0),
	(15, 15, 1, 0, 0, NULL, 0, NULL, 0),
	(16, 16, 0, 0, 0, NULL, 0, NULL, 0),
	(17, 17, 0, 0, 0, NULL, 0, NULL, 0);
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


-- Дамп структуры для таблица moon_2040.start_cost_resources
DROP TABLE IF EXISTS `start_cost_resources`;
CREATE TABLE IF NOT EXISTS `start_cost_resources` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `resources` bigint(19) unsigned NOT NULL,
  `cost` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk$start_cost$game$game` (`game`),
  KEY `fk$start_cost$res$res` (`resources`),
  CONSTRAINT `fk$start_cost$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$start_cost$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='В начале игры игроку предоставляется возможность на имеющиеся кредиты единоразово закупить ресурсы, в этой таблице представлены цены на ресурсы для старта игры';

-- Дамп данных таблицы moon_2040.start_cost_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `start_cost_resources` DISABLE KEYS */;
INSERT INTO `start_cost_resources` (`id`, `game`, `resources`, `cost`) VALUES
	(1, 1, 3, 15),
	(2, 1, 4, 10),
	(3, 1, 8, 25),
	(4, 1, 5, 150),
	(5, 1, 6, 150),
	(6, 1, 7, 30),
	(7, 1, 9, 150),
	(8, 1, 10, 150),
	(9, 1, 11, 150),
	(10, 1, 12, 150),
	(11, 1, 13, 150),
	(12, 1, 14, 150),
	(13, 1, 15, 150),
	(14, 1, 16, 150);
/*!40000 ALTER TABLE `start_cost_resources` ENABLE KEYS */;


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
  `game_date` bigint(19) unsigned NOT NULL COMMENT 'День игры',
  `usr` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор пользователя',
  `resources` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор ресурса',
  `count_res` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Количество ресурсов у пользователя',
  `hide_res` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'Ключ, позволяет скрывать ресурсы от других пользователей (1 - скрыто, 0 - видно)',
  `show_count` int(11) DEFAULT NULL COMMENT 'Если ресурсы видны то равно количеству ресурсов, если скрыты то это значение задаёт пользователь',
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Индекс 4` (`game`,`game_date`,`usr`,`resources`),
  KEY `fk$st_res$usr$usr_idx` (`usr`),
  KEY `fk$st_res$res$res_idx` (`resources`),
  KEY `fk$st_res$sd$sd` (`game_date`),
  CONSTRAINT `fk$st_res$sd$sd` FOREIGN KEY (`game_date`) REFERENCES `game_day` (`id`),
  CONSTRAINT `fk$st_res$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$st_res$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`),
  CONSTRAINT `fk$st_res$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Состояние ресурсов на игровой день по игрокам';

-- Дамп данных таблицы moon_2040.state_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `state_resources` DISABLE KEYS */;
INSERT INTO `state_resources` (`game`, `game_date`, `usr`, `resources`, `count_res`, `hide_res`, `show_count`, `id`) VALUES
	(1, 2, 1, 15, 1, 1, NULL, 3),
	(1, 2, 1, 2, 500, 1, NULL, 4);
/*!40000 ALTER TABLE `state_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.state_resources_ppl
DROP TABLE IF EXISTS `state_resources_ppl`;
CREATE TABLE IF NOT EXISTS `state_resources_ppl` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `game_date` bigint(19) unsigned NOT NULL COMMENT 'День игры',
  `ppl` bigint(19) unsigned NOT NULL COMMENT 'Коллонист',
  `credit` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Кредиты',
  `pay_house` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Плата за жильё',
  `salary` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Зарплата',
  `parasit` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Тунеядец (0 - нет, 1 - да)',
  `parasit_step` int(2) unsigned NOT NULL DEFAULT '0' COMMENT 'Шаг тунеядца (не меньше двух)',
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk$st_res_ppl` (`game`,`game_date`,`ppl`),
  KEY `fk$st_res_ppl$state_ppl` (`ppl`),
  KEY `fk$st_res_ppl$sd$sd` (`game_date`),
  CONSTRAINT `fk$st_res_ppl$sd$sd` FOREIGN KEY (`game_date`) REFERENCES `game_day` (`id`),
  CONSTRAINT `fk$st_res_ppl$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$st_res_ppl$state_ppl` FOREIGN KEY (`ppl`) REFERENCES `ppl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='Состояние колонистов на каждом ходе игры';

-- Дамп данных таблицы moon_2040.state_resources_ppl: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `state_resources_ppl` DISABLE KEYS */;
INSERT INTO `state_resources_ppl` (`game`, `game_date`, `ppl`, `credit`, `pay_house`, `salary`, `parasit`, `parasit_step`, `id`) VALUES
	(1, 2, 1, 20, 0, 0, b'00000000', 0, 1),
	(1, 2, 2, 20, 0, 0, b'00000000', 0, 2),
	(1, 2, 3, 20, 0, 0, b'00000000', 0, 3),
	(1, 2, 4, 20, 0, 0, b'00000000', 0, 4),
	(1, 2, 5, 20, 0, 0, b'00000000', 0, 5),
	(1, 2, 6, 20, 0, 0, b'00000000', 0, 6),
	(1, 2, 7, 20, 0, 0, b'00000000', 0, 7),
	(1, 2, 8, 20, 0, 0, b'00000000', 0, 8),
	(1, 2, 9, 20, 0, 0, b'00000000', 0, 9),
	(1, 2, 10, 20, 0, 0, b'00000000', 0, 10),
	(1, 2, 11, 20, 0, 0, b'00000000', 0, 11),
	(1, 2, 12, 20, 0, 0, b'00000000', 0, 12),
	(1, 2, 13, 20, 0, 0, b'00000000', 0, 13),
	(1, 2, 14, 20, 0, 0, b'00000000', 0, 14),
	(1, 2, 15, 20, 0, 0, b'00000000', 0, 15),
	(1, 2, 16, 20, 0, 0, b'00000000', 0, 16),
	(1, 2, 17, 20, 0, 0, b'00000000', 0, 17),
	(1, 2, 18, 20, 0, 0, b'00000000', 0, 18),
	(1, 2, 19, 20, 0, 0, b'00000000', 0, 19),
	(1, 2, 20, 20, 0, 0, b'00000000', 0, 20),
	(1, 2, 21, 20, 0, 0, b'00000000', 0, 21),
	(1, 2, 22, 20, 0, 0, b'00000000', 0, 22),
	(1, 2, 23, 20, 0, 0, b'00000000', 0, 23),
	(1, 2, 24, 20, 0, 0, b'00000000', 0, 24),
	(1, 2, 25, 20, 0, 0, b'00000000', 0, 25),
	(1, 2, 26, 20, 0, 0, b'00000000', 0, 26),
	(1, 2, 27, 20, 0, 0, b'00000000', 0, 27),
	(1, 2, 28, 20, 0, 0, b'00000000', 0, 28),
	(1, 2, 29, 20, 0, 0, b'00000000', 0, 29),
	(1, 2, 30, 20, 0, 0, b'00000000', 0, 30),
	(1, 2, 31, 20, 0, 0, b'00000000', 0, 31),
	(1, 2, 32, 20, 0, 0, b'00000000', 0, 32),
	(1, 2, 33, 20, 0, 0, b'00000000', 0, 33),
	(1, 2, 34, 20, 0, 0, b'00000000', 0, 34),
	(1, 2, 35, 20, 0, 0, b'00000000', 0, 35),
	(1, 2, 36, 20, 0, 0, b'00000000', 0, 36),
	(1, 2, 37, 20, 0, 0, b'00000000', 0, 37),
	(1, 2, 38, 20, 0, 0, b'00000000', 0, 38),
	(1, 2, 39, 20, 0, 0, b'00000000', 0, 39),
	(1, 2, 40, 20, 0, 0, b'00000000', 0, 40),
	(1, 2, 41, 20, 0, 0, b'00000000', 0, 41),
	(1, 2, 42, 20, 0, 0, b'00000000', 0, 42),
	(1, 2, 43, 20, 0, 0, b'00000000', 0, 43),
	(1, 2, 44, 20, 0, 0, b'00000000', 0, 44),
	(1, 2, 45, 20, 0, 0, b'00000000', 0, 45),
	(1, 2, 46, 20, 0, 0, b'00000000', 0, 46),
	(1, 2, 47, 20, 0, 0, b'00000000', 0, 47),
	(1, 2, 48, 20, 0, 0, b'00000000', 0, 48),
	(1, 2, 49, 20, 0, 0, b'00000000', 0, 49),
	(1, 2, 50, 20, 0, 0, b'00000000', 0, 50),
	(1, 2, 51, 20, 0, 0, b'00000000', 0, 51),
	(1, 2, 52, 20, 0, 0, b'00000000', 0, 52),
	(1, 2, 53, 20, 0, 0, b'00000000', 0, 53),
	(1, 2, 54, 20, 0, 0, b'00000000', 0, 54),
	(1, 2, 55, 20, 0, 0, b'00000000', 0, 55),
	(1, 2, 56, 20, 0, 0, b'00000000', 0, 56),
	(1, 2, 57, 20, 0, 0, b'00000000', 0, 57),
	(1, 2, 58, 20, 0, 0, b'00000000', 0, 58),
	(1, 2, 59, 20, 0, 0, b'00000000', 0, 59),
	(1, 2, 60, 20, 0, 0, b'00000000', 0, 60),
	(1, 2, 61, 20, 0, 0, b'00000000', 0, 61),
	(1, 2, 62, 20, 0, 0, b'00000000', 0, 62),
	(1, 2, 63, 20, 0, 0, b'00000000', 0, 63),
	(1, 2, 64, 20, 0, 0, b'00000000', 0, 64),
	(1, 2, 65, 20, 0, 0, b'00000000', 0, 65),
	(1, 2, 66, 20, 0, 0, b'00000000', 0, 66),
	(1, 2, 67, 20, 0, 0, b'00000000', 0, 67),
	(1, 2, 68, 20, 0, 0, b'00000000', 0, 68),
	(1, 2, 69, 20, 0, 0, b'00000000', 0, 69),
	(1, 2, 70, 20, 0, 0, b'00000000', 0, 70),
	(1, 2, 71, 20, 0, 0, b'00000000', 0, 71),
	(1, 2, 72, 20, 0, 0, b'00000000', 0, 72),
	(1, 2, 73, 20, 0, 0, b'00000000', 0, 73),
	(1, 2, 74, 20, 0, 0, b'00000000', 0, 74),
	(1, 2, 75, 20, 0, 0, b'00000000', 0, 75),
	(1, 2, 76, 20, 0, 0, b'00000000', 0, 76),
	(1, 2, 77, 20, 0, 0, b'00000000', 0, 77),
	(1, 2, 78, 20, 0, 0, b'00000000', 0, 78),
	(1, 2, 79, 20, 0, 0, b'00000000', 0, 79),
	(1, 2, 80, 20, 0, 0, b'00000000', 0, 80),
	(1, 2, 81, 20, 0, 0, b'00000000', 0, 81),
	(1, 2, 82, 20, 0, 0, b'00000000', 0, 82),
	(1, 2, 83, 20, 0, 0, b'00000000', 0, 83),
	(1, 2, 84, 20, 0, 0, b'00000000', 0, 84),
	(1, 2, 85, 20, 0, 0, b'00000000', 0, 85),
	(1, 2, 86, 20, 0, 0, b'00000000', 0, 86),
	(1, 2, 87, 20, 0, 0, b'00000000', 0, 87),
	(1, 2, 88, 20, 0, 0, b'00000000', 0, 88),
	(1, 2, 89, 20, 0, 0, b'00000000', 0, 89),
	(1, 2, 90, 20, 0, 0, b'00000000', 0, 90),
	(1, 2, 91, 20, 0, 0, b'00000000', 0, 91),
	(1, 2, 92, 20, 0, 0, b'00000000', 0, 92),
	(1, 2, 93, 20, 0, 0, b'00000000', 0, 93),
	(1, 2, 94, 20, 0, 0, b'00000000', 0, 94),
	(1, 2, 95, 20, 0, 0, b'00000000', 0, 95),
	(1, 2, 96, 20, 0, 0, b'00000000', 0, 96),
	(1, 2, 97, 20, 0, 0, b'00000000', 0, 97),
	(1, 2, 98, 20, 0, 0, b'00000000', 0, 98),
	(1, 2, 99, 20, 0, 0, b'00000000', 0, 99),
	(1, 2, 100, 20, 0, 0, b'00000000', 0, 100);
/*!40000 ALTER TABLE `state_resources_ppl` ENABLE KEYS */;


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
	(1, '5289cea39210a73464000002', 'fd@fedichkindenis.ru', 'Денис', 'Федичкин');
/*!40000 ALTER TABLE `usr` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.usr_game
DROP TABLE IF EXISTS `usr_game`;
CREATE TABLE IF NOT EXISTS `usr_game` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `usr` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор пользователя',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата и время регисрации пользователя в игре',
  `del_date` timestamp NULL DEFAULT NULL COMMENT 'Время и дата удаления пользователя из игры',
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk$usr_game$usr$usr_idx` (`usr`),
  KEY `fk$usr_game$game$game` (`game`),
  CONSTRAINT `fk$usr_game$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$usr_game$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Связь пользователя и игры';

-- Дамп данных таблицы moon_2040.usr_game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `usr_game` DISABLE KEYS */;
INSERT INTO `usr_game` (`game`, `usr`, `reg_date`, `del_date`, `id`) VALUES
	(1, 1, '2014-03-24 21:42:58', NULL, 1);
/*!40000 ALTER TABLE `usr_game` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.usr_role
DROP TABLE IF EXISTS `usr_role`;
CREATE TABLE IF NOT EXISTS `usr_role` (
  `usr` bigint(19) unsigned NOT NULL,
  `role` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`usr`,`role`),
  KEY `fk$usr_role$role$role_idx` (`role`),
  CONSTRAINT `fk$usr_role$role$role` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `fk$usr_role$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Связь пользователей и ролей';

-- Дамп данных таблицы moon_2040.usr_role: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `usr_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `usr_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
