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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='Функция, можест представлять как формулу так и условие';

-- Дамп данных таблицы moon_2040.function: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
INSERT INTO `function` (`id`, `is_if`, `type_if`, `el_function`, `operand`, `const_operand`, `func_operand`, `next_step`) VALUES
	(126, b'00000000', NULL, NULL, 5, NULL, NULL, NULL),
	(127, b'00000000', NULL, 0, NULL, NULL, NULL, 126),
	(128, b'00000000', NULL, NULL, NULL, 1.00, NULL, 127),
	(129, b'00000000', NULL, 0, NULL, NULL, NULL, 128),
	(130, b'00000000', NULL, NULL, NULL, 0.40, NULL, 129),
	(131, b'00000000', NULL, 2, NULL, NULL, NULL, 130),
	(132, b'00000000', NULL, NULL, 5, NULL, NULL, 131),
	(133, b'00000000', NULL, NULL, 3, NULL, NULL, NULL),
	(134, b'00000000', NULL, 2, NULL, NULL, NULL, 133),
	(135, b'00000000', NULL, 5, NULL, NULL, NULL, 134),
	(136, b'00000000', NULL, NULL, 4, NULL, NULL, 135),
	(137, b'00000000', NULL, 3, NULL, NULL, NULL, 136),
	(138, b'00000000', NULL, NULL, 1, NULL, NULL, 137),
	(139, b'00000000', NULL, 1, NULL, NULL, NULL, 138),
	(140, b'00000000', NULL, NULL, 7, NULL, NULL, 139),
	(141, b'00000000', NULL, 4, NULL, NULL, NULL, 140),
	(142, b'00000000', NULL, 1, NULL, NULL, NULL, 141),
	(143, b'00000000', NULL, NULL, 7, NULL, NULL, 142),
	(144, b'00000000', NULL, 5, NULL, NULL, NULL, NULL),
	(145, b'00000000', NULL, NULL, NULL, NULL, 143, 144),
	(146, b'00000000', NULL, 3, NULL, NULL, NULL, 145),
	(147, b'00000000', NULL, NULL, 1, NULL, NULL, 146),
	(148, b'00000000', NULL, 4, NULL, NULL, NULL, 147),
	(149, b'00000000', NULL, 2, NULL, NULL, NULL, 148),
	(150, b'00000000', NULL, NULL, 0, NULL, NULL, 149),
	(151, b'10000000', 5, NULL, NULL, 2000.00, NULL, NULL),
	(152, b'10000000', 2, NULL, NULL, 0.00, NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Игры';

-- Дамп данных таблицы moon_2040.game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` (`id`, `name`, `max_player`, `start_date`, `finish_date`, `step`, `count_ppl`, `credit_ppl`, `credit_user`, `life_out_flat`, `description`, `status`) VALUES
	(9, 'Тестовая игра №1', 50, '2014-03-21 22:24:01', NULL, '03:00:00', 100, 20, 500, 5, 'Игра формируется через сервлет', 1);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Функции используемые в игре для расчёта';

-- Дамп данных таблицы moon_2040.game_functions: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_functions` DISABLE KEYS */;
INSERT INTO `game_functions` (`id`, `game`, `name_func`, `function`) VALUES
	(13, 9, 0, 132),
	(14, 9, 1, 150),
	(15, 9, 2, 143);
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
	(20, 9, 3, 8, 12, 60),
	(21, 9, 4, 8, 12, 64),
	(22, 9, 8, 8, 12, 68),
	(23, 9, 5, 4, 6, 72),
	(24, 9, 7, 1, 1, 81),
	(25, 9, 7, 2, 2, 86),
	(26, 9, 7, 3, 3, 91),
	(27, 9, 7, 4, 4, 96),
	(28, 9, 7, 5, 5, 101),
	(29, 9, 9, 1, 1, 106),
	(30, 9, 10, 1, 1, 106),
	(31, 9, 11, 1, 1, 106),
	(32, 9, 12, 1, 1, 106),
	(33, 9, 13, 1, 1, 106),
	(34, 9, 14, 1, 1, 106),
	(35, 9, 15, 1, 1, 106),
	(36, 9, 16, 1, 1, 106),
	(37, 9, 17, 10, 10, 108),
	(38, 9, 2, NULL, NULL, 112);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='Связь статистики ресурсов с статистикой игры';

-- Дамп данных таблицы moon_2040.game_res_stat: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_res_stat` DISABLE KEYS */;
INSERT INTO `game_res_stat` (`id`, `game_stat`, `res_stat`) VALUES
	(35, 10, 52),
	(36, 10, 53),
	(37, 10, 54),
	(38, 10, 55),
	(39, 10, 56),
	(40, 10, 57),
	(41, 10, 58),
	(42, 10, 59),
	(43, 10, 60),
	(44, 10, 61),
	(45, 10, 62),
	(46, 10, 63),
	(47, 10, 64),
	(48, 10, 65),
	(49, 10, 66),
	(50, 10, 67),
	(51, 10, 68);
/*!40000 ALTER TABLE `game_res_stat` ENABLE KEYS */;


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
  `salary_max` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'максимальная зарплата',
  `salary_min` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'минимальная зарплата',
  `salary_avg` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'средняя зарплата',
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk$game_stat` (`game`,`game_date`),
  CONSTRAINT `fk$game_stat$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Статистика игры по каждому ходу, генерируется перед началом нового хода';

-- Дамп данных таблицы moon_2040.game_statistics: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `game_statistics` DISABLE KEYS */;
INSERT INTO `game_statistics` (`game`, `game_date`, `count_ppl`, `change_count_ppl`, `summ_max_ppl`, `summ_min_ppl`, `summ_avg_ppl`, `workless_count`, `parazit_count`, `flat_count`, `flat_count_empty`, `price_max_flat`, `price_min_flat`, `price_avg_flat`, `salary_max`, `salary_min`, `salary_avg`, `id`) VALUES
	(9, '2014-03-21 00:00:00', 100, 0, 20, 20, 20, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Цель игры, какие ресурсы и чему должны быть равны в случае победы и в случае поражения';

-- Дамп данных таблицы moon_2040.goal_game: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `goal_game` DISABLE KEYS */;
INSERT INTO `goal_game` (`game`, `resources`, `function`, `win`, `id`) VALUES
	(9, 1, 151, b'10000000', 5),
	(9, 1, 152, b'00000000', 6);
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
	(7, 9, 3, 20, 1000, 3000, 0.20, 3),
	(8, 9, 4, 20, 1000, 7000, 0.15, 3),
	(9, 9, 8, 20, 500, 2500, 0.18, 5),
	(10, 9, 5, 20, 300, 100, 0.05, 5),
	(11, 9, 6, 20, 300, 100, 0.05, 4),
	(12, 9, 7, 20, 750, 1200, 0.15, 4);
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
	(57, 9, 1, NULL),
	(58, 8, 1, 57),
	(59, 1, 1, 58),
	(60, 2, NULL, 59),
	(61, 10, 1, NULL),
	(62, 8, 1, 61),
	(63, 1, 1, 62),
	(64, 2, NULL, 63),
	(65, 11, 1, NULL),
	(66, 5, 2, 65),
	(67, 1, 1, 66),
	(68, 2, NULL, 67),
	(69, 12, 1, NULL),
	(70, 8, 1, 69),
	(71, 1, 2, 70),
	(72, 2, NULL, 71),
	(73, 13, 1, NULL),
	(74, 8, 2, 73),
	(75, 1, 2, 74),
	(76, 2, NULL, 75),
	(77, 14, 1, NULL),
	(78, 8, 1, 77),
	(79, 6, 1, 78),
	(80, 1, 2, 79),
	(81, 2, NULL, 80),
	(82, 14, 1, NULL),
	(83, 8, 1, 82),
	(84, 6, 2, 83),
	(85, 1, 2, 84),
	(86, 2, NULL, 85),
	(87, 14, 1, NULL),
	(88, 8, 1, 87),
	(89, 6, 3, 88),
	(90, 1, 2, 89),
	(91, 2, NULL, 90),
	(92, 14, 1, NULL),
	(93, 8, 1, 92),
	(94, 6, 4, 93),
	(95, 1, 2, 94),
	(96, 2, NULL, 95),
	(97, 14, 1, NULL),
	(98, 8, 1, 97),
	(99, 6, 5, 98),
	(100, 1, 2, 99),
	(101, 2, NULL, 100),
	(102, 15, 1, NULL),
	(103, 8, 1, 102),
	(104, 7, 2, 103),
	(105, 1, 2, 104),
	(106, 2, NULL, 105),
	(107, 16, 1, NULL),
	(108, 8, 1, 107),
	(109, 17, 1, NULL),
	(110, 4, 1, 109),
	(111, 3, 1, 110),
	(112, 1, 1, 111);
/*!40000 ALTER TABLE `link_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.market_earth
DROP TABLE IF EXISTS `market_earth`;
CREATE TABLE IF NOT EXISTS `market_earth` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `game_date` datetime NOT NULL,
  `resources` bigint(19) unsigned NOT NULL,
  `sale_cost` int(10) unsigned NOT NULL,
  `val` int(10) unsigned NOT NULL,
  `buy_cost` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk$market$game$game` (`game`),
  KEY `fk$market$res$res` (`resources`),
  CONSTRAINT `fk$market$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$market$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='Рынок Земли';

-- Дамп данных таблицы moon_2040.market_earth: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `market_earth` DISABLE KEYS */;
INSERT INTO `market_earth` (`id`, `game`, `game_date`, `resources`, `sale_cost`, `val`, `buy_cost`) VALUES
	(57, 9, '2014-03-21', 3, 20, 3000, 29),
	(58, 9, '2014-03-21', 4, 20, 7000, 29),
	(59, 9, '2014-03-21', 8, 20, 2500, 29),
	(60, 9, '2014-03-21', 5, 20, 100, 29),
	(61, 9, '2014-03-21', 6, 20, 100, 29),
	(62, 9, '2014-03-21', 7, 20, 1200, 29);
/*!40000 ALTER TABLE `market_earth` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.operation_game
DROP TABLE IF EXISTS `operation_game`;
CREATE TABLE IF NOT EXISTS `operation_game` (
  `id` bigint(19) unsigned NOT NULL,
  `game` bigint(19) unsigned NOT NULL COMMENT 'Индентификатор игры',
  `game_date` datetime NOT NULL COMMENT 'Дата игры',
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
  `add_date` datetime NOT NULL COMMENT 'дата прилёта на Луну (день игры)',
  `del_date` datetime DEFAULT NULL COMMENT 'дата смерти или покидания Луны (день игры)',
  PRIMARY KEY (`id`),
  KEY `fk$state_ppl$game$game` (`game`),
  CONSTRAINT `fk$state_ppl$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1101 DEFAULT CHARSET=utf8 COMMENT='Общее состояние каждого колониста';

-- Дамп данных таблицы moon_2040.ppl: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `ppl` DISABLE KEYS */;
INSERT INTO `ppl` (`id`, `game`, `days_capsule`, `stat`, `add_date`, `del_date`) VALUES
	(1101, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1102, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1103, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1104, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1105, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1106, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1107, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1108, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1109, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1110, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1111, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1112, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1113, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1114, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1115, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1116, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1117, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1118, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1119, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1120, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1121, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1122, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1123, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1124, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1125, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1126, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1127, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1128, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1129, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1130, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1131, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1132, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1133, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1134, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1135, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1136, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1137, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1138, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1139, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1140, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1141, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1142, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1143, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1144, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1145, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1146, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1147, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1148, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1149, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1150, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1151, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1152, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1153, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1154, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1155, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1156, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1157, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1158, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1159, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1160, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1161, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1162, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1163, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1164, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1165, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1166, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1167, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1168, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1169, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1170, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1171, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1172, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1173, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1174, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1175, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1176, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1177, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1178, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1179, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1180, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1181, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1182, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1183, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1184, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1185, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1186, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1187, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1188, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1189, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1190, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1191, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1192, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1193, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1194, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1195, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1196, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1197, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1198, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1199, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL),
	(1200, 9, 1, b'00000000', '2014-03-21 00:00:00', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='Очередь ресурс ов для каждой игры, для определения последовательности раздачи ресурсов игрокам. На данный момент учитывается что раздаётся только один ресурс (не считая кредитов, они фиксированы)';

-- Дамп данных таблицы moon_2040.queue_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `queue_resources` DISABLE KEYS */;
INSERT INTO `queue_resources` (`game`, `id`, `sort_res`, `resources`, `queue`) VALUES
	(9, 17, 1, 15, 1),
	(9, 18, 2, 16, 0),
	(9, 19, 3, 9, 0),
	(9, 20, 4, 10, 0),
	(9, 21, 5, 11, 0),
	(9, 22, 6, 12, 0),
	(9, 23, 7, 13, 0),
	(9, 24, 8, 14, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='Статистика по ресурсам за каждый ход игры, вычисляется перед началом каждого хода';

-- Дамп данных таблицы moon_2040.resources_statistics: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `resources_statistics` DISABLE KEYS */;
INSERT INTO `resources_statistics` (`id`, `resources`, `count_r`, `add_r`, `del_r`, `sale_price`, `sale_price_change`, `buy_price`, `buy_price_change`) VALUES
	(52, 1, 0, 0, 0, NULL, 0, NULL, 0),
	(53, 2, 500, 0, 0, NULL, 0, NULL, 0),
	(54, 3, 0, 0, 0, 20, 0, 29, 0),
	(55, 4, 0, 0, 0, 20, 0, 29, 0),
	(56, 5, 0, 0, 0, 20, 0, 29, 0),
	(57, 6, 0, 0, 0, 20, 0, 29, 0),
	(58, 7, 0, 0, 0, 20, 0, 29, 0),
	(59, 8, 0, 0, 0, 20, 0, 29, 0),
	(60, 9, 0, 0, 0, NULL, 0, NULL, 0),
	(61, 10, 0, 0, 0, NULL, 0, NULL, 0),
	(62, 11, 0, 0, 0, NULL, 0, NULL, 0),
	(63, 12, 0, 0, 0, NULL, 0, NULL, 0),
	(64, 13, 0, 0, 0, NULL, 0, NULL, 0),
	(65, 14, 0, 0, 0, NULL, 0, NULL, 0),
	(66, 15, 1, 0, 0, NULL, 0, NULL, 0),
	(67, 16, 0, 0, 0, NULL, 0, NULL, 0),
	(68, 17, 0, 0, 0, NULL, 0, NULL, 0);
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
	(15, 9, 3, 15),
	(16, 9, 4, 10),
	(17, 9, 8, 25),
	(18, 9, 5, 150),
	(19, 9, 6, 150),
	(20, 9, 7, 30),
	(21, 9, 9, 150),
	(22, 9, 10, 150),
	(23, 9, 11, 150),
	(24, 9, 12, 150),
	(25, 9, 13, 150),
	(26, 9, 14, 150),
	(27, 9, 15, 150),
	(28, 9, 16, 150);
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
  `game_date` datetime NOT NULL COMMENT 'День игры',
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
  CONSTRAINT `fk$st_res$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$st_res$res$res` FOREIGN KEY (`resources`) REFERENCES `resources` (`id`),
  CONSTRAINT `fk$st_res$usr$usr` FOREIGN KEY (`usr`) REFERENCES `usr` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='Состояние ресурсов на игровой день по игрокам';

-- Дамп данных таблицы moon_2040.state_resources: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `state_resources` DISABLE KEYS */;
INSERT INTO `state_resources` (`game`, `game_date`, `usr`, `resources`, `count_res`, `hide_res`, `show_count`, `id`) VALUES
	(9, '2014-03-21 00:00:00', 1, 15, 1, 1, NULL, 23),
	(9, '2014-03-21 00:00:00', 1, 2, 500, 1, NULL, 24);
/*!40000 ALTER TABLE `state_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.state_resources_ppl
DROP TABLE IF EXISTS `state_resources_ppl`;
CREATE TABLE IF NOT EXISTS `state_resources_ppl` (
  `game` bigint(19) unsigned NOT NULL COMMENT 'Идентификатор игры',
  `game_date` datetime NOT NULL COMMENT 'День игры',
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
  CONSTRAINT `fk$st_res_ppl$game$game` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  CONSTRAINT `fk$st_res_ppl$state_ppl` FOREIGN KEY (`ppl`) REFERENCES `ppl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1101 DEFAULT CHARSET=utf8 COMMENT='Состояние колонистов на каждом ходе игры';

-- Дамп данных таблицы moon_2040.state_resources_ppl: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `state_resources_ppl` DISABLE KEYS */;
INSERT INTO `state_resources_ppl` (`game`, `game_date`, `ppl`, `credit`, `pay_house`, `salary`, `parasit`, `parasit_step`, `id`) VALUES
	(9, '2014-03-21 00:00:00', 1101, 20, 0, 0, b'00000000', 0, 1101),
	(9, '2014-03-21 00:00:00', 1102, 20, 0, 0, b'00000000', 0, 1102),
	(9, '2014-03-21 00:00:00', 1103, 20, 0, 0, b'00000000', 0, 1103),
	(9, '2014-03-21 00:00:00', 1104, 20, 0, 0, b'00000000', 0, 1104),
	(9, '2014-03-21 00:00:00', 1105, 20, 0, 0, b'00000000', 0, 1105),
	(9, '2014-03-21 00:00:00', 1106, 20, 0, 0, b'00000000', 0, 1106),
	(9, '2014-03-21 00:00:00', 1107, 20, 0, 0, b'00000000', 0, 1107),
	(9, '2014-03-21 00:00:00', 1108, 20, 0, 0, b'00000000', 0, 1108),
	(9, '2014-03-21 00:00:00', 1109, 20, 0, 0, b'00000000', 0, 1109),
	(9, '2014-03-21 00:00:00', 1110, 20, 0, 0, b'00000000', 0, 1110),
	(9, '2014-03-21 00:00:00', 1111, 20, 0, 0, b'00000000', 0, 1111),
	(9, '2014-03-21 00:00:00', 1112, 20, 0, 0, b'00000000', 0, 1112),
	(9, '2014-03-21 00:00:00', 1113, 20, 0, 0, b'00000000', 0, 1113),
	(9, '2014-03-21 00:00:00', 1114, 20, 0, 0, b'00000000', 0, 1114),
	(9, '2014-03-21 00:00:00', 1115, 20, 0, 0, b'00000000', 0, 1115),
	(9, '2014-03-21 00:00:00', 1116, 20, 0, 0, b'00000000', 0, 1116),
	(9, '2014-03-21 00:00:00', 1117, 20, 0, 0, b'00000000', 0, 1117),
	(9, '2014-03-21 00:00:00', 1118, 20, 0, 0, b'00000000', 0, 1118),
	(9, '2014-03-21 00:00:00', 1119, 20, 0, 0, b'00000000', 0, 1119),
	(9, '2014-03-21 00:00:00', 1120, 20, 0, 0, b'00000000', 0, 1120),
	(9, '2014-03-21 00:00:00', 1121, 20, 0, 0, b'00000000', 0, 1121),
	(9, '2014-03-21 00:00:00', 1122, 20, 0, 0, b'00000000', 0, 1122),
	(9, '2014-03-21 00:00:00', 1123, 20, 0, 0, b'00000000', 0, 1123),
	(9, '2014-03-21 00:00:00', 1124, 20, 0, 0, b'00000000', 0, 1124),
	(9, '2014-03-21 00:00:00', 1125, 20, 0, 0, b'00000000', 0, 1125),
	(9, '2014-03-21 00:00:00', 1126, 20, 0, 0, b'00000000', 0, 1126),
	(9, '2014-03-21 00:00:00', 1127, 20, 0, 0, b'00000000', 0, 1127),
	(9, '2014-03-21 00:00:00', 1128, 20, 0, 0, b'00000000', 0, 1128),
	(9, '2014-03-21 00:00:00', 1129, 20, 0, 0, b'00000000', 0, 1129),
	(9, '2014-03-21 00:00:00', 1130, 20, 0, 0, b'00000000', 0, 1130),
	(9, '2014-03-21 00:00:00', 1131, 20, 0, 0, b'00000000', 0, 1131),
	(9, '2014-03-21 00:00:00', 1132, 20, 0, 0, b'00000000', 0, 1132),
	(9, '2014-03-21 00:00:00', 1133, 20, 0, 0, b'00000000', 0, 1133),
	(9, '2014-03-21 00:00:00', 1134, 20, 0, 0, b'00000000', 0, 1134),
	(9, '2014-03-21 00:00:00', 1135, 20, 0, 0, b'00000000', 0, 1135),
	(9, '2014-03-21 00:00:00', 1136, 20, 0, 0, b'00000000', 0, 1136),
	(9, '2014-03-21 00:00:00', 1137, 20, 0, 0, b'00000000', 0, 1137),
	(9, '2014-03-21 00:00:00', 1138, 20, 0, 0, b'00000000', 0, 1138),
	(9, '2014-03-21 00:00:00', 1139, 20, 0, 0, b'00000000', 0, 1139),
	(9, '2014-03-21 00:00:00', 1140, 20, 0, 0, b'00000000', 0, 1140),
	(9, '2014-03-21 00:00:00', 1141, 20, 0, 0, b'00000000', 0, 1141),
	(9, '2014-03-21 00:00:00', 1142, 20, 0, 0, b'00000000', 0, 1142),
	(9, '2014-03-21 00:00:00', 1143, 20, 0, 0, b'00000000', 0, 1143),
	(9, '2014-03-21 00:00:00', 1144, 20, 0, 0, b'00000000', 0, 1144),
	(9, '2014-03-21 00:00:00', 1145, 20, 0, 0, b'00000000', 0, 1145),
	(9, '2014-03-21 00:00:00', 1146, 20, 0, 0, b'00000000', 0, 1146),
	(9, '2014-03-21 00:00:00', 1147, 20, 0, 0, b'00000000', 0, 1147),
	(9, '2014-03-21 00:00:00', 1148, 20, 0, 0, b'00000000', 0, 1148),
	(9, '2014-03-21 00:00:00', 1149, 20, 0, 0, b'00000000', 0, 1149),
	(9, '2014-03-21 00:00:00', 1150, 20, 0, 0, b'00000000', 0, 1150),
	(9, '2014-03-21 00:00:00', 1151, 20, 0, 0, b'00000000', 0, 1151),
	(9, '2014-03-21 00:00:00', 1152, 20, 0, 0, b'00000000', 0, 1152),
	(9, '2014-03-21 00:00:00', 1153, 20, 0, 0, b'00000000', 0, 1153),
	(9, '2014-03-21 00:00:00', 1154, 20, 0, 0, b'00000000', 0, 1154),
	(9, '2014-03-21 00:00:00', 1155, 20, 0, 0, b'00000000', 0, 1155),
	(9, '2014-03-21 00:00:00', 1156, 20, 0, 0, b'00000000', 0, 1156),
	(9, '2014-03-21 00:00:00', 1157, 20, 0, 0, b'00000000', 0, 1157),
	(9, '2014-03-21 00:00:00', 1158, 20, 0, 0, b'00000000', 0, 1158),
	(9, '2014-03-21 00:00:00', 1159, 20, 0, 0, b'00000000', 0, 1159),
	(9, '2014-03-21 00:00:00', 1160, 20, 0, 0, b'00000000', 0, 1160),
	(9, '2014-03-21 00:00:00', 1161, 20, 0, 0, b'00000000', 0, 1161),
	(9, '2014-03-21 00:00:00', 1162, 20, 0, 0, b'00000000', 0, 1162),
	(9, '2014-03-21 00:00:00', 1163, 20, 0, 0, b'00000000', 0, 1163),
	(9, '2014-03-21 00:00:00', 1164, 20, 0, 0, b'00000000', 0, 1164),
	(9, '2014-03-21 00:00:00', 1165, 20, 0, 0, b'00000000', 0, 1165),
	(9, '2014-03-21 00:00:00', 1166, 20, 0, 0, b'00000000', 0, 1166),
	(9, '2014-03-21 00:00:00', 1167, 20, 0, 0, b'00000000', 0, 1167),
	(9, '2014-03-21 00:00:00', 1168, 20, 0, 0, b'00000000', 0, 1168),
	(9, '2014-03-21 00:00:00', 1169, 20, 0, 0, b'00000000', 0, 1169),
	(9, '2014-03-21 00:00:00', 1170, 20, 0, 0, b'00000000', 0, 1170),
	(9, '2014-03-21 00:00:00', 1171, 20, 0, 0, b'00000000', 0, 1171),
	(9, '2014-03-21 00:00:00', 1172, 20, 0, 0, b'00000000', 0, 1172),
	(9, '2014-03-21 00:00:00', 1173, 20, 0, 0, b'00000000', 0, 1173),
	(9, '2014-03-21 00:00:00', 1174, 20, 0, 0, b'00000000', 0, 1174),
	(9, '2014-03-21 00:00:00', 1175, 20, 0, 0, b'00000000', 0, 1175),
	(9, '2014-03-21 00:00:00', 1176, 20, 0, 0, b'00000000', 0, 1176),
	(9, '2014-03-21 00:00:00', 1177, 20, 0, 0, b'00000000', 0, 1177),
	(9, '2014-03-21 00:00:00', 1178, 20, 0, 0, b'00000000', 0, 1178),
	(9, '2014-03-21 00:00:00', 1179, 20, 0, 0, b'00000000', 0, 1179),
	(9, '2014-03-21 00:00:00', 1180, 20, 0, 0, b'00000000', 0, 1180),
	(9, '2014-03-21 00:00:00', 1181, 20, 0, 0, b'00000000', 0, 1181),
	(9, '2014-03-21 00:00:00', 1182, 20, 0, 0, b'00000000', 0, 1182),
	(9, '2014-03-21 00:00:00', 1183, 20, 0, 0, b'00000000', 0, 1183),
	(9, '2014-03-21 00:00:00', 1184, 20, 0, 0, b'00000000', 0, 1184),
	(9, '2014-03-21 00:00:00', 1185, 20, 0, 0, b'00000000', 0, 1185),
	(9, '2014-03-21 00:00:00', 1186, 20, 0, 0, b'00000000', 0, 1186),
	(9, '2014-03-21 00:00:00', 1187, 20, 0, 0, b'00000000', 0, 1187),
	(9, '2014-03-21 00:00:00', 1188, 20, 0, 0, b'00000000', 0, 1188),
	(9, '2014-03-21 00:00:00', 1189, 20, 0, 0, b'00000000', 0, 1189),
	(9, '2014-03-21 00:00:00', 1190, 20, 0, 0, b'00000000', 0, 1190),
	(9, '2014-03-21 00:00:00', 1191, 20, 0, 0, b'00000000', 0, 1191),
	(9, '2014-03-21 00:00:00', 1192, 20, 0, 0, b'00000000', 0, 1192),
	(9, '2014-03-21 00:00:00', 1193, 20, 0, 0, b'00000000', 0, 1193),
	(9, '2014-03-21 00:00:00', 1194, 20, 0, 0, b'00000000', 0, 1194),
	(9, '2014-03-21 00:00:00', 1195, 20, 0, 0, b'00000000', 0, 1195),
	(9, '2014-03-21 00:00:00', 1196, 20, 0, 0, b'00000000', 0, 1196),
	(9, '2014-03-21 00:00:00', 1197, 20, 0, 0, b'00000000', 0, 1197),
	(9, '2014-03-21 00:00:00', 1198, 20, 0, 0, b'00000000', 0, 1198),
	(9, '2014-03-21 00:00:00', 1199, 20, 0, 0, b'00000000', 0, 1199),
	(9, '2014-03-21 00:00:00', 1200, 20, 0, 0, b'00000000', 0, 1200);
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
	(9, 1, '2014-03-21 22:14:53', NULL, 2);
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
