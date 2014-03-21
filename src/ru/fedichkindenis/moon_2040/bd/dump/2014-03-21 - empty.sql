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

-- Дамп данных таблицы moon_2040.function: ~27 rows (приблизительно)
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.game: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.game_functions: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `game_functions` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.game_link_resources: ~19 rows (приблизительно)
/*!40000 ALTER TABLE `game_link_resources` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.goal_game: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `goal_game` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.init_market_earth: ~6 rows (приблизительно)
/*!40000 ALTER TABLE `init_market_earth` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.link_resources: ~56 rows (приблизительно)
/*!40000 ALTER TABLE `link_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `link_resources` ENABLE KEYS */;


-- Дамп структуры для таблица moon_2040.market_earth
DROP TABLE IF EXISTS `market_earth`;
CREATE TABLE IF NOT EXISTS `market_earth` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `game` bigint(19) unsigned NOT NULL,
  `game_date` date NOT NULL,
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

-- Дамп данных таблицы moon_2040.queue_resources: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `queue_resources` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.start_cost_resources: ~14 rows (приблизительно)
/*!40000 ALTER TABLE `start_cost_resources` DISABLE KEYS */;
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

-- Дамп данных таблицы moon_2040.usr_game: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `usr_game` DISABLE KEYS */;
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
