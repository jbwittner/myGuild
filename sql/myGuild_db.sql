-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql
-- Généré le : lun. 28 déc. 2020 à 22:29
-- Version du serveur :  8.0.20
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `myGuild_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `CHARACTERS`
--

CREATE TABLE `CHARACTERS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `AVATAR_URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `AVERAGE_ITEM_LEVEL` int DEFAULT NULL,
  `EQUIPPED_ITEM_LEVEL` int DEFAULT NULL,
  `INSET_URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IS_FAVORITE` bit(1) DEFAULT NULL,
  `IS_TOO_OLD` bit(1) DEFAULT NULL,
  `LAST_LOGIN_TIMESTAMP` bigint DEFAULT NULL,
  `LEVEL` int DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `FACTION_ID` int DEFAULT NULL,
  `GUILD_ID` int DEFAULT NULL,
  `GUILD_RANK_ID` int DEFAULT NULL,
  `PLAYABLE_CLASS_ID` int DEFAULT NULL,
  `PLAYABLE_RACE_ID` int DEFAULT NULL,
  `REALM_ID` int DEFAULT NULL,
  `USER_ACCOUNT_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `CHARACTERS`
--

INSERT INTO `CHARACTERS` (`ID`, `IS_UPDATED`, `AVATAR_URL`, `AVERAGE_ITEM_LEVEL`, `EQUIPPED_ITEM_LEVEL`, `INSET_URL`, `IS_FAVORITE`, `IS_TOO_OLD`, `LAST_LOGIN_TIMESTAMP`, `LEVEL`, `NAME`, `UPDATE_DATE`, `FACTION_ID`, `GUILD_ID`, `GUILD_RANK_ID`, `PLAYABLE_CLASS_ID`, `PLAYABLE_RACE_ID`, `REALM_ID`, `USER_ACCOUNT_ID`) VALUES
(105731589, b'1', NULL, 201, 201, NULL, b'0', b'0', 1609190462000, 60, 'Kalfitz', '2020-12-28 22:23:30', 4, 84810965, 7, 3, 4, 539, NULL),
(106052777, b'1', NULL, 90, 90, NULL, b'0', b'0', 1608941204000, 50, 'Holyfitz', '2020-12-28 22:23:30', 4, 84810965, 8, 5, 11, 539, NULL),
(106590432, b'1', NULL, 125, 124, NULL, b'0', b'0', 1606327359000, 55, 'Popoupidou', '2020-12-28 22:23:31', 4, 84810965, 7, 2, 11, 539, NULL),
(106603257, b'1', NULL, 121, 117, NULL, b'0', b'0', 1609192815000, 58, 'Santiagø', '2020-12-28 22:24:00', 4, 84810965, 10, 2, 1, 539, NULL),
(106604677, b'1', NULL, 145, 144, NULL, b'0', b'1', 1590769172000, 42, 'Poupopïdou', '2020-12-28 22:23:31', 4, 84810965, 8, 8, 22, 539, NULL),
(106717823, b'1', NULL, 121, 121, NULL, b'0', b'0', 1608651951000, 50, 'Kiritá', '2020-12-28 22:23:31', 4, 84810965, 9, 11, 4, 539, NULL),
(106797830, b'1', NULL, 108, 108, NULL, b'0', b'0', 1607127388000, 56, 'Kàlye', '2020-12-28 22:23:32', 4, 84810965, 8, 5, 1, 539, NULL),
(106909747, b'1', NULL, 100, 100, NULL, b'0', b'0', 1609189996000, 51, 'Kaifitz', '2020-12-28 22:23:32', 4, 84810965, 8, 10, 25, 539, NULL),
(106997506, b'1', NULL, 195, 195, NULL, b'0', b'0', 1609187615000, 60, 'Øxow', '2020-12-28 22:23:32', 4, 84810965, 9, 4, 4, 539, NULL),
(106999455, b'1', NULL, 179, 179, NULL, b'0', b'0', 1607603417000, 60, 'Glaren', '2020-12-28 22:23:32', 4, 84810965, 8, 2, 11, 539, NULL),
(107013536, b'1', NULL, 191, 190, NULL, b'0', b'0', 1609107240000, 60, 'Hosur', '2020-12-28 22:23:32', 4, 84810965, 10, 3, 3, 539, NULL),
(107062558, b'1', NULL, 205, 205, NULL, b'0', b'0', 1609189804000, 60, 'Malørf', '2020-12-28 22:23:33', 4, 84810965, 7, 7, 11, 539, NULL),
(107270090, b'1', NULL, 101, 101, NULL, b'0', b'0', 1608931840000, 51, 'Fitzood', '2020-12-28 22:23:33', 4, 84810965, 8, 11, 4, 539, NULL),
(107471704, b'1', NULL, 195, 195, NULL, b'0', b'0', 1609139908000, 60, 'Dhorok', '2020-12-28 22:23:33', 4, 84810965, 11, 7, 34, 539, NULL),
(107474251, b'1', NULL, 203, 202, NULL, b'0', b'0', 1609186844000, 60, 'Ðealyia', '2020-12-28 22:23:34', 4, 84810965, 9, 5, 1, 539, NULL),
(107492544, b'1', NULL, 131, 131, NULL, b'0', b'0', 1607514415000, 50, 'Dealyia', '2020-12-28 22:23:34', 4, 84810965, 8, 3, 1, 539, NULL),
(107501795, b'1', NULL, 132, 131, NULL, b'0', b'0', 1604929958000, 50, 'Lyande', '2020-12-28 22:23:34', 4, 84810965, 10, 2, 11, 539, NULL),
(107516359, b'1', NULL, 108, 107, NULL, b'0', b'0', 1608920310000, 54, 'Gormuz', '2020-12-28 22:23:34', 4, 84810965, 8, 6, 3, 539, NULL),
(107518342, b'1', NULL, 107, 107, NULL, b'0', b'0', 1607250815000, 50, 'Zoïdbêurg', '2020-12-28 22:23:34', 4, 84810965, 8, 1, 1, 539, NULL),
(112297337, b'1', NULL, 199, 193, NULL, b'0', b'0', 1609169232000, 60, 'Fatlord', '2020-12-28 22:23:34', 4, 84810965, 9, 1, 1, 539, NULL),
(117842717, b'1', NULL, 426, 426, NULL, b'0', b'1', 1591804930000, 50, 'Decàlïon', '2020-12-28 22:23:34', 4, 84810965, 7, 3, 22, 539, NULL),
(118810658, b'1', NULL, 110, 110, NULL, b'0', b'0', 1608462864000, 52, 'Dôw', '2020-12-28 22:23:34', 4, 84810965, 8, 2, 1, 539, NULL),
(121068019, b'1', NULL, 186, 183, NULL, b'0', b'0', 1609186755000, 60, 'Lhÿnn', '2020-12-28 22:23:34', 4, 84810965, 10, 10, 4, 539, NULL),
(121096187, b'1', NULL, 93, 93, NULL, b'0', b'0', 1607354195000, 50, 'Yvanovitch', '2020-12-28 22:23:34', 4, 84810965, 8, 3, 1, 539, NULL),
(124595230, b'1', NULL, 194, 194, NULL, b'0', b'0', 1609178232000, 60, 'Torar', '2020-12-28 22:23:34', 4, 84810965, 12, 4, 4, 539, NULL),
(125582074, b'1', NULL, 181, 181, NULL, b'0', b'0', 1609193682000, 60, 'Loonette', '2020-12-28 22:23:35', 4, 84810965, 12, 7, 11, 539, NULL),
(125711853, b'1', NULL, 12, 12, NULL, b'0', b'0', 1605473845000, 16, 'Tymara', '2020-12-28 22:23:35', 4, 84810965, 13, 10, 4, 539, NULL),
(125711854, b'1', NULL, 193, 193, NULL, b'0', b'0', 1609189359000, 60, 'Dohakor', '2020-12-28 22:23:35', 4, 84810965, 14, 5, 4, 539, NULL),
(135156281, b'1', NULL, 104, 104, NULL, b'0', b'0', 1605452758000, 50, 'Rakü', '2020-12-28 22:23:40', 4, 84810965, 8, 5, 4, 539, NULL),
(140815781, b'1', NULL, 169, 169, NULL, b'0', b'0', 1608411540000, 60, 'Cumhal', '2020-12-28 22:23:40', 4, 84810965, 8, 11, 4, 539, NULL),
(140861265, b'1', NULL, 203, 202, NULL, b'0', b'0', 1609188660000, 60, 'Lørthar', '2020-12-28 22:23:40', 4, 84810965, 11, 2, 3, 539, NULL),
(141879261, b'1', NULL, 199, 197, NULL, b'0', b'0', 1608716243000, 60, 'Catequil', '2020-12-28 22:23:40', 4, 84810965, 7, 7, 11, 539, NULL),
(142296763, b'1', NULL, 126, 126, NULL, b'0', b'0', 1607129210000, 50, 'Bloodilova', '2020-12-28 22:23:40', 4, 84810965, 8, 12, 4, 539, NULL),
(143227804, b'1', NULL, 174, 174, NULL, b'0', b'0', 1609028738000, 60, 'Ivanolitch', '2020-12-28 22:23:40', 4, 84810965, 10, 12, 4, 539, NULL),
(143307094, b'1', NULL, 126, 126, NULL, b'0', b'0', 1609106516000, 51, 'Makame', '2020-12-28 22:23:40', 4, 84810965, 8, 3, 1, 539, NULL),
(149261132, b'1', NULL, 476, 472, NULL, b'0', b'1', 1601485437000, 50, 'Røckø', '2020-12-28 22:23:40', 4, 84810965, 9, 4, 29, 539, NULL),
(151190644, b'1', NULL, 117, 116, NULL, b'0', b'0', 1607649658000, 50, 'Blackadder', '2020-12-28 22:23:40', 4, 84810965, 10, 1, 1, 539, NULL),
(154301259, b'1', NULL, 197, 197, NULL, b'0', b'0', 1609193710000, 60, 'Ðáw', '2020-12-28 22:23:40', 4, 84810965, 7, 3, 1, 539, NULL),
(155124404, b'1', NULL, 112, 111, NULL, b'0', b'0', 1608997548000, 50, 'Boltkrang', '2020-12-28 22:23:40', 4, 84810965, 8, 4, 1, 539, NULL),
(160194802, b'1', NULL, 56, 54, NULL, b'0', b'0', 1608937452000, 49, 'Dow', '2020-12-28 22:23:40', 4, 84810965, 8, 6, 1, 539, NULL),
(160499607, b'1', NULL, 121, 119, NULL, b'0', b'0', 1608165872000, 51, 'Astroth', '2020-12-28 22:23:40', 4, 84810965, 8, 5, 29, 539, NULL),
(164071200, b'1', NULL, 75, 74, NULL, b'0', b'0', 1606063873000, 50, 'Ugruy', '2020-12-28 22:23:40', 4, 84810965, 8, 11, 4, 539, NULL),
(164671302, b'1', NULL, 440, 440, NULL, b'0', b'1', 1600705568000, 50, 'Fatlørd', '2020-12-28 22:23:40', 4, 84810965, 8, 3, 29, 539, NULL),
(164982742, b'1', NULL, 475, 472, NULL, b'0', b'1', 1596664009000, 50, 'Rumstinr', '2020-12-28 22:23:40', 4, 84810965, 9, 5, 29, 539, NULL),
(165997564, b'1', NULL, 188, 184, NULL, b'0', b'0', 1609098035000, 60, 'Sheezy', '2020-12-28 22:23:40', 4, 84810965, 10, 2, 1, 539, NULL),
(166297098, b'1', NULL, 193, 190, NULL, b'0', b'0', 1609181623000, 60, 'Nararith', '2020-12-28 22:23:40', 4, 84810965, 10, 1, 29, 539, NULL),
(168575985, b'1', NULL, 479, 478, NULL, b'0', b'1', 1596969656000, 50, 'Kastelrouge', '2020-12-28 22:23:40', 4, 84810965, 10, 1, 34, 539, NULL),
(170249399, b'1', NULL, 17, 17, NULL, b'0', b'0', 1606171481000, 19, 'Sebpriest', '2020-12-28 22:23:40', 4, 84810965, 8, 5, 1, 539, NULL),
(170251842, b'1', NULL, 99, 99, NULL, b'0', b'0', 1606317334000, 51, 'Sebdemo', '2020-12-28 22:23:40', 4, 84810965, 8, 9, 1, 539, NULL),
(175564399, b'1', NULL, 196, 196, NULL, b'0', b'0', 1608977710000, 60, 'Frozøne', '2020-12-28 22:23:40', 4, 84810965, 10, 6, 22, 539, NULL),
(175745862, b'1', NULL, 112, 112, NULL, b'0', b'0', 1608134498000, 50, 'Shuinul', '2020-12-28 22:23:40', 4, 84810965, 7, 9, 1, 539, NULL),
(177595178, b'1', NULL, 204, 204, NULL, b'0', b'0', 1609164522000, 60, 'Cabzout', '2020-12-28 22:23:40', 4, 84810965, 10, 1, 1, 539, NULL),
(177624920, b'1', NULL, 182, 182, NULL, b'0', b'0', 1608135483000, 60, 'Rodouzout', '2020-12-28 22:23:40', 4, 84810965, 10, 8, 1, 539, NULL),
(177729792, b'1', NULL, 119, 119, NULL, b'0', b'0', 1606608482000, 50, 'Yuwî', '2020-12-28 22:23:40', 4, 84810965, 8, 6, 7, 539, NULL),
(180103920, b'1', NULL, 169, 165, NULL, b'0', b'0', 1609180749000, 60, 'Vertich', '2020-12-28 22:23:40', 4, 84810965, 8, 9, 3, 539, NULL),
(181901147, b'1', NULL, 198, 198, NULL, b'0', b'0', 1609100605000, 60, 'Gélatine', '2020-12-28 22:23:40', 4, 84810965, 12, 11, 4, 539, NULL),
(182241151, b'1', NULL, 175, 173, NULL, b'0', b'0', 1608808740000, 60, 'Garaum', '2020-12-28 22:23:40', 4, 84810965, 12, 1, 1, 539, NULL),
(182871286, b'1', NULL, 138, 133, NULL, b'0', b'0', 1609180817000, 60, 'Semia', '2020-12-28 22:23:40', 4, 84810965, 10, 11, 4, 539, NULL),
(183404049, b'1', NULL, 28, 25, NULL, b'0', b'1', 1603042234000, 25, 'Rhéalouette', '2020-12-28 22:23:40', 4, 84810965, 13, 8, 1, 539, NULL),
(183508561, b'1', NULL, 166, 165, NULL, b'0', b'0', 1606664118000, 60, 'Sebchassou', '2020-12-28 22:23:40', 4, 84810965, 9, 3, 1, 539, NULL),
(183625458, b'1', NULL, 127, 121, NULL, b'0', b'0', 1606081606000, 50, 'Sulion', '2020-12-28 22:23:40', 4, 84810965, 15, 12, 4, 539, NULL),
(183840154, b'1', NULL, 99, 99, NULL, b'0', b'0', 1608940812000, 50, 'Rhéaloupette', '2020-12-28 22:24:00', 4, 84810965, 13, 12, 4, 539, NULL),
(183878278, b'1', NULL, 108, 108, NULL, b'0', b'0', 1605132452000, 50, 'Illdriss', '2020-12-28 22:24:00', 4, 84810965, 13, 11, 4, 539, NULL),
(183887310, b'1', NULL, 193, 193, NULL, b'0', b'0', 1609193330000, 60, 'Raphthx', '2020-12-28 22:24:00', 4, 84810965, 10, 6, 4, 539, NULL),
(183969831, b'1', NULL, 127, 127, NULL, b'0', b'0', 1608154509000, 50, 'Vedalken', '2020-12-28 22:24:00', 4, 84810965, 9, 3, 4, 539, NULL),
(184244657, b'1', NULL, 448, 448, NULL, b'0', b'1', 1600817208000, 50, 'Mächällah', '2020-12-28 22:24:00', 4, 84810965, 8, 6, 32, 539, NULL),
(184298656, b'1', NULL, 124, 124, NULL, b'0', b'0', 1607465851000, 56, 'Bregss', '2020-12-28 22:24:00', 4, 84810965, 11, 10, 25, 539, NULL),
(184375343, b'1', NULL, 178, 178, NULL, b'0', b'0', 1608732995000, 60, 'Thanastroth', '2020-12-28 22:24:00', 4, 84810965, 8, 6, 4, 539, NULL),
(184424742, b'1', NULL, 201, 200, NULL, b'0', b'0', 1608729881000, 60, 'Thorrtatué', '2020-12-28 22:24:00', 4, 84810965, 11, 6, 1, 539, NULL),
(184526264, b'1', NULL, 115, 114, NULL, b'0', b'0', 1604504205000, 50, 'Brogss', '2020-12-28 22:24:00', 4, 84810965, 8, 9, 1, 539, NULL),
(184579517, b'1', NULL, 118, 118, NULL, b'0', b'0', 1608414114000, 53, 'Rogueroth', '2020-12-28 22:24:00', 4, 84810965, 8, 4, 4, 539, NULL),
(184651661, b'1', NULL, 90, 89, NULL, b'0', b'0', 1604503886000, 50, 'Brigss', '2020-12-28 22:24:00', 4, 84810965, 8, 1, 4, 539, NULL),
(184657689, b'1', NULL, 122, 119, NULL, b'0', b'0', 1605716352000, 50, 'Trottato', '2020-12-28 22:24:00', 4, 84810965, 8, 10, 1, 539, NULL),
(184658870, b'1', NULL, 86, 85, NULL, b'0', b'0', 1605989720000, 50, 'Tesath', '2020-12-28 22:24:00', 4, 84810965, 15, 8, 29, 539, NULL),
(184856105, b'1', NULL, 161, 156, NULL, b'0', b'0', 1607556392000, 60, 'Xélore', '2020-12-28 22:24:00', 4, 84810965, 9, 9, 29, 539, NULL),
(185053861, b'1', NULL, 12, 12, NULL, b'0', b'1', 1588250737000, 10, 'Edar', '2020-12-28 22:24:00', 4, 84810965, 13, 2, 30, 539, NULL),
(185054418, b'1', NULL, 126, 119, NULL, b'0', b'0', 1606081206000, 50, 'Barwen', '2020-12-28 22:24:00', 4, 84810965, 13, 6, 1, 539, NULL),
(185101320, b'1', NULL, 126, 125, NULL, b'0', b'0', 1604872218000, 50, 'Akke', '2020-12-28 22:24:00', 4, 84810965, 8, 1, 4, 539, NULL),
(185117285, b'1', NULL, 196, 194, NULL, b'0', b'0', 1609191415000, 60, 'Thorttati', '2020-12-28 22:24:00', 4, 84810965, 8, 12, 4, 539, NULL),
(185208518, b'1', NULL, 115, 114, NULL, b'0', b'0', 1606083079000, 50, 'Langøustina', '2020-12-28 22:24:00', 4, 84810965, 13, 2, 30, 539, NULL),
(185211359, b'1', NULL, 196, 195, NULL, b'0', b'0', 1609179278000, 60, 'Fleimkepa', '2020-12-28 22:24:00', 4, 84810965, 7, 12, 4, 539, NULL),
(185517009, b'1', NULL, 110, 110, NULL, b'0', b'0', 1606065628000, 50, 'Nennviàl', '2020-12-28 22:24:00', 4, 84810965, 13, 11, 4, 539, NULL),
(185600531, b'1', NULL, 125, 120, NULL, b'0', b'0', 1608668612000, 56, 'Boolay', '2020-12-28 22:24:00', 4, 84810965, 8, 10, 25, 539, NULL),
(185919686, b'1', NULL, 404, 404, NULL, b'0', b'1', 1596045398000, 50, 'Rümstin', '2020-12-28 22:24:00', 4, 84810965, 8, 2, 34, 539, NULL),
(185985618, b'1', NULL, 184, 184, NULL, b'0', b'0', 1609184507000, 60, 'Deviil', '2020-12-28 22:24:00', 4, 84810965, 8, 2, 11, 539, NULL),
(186008193, b'1', NULL, 12, 12, NULL, b'0', b'1', 1588250635000, 10, 'Kapouèh', '2020-12-28 22:24:00', 4, 84810965, 15, 9, 29, 539, NULL),
(186008261, b'1', NULL, 61, 61, NULL, b'0', b'0', 1606050934000, 50, 'Keelthanas', '2020-12-28 22:24:00', 4, 84810965, 13, 5, 29, 539, NULL),
(186261876, b'1', NULL, 113, 112, NULL, b'0', b'0', 1605285317000, 50, 'Titekawette', '2020-12-28 22:24:00', 4, 84810965, 10, 8, 25, 539, NULL),
(186309432, b'1', NULL, 30, 30, NULL, b'0', b'0', 1607986997000, 26, 'Akenatsu', '2020-12-28 22:24:00', 4, 84810965, 8, 7, 34, 539, NULL),
(186492449, b'1', NULL, 202, 200, NULL, b'0', b'0', 1609178859000, 60, 'Soulédan', '2020-12-28 22:24:00', 4, 84810965, 10, 12, 4, 539, NULL),
(186515018, b'1', NULL, 122, 122, NULL, b'0', b'0', 1606664366000, 50, 'Lundareï', '2020-12-28 22:24:00', 4, 84810965, 10, 11, 4, 539, NULL),
(186541595, b'1', NULL, 88, 88, NULL, b'0', b'0', 1605989626000, 50, 'Amolaric', '2020-12-28 22:24:00', 4, 84810965, 15, 7, 11, 539, NULL),
(186838547, b'1', NULL, 125, 121, NULL, b'0', b'0', 1606085035000, 50, 'ßlást', '2020-12-28 22:24:00', 4, 84810965, 10, 4, 1, 539, NULL),
(187198384, b'1', NULL, 123, 122, NULL, b'0', b'0', 1609066194000, 51, 'Däw', '2020-12-28 22:24:00', 4, 84810965, 8, 9, 7, 539, NULL),
(187243965, b'1', NULL, 128, 127, NULL, b'0', b'0', 1607809989000, 50, 'Killwill', '2020-12-28 22:24:00', 4, 84810965, 9, 4, 4, 539, NULL),
(187430518, b'1', NULL, 196, 196, NULL, b'0', b'0', 1609184597000, 60, 'Devîîl', '2020-12-28 22:24:00', 4, 84810965, 7, 3, 37, 539, NULL),
(187430625, b'1', NULL, 184, 183, NULL, b'0', b'0', 1609188240000, 60, 'Kalshamfitz', '2020-12-28 22:24:00', 4, 84810965, 8, 7, 32, 539, NULL),
(187467637, b'1', NULL, 92, 92, NULL, b'0', b'0', 1604868830000, 50, 'Astarioth', '2020-12-28 22:24:00', 4, 84810965, 10, 9, 1, 539, NULL),
(187475958, b'1', NULL, 176, 174, NULL, b'0', b'0', 1608141151000, 60, 'Valytria', '2020-12-28 22:24:00', 4, 84810965, 7, 10, 4, 539, NULL),
(187655882, b'1', NULL, 97, 97, NULL, b'0', b'0', 1605188943000, 50, 'Frý', '2020-12-28 22:24:00', 4, 84810965, 8, 8, 1, 539, NULL),
(187712084, b'1', NULL, 119, 118, NULL, b'0', b'0', 1609183935000, 50, 'Arunasura', '2020-12-28 22:24:00', 4, 84810965, 13, 9, 29, 539, NULL),
(187713485, b'1', NULL, 115, 114, NULL, b'0', b'0', 1606082751000, 50, 'Daëlynn', '2020-12-28 22:24:00', 4, 84810965, 13, 8, 29, 539, NULL),
(187746890, b'1', NULL, 153, 153, NULL, b'0', b'0', 1607466072000, 60, 'Thoratta', '2020-12-28 22:24:00', 4, 84810965, 8, 5, 1, 539, NULL),
(187863476, b'1', NULL, 103, 103, NULL, b'0', b'0', 1609004760000, 50, 'Lightfitz', '2020-12-28 22:24:00', 4, 84810965, 8, 2, 1, 539, NULL),
(187927602, b'1', NULL, 129, 126, NULL, b'0', b'1', 1603032557000, 50, 'Solarions', '2020-12-28 22:24:00', 4, 84810965, 9, 2, 1, 539, NULL),
(188094798, b'1', NULL, 206, 204, NULL, b'0', b'0', 1609184647000, 60, 'Yhørm', '2020-12-28 22:24:00', 4, 84810965, 9, 1, 4, 539, NULL),
(188274371, b'1', NULL, 108, 107, NULL, b'0', b'0', 1608937156000, 50, 'Ðäw', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 4, 539, NULL),
(188281634, b'1', NULL, 450, 447, NULL, b'0', b'1', 1596044908000, 50, 'Rums', '2020-12-28 22:24:00', 4, 84810965, 8, 3, 1, 539, NULL),
(188315956, b'1', NULL, 89, 89, NULL, b'0', b'0', 1608150986000, 50, 'Védal', '2020-12-28 22:24:00', 4, 84810965, 8, 5, 1, 539, NULL),
(188340504, b'1', NULL, 473, 471, NULL, b'0', b'1', 1601741977000, 50, 'Khorgrims', '2020-12-28 22:24:00', 4, 84810965, 8, 7, 34, 539, NULL),
(188582352, b'1', NULL, 119, 118, NULL, b'0', b'0', 1608405444000, 50, 'Miistâ', '2020-12-28 22:24:00', 4, 84810965, 8, 1, 1, 539, NULL),
(188720558, b'1', NULL, 120, 115, NULL, b'0', b'0', 1606084251000, 50, 'Êsräs', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 4, 539, NULL),
(188803041, b'1', NULL, 358, 358, NULL, b'0', b'1', 1597187631000, 50, 'Neoken', '2020-12-28 22:24:00', 4, 84810965, 8, 8, 1, 539, NULL),
(188875196, b'1', NULL, 70, 70, NULL, b'0', b'0', 1607989661000, 50, 'Thywise', '2020-12-28 22:24:00', 4, 84810965, 13, 1, 34, 539, NULL),
(189056062, b'1', NULL, 200, 195, NULL, b'0', b'0', 1608824825000, 60, 'Almâr', '2020-12-28 22:24:00', 4, 84810965, 9, 1, 1, 539, NULL),
(191351058, b'1', NULL, 122, 120, NULL, b'0', b'0', 1606745690000, 50, 'Almär', '2020-12-28 22:24:00', 4, 84810965, 9, 6, 1, 539, NULL),
(191380027, b'1', NULL, 86, 86, NULL, b'0', b'0', 1606854596000, 50, 'Aménelle', '2020-12-28 22:24:00', 4, 84810965, 8, 10, 25, 539, NULL),
(191443156, b'1', NULL, 29, 29, NULL, b'0', b'0', 1606070346000, 25, 'Zangfeï', '2020-12-28 22:24:00', 4, 84810965, 10, 6, 25, 539, NULL),
(191528267, b'1', NULL, 400, 399, NULL, b'0', b'1', 1601834742000, 50, 'Shannaras', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 4, 539, NULL),
(191548454, b'1', NULL, 102, 102, NULL, b'0', b'0', 1605907634000, 50, 'Fray', '2020-12-28 22:24:00', 4, 84810965, 8, 9, 1, 539, NULL),
(191598634, b'1', NULL, 109, 109, NULL, b'0', b'0', 1608983548000, 51, 'Kynixpriest', '2020-12-28 22:24:00', 4, 84810965, 9, 5, 30, 539, NULL),
(191605254, b'1', NULL, 112, 112, NULL, b'0', b'1', 1603383183000, 50, 'Solatrion', '2020-12-28 22:24:00', 4, 84810965, 9, 9, 1, 539, NULL),
(191634535, b'1', NULL, 194, 194, NULL, b'0', b'0', 1609193395000, 60, 'Galdwarlock', '2020-12-28 22:24:00', 4, 84810965, 9, 9, 7, 539, NULL),
(191642245, b'1', NULL, 182, 179, NULL, b'0', b'0', 1608768594000, 60, 'Kynixwar', '2020-12-28 22:24:00', 4, 84810965, 8, 1, 1, 539, NULL),
(191642251, b'1', NULL, 195, 194, NULL, b'0', b'0', 1609071295000, 60, 'Kynixpal', '2020-12-28 22:24:00', 4, 84810965, 9, 2, 3, 539, NULL),
(191721893, b'1', NULL, 10, 10, NULL, b'0', b'0', 1607989441000, 13, 'Rhéagirouet', '2020-12-28 22:24:00', 4, 84810965, 13, 10, 29, 539, NULL),
(191790228, b'1', NULL, 72, 72, NULL, b'0', b'0', 1608937256000, 50, 'Ðãw', '2020-12-28 22:24:00', 4, 84810965, 8, 10, 25, 539, NULL),
(191887124, b'1', NULL, 100, 94, NULL, b'0', b'1', 1603273091000, 50, 'Lêolulu', '2020-12-28 22:24:00', 4, 84810965, 8, 10, 1, 539, NULL),
(191931821, b'1', NULL, 110, 110, NULL, b'0', b'0', 1607160067000, 50, 'Dohakør', '2020-12-28 22:24:00', 4, 84810965, 15, 9, 29, 539, NULL),
(192037793, b'1', NULL, 197, 197, NULL, b'0', b'0', 1609184062000, 60, 'Udus', '2020-12-28 22:24:00', 4, 84810965, 13, 8, 32, 539, NULL),
(192095689, b'1', NULL, 107, 104, NULL, b'0', b'0', 1606576223000, 50, 'Møet', '2020-12-28 22:24:00', 4, 84810965, 9, 11, 4, 539, NULL),
(192164231, b'1', NULL, 195, 195, NULL, b'0', b'0', 1609148220000, 60, 'Lÿndreth', '2020-12-28 22:24:00', 4, 84810965, 9, 8, 4, 539, NULL),
(192164613, b'1', NULL, 17, 13, NULL, b'0', b'0', 1605138984000, 17, 'Dinkläge', '2020-12-28 22:24:00', 4, 84810965, 10, 4, 7, 539, NULL),
(192528938, b'1', NULL, 27, 26, NULL, b'0', b'1', 1603151592000, 23, 'Kuamä', '2020-12-28 22:24:00', 4, 84810965, 10, 10, 37, 539, NULL),
(192637695, b'1', NULL, 82, 82, NULL, b'0', b'0', 1605740072000, 50, 'Fatinøth', '2020-12-28 22:24:00', 4, 84810965, 8, 7, 32, 539, NULL),
(192845803, b'1', NULL, 29, 29, NULL, b'0', b'1', 1603377381000, 26, 'Loomie', '2020-12-28 22:24:00', 4, 84810965, 10, 6, 22, 539, NULL),
(192967458, b'1', NULL, 60, 60, NULL, b'0', b'1', 1602586888000, 24, 'Karnäk', '2020-12-28 22:24:00', 4, 84810965, 8, 5, 32, 539, NULL),
(193154992, b'1', NULL, 195, 195, NULL, b'0', b'0', 1609178175000, 60, 'Kamkaz', '2020-12-28 22:24:00', 4, 84810965, 9, 3, 4, 539, NULL),
(193179804, b'1', NULL, 62, 62, NULL, b'0', b'1', 1603308392000, 50, 'Pignoof', '2020-12-28 22:24:00', 4, 84810965, 8, 3, 37, 539, NULL),
(193294666, b'1', NULL, 197, 196, NULL, b'0', b'0', 1608251059000, 60, 'Mydoc', '2020-12-28 22:24:00', 4, 84810965, 7, 7, 11, 539, NULL),
(193302786, b'1', NULL, 69, 64, NULL, b'0', b'0', 1605788762000, 50, 'Thorttatone', '2020-12-28 22:24:00', 4, 84810965, 10, 2, 11, 539, NULL),
(193505740, b'1', NULL, 104, 104, NULL, b'0', b'0', 1606084202000, 50, 'Lédriel', '2020-12-28 22:24:00', 4, 84810965, 8, 5, 29, 539, NULL),
(193527740, b'1', NULL, 14, 14, NULL, b'0', b'0', 1606081541000, 13, 'Chandon', '2020-12-28 22:24:00', 4, 84810965, 8, 4, 37, 539, NULL),
(193527918, b'1', NULL, 12, 12, NULL, b'0', b'0', 1608607406000, 19, 'Alokakok', '2020-12-28 22:24:00', 4, 84810965, 10, 9, 1, 539, NULL),
(193535692, b'1', NULL, 63, 63, NULL, b'0', b'0', 1606194171000, 50, 'Déviil', '2020-12-28 22:24:00', 4, 84810965, 8, 6, 34, 539, NULL),
(193546955, b'1', NULL, 103, 103, NULL, b'0', b'0', 1606194145000, 50, 'Bryfou', '2020-12-28 22:24:00', 4, 84810965, 8, 12, 4, 539, NULL),
(193559054, b'1', NULL, 196, 194, NULL, b'0', b'0', 1609101972000, 60, 'Kamùì', '2020-12-28 22:24:00', 4, 84810965, 7, 2, 30, 539, NULL),
(193711148, b'1', NULL, 80, 80, NULL, b'0', b'1', 1603747096000, 50, 'Nyrenthia', '2020-12-28 22:24:00', 4, 84810965, 8, 5, 30, 539, NULL),
(193732098, b'1', NULL, 24, 24, NULL, b'0', b'1', 1603971490000, 25, 'Thorfufu', '2020-12-28 22:24:00', 4, 84810965, 8, 4, 7, 539, NULL),
(193880425, b'1', NULL, 99, 99, NULL, b'0', b'0', 1604951097000, 50, 'Zaheerä', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 22, 539, NULL),
(193882732, b'1', NULL, 40, 40, NULL, b'0', b'1', 1603727425000, 40, 'Kimzer', '2020-12-28 22:24:00', 4, 84810965, 10, 12, 4, 539, NULL),
(194061267, b'1', NULL, 27, 27, NULL, b'0', b'0', 1605476115000, 26, 'Dhafalgan', '2020-12-28 22:24:00', 4, 84810965, 8, 2, 1, 539, NULL),
(194061391, b'1', NULL, 94, 94, NULL, b'0', b'0', 1606217358000, 54, 'Soumour', '2020-12-28 22:24:00', 4, 84810965, 10, 12, 4, 539, NULL),
(194074369, b'1', NULL, 172, 169, NULL, b'0', b'0', 1608829890000, 60, 'Draksk', '2020-12-28 22:24:00', 4, 84810965, 8, 7, 3, 539, NULL),
(194079879, b'1', NULL, 69, 69, NULL, b'0', b'0', 1608469199000, 52, 'Crazypanda', '2020-12-28 22:24:00', 4, 84810965, 10, 10, 25, 539, NULL),
(194135455, b'1', NULL, 74, 68, NULL, b'0', b'0', 1605898734000, 50, 'Ätøme', '2020-12-28 22:24:00', 4, 84810965, 8, 3, 34, 539, NULL),
(194292412, b'1', NULL, 92, 92, NULL, b'0', b'0', 1606075792000, 50, 'Kenøsa', '2020-12-28 22:24:00', 4, 84810965, 13, 9, 29, 539, NULL),
(194292440, b'1', NULL, 64, 64, NULL, b'0', b'0', 1605905551000, 50, 'Pälaz', '2020-12-28 22:24:00', 4, 84810965, 8, 2, 30, 539, NULL),
(194330661, b'1', NULL, 45, 43, NULL, b'0', b'0', 1606677839000, 44, 'Ledriel', '2020-12-28 22:24:00', 4, 84810965, 8, 8, 29, 539, NULL),
(194452226, b'1', NULL, 59, 57, NULL, b'0', b'0', 1607721805000, 50, 'Gayya', '2020-12-28 22:24:00', 4, 84810965, 10, 6, 1, 539, NULL),
(194522340, b'1', NULL, 51, 50, NULL, b'0', b'0', 1606004166000, 50, 'Ibhuprofene', '2020-12-28 22:24:00', 4, 84810965, 8, 3, 7, 539, NULL),
(194592899, b'1', NULL, 126, 126, NULL, b'0', b'0', 1608564414000, 56, 'Liliahna', '2020-12-28 22:24:00', 4, 84810965, 8, 1, 1, 539, NULL),
(194681106, b'1', NULL, 115, 115, NULL, b'0', b'0', 1608139959000, 50, 'Oxow', '2020-12-28 22:24:00', 4, 84810965, 8, 6, 29, 539, NULL),
(194833954, b'1', NULL, 165, 165, NULL, b'0', b'0', 1608670949000, 60, 'Pandarouusse', '2020-12-28 22:24:00', 4, 84810965, 10, 7, 25, 539, NULL),
(194840816, b'1', NULL, 13, 13, NULL, b'0', b'0', 1609018579000, 20, 'Gangquán', '2020-12-28 22:24:00', 4, 84810965, 10, 10, 34, 539, NULL),
(194941099, b'1', NULL, 58, 58, NULL, b'0', b'0', 1608340712000, 50, 'Dévîll', '2020-12-28 22:24:00', 4, 84810965, 8, 3, 22, 539, NULL),
(194941136, b'1', NULL, 17, 17, NULL, b'0', b'0', 1604185369000, 20, 'Chiingchong', '2020-12-28 22:24:00', 4, 84810965, 8, 10, 25, 539, NULL),
(194978220, b'1', NULL, 165, 150, NULL, b'0', b'0', 1607121292000, 60, 'Hivius', '2020-12-28 22:24:00', 4, 84810965, 8, 3, 1, 539, NULL),
(195186892, b'1', NULL, 189, 189, NULL, b'0', b'0', 1608286617000, 60, 'Kvanyrh', '2020-12-28 22:24:00', 4, 84810965, 10, 6, 30, 539, NULL),
(195188508, b'1', NULL, 11, 11, NULL, b'0', b'0', 1608582508000, 11, 'Akìrame', '2020-12-28 22:24:00', 4, 84810965, 10, 5, 11, 539, NULL),
(195198183, b'1', NULL, 187, 186, NULL, b'0', b'0', 1609187137000, 60, 'Nídhögg', '2020-12-28 22:24:00', 4, 84810965, 9, 11, 22, 539, NULL),
(195198241, b'1', NULL, 89, 89, NULL, b'0', b'0', 1606614618000, 50, 'Déévîîl', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 4, 539, NULL),
(195246149, b'1', NULL, 185, 185, NULL, b'0', b'0', 1607639827000, 60, 'Talador', '2020-12-28 22:24:00', 4, 84810965, 8, 8, 1, 539, NULL),
(195255487, b'1', NULL, 150, 149, NULL, b'0', b'0', 1607365228000, 60, 'Sotits', '2020-12-28 22:24:00', 4, 84810965, 10, 9, 1, 539, NULL),
(195255694, b'1', NULL, 151, 151, NULL, b'0', b'0', 1609175767000, 60, 'Gamabuntta', '2020-12-28 22:24:00', 4, 84810965, 10, 11, 4, 539, NULL),
(195545040, b'1', NULL, 96, 94, NULL, b'0', b'0', 1605700915000, 50, 'Catepal', '2020-12-28 22:24:00', 4, 84810965, 8, 2, 30, 539, NULL),
(195634785, b'1', NULL, 204, 201, NULL, b'0', b'0', 1609190630000, 60, 'Rodoudrood', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 4, 539, NULL),
(195683469, b'1', NULL, 150, 149, NULL, b'0', b'0', 1606857909000, 60, 'Rhaego', '2020-12-28 22:24:00', 4, 84810965, 10, 2, 1, 539, NULL),
(195701264, b'1', NULL, 57, 57, NULL, b'0', b'0', 1606169210000, 50, 'Zireaelle', '2020-12-28 22:24:00', 4, 84810965, 8, 4, 4, 539, NULL),
(195764155, b'1', NULL, 20, 17, NULL, b'0', b'0', 1606084165000, 21, 'Silveryz', '2020-12-28 22:24:00', 4, 84810965, 8, 4, 4, 539, NULL),
(195906939, b'1', NULL, 195, 195, NULL, b'0', b'0', 1609194038000, 60, 'Moùsse', '2020-12-28 22:24:00', 4, 84810965, 9, 10, 1, 539, NULL),
(196066712, b'1', NULL, 65, 65, NULL, b'0', b'0', 1606153351000, 50, 'Alphir', '2020-12-28 22:24:00', 4, 84810965, 8, 9, 34, 539, NULL),
(196552220, b'1', NULL, 62, 60, NULL, b'0', b'0', 1606745070000, 51, 'Hïvius', '2020-12-28 22:24:00', 4, 84810965, 10, 8, 1, 539, NULL),
(196554876, b'1', NULL, 191, 191, NULL, b'0', b'0', 1609173527000, 60, 'Oxøw', '2020-12-28 22:24:00', 4, 84810965, 8, 5, 29, 539, NULL),
(196622962, b'1', NULL, 197, 197, NULL, b'0', b'0', 1609185325000, 60, 'Nylàn', '2020-12-28 22:24:00', 4, 84810965, 10, 5, 1, 539, NULL),
(196977875, b'1', NULL, 168, 168, NULL, b'0', b'0', 1609191960000, 60, 'Ryuren', '2020-12-28 22:24:00', 4, 84810965, 8, 11, 4, 539, NULL),
(197167600, b'1', NULL, 8, 8, NULL, b'0', b'0', 1608473960000, 10, 'Gélabankette', '2020-12-28 22:24:00', 4, 84810965, 13, 4, 37, 539, NULL),
(197229402, b'1', NULL, 201, 199, NULL, b'0', b'0', 1609179174000, 60, 'Dröoder', '2020-12-28 22:24:00', 4, 84810965, 10, 11, 22, 539, NULL),
(197251535, b'1', NULL, 13, 13, NULL, b'0', b'0', 1609078430000, 15, 'Døhakor', '2020-12-28 22:24:00', 4, 84810965, 15, 2, 30, 539, NULL),
(197612257, b'1', NULL, 16, 16, NULL, b'0', b'0', 1609078441000, 18, 'Elrinor', '2020-12-28 22:24:00', 4, 84810965, 13, 2, 30, 539, NULL),
(197619949, b'1', NULL, 14, 14, NULL, b'0', b'0', 1609077310000, 11, 'Akhë', '2020-12-28 22:24:00', 4, 84810965, 8, 6, 34, 539, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `FACTIONS`
--

CREATE TABLE `FACTIONS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `DE_DE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_GB` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_US` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_ES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_MX` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FR_FR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IT_IT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `KO_KR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PT_BR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RU_RU` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_CN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_TW` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TYPE` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `FACTIONS`
--

INSERT INTO `FACTIONS` (`ID`, `IS_UPDATED`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `TYPE`) VALUES
(4, b'1', 'Allianz', 'Alliance', 'Alliance', 'Alianza', 'Alianza', 'Alliance', 'Alleanza', '얼라이언스', 'Aliança', 'Альянс', '联盟', '聯盟', 'ALLIANCE'),
(5, b'1', 'Horde', 'Horde', 'Horde', 'Horda', 'Horda', 'Horde', 'Orda', '호드', 'Horda', 'Орда', '部落', '部落', 'HORDE'),
(6, b'1', 'Neutral', 'Neutral', 'Neutral', 'Neutral', 'Neutral', 'Neutre', 'Neutrale', '중립 지역', 'Neutra', 'Нейтралы', '中立', '中立', 'NEUTRAL');

-- --------------------------------------------------------

--
-- Structure de la table `GUILDS`
--

CREATE TABLE `GUILDS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `MEMBER_COUNT` int DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `USE_APPLICATION` bit(1) DEFAULT NULL,
  `FACTION_ID` int DEFAULT NULL,
  `REALM_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `GUILDS`
--

INSERT INTO `GUILDS` (`ID`, `IS_UPDATED`, `MEMBER_COUNT`, `NAME`, `UPDATE_DATE`, `USE_APPLICATION`, `FACTION_ID`, `REALM_ID`) VALUES
(84810965, b'1', 191, 'PeyPouZe', '2020-12-28 22:23:28', b'1', 4, 539);

-- --------------------------------------------------------

--
-- Structure de la table `GUILD_RANKS`
--

CREATE TABLE `GUILD_RANKS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GUILD_RANK` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `GUILD_RANKS`
--

INSERT INTO `GUILD_RANKS` (`ID`, `IS_UPDATED`, `NAME`, `GUILD_RANK`) VALUES
(7, b'1', 'RANK_6', 6),
(8, b'1', 'RANK_8', 8),
(9, b'1', 'RANK_7', 7),
(10, b'1', 'RANK_9', 9),
(11, b'1', 'RANK_4', 4),
(12, b'1', 'RANK_2', 2),
(13, b'1', 'RANK_3', 3),
(14, b'1', 'RANK_0', 0),
(15, b'1', 'RANK_1', 1);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(16),
(16),
(16);

-- --------------------------------------------------------

--
-- Structure de la table `PLAYABLE_CLASSES`
--

CREATE TABLE `PLAYABLE_CLASSES` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `DE_DE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_GB` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_US` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_ES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_MX` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FR_FR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IT_IT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `KO_KR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PT_BR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RU_RU` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_CN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_TW` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MEDIA_URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `PLAYABLE_CLASSES`
--

INSERT INTO `PLAYABLE_CLASSES` (`ID`, `IS_UPDATED`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `MEDIA_URL`) VALUES
(1, b'1', 'Krieger', 'Warrior', 'Warrior', 'Guerrero', 'Guerrero', 'Guerrier', 'Guerriero', '전사', 'Guerreiro', 'Воин', '战士', '戰士', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_warrior.jpg'),
(2, b'1', 'Paladin', 'Paladin', 'Paladin', 'Paladín', 'Paladín', 'Paladin', 'Paladino', '성기사', 'Paladino', 'Паладин', '圣骑士', '聖騎士', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_paladin.jpg'),
(3, b'1', 'Jäger', 'Hunter', 'Hunter', 'Cazador', 'Cazador', 'Chasseur', 'Cacciatore', '사냥꾼', 'Caçador', 'Охотник', '猎人', '獵人', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_hunter.jpg'),
(4, b'1', 'Schurke', 'Rogue', 'Rogue', 'Pícaro', 'Pícaro', 'Voleur', 'Ladro', '도적', 'Ladino', 'Разбойник', '潜行者', '盜賊', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_rogue.jpg'),
(5, b'1', 'Priester', 'Priest', 'Priest', 'Sacerdote', 'Sacerdote', 'Prêtre', 'Sacerdote', '사제', 'Sacerdote', 'Жрец', '牧师', '牧師', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_priest.jpg'),
(6, b'1', 'Todesritter', 'Death Knight', 'Death Knight', 'Caballero de la Muerte', 'Caballero de la Muerte', 'Chevalier de la mort', 'Cavaliere della Morte', '죽음의 기사', 'Cavaleiro da Morte', 'Рыцарь смерти', '死亡骑士', '死亡騎士', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_classicon.jpg'),
(7, b'1', 'Schamane', 'Shaman', 'Shaman', 'Chamán', 'Chamán', 'Chaman', 'Sciamano', '주술사', 'Xamã', 'Шаман', '萨满祭司', '薩滿', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_shaman.jpg'),
(8, b'1', 'Magier', 'Mage', 'Mage', 'Mago', 'Mago', 'Mage', 'Mago', '마법사', 'Mago', 'Маг', '法师', '法師', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_mage.jpg'),
(9, b'1', 'Hexenmeister', 'Warlock', 'Warlock', 'Brujo', 'Brujo', 'Démoniste', 'Stregone', '흑마법사', 'Bruxo', 'Чернокнижник', '术士', '術士', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_warlock.jpg'),
(10, b'1', 'Mönch', 'Monk', 'Monk', 'Monje', 'Monje', 'Moine', 'Monaco', '수도사', 'Monge', 'Монах', '武僧', '武僧', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_monk.jpg'),
(11, b'1', 'Druide', 'Druid', 'Druid', 'Druida', 'Druida', 'Druide', 'Druido', '드루이드', 'Druida', 'Друид', '德鲁伊', '德魯伊', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_druid.jpg'),
(12, b'1', 'Dämonenjäger', 'Demon Hunter', 'Demon Hunter', 'Cazador de demonios', 'Cazador de demonios', 'Chasseur de démons', 'Cacciatore di Demoni', '악마사냥꾼', 'Caçador de Demônios', 'Охотник на демонов', '恶魔猎手', '惡魔獵人', 'https://render-eu.worldofwarcraft.com/icons/56/achievement_boss_illidan.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `PLAYABLE_RACES`
--

CREATE TABLE `PLAYABLE_RACES` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `DE_DE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_GB` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_US` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_ES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_MX` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FR_FR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IT_IT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `KO_KR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PT_BR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RU_RU` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_CN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_TW` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FACTION_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `PLAYABLE_RACES`
--

INSERT INTO `PLAYABLE_RACES` (`ID`, `IS_UPDATED`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `FACTION_ID`) VALUES
(1, b'1', 'Mensch', 'Human', 'Human', 'Humano', 'Humano', 'Humain', 'Umano', '인간', 'Humano', 'Человек', '人类', '人類', 4),
(2, b'1', 'Orc', 'Orc', 'Orc', 'Orco', 'Orco', 'Orc', 'Orco', '오크', 'Orc', 'Орк', '兽人', '獸人', 5),
(3, b'1', 'Zwerg', 'Dwarf', 'Dwarf', 'Enano', 'Enano', 'Nain', 'Nano', '드워프', 'Anão', 'Дворф', '矮人', '矮人', 4),
(4, b'1', 'Nachtelf', 'Night Elf', 'Night Elf', 'Elfo de la noche', 'Elfo de la noche', 'Elfe de la nuit', 'Elfo della Notte', '나이트 엘프', 'Elfo Noturno', 'Ночной эльф', '暗夜精灵', '夜精靈', 4),
(5, b'1', 'Untoter', 'Undead', 'Undead', 'No-muerto', 'No-muerto', 'Mort-vivant', 'Non Morto', '언데드', 'Morto-vivo', 'Нежить', '亡灵', '不死族', 5),
(6, b'1', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', '타우렌', 'Tauren', 'Таурен', '牛头人', '牛頭人', 5),
(7, b'1', 'Gnom', 'Gnome', 'Gnome', 'Gnomo', 'Gnomo', 'Gnome', 'Gnomo', '노움', 'Gnomo', 'Гном', '侏儒', '地精', 4),
(8, b'1', 'Troll', 'Troll', 'Troll', 'Trol', 'Trol', 'Troll', 'Troll', '트롤', 'Troll', 'Тролль', '巨魔', '食人妖', 5),
(9, b'1', 'Goblin', 'Goblin', 'Goblin', 'Goblin', 'Goblin', 'Gobelin', 'Goblin', '고블린', 'Goblin', 'Гоблин', '地精', '哥布林', 5),
(10, b'1', 'Blutelf', 'Blood Elf', 'Blood Elf', 'Elfo de sangre', 'Elfo de sangre', 'Elfe de sang', 'Elfo del Sangue', '블러드 엘프', 'Elfo Sangrento', 'Эльф крови', '血精灵', '血精靈', 5),
(11, b'1', 'Draenei', 'Draenei', 'Draenei', 'Draenei', 'Draenei', 'Draeneï', 'Draenei', '드레나이', 'Draenei', 'Дреней', '德莱尼', '德萊尼', 4),
(22, b'1', 'Worgen', 'Worgen', 'Worgen', 'Huargen', 'Huargen', 'Worgen', 'Worgen', '늑대인간', 'Worgen', 'Ворген', '狼人', '狼人', 4),
(24, b'1', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 6),
(25, b'1', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 4),
(26, b'1', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 5),
(27, b'1', 'Nachtgeborener', 'Nightborne', 'Nightborne', 'Nocheterna', 'Natonocturno', 'Sacrenuit', 'Nobile Oscuro', '나이트본', 'Filho da Noite', 'Ночнорожденный', '夜之子', '夜裔精靈', 5),
(28, b'1', 'Hochbergtauren', 'Highmountain Tauren', 'Highmountain Tauren', 'Tauren Monte Alto', 'Tauren de Altamontaña', 'Tauren de Haut-Roc', 'Tauren di Alto Monte', '높은산 타우렌', 'Tauren Altamontês', 'Таурен Крутогорья', '至高岭牛头人', '高嶺牛頭人', 5),
(29, b'1', 'Leerenelf', 'Void Elf', 'Void Elf', 'Elfo del Vacío', 'Elfo del Vacío', 'Elfe du Vide', 'Elfo del Vuoto', '공허 엘프', 'Elfo Caótico', 'Эльф Бездны', '虚空精灵', '虛無精靈', 4),
(30, b'1', 'Lichtgeschmiedeter Draenei', 'Lightforged Draenei', 'Lightforged Draenei', 'Draenei forjado por la Luz', 'Draenei templeluz', 'Draeneï sancteforge', 'Draenei Forgialuce', '빛벼림 드레나이', 'Draenei Forjado a Luz', 'Озаренный дреней', '光铸德莱尼', '光鑄德萊尼', 4),
(31, b'1', 'Zandalaritroll', 'Zandalari Troll', 'Zandalari Troll', 'Trol Zandalari', 'Trol zandalari', 'Troll zandalari', 'Troll Zandalari', '잔달라 트롤', 'Troll Zandalari', 'Зандалар', '赞达拉巨魔', '贊達拉食人妖', 5),
(32, b'1', 'Kul Tiraner', 'Kul Tiran', 'Kul Tiran', 'Ciudadano de Kul Tiras', 'Kultirano', 'Kultirassien', 'Kul Tirano', '쿨 티란', 'Kultireno', 'Култирасец', '库尔提拉斯人', '庫爾提拉斯人', 4),
(34, b'1', 'Dunkeleisenzwerg', 'Dark Iron Dwarf', 'Dark Iron Dwarf', 'Enano Hierro Negro', 'Enano Hierro Negro', 'Nain sombrefer', 'Nano Ferroscuro', '검은무쇠 드워프', 'Anão Ferro Negro', 'Дворф из клана Черного Железа', '黑铁矮人', '黑鐵矮人', 4),
(35, b'1', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpérin', 'Vulpera', '불페라', 'Vulpera', 'Вульпера', '狐人', '狐狸人', 5),
(36, b'1', 'Mag\'har', 'Mag\'har Orc', 'Mag\'har Orc', 'Orco Mag\'har', 'Orco mag\'har', 'Orc mag’har', 'Orco Mag\'har', '마그하르 오크', 'Orc Mag\'har', 'Маг\'хар', '玛格汉兽人', '瑪格哈獸人', 5),
(37, b'1', 'Mechagnom', 'Mechagnome', 'Mechagnome', 'Mecagnomo', 'Mecagnomo', 'Mécagnome', 'Meccagnomo', '기계노움', 'Gnomecânico', 'Механогном', '机械侏儒', '機械地精', 4);

-- --------------------------------------------------------

--
-- Structure de la table `PLAYABLE_SPECIALIZATIONS`
--

CREATE TABLE `PLAYABLE_SPECIALIZATIONS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `DE_DE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_GB` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_US` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_ES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_MX` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FR_FR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IT_IT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `KO_KR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PT_BR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RU_RU` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_CN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_TW` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MEDIA_URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PLAYABLE_CLASS_ID` int DEFAULT NULL,
  `SPECIALIZATION_ROLE_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `PLAYABLE_SPECIALIZATIONS`
--

INSERT INTO `PLAYABLE_SPECIALIZATIONS` (`ID`, `IS_UPDATED`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `MEDIA_URL`, `PLAYABLE_CLASS_ID`, `SPECIALIZATION_ROLE_ID`) VALUES
(62, b'1', 'Arkan', 'Arcane', 'Arcane', 'Arcano', 'Arcano', 'Arcanes', 'Arcano', '비전', 'Arcano', 'Тайная магия', '奥术', '秘法', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_magicalsentry.jpg', 8, 1),
(63, b'1', 'Feuer', 'Fire', 'Fire', 'Fuego', 'Fuego', 'Feu', 'Fuoco', '화염', 'Fogo', 'Огонь', '火焰', '火焰', 'https://render-eu.worldofwarcraft.com/icons/56/spell_fire_firebolt02.jpg', 8, 1),
(64, b'1', 'Frost', 'Frost', 'Frost', 'Escarcha', 'Escarcha', 'Givre', 'Gelo', '냉기', 'Gélido', 'Лед', '冰霜', '冰霜', 'https://render-eu.worldofwarcraft.com/icons/56/spell_frost_frostbolt02.jpg', 8, 1),
(65, b'1', 'Heilig', 'Holy', 'Holy', 'Sagrado', 'Sagrado', 'Sacré', 'Sacro', '신성', 'Sagrado', 'Свет', '神圣', '神聖', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_holybolt.jpg', 2, 3),
(66, b'1', 'Schutz', 'Protection', 'Protection', 'Protección', 'Protección', 'Protection', 'Protezione', '보호', 'Proteção', 'Защита', '防护', '防護', 'https://render-eu.worldofwarcraft.com/icons/56/ability_paladin_shieldofthetemplar.jpg', 2, 2),
(70, b'1', 'Vergeltung', 'Retribution', 'Retribution', 'Reprensión', 'Reprensión', 'Vindicte', 'Castigo', '징벌', 'Retribuição', 'Воздаяние', '惩戒', '懲戒', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_auraoflight.jpg', 2, 1),
(71, b'1', 'Waffen', 'Arms', 'Arms', 'Armas', 'Armas', 'Armes', 'Armi', '무기', 'Armas', 'Оружие', '武器', '武器', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_savageblow.jpg', 1, 1),
(72, b'1', 'Furor', 'Fury', 'Fury', 'Furia', 'Furia', 'Fureur', 'Furia', '분노', 'Fúria', 'Неистовство', '狂怒', '狂怒', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_innerrage.jpg', 1, 1),
(73, b'1', 'Schutz', 'Protection', 'Protection', 'Protección', 'Protección', 'Protection', 'Protezione', '방어', 'Proteção', 'Защита', '防护', '防護', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_defensivestance.jpg', 1, 2),
(102, b'1', 'Gleichgewicht', 'Balance', 'Balance', 'Equilibrio', 'Equilibrio', 'Équilibre', 'Equilibrio', '조화', 'Equilíbrio', 'Баланс', '平衡', '平衡', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_starfall.jpg', 11, 1),
(103, b'1', 'Wildheit', 'Feral', 'Feral', 'Feral', 'Feral', 'Farouche', 'Aggressore Ferino', '야성', 'Feral', 'Сила зверя', '野性', '野性戰鬥', 'https://render-eu.worldofwarcraft.com/icons/56/ability_druid_catform.jpg', 11, 1),
(104, b'1', 'Wächter', 'Guardian', 'Guardian', 'Guardián', 'Guardián', 'Gardien', 'Guardiano Ferino', '수호', 'Guardião', 'Страж', '守护', '守護者', 'https://render-eu.worldofwarcraft.com/icons/56/ability_racial_bearform.jpg', 11, 2),
(105, b'1', 'Wiederherstellung', 'Restoration', 'Restoration', 'Restauración', 'Restauración', 'Restauration', 'Rigenerazione', '회복', 'Restauração', 'Исцеление', '恢复', '恢復', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_healingtouch.jpg', 11, 3),
(250, b'1', 'Blut', 'Blood', 'Blood', 'Sangre', 'Sangre', 'Sang', 'Sangue', '혈기', 'Sangue', 'Кровь', '鲜血', '血魄', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_bloodpresence.jpg', 6, 2),
(251, b'1', 'Frost', 'Frost', 'Frost', 'Escarcha', 'Escarcha', 'Givre', 'Gelo', '냉기', 'Gélido', 'Лед', '冰霜', '冰霜', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_frostpresence.jpg', 6, 1),
(252, b'1', 'Unheilig', 'Unholy', 'Unholy', 'Profano', 'Profano', 'Impie', 'Empietà', '부정', 'Profano', 'Нечестивость', '邪恶', '穢邪', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_unholypresence.jpg', 6, 1),
(253, b'1', 'Tierherrschaft', 'Beast Mastery', 'Beast Mastery', 'Bestias', 'Bestias', 'Maîtrise des bêtes', 'Affinità Animale', '야수', 'Domínio das Feras', 'Повелитель зверей', '野兽控制', '野獸控制', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_bestialdiscipline.jpg', 3, 1),
(254, b'1', 'Treffsicherheit', 'Marksmanship', 'Marksmanship', 'Puntería', 'Puntería', 'Précision', 'Precisione di Tiro', '사격', 'Precisão', 'Стрельба', '射击', '射擊', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_focusedaim.jpg', 3, 1),
(255, b'1', 'Überleben', 'Survival', 'Survival', 'Supervivencia', 'Supervivencia', 'Survie', 'Sopravvivenza', '생존', 'Sobrevivência', 'Выживание', '生存', '生存', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_camouflage.jpg', 3, 1),
(256, b'1', 'Disziplin', 'Discipline', 'Discipline', 'Disciplina', 'Disciplina', 'Discipline', 'Disciplina', '수양', 'Disciplina', 'Послушание', '戒律', '戒律', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_powerwordshield.jpg', 5, 3),
(257, b'1', 'Heilig', 'Holy', 'Holy', 'Sagrado', 'Sagrado', 'Sacré', 'Sacro', '신성', 'Sagrado', 'Свет', '神圣', '神聖', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_guardianspirit.jpg', 5, 3),
(258, b'1', 'Schatten', 'Shadow', 'Shadow', 'Sombra', 'Sombra', 'Ombre', 'Ombra', '암흑', 'Sombra', 'Тьма', '暗影', '暗影', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_shadowwordpain.jpg', 5, 1),
(259, b'1', 'Meucheln', 'Assassination', 'Assassination', 'Asesinato', 'Asesinato', 'Assassinat', 'Assassinio', '암살', 'Assassinato', 'Ликвидация', '奇袭', '刺殺', 'https://render-eu.worldofwarcraft.com/icons/56/ability_rogue_deadlybrew.jpg', 4, 1),
(260, b'1', 'Gesetzlosigkeit', 'Outlaw', 'Outlaw', 'Forajido', 'Forajido', 'Hors-la-loi', 'Fuorilegge', '무법', 'Fora da Lei', 'Головорез', '狂徒', '暴徒', 'https://render-eu.worldofwarcraft.com/icons/56/ability_rogue_waylay.jpg', 4, 1),
(261, b'1', 'Täuschung', 'Subtlety', 'Subtlety', 'Sutileza', 'Sutileza', 'Finesse', 'Scaltrezza', '잠행', 'Subterfúgio', 'Скрытность', '敏锐', '敏銳', 'https://render-eu.worldofwarcraft.com/icons/56/ability_stealth.jpg', 4, 1),
(262, b'1', 'Elementar', 'Elemental', 'Elemental', 'Elemental', 'Elemental', 'Élémentaire', 'Elementale', '정기', 'Elemental', 'Стихии', '元素', '元素', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_lightning.jpg', 7, 1),
(263, b'1', 'Verstärkung', 'Enhancement', 'Enhancement', 'Mejora', 'Mejora', 'Amélioration', 'Potenziamento', '고양', 'Aperfeiçoamento', 'Совершенствование', '增强', '增強', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shaman_improvedstormstrike.jpg', 7, 1),
(264, b'1', 'Wiederherstellung', 'Restoration', 'Restoration', 'Restauración', 'Restauración', 'Restauration', 'Rigenerazione', '복원', 'Restauração', 'Исцеление', '恢复', '恢復', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_magicimmunity.jpg', 7, 3),
(265, b'1', 'Gebrechen', 'Affliction', 'Affliction', 'Aflicción', 'Aflicción', 'Affliction', 'Afflizione', '고통', 'Suplício', 'Колдовство', '痛苦', '痛苦', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_deathcoil.jpg', 9, 1),
(266, b'1', 'Dämonologie', 'Demonology', 'Demonology', 'Demonología', 'Demonología', 'Démonologie', 'Demonologia', '악마', 'Demonologia', 'Демонология', '恶魔学识', '惡魔學識', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_metamorphosis.jpg', 9, 1),
(267, b'1', 'Zerstörung', 'Destruction', 'Destruction', 'Destrucción', 'Destrucción', 'Destruction', 'Distruzione', '파괴', 'Destruição', 'Разрушение', '毁灭', '毀滅', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_rainoffire.jpg', 9, 1),
(268, b'1', 'Braumeister', 'Brewmaster', 'Brewmaster', 'Maestro cervecero', 'Maestro cervecero', 'Maître brasseur', 'Mastro Birraio', '양조', 'Mestre Cervejeiro', 'Хмелевар', '酒仙', '釀酒', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_brewmaster_spec.jpg', 10, 2),
(269, b'1', 'Windläufer', 'Windwalker', 'Windwalker', 'Viajero del viento', 'Viajero del viento', 'Marche-vent', 'Impeto', '풍운', 'Andarilho do Vento', 'Танцующий с ветром', '踏风', '御風', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_windwalker_spec.jpg', 10, 1),
(270, b'1', 'Nebelwirker', 'Mistweaver', 'Mistweaver', 'Tejedor de niebla', 'Tejedor de niebla', 'Tisse-brume', 'Misticismo', '운무', 'Tecelão da Névoa', 'Ткач туманов', '织雾', '織霧', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_mistweaver_spec.jpg', 10, 3),
(577, b'1', 'Verwüstung', 'Havoc', 'Havoc', 'Devastación', 'Caos', 'Dévastation', 'Rovina', '파멸', 'Devastação', 'Истребление', '浩劫', '災虐', 'https://render-eu.worldofwarcraft.com/icons/56/ability_demonhunter_specdps.jpg', 12, 1),
(581, b'1', 'Rachsucht', 'Vengeance', 'Vengeance', 'Venganza', 'Venganza', 'Vengeance', 'Vendetta', '복수', 'Vingança', 'Месть', '复仇', '復仇', 'https://render-eu.worldofwarcraft.com/icons/56/ability_demonhunter_spectank.jpg', 12, 2);

-- --------------------------------------------------------

--
-- Structure de la table `REALMS`
--

CREATE TABLE `REALMS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `DE_DE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_GB` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_US` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_ES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_MX` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FR_FR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IT_IT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `KO_KR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PT_BR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RU_RU` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_CN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_TW` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SLUG` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `REALMS`
--

INSERT INTO `REALMS` (`ID`, `IS_UPDATED`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `SLUG`) VALUES
(539, b'1', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', '阿克蒙德', '阿克蒙德', 'archimonde');

-- --------------------------------------------------------

--
-- Structure de la table `SPECIALIZATION_ROLES`
--

CREATE TABLE `SPECIALIZATION_ROLES` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `DE_DE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_GB` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EN_US` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_ES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ES_MX` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FR_FR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IT_IT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `KO_KR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PT_BR` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RU_RU` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_CN` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ZH_TW` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TYPE` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `SPECIALIZATION_ROLES`
--

INSERT INTO `SPECIALIZATION_ROLES` (`ID`, `IS_UPDATED`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `TYPE`) VALUES
(1, b'1', 'Schaden', 'Damage', 'Damage', 'Daño', 'Daño', 'Dégâts', 'Assaltatore', '공격 전담', 'Dano', 'Боец', '伤害输出', '傷害輸出', 'DAMAGE'),
(2, b'1', 'Tank', 'Tank', 'Tank', 'Tanque', 'Tanque', 'Tank', 'Difensore', '방어 전담', 'Tanque', 'Танк', '坦克', '坦克', 'TANK'),
(3, b'1', 'Heilung', 'Healer', 'Healer', 'Sanador', 'Sanador', 'Soins', 'Guaritore', '치유 전담', 'Cura', 'Лекарь', '治疗', '治療者', 'HEALER');

-- --------------------------------------------------------

--
-- Structure de la table `USER_ACCOUNT`
--

CREATE TABLE `USER_ACCOUNT` (
  `BLIZZARD_ID` int NOT NULL,
  `BATTLE_TAG` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CREATION_DATE_TIME` datetime NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `NICK_NAME` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `UPDATE_DATE_TIME` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `CHARACTERS`
--
ALTER TABLE `CHARACTERS`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKsqlb9e2nt3eftsaadv1yeu8lm` (`FACTION_ID`),
  ADD KEY `FK81mb8xudfwnt3pfsg8n2xict` (`GUILD_ID`),
  ADD KEY `FKhu2ubby85vp38eff50jlu3vv2` (`GUILD_RANK_ID`),
  ADD KEY `FK1waptb5463xrawi5vd1qa40jc` (`PLAYABLE_CLASS_ID`),
  ADD KEY `FK24t2si16hjyr1a6rifypkmspf` (`PLAYABLE_RACE_ID`),
  ADD KEY `FKrg2q8frb9768dltvrcepq2wt1` (`REALM_ID`),
  ADD KEY `FKaj31msaiobnppeetkmcdcsomy` (`USER_ACCOUNT_ID`);

--
-- Index pour la table `FACTIONS`
--
ALTER TABLE `FACTIONS`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_86xiwkjnb2ly7qvdu3pvu37hr` (`TYPE`);

--
-- Index pour la table `GUILDS`
--
ALTER TABLE `GUILDS`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKnsx4lvrdhk0xykm96swyhe64x` (`FACTION_ID`),
  ADD KEY `FKbc8b0f0npxdtaid7u7n8hralb` (`REALM_ID`);

--
-- Index pour la table `GUILD_RANKS`
--
ALTER TABLE `GUILD_RANKS`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `PLAYABLE_CLASSES`
--
ALTER TABLE `PLAYABLE_CLASSES`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `PLAYABLE_RACES`
--
ALTER TABLE `PLAYABLE_RACES`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKifi40xiidm18hf0udcwge2vn9` (`FACTION_ID`);

--
-- Index pour la table `PLAYABLE_SPECIALIZATIONS`
--
ALTER TABLE `PLAYABLE_SPECIALIZATIONS`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKfop6bkcn7ca15av4pd4wrd6ls` (`PLAYABLE_CLASS_ID`),
  ADD KEY `FKqrjw9tjrwvel7s2p75aev6jvw` (`SPECIALIZATION_ROLE_ID`);

--
-- Index pour la table `REALMS`
--
ALTER TABLE `REALMS`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_i54fqe1cvgloy91n54kfnyred` (`SLUG`);

--
-- Index pour la table `SPECIALIZATION_ROLES`
--
ALTER TABLE `SPECIALIZATION_ROLES`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_glq8xathhpsysfed3k1sqmtwq` (`TYPE`);

--
-- Index pour la table `USER_ACCOUNT`
--
ALTER TABLE `USER_ACCOUNT`
  ADD PRIMARY KEY (`BLIZZARD_ID`),
  ADD UNIQUE KEY `UK_BATTLE_TAG` (`BATTLE_TAG`),
  ADD UNIQUE KEY `UK_EMAIL` (`EMAIL`),
  ADD UNIQUE KEY `UK_NICK_NAME` (`NICK_NAME`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `CHARACTERS`
--
ALTER TABLE `CHARACTERS`
  ADD CONSTRAINT `FK1waptb5463xrawi5vd1qa40jc` FOREIGN KEY (`PLAYABLE_CLASS_ID`) REFERENCES `PLAYABLE_CLASSES` (`ID`),
  ADD CONSTRAINT `FK24t2si16hjyr1a6rifypkmspf` FOREIGN KEY (`PLAYABLE_RACE_ID`) REFERENCES `PLAYABLE_RACES` (`ID`),
  ADD CONSTRAINT `FK81mb8xudfwnt3pfsg8n2xict` FOREIGN KEY (`GUILD_ID`) REFERENCES `GUILDS` (`ID`),
  ADD CONSTRAINT `FKaj31msaiobnppeetkmcdcsomy` FOREIGN KEY (`USER_ACCOUNT_ID`) REFERENCES `USER_ACCOUNT` (`BLIZZARD_ID`),
  ADD CONSTRAINT `FKhu2ubby85vp38eff50jlu3vv2` FOREIGN KEY (`GUILD_RANK_ID`) REFERENCES `GUILD_RANKS` (`ID`),
  ADD CONSTRAINT `FKrg2q8frb9768dltvrcepq2wt1` FOREIGN KEY (`REALM_ID`) REFERENCES `REALMS` (`ID`),
  ADD CONSTRAINT `FKsqlb9e2nt3eftsaadv1yeu8lm` FOREIGN KEY (`FACTION_ID`) REFERENCES `FACTIONS` (`ID`);

--
-- Contraintes pour la table `GUILDS`
--
ALTER TABLE `GUILDS`
  ADD CONSTRAINT `FKbc8b0f0npxdtaid7u7n8hralb` FOREIGN KEY (`REALM_ID`) REFERENCES `REALMS` (`ID`),
  ADD CONSTRAINT `FKnsx4lvrdhk0xykm96swyhe64x` FOREIGN KEY (`FACTION_ID`) REFERENCES `FACTIONS` (`ID`);

--
-- Contraintes pour la table `PLAYABLE_RACES`
--
ALTER TABLE `PLAYABLE_RACES`
  ADD CONSTRAINT `FKifi40xiidm18hf0udcwge2vn9` FOREIGN KEY (`FACTION_ID`) REFERENCES `FACTIONS` (`ID`);

--
-- Contraintes pour la table `PLAYABLE_SPECIALIZATIONS`
--
ALTER TABLE `PLAYABLE_SPECIALIZATIONS`
  ADD CONSTRAINT `FKfop6bkcn7ca15av4pd4wrd6ls` FOREIGN KEY (`PLAYABLE_CLASS_ID`) REFERENCES `PLAYABLE_CLASSES` (`ID`),
  ADD CONSTRAINT `FKqrjw9tjrwvel7s2p75aev6jvw` FOREIGN KEY (`SPECIALIZATION_ROLE_ID`) REFERENCES `SPECIALIZATION_ROLES` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
