-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Vært: 127.0.0.1:3306
-- Genereringstid: 29. 05 2024 kl. 10:32:11
-- Serverversion: 8.2.0
-- PHP-version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `minkostplan`
--

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `calories` double NOT NULL,
  `fat` double NOT NULL,
  `protein` double NOT NULL,
  `carbohydrates` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Data dump for tabellen `ingredient`
--

INSERT INTO `ingredient` (`id`, `name`, `calories`, `fat`, `protein`, `carbohydrates`) VALUES
(0, 'Havregryn', 389, 6.9, 16.9, 66),
(1, 'Gulerod', 0, 0, 0, 0),
(2, 'Kartoffel', 0, 0, 0, 0),
(3, 'Mel', 0, 0, 0, 0),
(4, 'Rødbeder', 0, 0, 0, 0),
(5, 'Rugbrød', 0, 0, 0, 0),
(6, 'Mælk', 132, 0.1, 3.5, 5),
(7, 'Vand', 0, 0, 0, 0),
(8, 'Olivenolie', 0, 0, 0, 0),
(9, 'Kylling', 0, 0, 0, 0),
(10, 'Oksekød', 0, 0, 0, 0),
(11, 'Fisk', 0, 0, 0, 0),
(12, 'Pølser', 0, 0, 0, 0),
(13, 'Kalkun pølse', 0, 0, 0, 0),
(14, 'Kalukun bacon', 0, 0, 0, 0),
(15, 'Iceberg salat', 0, 0, 0, 0),
(16, 'Æg', 0, 0, 0, 0),
(17, 'sur-sød sovs', 0, 0, 0, 0),
(18, 'Ris', 0, 0, 0, 0),
(19, 'Pasta', 0, 0, 0, 0),
(20, 'grov pasta', 0, 0, 0, 0),
(21, 'Pita', 0, 0, 0, 0),
(22, 'Æbler', 52, 0.2, 0.3, 14),
(23, 'Løg', 0, 0, 0, 0),
(24, 'Honning', 304, 0.2, 0.3, 82.4),
(25, 'Rødløg', 0, 0, 0, 0),
(26, 'Laks', 0, 0, 0, 0),
(27, 'Tun', 0, 0, 0, 0),
(28, 'Sild', 0, 0, 0, 0),
(29, 'Havregryn', 0, 0, 0, 0),
(30, 'Blåbær', 0, 0, 0, 0),
(31, 'Is terning', 0, 0, 0, 0),
(32, 'Bær', 0, 0, 0, 0),
(33, 'Ærter', 0, 0, 0, 0),
(34, 'salt', 0, 0, 0, 0),
(35, 'Pebber', 0, 0, 0, 0),
(36, 'hvidløgssalt', 0, 0, 0, 0),
(37, 'chili', 0, 0, 0, 0),
(38, 'kardemomme', 0, 0, 0, 0),
(39, 'Karry', 0, 0, 0, 0),
(40, 'Hakket oksekød', 0, 0, 0, 0),
(41, 'Hakket grisekød', 0, 0, 0, 0),
(42, 'Bacon', 0, 0, 0, 0),
(43, 'Lactose fri mælk', 0, 0, 0, 0),
(44, 'Sukker', 0, 0, 0, 0),
(45, 'Gær', 0, 0, 0, 0),
(46, 'olie', 0, 0, 0, 0),
(47, 'Aspers ', 0, 0, 0, 0),
(48, 'Jordbær', 0, 0, 0, 0),
(49, 'Blommer', 0, 0, 0, 0),
(50, 'Kirsebær', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `recipe`
--

DROP TABLE IF EXISTS `recipe`;
CREATE TABLE IF NOT EXISTS `recipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `mealType` smallint NOT NULL,
  `method` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `protein` int NOT NULL,
  `calories` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Data dump for tabellen `recipe`
--

INSERT INTO `recipe` (`id`, `name`, `mealType`, `method`, `protein`, `calories`) VALUES
(1, 'Wok med laks og lilla spidskål', 2, '½ gul peberfrugt\r\n1 rødløg\r\n250 g rød spidskål\r\n2 stk. forårsløg\r\n225 g laks\r\nLidt frisk koriander\r\n1 chili\r\n\r\nEvt. lime\r\nSkær peberfrugt og rødløg i strimler. Snit spidskålen, og skær forårsløg i skiver.\r\nSautér alle grøntsagerne i en varm wok i 3-4 min. Stil det til side.\r\nSteg laksestykkerne ca. 3 min. på den ene side og 1 min. på den anden.\r\nAnret grøntsager sammen med laks skåret i tern, og top med chili og koriander. Servér med basissovsen og evt. friske limebåde.\r\n', 0, 366),
(2, '\r\n\r\nTortilla med kylling', 2, '250 g hakket kylling.\r\nTortilla.\r\n200 g salat.\r\n1 tomat\r\nfedt fri dressing.\r\n\r\nKlargør kylling, med krydderi efter behov, skær salat i strimler, skær tomat i skiver.\r\n\r\nSteg kylling i 7 minuter sørg for at det er gennemstegt', 0, 458),
(3, '\r\n\r\nHakket oksekød med pita.\r\n', 2, 'Hakker oksekød 4-7%\r\npita\r\n200 g salat.\r\n1 tomat\r\nfedt fri dressing.\r\n\r\nKlargør hakket oksekød, med salt, peber, paprika og allround.\r\n\r\nsteg i 6 min over høj varme.', 0, 426),
(4, '\r\n\r\nStegt laks, med citron.', 2, 'Laks 300 gram.\r\n1 citron.\r\nsmør\r\nKartofler.\r\n\r\nSteg laks over medium varme for 5 min med en tsk smør.\r\nKog kartofler, evt. med lidt persille.', 0, 690),
(5, 'Æggekage', 2, '6 cherry tomater.\r\n50g persille.\r\n6 æg\r\n300 g kalukun bacon i skiver.\r\n\r\nPisk ægge blommer, steg kalkun bacon på forhånd. Efter noget tid fjern bacon fra pande, hæld æg i panden, tilføj bacon og tomat og persille.', 0, 700),
(6, '\r\n\r\nGrov pasta med tomat sauce', 2, '400 gram grov pasta.\r\n7 tomater.\r\nsmør og olie\r\ngulerod\r\nHvidløg\r\nbasilikum \r\n\r\nKog pasta med saltet vand.\r\nkog tomater til skinnet falder, køl i isvand, sauter gulerod og hvidløg til de er bløde. blend tomater og put derfor det helle i en pande', 0, 634),
(7, '\r\n\r\nMysli', 0, 'Mælk 200 ml\r\nMysli 300 g\r\n\r\ntilføj mælk og mysli til en skål og nyd', 0, 450),
(8, '\r\n\r\nHavregryn', 0, 'mælk 150 ml.\r\nHavregryn 250 g.\r\n\r\n\r\ntilføj mælk og havregryn til en skål og nyd.', 0, 400),
(9, 'Sandwich - Kylling', 1, 'Smør brød med noget fedt fri.\r\nTilføj kylling og bacon, samt tomat, løg og en smule salat.\r\nLuk sammen', 0, 650),
(10, 'Overnight Havregrød med Æble og Kanel', 0, 'Fremgangsmåde:\r\nI en skål eller et glas skal du mixe havregryn og mælk. Tilsæt også en knivspids kanel og honning efter smag. Rør ingredienserne sammen.\r\n\r\nSkær æblet i små tern og tilsæt det til havregrøden. Rør forsigtigt for at fordele æblestykkerne jævnt.\r\n\r\nDæk skålen eller glasset med plastfolie eller et låg og lad det stå i køleskabet natten over. Dette giver havregrøden tid til at suge væsken og blive dejlig og blød.\r\n\r\nNæste morgen kan du tage havregrøden ud af køleskabet, fjerne plastfolien eller låget, og nyde den kold. Du kan eventuelt toppe den med ekstra æbletern og en smule honning, hvis du ønsker det.\r\n', 0, 0);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `recipeingredients`
--

DROP TABLE IF EXISTS `recipeingredients`;
CREATE TABLE IF NOT EXISTS `recipeingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipeId` int NOT NULL,
  `quantity` int NOT NULL,
  `ingredientId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ingredientId` (`ingredientId`),
  KEY `fk_recipeIngredient` (`recipeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Data dump for tabellen `recipeingredients`
--

INSERT INTO `recipeingredients` (`id`, `recipeId`, `quantity`, `ingredientId`) VALUES
(1, 10, 40, 0),
(2, 10, 150, 6),
(3, 10, 100, 22),
(4, 10, 10, 24);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `sex` smallint NOT NULL,
  `dateBirth` datetime NOT NULL,
  `heightCm` int NOT NULL,
  `weightKg` int NOT NULL,
  `bmr` double DEFAULT NULL,
  `goal` smallint NOT NULL,
  `activity` smallint NOT NULL,
  `subscribed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqueEmail` (`email`(50))
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Data dump for tabellen `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `sex`, `dateBirth`, `heightCm`, `weightKg`, `bmr`, `goal`, `activity`, `subscribed`) VALUES
(11, 'Oliver', 'olimurel@gmail.com', '$2a$10$zXlsPK5vWFDJScoOGJ.xfO6/Zb7XPDDRPddM67vaampvDoP54xMB.', 0, '2002-11-28 00:00:00', 197, 100, 2681, 0, 1, 1),
(17, 'Susanne', 'Susanne@gmail.com', '$2a$10$eGB92XCjxuUOk3TFBV3BlOxyrpn8zbpQFK/INqAKN.1fEdEunGt4G', 1, '1982-02-11 00:00:00', 174, 84, 2646, 0, 2, 1);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `userrecipe`
--

DROP TABLE IF EXISTS `userrecipe`;
CREATE TABLE IF NOT EXISTS `userrecipe` (
  `userId` int NOT NULL,
  `recipeId` int NOT NULL,
  KEY `fk_userId` (`userId`),
  KEY `fk_recipeId` (`recipeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Begrænsninger for dumpede tabeller
--

--
-- Begrænsninger for tabel `recipeingredients`
--
ALTER TABLE `recipeingredients`
  ADD CONSTRAINT `fk_ingredientId` FOREIGN KEY (`ingredientId`) REFERENCES `ingredient` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_recipeIngredient` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Begrænsninger for tabel `userrecipe`
--
ALTER TABLE `userrecipe`
  ADD CONSTRAINT `fk_recipeId` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
