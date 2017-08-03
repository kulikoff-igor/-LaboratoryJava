-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 6.0.622.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 10.05.2017 20:00:56
-- Версия сервера: 5.5.28
-- Версия клиента: 4.1

-- 
-- Отключение внешних ключей
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

-- 
-- Установка базы данных по умолчанию
--
USE lab2;

--
-- Описание для таблицы weather
--
DROP TABLE IF EXISTS weather;
CREATE TABLE weather (
  weather_ID INT(11) NOT NULL AUTO_INCREMENT,
  date VARCHAR(255) DEFAULT NULL,
  temperature VARCHAR(50) DEFAULT NULL,
  windSpeed VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (weather_ID)
)
ENGINE = MEMORY
AUTO_INCREMENT = 15
AVG_ROW_LENGTH = 1690
CHARACTER SET utf8
COLLATE utf8_general_ci;

-- 
-- Вывод данных для таблицы weather
--
INSERT INTO weather VALUES
(12, '4', '4', '4'),
(9, '23,05,2017', '25', '15'),
(11, '3', '3', '3'),
(13, '23,05,2017', '3', '8'),
(14, 'аро7', 'паор', '5');

-- 
-- Включение внешних ключей
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;