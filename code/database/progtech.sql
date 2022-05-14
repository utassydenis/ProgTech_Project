-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Máj 14. 10:49
-- Kiszolgáló verziója: 10.4.22-MariaDB
-- PHP verzió: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `progtech`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `sid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `power_plants`
--

CREATE TABLE `power_plants` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `power` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `power_plants`
--

INSERT INTO `power_plants` (`id`, `name`, `power`, `price`) VALUES
(1, 'JS-300', 3681, 19700),
(2, 'Fortitude', 4207, 26308),
(3, 'SunFlare', 1337, 2800),
(4, 'Slipstream', 2413, 18600),
(5, 'Starheat', 1510, 4150),
(6, 'Charger', 2396, 12700);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `quantum_drives`
--

CREATE TABLE `quantum_drives` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `speed` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `quantum_drives`
--

INSERT INTO `quantum_drives` (`id`, `name`, `speed`, `price`) VALUES
(1, 'VK-00', 283046, 43300),
(2, 'Atlas', 151951, 17900),
(3, 'Spectre', 201112, 36150),
(4, 'Goliath', 89383, 12070),
(5, 'Eos', 138544, 15674);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `spaceships`
--

CREATE TABLE `spaceships` (
  `id` int(11) NOT NULL,
  `type` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `fuel` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `consumption` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `weapon` int(11) DEFAULT NULL,
  `power_plant` int(11) DEFAULT NULL,
  `quantum_drive` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `weapons`
--

CREATE TABLE `weapons` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `dps` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `weapons`
--

INSERT INTO `weapons` (`id`, `name`, `dps`, `type`, `price`) VALUES
(1, 'M5A', 500, 'Laser Cannon', 19300),
(2, 'FL-33', 500, 'Laser Cannon', 19200),
(3, 'MANTIS GT-220', 800, 'Ballistic Gatling', 13500),
(4, 'Tigerstrike T-21', 800, 'Ballistic Gatling', 13000),
(5, 'Deadbolt III', 625, 'Ballistic Cannon', 18240),
(6, 'Attrition-3', 500, 'Laser Repeater', 12000),
(7, 'CF-337 Panther', 500, 'Laser Repeater', 8925),
(8, 'Suckerpunch-XL', 338, 'Distortion Cannon', 10050);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `uid` (`uid`),
  ADD KEY `sid` (`sid`);

--
-- A tábla indexei `power_plants`
--
ALTER TABLE `power_plants`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `quantum_drives`
--
ALTER TABLE `quantum_drives`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `spaceships`
--
ALTER TABLE `spaceships`
  ADD PRIMARY KEY (`id`),
  ADD KEY `weapon` (`weapon`),
  ADD KEY `power_plant` (`power_plant`),
  ADD KEY `quantum_drive` (`quantum_drive`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `weapons`
--
ALTER TABLE `weapons`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `power_plants`
--
ALTER TABLE `power_plants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT a táblához `quantum_drives`
--
ALTER TABLE `quantum_drives`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `spaceships`
--
ALTER TABLE `spaceships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `weapons`
--
ALTER TABLE `weapons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `spaceships` (`id`) ON DELETE CASCADE;

--
-- Megkötések a táblához `spaceships`
--
ALTER TABLE `spaceships`
  ADD CONSTRAINT `spaceships_ibfk_1` FOREIGN KEY (`weapon`) REFERENCES `weapons` (`id`),
  ADD CONSTRAINT `spaceships_ibfk_2` FOREIGN KEY (`power_plant`) REFERENCES `power_plants` (`id`),
  ADD CONSTRAINT `spaceships_ibfk_3` FOREIGN KEY (`quantum_drive`) REFERENCES `quantum_drives` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
