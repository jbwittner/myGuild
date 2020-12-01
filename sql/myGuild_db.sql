-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql
-- Généré le : mar. 01 déc. 2020 à 23:14
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

INSERT INTO `CHARACTERS` (`ID`, `IS_UPDATED`, `AVATAR_URL`, `AVERAGE_ITEM_LEVEL`, `EQUIPPED_ITEM_LEVEL`, `INSET_URL`, `IS_FAVORITE`, `LAST_LOGIN_TIMESTAMP`, `LEVEL`, `NAME`, `UPDATE_DATE`, `FACTION_ID`, `GUILD_ID`, `GUILD_RANK_ID`, `PLAYABLE_CLASS_ID`, `PLAYABLE_RACE_ID`, `REALM_ID`, `USER_ACCOUNT_ID`) VALUES
(91085728, b'1', 'https://render-eu.worldofwarcraft.com/character/illidan/160/91085728-avatar.jpg', 108, 73, 'https://render-eu.worldofwarcraft.com/character/illidan/160/91085728-inset.jpg', b'0', 1594071506000, 32, 'Chrønos', '2020-12-01 15:15:18', 7, NULL, NULL, 7, 8, 541, 100074258),
(91096823, b'1', 'https://render-eu.worldofwarcraft.com/character/illidan/247/91096823-avatar.jpg', 106, 0, 'https://render-eu.worldofwarcraft.com/character/illidan/247/91096823-inset.jpg', b'0', 1594851981000, 32, 'Dohakor', '2020-12-01 15:15:19', 7, NULL, NULL, 1, 2, 541, 100074258),
(125711854, b'1', 'https://render-eu.worldofwarcraft.com/character/archimonde/238/125711854-avatar.jpg', 174, 174, 'https://render-eu.worldofwarcraft.com/character/archimonde/238/125711854-inset.jpg', b'1', 1606820776000, 60, 'Dohakor', '2020-12-01 16:10:11', 6, 84810965, NULL, 5, 4, 539, 100074258),
(125785702, b'1', 'https://render-eu.worldofwarcraft.com/character/archimonde/102/125785702-avatar.jpg', 138, 138, 'https://render-eu.worldofwarcraft.com/character/archimonde/102/125785702-inset.jpg', b'0', 1602428074000, 40, 'Ksyujin', '2020-12-01 15:15:17', 6, NULL, NULL, 3, 4, 539, 100074258),
(143443966, b'1', 'https://render-eu.worldofwarcraft.com/character/hyjal/254/143443966-avatar.jpg', 314, 306, 'https://render-eu.worldofwarcraft.com/character/hyjal/254/143443966-inset.jpg', b'0', 1602421232000, 50, 'Dohakor', '2020-12-01 15:15:18', 7, 69927966, NULL, 7, 2, 542, 100074258),
(144257552, b'1', 'https://render-eu.worldofwarcraft.com/character/hyjal/16/144257552-avatar.jpg', 163, 163, 'https://render-eu.worldofwarcraft.com/character/hyjal/16/144257552-inset.jpg', b'0', 1602420031000, 45, 'Tesath', '2020-12-01 15:15:18', 7, 69927966, NULL, 6, 10, 542, 100074258),
(148117761, b'1', 'https://render-eu.worldofwarcraft.com/character/hyjal/1/148117761-avatar.jpg', 181, 181, 'https://render-eu.worldofwarcraft.com/character/hyjal/1/148117761-inset.jpg', b'0', 1602422183000, 45, 'Firath', '2020-12-01 15:15:18', 7, NULL, NULL, 2, 10, 542, 100074258),
(163683171, b'1', 'https://render-eu.worldofwarcraft.com/character/hyjal/99/163683171-avatar.jpg', 325, 325, 'https://render-eu.worldofwarcraft.com/character/hyjal/99/163683171-inset.jpg', b'0', 1602421799000, 50, 'Ksyujin', '2020-12-01 15:15:18', 7, NULL, NULL, 5, 10, 542, 100074258),
(183625458, b'1', 'https://render-eu.worldofwarcraft.com/character/archimonde/242/183625458-avatar.jpg', 127, 121, 'https://render-eu.worldofwarcraft.com/character/archimonde/242/183625458-inset.jpg', b'0', 1606081606000, 50, 'Sulion', '2020-12-01 15:15:17', 6, 84810965, NULL, 12, 4, 539, 100074258),
(184658870, b'1', 'https://render-eu.worldofwarcraft.com/character/archimonde/182/184658870-avatar.jpg', 86, 85, 'https://render-eu.worldofwarcraft.com/character/archimonde/182/184658870-inset.jpg', b'0', 1605989720000, 50, 'Tesath', '2020-12-01 15:15:17', 6, 84810965, NULL, 8, 29, 539, 100074258),
(186541595, b'1', 'https://render-eu.worldofwarcraft.com/character/archimonde/27/186541595-avatar.jpg', 88, 88, 'https://render-eu.worldofwarcraft.com/character/archimonde/27/186541595-inset.jpg', b'0', 1605989626000, 50, 'Amolaric', '2020-12-01 15:15:17', 6, 84810965, NULL, 7, 11, 539, 100074258),
(192934426, b'1', 'https://render-eu.worldofwarcraft.com/character/archimonde/26/192934426-avatar.jpg', 31, 31, 'https://render-eu.worldofwarcraft.com/character/archimonde/26/192934426-inset.jpg', b'0', 1602877314000, 27, 'Døhakor', '2020-12-01 16:16:26', 6, 84810965, NULL, 6, 1, 539, 100074258);

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
(6, b'1', 'Allianz', 'Alliance', 'Alliance', 'Alianza', 'Alianza', 'Alliance', 'Alleanza', '얼라이언스', 'Aliança', 'Альянс', '联盟', '聯盟', 'ALLIANCE'),
(7, b'1', 'Horde', 'Horde', 'Horde', 'Horda', 'Horda', 'Horde', 'Orda', '호드', 'Horda', 'Орда', '部落', '部落', 'HORDE'),
(8, b'1', 'Neutral', 'Neutral', 'Neutral', 'Neutral', 'Neutral', 'Neutre', 'Neutrale', '중립 지역', 'Neutra', 'Нейтралы', '中立', '中立', 'NEUTRAL');

-- --------------------------------------------------------

--
-- Structure de la table `FAVORITE_GUILDS`
--

CREATE TABLE `FAVORITE_GUILDS` (
  `USER_ACCOUNT_BLIZZARD_ID` int NOT NULL,
  `GUILD_ID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `FAVORITE_GUILDS`
--

INSERT INTO `FAVORITE_GUILDS` (`USER_ACCOUNT_BLIZZARD_ID`, `GUILD_ID`) VALUES
(100074258, 84810965);

-- --------------------------------------------------------

--
-- Structure de la table `GUILDS`
--

CREATE TABLE `GUILDS` (
  `ID` int NOT NULL,
  `IS_UPDATED` bit(1) NOT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `USE_APPLICATION` bit(1) DEFAULT NULL,
  `FACTION_ID` int DEFAULT NULL,
  `REALM_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `GUILDS`
--

INSERT INTO `GUILDS` (`ID`, `IS_UPDATED`, `NAME`, `UPDATE_DATE`, `USE_APPLICATION`, `FACTION_ID`, `REALM_ID`) VALUES
(69927966, b'1', 'PeyPouze', '2020-12-01 15:15:18', b'0', 7, 542),
(84810965, b'1', 'PeyPouZe', '2020-12-01 15:15:17', b'0', 6, 539);

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
(9),
(9),
(9);

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
(1, b'1', 'Mensch', 'Human', 'Human', 'Humano', 'Humano', 'Humain', 'Umano', '인간', 'Humano', 'Человек', '人类', '人類', 6),
(2, b'1', 'Orc', 'Orc', 'Orc', 'Orco', 'Orco', 'Orc', 'Orco', '오크', 'Orc', 'Орк', '兽人', '獸人', 7),
(3, b'1', 'Zwerg', 'Dwarf', 'Dwarf', 'Enano', 'Enano', 'Nain', 'Nano', '드워프', 'Anão', 'Дворф', '矮人', '矮人', 6),
(4, b'1', 'Nachtelf', 'Night Elf', 'Night Elf', 'Elfo de la noche', 'Elfo de la noche', 'Elfe de la nuit', 'Elfo della Notte', '나이트 엘프', 'Elfo Noturno', 'Ночной эльф', '暗夜精灵', '夜精靈', 6),
(5, b'1', 'Untoter', 'Undead', 'Undead', 'No-muerto', 'No-muerto', 'Mort-vivant', 'Non Morto', '언데드', 'Morto-vivo', 'Нежить', '亡灵', '不死族', 7),
(6, b'1', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', '타우렌', 'Tauren', 'Таурен', '牛头人', '牛頭人', 7),
(7, b'1', 'Gnom', 'Gnome', 'Gnome', 'Gnomo', 'Gnomo', 'Gnome', 'Gnomo', '노움', 'Gnomo', 'Гном', '侏儒', '地精', 6),
(8, b'1', 'Troll', 'Troll', 'Troll', 'Trol', 'Trol', 'Troll', 'Troll', '트롤', 'Troll', 'Тролль', '巨魔', '食人妖', 7),
(9, b'1', 'Goblin', 'Goblin', 'Goblin', 'Goblin', 'Goblin', 'Gobelin', 'Goblin', '고블린', 'Goblin', 'Гоблин', '地精', '哥布林', 7),
(10, b'1', 'Blutelf', 'Blood Elf', 'Blood Elf', 'Elfo de sangre', 'Elfo de sangre', 'Elfe de sang', 'Elfo del Sangue', '블러드 엘프', 'Elfo Sangrento', 'Эльф крови', '血精灵', '血精靈', 7),
(11, b'1', 'Draenei', 'Draenei', 'Draenei', 'Draenei', 'Draenei', 'Draeneï', 'Draenei', '드레나이', 'Draenei', 'Дреней', '德莱尼', '德萊尼', 6),
(22, b'1', 'Worgen', 'Worgen', 'Worgen', 'Huargen', 'Huargen', 'Worgen', 'Worgen', '늑대인간', 'Worgen', 'Ворген', '狼人', '狼人', 6),
(24, b'1', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 8),
(25, b'1', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 6),
(26, b'1', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 7),
(27, b'1', 'Nachtgeborener', 'Nightborne', 'Nightborne', 'Nocheterna', 'Natonocturno', 'Sacrenuit', 'Nobile Oscuro', '나이트본', 'Filho da Noite', 'Ночнорожденный', '夜之子', '夜裔精靈', 7),
(28, b'1', 'Hochbergtauren', 'Highmountain Tauren', 'Highmountain Tauren', 'Tauren Monte Alto', 'Tauren de Altamontaña', 'Tauren de Haut-Roc', 'Tauren di Alto Monte', '높은산 타우렌', 'Tauren Altamontês', 'Таурен Крутогорья', '至高岭牛头人', '高嶺牛頭人', 7),
(29, b'1', 'Leerenelf', 'Void Elf', 'Void Elf', 'Elfo del Vacío', 'Elfo del Vacío', 'Elfe du Vide', 'Elfo del Vuoto', '공허 엘프', 'Elfo Caótico', 'Эльф Бездны', '虚空精灵', '虛無精靈', 6),
(30, b'1', 'Lichtgeschmiedeter Draenei', 'Lightforged Draenei', 'Lightforged Draenei', 'Draenei forjado por la Luz', 'Draenei templeluz', 'Draeneï sancteforge', 'Draenei Forgialuce', '빛벼림 드레나이', 'Draenei Forjado a Luz', 'Озаренный дреней', '光铸德莱尼', '光鑄德萊尼', 6),
(31, b'1', 'Zandalaritroll', 'Zandalari Troll', 'Zandalari Troll', 'Trol Zandalari', 'Trol zandalari', 'Troll zandalari', 'Troll Zandalari', '잔달라 트롤', 'Troll Zandalari', 'Зандалар', '赞达拉巨魔', '贊達拉食人妖', 7),
(32, b'1', 'Kul Tiraner', 'Kul Tiran', 'Kul Tiran', 'Ciudadano de Kul Tiras', 'Kultirano', 'Kultirassien', 'Kul Tirano', '쿨 티란', 'Kultireno', 'Култирасец', '库尔提拉斯人', '庫爾提拉斯人', 6),
(34, b'1', 'Dunkeleisenzwerg', 'Dark Iron Dwarf', 'Dark Iron Dwarf', 'Enano Hierro Negro', 'Enano Hierro Negro', 'Nain sombrefer', 'Nano Ferroscuro', '검은무쇠 드워프', 'Anão Ferro Negro', 'Дворф из клана Черного Железа', '黑铁矮人', '黑鐵矮人', 6),
(35, b'1', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpérin', 'Vulpera', '불페라', 'Vulpera', 'Вульпера', '狐人', '狐狸人', 7),
(36, b'1', 'Mag\'har', 'Mag\'har Orc', 'Mag\'har Orc', 'Orco Mag\'har', 'Orco mag\'har', 'Orc mag’har', 'Orco Mag\'har', '마그하르 오크', 'Orc Mag\'har', 'Маг\'хар', '玛格汉兽人', '瑪格哈獸人', 7),
(37, b'1', 'Mechagnom', 'Mechagnome', 'Mechagnome', 'Mecagnomo', 'Mecagnomo', 'Mécagnome', 'Meccagnomo', '기계노움', 'Gnomecânico', 'Механогном', '机械侏儒', '機械地精', 6);

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
(62, b'1', 'Arkan', 'Arcane', 'Arcane', 'Arcano', 'Arcano', 'Arcanes', 'Arcano', '비전', 'Arcano', 'Тайная магия', '奥术', '秘法', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_magicalsentry.jpg', 8, 3),
(63, b'1', 'Feuer', 'Fire', 'Fire', 'Fuego', 'Fuego', 'Feu', 'Fuoco', '화염', 'Fogo', 'Огонь', '火焰', '火焰', 'https://render-eu.worldofwarcraft.com/icons/56/spell_fire_firebolt02.jpg', 8, 3),
(64, b'1', 'Frost', 'Frost', 'Frost', 'Escarcha', 'Escarcha', 'Givre', 'Gelo', '냉기', 'Gélido', 'Лед', '冰霜', '冰霜', 'https://render-eu.worldofwarcraft.com/icons/56/spell_frost_frostbolt02.jpg', 8, 3),
(65, b'1', 'Heilig', 'Holy', 'Holy', 'Sagrado', 'Sagrado', 'Sacré', 'Sacro', '신성', 'Sagrado', 'Свет', '神圣', '神聖', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_holybolt.jpg', 2, 5),
(66, b'1', 'Schutz', 'Protection', 'Protection', 'Protección', 'Protección', 'Protection', 'Protezione', '보호', 'Proteção', 'Защита', '防护', '防護', 'https://render-eu.worldofwarcraft.com/icons/56/ability_paladin_shieldofthetemplar.jpg', 2, 4),
(70, b'1', 'Vergeltung', 'Retribution', 'Retribution', 'Reprensión', 'Reprensión', 'Vindicte', 'Castigo', '징벌', 'Retribuição', 'Воздаяние', '惩戒', '懲戒', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_auraoflight.jpg', 2, 3),
(71, b'1', 'Waffen', 'Arms', 'Arms', 'Armas', 'Armas', 'Armes', 'Armi', '무기', 'Armas', 'Оружие', '武器', '武器', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_savageblow.jpg', 1, 3),
(72, b'1', 'Furor', 'Fury', 'Fury', 'Furia', 'Furia', 'Fureur', 'Furia', '분노', 'Fúria', 'Неистовство', '狂怒', '狂怒', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_innerrage.jpg', 1, 3),
(73, b'1', 'Schutz', 'Protection', 'Protection', 'Protección', 'Protección', 'Protection', 'Protezione', '방어', 'Proteção', 'Защита', '防护', '防護', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_defensivestance.jpg', 1, 4),
(102, b'1', 'Gleichgewicht', 'Balance', 'Balance', 'Equilibrio', 'Equilibrio', 'Équilibre', 'Equilibrio', '조화', 'Equilíbrio', 'Баланс', '平衡', '平衡', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_starfall.jpg', 11, 3),
(103, b'1', 'Wildheit', 'Feral', 'Feral', 'Feral', 'Feral', 'Farouche', 'Aggressore Ferino', '야성', 'Feral', 'Сила зверя', '野性', '野性戰鬥', 'https://render-eu.worldofwarcraft.com/icons/56/ability_druid_catform.jpg', 11, 3),
(104, b'1', 'Wächter', 'Guardian', 'Guardian', 'Guardián', 'Guardián', 'Gardien', 'Guardiano Ferino', '수호', 'Guardião', 'Страж', '守护', '守護者', 'https://render-eu.worldofwarcraft.com/icons/56/ability_racial_bearform.jpg', 11, 4),
(105, b'1', 'Wiederherstellung', 'Restoration', 'Restoration', 'Restauración', 'Restauración', 'Restauration', 'Rigenerazione', '회복', 'Restauração', 'Исцеление', '恢复', '恢復', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_healingtouch.jpg', 11, 5),
(250, b'1', 'Blut', 'Blood', 'Blood', 'Sangre', 'Sangre', 'Sang', 'Sangue', '혈기', 'Sangue', 'Кровь', '鲜血', '血魄', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_bloodpresence.jpg', 6, 4),
(251, b'1', 'Frost', 'Frost', 'Frost', 'Escarcha', 'Escarcha', 'Givre', 'Gelo', '냉기', 'Gélido', 'Лед', '冰霜', '冰霜', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_frostpresence.jpg', 6, 3),
(252, b'1', 'Unheilig', 'Unholy', 'Unholy', 'Profano', 'Profano', 'Impie', 'Empietà', '부정', 'Profano', 'Нечестивость', '邪恶', '穢邪', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_unholypresence.jpg', 6, 3),
(253, b'1', 'Tierherrschaft', 'Beast Mastery', 'Beast Mastery', 'Bestias', 'Bestias', 'Maîtrise des bêtes', 'Affinità Animale', '야수', 'Domínio das Feras', 'Повелитель зверей', '野兽控制', '野獸控制', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_bestialdiscipline.jpg', 3, 3),
(254, b'1', 'Treffsicherheit', 'Marksmanship', 'Marksmanship', 'Puntería', 'Puntería', 'Précision', 'Precisione di Tiro', '사격', 'Precisão', 'Стрельба', '射击', '射擊', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_focusedaim.jpg', 3, 3),
(255, b'1', 'Überleben', 'Survival', 'Survival', 'Supervivencia', 'Supervivencia', 'Survie', 'Sopravvivenza', '생존', 'Sobrevivência', 'Выживание', '生存', '生存', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_camouflage.jpg', 3, 3),
(256, b'1', 'Disziplin', 'Discipline', 'Discipline', 'Disciplina', 'Disciplina', 'Discipline', 'Disciplina', '수양', 'Disciplina', 'Послушание', '戒律', '戒律', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_powerwordshield.jpg', 5, 5),
(257, b'1', 'Heilig', 'Holy', 'Holy', 'Sagrado', 'Sagrado', 'Sacré', 'Sacro', '신성', 'Sagrado', 'Свет', '神圣', '神聖', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_guardianspirit.jpg', 5, 5),
(258, b'1', 'Schatten', 'Shadow', 'Shadow', 'Sombra', 'Sombra', 'Ombre', 'Ombra', '암흑', 'Sombra', 'Тьма', '暗影', '暗影', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_shadowwordpain.jpg', 5, 3),
(259, b'1', 'Meucheln', 'Assassination', 'Assassination', 'Asesinato', 'Asesinato', 'Assassinat', 'Assassinio', '암살', 'Assassinato', 'Ликвидация', '奇袭', '刺殺', 'https://render-eu.worldofwarcraft.com/icons/56/ability_rogue_deadlybrew.jpg', 4, 3),
(260, b'1', 'Gesetzlosigkeit', 'Outlaw', 'Outlaw', 'Forajido', 'Forajido', 'Hors-la-loi', 'Fuorilegge', '무법', 'Fora da Lei', 'Головорез', '狂徒', '暴徒', 'https://render-eu.worldofwarcraft.com/icons/56/ability_rogue_waylay.jpg', 4, 3),
(261, b'1', 'Täuschung', 'Subtlety', 'Subtlety', 'Sutileza', 'Sutileza', 'Finesse', 'Scaltrezza', '잠행', 'Subterfúgio', 'Скрытность', '敏锐', '敏銳', 'https://render-eu.worldofwarcraft.com/icons/56/ability_stealth.jpg', 4, 3),
(262, b'1', 'Elementar', 'Elemental', 'Elemental', 'Elemental', 'Elemental', 'Élémentaire', 'Elementale', '정기', 'Elemental', 'Стихии', '元素', '元素', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_lightning.jpg', 7, 3),
(263, b'1', 'Verstärkung', 'Enhancement', 'Enhancement', 'Mejora', 'Mejora', 'Amélioration', 'Potenziamento', '고양', 'Aperfeiçoamento', 'Совершенствование', '增强', '增強', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shaman_improvedstormstrike.jpg', 7, 3),
(264, b'1', 'Wiederherstellung', 'Restoration', 'Restoration', 'Restauración', 'Restauración', 'Restauration', 'Rigenerazione', '복원', 'Restauração', 'Исцеление', '恢复', '恢復', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_magicimmunity.jpg', 7, 5),
(265, b'1', 'Gebrechen', 'Affliction', 'Affliction', 'Aflicción', 'Aflicción', 'Affliction', 'Afflizione', '고통', 'Suplício', 'Колдовство', '痛苦', '痛苦', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_deathcoil.jpg', 9, 3),
(266, b'1', 'Dämonologie', 'Demonology', 'Demonology', 'Demonología', 'Demonología', 'Démonologie', 'Demonologia', '악마', 'Demonologia', 'Демонология', '恶魔学识', '惡魔學識', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_metamorphosis.jpg', 9, 3),
(267, b'1', 'Zerstörung', 'Destruction', 'Destruction', 'Destrucción', 'Destrucción', 'Destruction', 'Distruzione', '파괴', 'Destruição', 'Разрушение', '毁灭', '毀滅', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_rainoffire.jpg', 9, 3),
(268, b'1', 'Braumeister', 'Brewmaster', 'Brewmaster', 'Maestro cervecero', 'Maestro cervecero', 'Maître brasseur', 'Mastro Birraio', '양조', 'Mestre Cervejeiro', 'Хмелевар', '酒仙', '釀酒', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_brewmaster_spec.jpg', 10, 4),
(269, b'1', 'Windläufer', 'Windwalker', 'Windwalker', 'Viajero del viento', 'Viajero del viento', 'Marche-vent', 'Impeto', '풍운', 'Andarilho do Vento', 'Танцующий с ветром', '踏风', '御風', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_windwalker_spec.jpg', 10, 3),
(270, b'1', 'Nebelwirker', 'Mistweaver', 'Mistweaver', 'Tejedor de niebla', 'Tejedor de niebla', 'Tisse-brume', 'Misticismo', '운무', 'Tecelão da Névoa', 'Ткач туманов', '织雾', '織霧', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_mistweaver_spec.jpg', 10, 5),
(577, b'1', 'Verwüstung', 'Havoc', 'Havoc', 'Devastación', 'Caos', 'Dévastation', 'Rovina', '파멸', 'Devastação', 'Истребление', '浩劫', '災虐', 'https://render-eu.worldofwarcraft.com/icons/56/ability_demonhunter_specdps.jpg', 12, 3),
(581, b'1', 'Rachsucht', 'Vengeance', 'Vengeance', 'Venganza', 'Venganza', 'Vengeance', 'Vendetta', '복수', 'Vingança', 'Месть', '复仇', '復仇', 'https://render-eu.worldofwarcraft.com/icons/56/ability_demonhunter_spectank.jpg', 12, 4);

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
(539, b'1', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', 'Archimonde', '阿克蒙德', '阿克蒙德', 'archimonde'),
(541, b'1', 'Illidan', 'Illidan', 'Illidan', 'Illidan', 'Illidan', 'Illidan', 'Illidan', 'Illidan', 'Illidan', 'Illidan', '伊利丹', '伊利丹', 'illidan'),
(542, b'1', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', 'Hyjal', '海加尔', '海加爾山', 'hyjal');

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
(3, b'1', 'Schaden', 'Damage', 'Damage', 'Daño', 'Daño', 'Dégâts', 'Assaltatore', '공격 전담', 'Dano', 'Боец', '伤害输出', '傷害輸出', 'DAMAGE'),
(4, b'1', 'Tank', 'Tank', 'Tank', 'Tanque', 'Tanque', 'Tank', 'Difensore', '방어 전담', 'Tanque', 'Танк', '坦克', '坦克', 'TANK'),
(5, b'1', 'Heilung', 'Healer', 'Healer', 'Sanador', 'Sanador', 'Soins', 'Guaritore', '치유 전담', 'Cura', 'Лекарь', '治疗', '治療者', 'HEALER');

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
-- Déchargement des données de la table `USER_ACCOUNT`
--

INSERT INTO `USER_ACCOUNT` (`BLIZZARD_ID`, `BATTLE_TAG`, `CREATION_DATE_TIME`, `EMAIL`, `ENABLED`, `NICK_NAME`, `UPDATE_DATE_TIME`) VALUES
(100074258, 'Dohakor#2286', '2020-12-01 15:12:50', 'dohakor@outlook.com', b'1', 'dohakor', '2020-12-01 15:12:50');

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
-- Index pour la table `FAVORITE_GUILDS`
--
ALTER TABLE `FAVORITE_GUILDS`
  ADD KEY `FKdtej58e4c4vm4x4ldqapgumdl` (`GUILD_ID`),
  ADD KEY `FK4gbwjuocu5ctrm4e85ww3f7mv` (`USER_ACCOUNT_BLIZZARD_ID`);

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
-- Contraintes pour la table `FAVORITE_GUILDS`
--
ALTER TABLE `FAVORITE_GUILDS`
  ADD CONSTRAINT `FK4gbwjuocu5ctrm4e85ww3f7mv` FOREIGN KEY (`USER_ACCOUNT_BLIZZARD_ID`) REFERENCES `USER_ACCOUNT` (`BLIZZARD_ID`),
  ADD CONSTRAINT `FKdtej58e4c4vm4x4ldqapgumdl` FOREIGN KEY (`GUILD_ID`) REFERENCES `GUILDS` (`ID`);

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
