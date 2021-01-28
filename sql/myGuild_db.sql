-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql
-- Généré le : Dim 03 jan. 2021 à 00:33
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
  `AVERAGE_ITEM_LEVEL` int DEFAULT NULL,
  `EQUIPPED_ITEM_LEVEL` int DEFAULT NULL,
  `IS_FAVORITE` bit(1) DEFAULT NULL,
  `LAST_LOGIN_TIMESTAMP` bigint DEFAULT NULL,
  `LEVEL` int DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FACTION_ID` int DEFAULT NULL,
  `GUILD_ID` int DEFAULT NULL,
  `GUILD_RANK_ID` int DEFAULT NULL,
  `PLAYABLE_CLASS_ID` int DEFAULT NULL,
  `PLAYABLE_RACE_ID` int DEFAULT NULL,
  `REALM_ID` int DEFAULT NULL,
  `USER_ACCOUNT_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `FACTIONS`
--

CREATE TABLE `FACTIONS` (
  `ID` int NOT NULL,
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

INSERT INTO `FACTIONS` (`ID`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `TYPE`) VALUES
(4, 'Allianz', 'Alliance', 'Alliance', 'Alianza', 'Alianza', 'Alliance', 'Alleanza', '얼라이언스', 'Aliança', 'Альянс', '联盟', '聯盟', 'ALLIANCE'),
(5, 'Horde', 'Horde', 'Horde', 'Horda', 'Horda', 'Horde', 'Orda', '호드', 'Horda', 'Орда', '部落', '部落', 'HORDE'),
(6, 'Neutral', 'Neutral', 'Neutral', 'Neutral', 'Neutral', 'Neutre', 'Neutrale', '중립 지역', 'Neutra', 'Нейтралы', '中立', '中立', 'NEUTRAL');

-- --------------------------------------------------------

--
-- Structure de la table `GUILDS`
--

CREATE TABLE `GUILDS` (
  `ID` int NOT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USE_APPLICATION` bit(1) DEFAULT NULL,
  `FACTION_ID` int DEFAULT NULL,
  `REALM_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `GUILD_RANKS`
--

CREATE TABLE `GUILD_RANKS` (
  `ID` int NOT NULL,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GUILD_RANK` int DEFAULT NULL,
  `GUILD_ID` int DEFAULT NULL
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
(7);

-- --------------------------------------------------------

--
-- Structure de la table `PLAYABLE_CLASSES`
--

CREATE TABLE `PLAYABLE_CLASSES` (
  `ID` int NOT NULL,
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

INSERT INTO `PLAYABLE_CLASSES` (`ID`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `MEDIA_URL`) VALUES
(1, 'Krieger', 'Warrior', 'Warrior', 'Guerrero', 'Guerrero', 'Guerrier', 'Guerriero', '전사', 'Guerreiro', 'Воин', '战士', '戰士', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_warrior.jpg'),
(2, 'Paladin', 'Paladin', 'Paladin', 'Paladín', 'Paladín', 'Paladin', 'Paladino', '성기사', 'Paladino', 'Паладин', '圣骑士', '聖騎士', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_paladin.jpg'),
(3, 'Jäger', 'Hunter', 'Hunter', 'Cazador', 'Cazador', 'Chasseur', 'Cacciatore', '사냥꾼', 'Caçador', 'Охотник', '猎人', '獵人', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_hunter.jpg'),
(4, 'Schurke', 'Rogue', 'Rogue', 'Pícaro', 'Pícaro', 'Voleur', 'Ladro', '도적', 'Ladino', 'Разбойник', '潜行者', '盜賊', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_rogue.jpg'),
(5, 'Priester', 'Priest', 'Priest', 'Sacerdote', 'Sacerdote', 'Prêtre', 'Sacerdote', '사제', 'Sacerdote', 'Жрец', '牧师', '牧師', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_priest.jpg'),
(6, 'Todesritter', 'Death Knight', 'Death Knight', 'Caballero de la Muerte', 'Caballero de la Muerte', 'Chevalier de la mort', 'Cavaliere della Morte', '죽음의 기사', 'Cavaleiro da Morte', 'Рыцарь смерти', '死亡骑士', '死亡騎士', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_classicon.jpg'),
(7, 'Schamane', 'Shaman', 'Shaman', 'Chamán', 'Chamán', 'Chaman', 'Sciamano', '주술사', 'Xamã', 'Шаман', '萨满祭司', '薩滿', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_shaman.jpg'),
(8, 'Magier', 'Mage', 'Mage', 'Mago', 'Mago', 'Mage', 'Mago', '마법사', 'Mago', 'Маг', '法师', '法師', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_mage.jpg'),
(9, 'Hexenmeister', 'Warlock', 'Warlock', 'Brujo', 'Brujo', 'Démoniste', 'Stregone', '흑마법사', 'Bruxo', 'Чернокнижник', '术士', '術士', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_warlock.jpg'),
(10, 'Mönch', 'Monk', 'Monk', 'Monje', 'Monje', 'Moine', 'Monaco', '수도사', 'Monge', 'Монах', '武僧', '武僧', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_monk.jpg'),
(11, 'Druide', 'Druid', 'Druid', 'Druida', 'Druida', 'Druide', 'Druido', '드루이드', 'Druida', 'Друид', '德鲁伊', '德魯伊', 'https://render-eu.worldofwarcraft.com/icons/56/classicon_druid.jpg'),
(12, 'Dämonenjäger', 'Demon Hunter', 'Demon Hunter', 'Cazador de demonios', 'Cazador de demonios', 'Chasseur de démons', 'Cacciatore di Demoni', '악마사냥꾼', 'Caçador de Demônios', 'Охотник на демонов', '恶魔猎手', '惡魔獵人', 'https://render-eu.worldofwarcraft.com/icons/56/achievement_boss_illidan.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `PLAYABLE_RACES`
--

CREATE TABLE `PLAYABLE_RACES` (
  `ID` int NOT NULL,
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

INSERT INTO `PLAYABLE_RACES` (`ID`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `FACTION_ID`) VALUES
(1, 'Mensch', 'Human', 'Human', 'Humano', 'Humano', 'Humain', 'Umano', '인간', 'Humano', 'Человек', '人类', '人類', 4),
(2, 'Orc', 'Orc', 'Orc', 'Orco', 'Orco', 'Orc', 'Orco', '오크', 'Orc', 'Орк', '兽人', '獸人', 5),
(3, 'Zwerg', 'Dwarf', 'Dwarf', 'Enano', 'Enano', 'Nain', 'Nano', '드워프', 'Anão', 'Дворф', '矮人', '矮人', 4),
(4, 'Nachtelf', 'Night Elf', 'Night Elf', 'Elfo de la noche', 'Elfo de la noche', 'Elfe de la nuit', 'Elfo della Notte', '나이트 엘프', 'Elfo Noturno', 'Ночной эльф', '暗夜精灵', '夜精靈', 4),
(5, 'Untoter', 'Undead', 'Undead', 'No-muerto', 'No-muerto', 'Mort-vivant', 'Non Morto', '언데드', 'Morto-vivo', 'Нежить', '亡灵', '不死族', 5),
(6, 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', 'Tauren', '타우렌', 'Tauren', 'Таурен', '牛头人', '牛頭人', 5),
(7, 'Gnom', 'Gnome', 'Gnome', 'Gnomo', 'Gnomo', 'Gnome', 'Gnomo', '노움', 'Gnomo', 'Гном', '侏儒', '地精', 4),
(8, 'Troll', 'Troll', 'Troll', 'Trol', 'Trol', 'Troll', 'Troll', '트롤', 'Troll', 'Тролль', '巨魔', '食人妖', 5),
(9, 'Goblin', 'Goblin', 'Goblin', 'Goblin', 'Goblin', 'Gobelin', 'Goblin', '고블린', 'Goblin', 'Гоблин', '地精', '哥布林', 5),
(10, 'Blutelf', 'Blood Elf', 'Blood Elf', 'Elfo de sangre', 'Elfo de sangre', 'Elfe de sang', 'Elfo del Sangue', '블러드 엘프', 'Elfo Sangrento', 'Эльф крови', '血精灵', '血精靈', 5),
(11, 'Draenei', 'Draenei', 'Draenei', 'Draenei', 'Draenei', 'Draeneï', 'Draenei', '드레나이', 'Draenei', 'Дреней', '德莱尼', '德萊尼', 4),
(22, 'Worgen', 'Worgen', 'Worgen', 'Huargen', 'Huargen', 'Worgen', 'Worgen', '늑대인간', 'Worgen', 'Ворген', '狼人', '狼人', 4),
(24, 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 6),
(25, 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 4),
(26, 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', 'Pandaren', '판다렌', 'Pandaren', 'Пандарен', '熊猫人', '熊貓人', 5),
(27, 'Nachtgeborener', 'Nightborne', 'Nightborne', 'Nocheterna', 'Natonocturno', 'Sacrenuit', 'Nobile Oscuro', '나이트본', 'Filho da Noite', 'Ночнорожденный', '夜之子', '夜裔精靈', 5),
(28, 'Hochbergtauren', 'Highmountain Tauren', 'Highmountain Tauren', 'Tauren Monte Alto', 'Tauren de Altamontaña', 'Tauren de Haut-Roc', 'Tauren di Alto Monte', '높은산 타우렌', 'Tauren Altamontês', 'Таурен Крутогорья', '至高岭牛头人', '高嶺牛頭人', 5),
(29, 'Leerenelf', 'Void Elf', 'Void Elf', 'Elfo del Vacío', 'Elfo del Vacío', 'Elfe du Vide', 'Elfo del Vuoto', '공허 엘프', 'Elfo Caótico', 'Эльф Бездны', '虚空精灵', '虛無精靈', 4),
(30, 'Lichtgeschmiedeter Draenei', 'Lightforged Draenei', 'Lightforged Draenei', 'Draenei forjado por la Luz', 'Draenei templeluz', 'Draeneï sancteforge', 'Draenei Forgialuce', '빛벼림 드레나이', 'Draenei Forjado a Luz', 'Озаренный дреней', '光铸德莱尼', '光鑄德萊尼', 4),
(31, 'Zandalaritroll', 'Zandalari Troll', 'Zandalari Troll', 'Trol Zandalari', 'Trol zandalari', 'Troll zandalari', 'Troll Zandalari', '잔달라 트롤', 'Troll Zandalari', 'Зандалар', '赞达拉巨魔', '贊達拉食人妖', 5),
(32, 'Kul Tiraner', 'Kul Tiran', 'Kul Tiran', 'Ciudadano de Kul Tiras', 'Kultirano', 'Kultirassien', 'Kul Tirano', '쿨 티란', 'Kultireno', 'Култирасец', '库尔提拉斯人', '庫爾提拉斯人', 4),
(34, 'Dunkeleisenzwerg', 'Dark Iron Dwarf', 'Dark Iron Dwarf', 'Enano Hierro Negro', 'Enano Hierro Negro', 'Nain sombrefer', 'Nano Ferroscuro', '검은무쇠 드워프', 'Anão Ferro Negro', 'Дворф из клана Черного Железа', '黑铁矮人', '黑鐵矮人', 4),
(35, 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpera', 'Vulpérin', 'Vulpera', '불페라', 'Vulpera', 'Вульпера', '狐人', '狐狸人', 5),
(36, 'Mag\'har', 'Mag\'har Orc', 'Mag\'har Orc', 'Orco Mag\'har', 'Orco mag\'har', 'Orc mag’har', 'Orco Mag\'har', '마그하르 오크', 'Orc Mag\'har', 'Маг\'хар', '玛格汉兽人', '瑪格哈獸人', 5),
(37, 'Mechagnom', 'Mechagnome', 'Mechagnome', 'Mecagnomo', 'Mecagnomo', 'Mécagnome', 'Meccagnomo', '기계노움', 'Gnomecânico', 'Механогном', '机械侏儒', '機械地精', 4);

-- --------------------------------------------------------

--
-- Structure de la table `PLAYABLE_SPECIALIZATIONS`
--

CREATE TABLE `PLAYABLE_SPECIALIZATIONS` (
  `ID` int NOT NULL,
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

INSERT INTO `PLAYABLE_SPECIALIZATIONS` (`ID`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `MEDIA_URL`, `PLAYABLE_CLASS_ID`, `SPECIALIZATION_ROLE_ID`) VALUES
(62, 'Arkan', 'Arcane', 'Arcane', 'Arcano', 'Arcano', 'Arcanes', 'Arcano', '비전', 'Arcano', 'Тайная магия', '奥术', '秘法', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_magicalsentry.jpg', 8, 1),
(63, 'Feuer', 'Fire', 'Fire', 'Fuego', 'Fuego', 'Feu', 'Fuoco', '화염', 'Fogo', 'Огонь', '火焰', '火焰', 'https://render-eu.worldofwarcraft.com/icons/56/spell_fire_firebolt02.jpg', 8, 1),
(64, 'Frost', 'Frost', 'Frost', 'Escarcha', 'Escarcha', 'Givre', 'Gelo', '냉기', 'Gélido', 'Лед', '冰霜', '冰霜', 'https://render-eu.worldofwarcraft.com/icons/56/spell_frost_frostbolt02.jpg', 8, 1),
(65, 'Heilig', 'Holy', 'Holy', 'Sagrado', 'Sagrado', 'Sacré', 'Sacro', '신성', 'Sagrado', 'Свет', '神圣', '神聖', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_holybolt.jpg', 2, 3),
(66, 'Schutz', 'Protection', 'Protection', 'Protección', 'Protección', 'Protection', 'Protezione', '보호', 'Proteção', 'Защита', '防护', '防護', 'https://render-eu.worldofwarcraft.com/icons/56/ability_paladin_shieldofthetemplar.jpg', 2, 2),
(70, 'Vergeltung', 'Retribution', 'Retribution', 'Reprensión', 'Reprensión', 'Vindicte', 'Castigo', '징벌', 'Retribuição', 'Воздаяние', '惩戒', '懲戒', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_auraoflight.jpg', 2, 1),
(71, 'Waffen', 'Arms', 'Arms', 'Armas', 'Armas', 'Armes', 'Armi', '무기', 'Armas', 'Оружие', '武器', '武器', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_savageblow.jpg', 1, 1),
(72, 'Furor', 'Fury', 'Fury', 'Furia', 'Furia', 'Fureur', 'Furia', '분노', 'Fúria', 'Неистовство', '狂怒', '狂怒', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_innerrage.jpg', 1, 1),
(73, 'Schutz', 'Protection', 'Protection', 'Protección', 'Protección', 'Protection', 'Protezione', '방어', 'Proteção', 'Защита', '防护', '防護', 'https://render-eu.worldofwarcraft.com/icons/56/ability_warrior_defensivestance.jpg', 1, 2),
(102, 'Gleichgewicht', 'Balance', 'Balance', 'Equilibrio', 'Equilibrio', 'Équilibre', 'Equilibrio', '조화', 'Equilíbrio', 'Баланс', '平衡', '平衡', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_starfall.jpg', 11, 1),
(103, 'Wildheit', 'Feral', 'Feral', 'Feral', 'Feral', 'Farouche', 'Aggressore Ferino', '야성', 'Feral', 'Сила зверя', '野性', '野性戰鬥', 'https://render-eu.worldofwarcraft.com/icons/56/ability_druid_catform.jpg', 11, 1),
(104, 'Wächter', 'Guardian', 'Guardian', 'Guardián', 'Guardián', 'Gardien', 'Guardiano Ferino', '수호', 'Guardião', 'Страж', '守护', '守護者', 'https://render-eu.worldofwarcraft.com/icons/56/ability_racial_bearform.jpg', 11, 2),
(105, 'Wiederherstellung', 'Restoration', 'Restoration', 'Restauración', 'Restauración', 'Restauration', 'Rigenerazione', '회복', 'Restauração', 'Исцеление', '恢复', '恢復', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_healingtouch.jpg', 11, 3),
(250, 'Blut', 'Blood', 'Blood', 'Sangre', 'Sangre', 'Sang', 'Sangue', '혈기', 'Sangue', 'Кровь', '鲜血', '血魄', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_bloodpresence.jpg', 6, 2),
(251, 'Frost', 'Frost', 'Frost', 'Escarcha', 'Escarcha', 'Givre', 'Gelo', '냉기', 'Gélido', 'Лед', '冰霜', '冰霜', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_frostpresence.jpg', 6, 1),
(252, 'Unheilig', 'Unholy', 'Unholy', 'Profano', 'Profano', 'Impie', 'Empietà', '부정', 'Profano', 'Нечестивость', '邪恶', '穢邪', 'https://render-eu.worldofwarcraft.com/icons/56/spell_deathknight_unholypresence.jpg', 6, 1),
(253, 'Tierherrschaft', 'Beast Mastery', 'Beast Mastery', 'Bestias', 'Bestias', 'Maîtrise des bêtes', 'Affinità Animale', '야수', 'Domínio das Feras', 'Повелитель зверей', '野兽控制', '野獸控制', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_bestialdiscipline.jpg', 3, 1),
(254, 'Treffsicherheit', 'Marksmanship', 'Marksmanship', 'Puntería', 'Puntería', 'Précision', 'Precisione di Tiro', '사격', 'Precisão', 'Стрельба', '射击', '射擊', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_focusedaim.jpg', 3, 1),
(255, 'Überleben', 'Survival', 'Survival', 'Supervivencia', 'Supervivencia', 'Survie', 'Sopravvivenza', '생존', 'Sobrevivência', 'Выживание', '生存', '生存', 'https://render-eu.worldofwarcraft.com/icons/56/ability_hunter_camouflage.jpg', 3, 1),
(256, 'Disziplin', 'Discipline', 'Discipline', 'Disciplina', 'Disciplina', 'Discipline', 'Disciplina', '수양', 'Disciplina', 'Послушание', '戒律', '戒律', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_powerwordshield.jpg', 5, 3),
(257, 'Heilig', 'Holy', 'Holy', 'Sagrado', 'Sagrado', 'Sacré', 'Sacro', '신성', 'Sagrado', 'Свет', '神圣', '神聖', 'https://render-eu.worldofwarcraft.com/icons/56/spell_holy_guardianspirit.jpg', 5, 3),
(258, 'Schatten', 'Shadow', 'Shadow', 'Sombra', 'Sombra', 'Ombre', 'Ombra', '암흑', 'Sombra', 'Тьма', '暗影', '暗影', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_shadowwordpain.jpg', 5, 1),
(259, 'Meucheln', 'Assassination', 'Assassination', 'Asesinato', 'Asesinato', 'Assassinat', 'Assassinio', '암살', 'Assassinato', 'Ликвидация', '奇袭', '刺殺', 'https://render-eu.worldofwarcraft.com/icons/56/ability_rogue_deadlybrew.jpg', 4, 1),
(260, 'Gesetzlosigkeit', 'Outlaw', 'Outlaw', 'Forajido', 'Forajido', 'Hors-la-loi', 'Fuorilegge', '무법', 'Fora da Lei', 'Головорез', '狂徒', '暴徒', 'https://render-eu.worldofwarcraft.com/icons/56/ability_rogue_waylay.jpg', 4, 1),
(261, 'Täuschung', 'Subtlety', 'Subtlety', 'Sutileza', 'Sutileza', 'Finesse', 'Scaltrezza', '잠행', 'Subterfúgio', 'Скрытность', '敏锐', '敏銳', 'https://render-eu.worldofwarcraft.com/icons/56/ability_stealth.jpg', 4, 1),
(262, 'Elementar', 'Elemental', 'Elemental', 'Elemental', 'Elemental', 'Élémentaire', 'Elementale', '정기', 'Elemental', 'Стихии', '元素', '元素', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_lightning.jpg', 7, 1),
(263, 'Verstärkung', 'Enhancement', 'Enhancement', 'Mejora', 'Mejora', 'Amélioration', 'Potenziamento', '고양', 'Aperfeiçoamento', 'Совершенствование', '增强', '增強', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shaman_improvedstormstrike.jpg', 7, 1),
(264, 'Wiederherstellung', 'Restoration', 'Restoration', 'Restauración', 'Restauración', 'Restauration', 'Rigenerazione', '복원', 'Restauração', 'Исцеление', '恢复', '恢復', 'https://render-eu.worldofwarcraft.com/icons/56/spell_nature_magicimmunity.jpg', 7, 3),
(265, 'Gebrechen', 'Affliction', 'Affliction', 'Aflicción', 'Aflicción', 'Affliction', 'Afflizione', '고통', 'Suplício', 'Колдовство', '痛苦', '痛苦', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_deathcoil.jpg', 9, 1),
(266, 'Dämonologie', 'Demonology', 'Demonology', 'Demonología', 'Demonología', 'Démonologie', 'Demonologia', '악마', 'Demonologia', 'Демонология', '恶魔学识', '惡魔學識', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_metamorphosis.jpg', 9, 1),
(267, 'Zerstörung', 'Destruction', 'Destruction', 'Destrucción', 'Destrucción', 'Destruction', 'Distruzione', '파괴', 'Destruição', 'Разрушение', '毁灭', '毀滅', 'https://render-eu.worldofwarcraft.com/icons/56/spell_shadow_rainoffire.jpg', 9, 1),
(268, 'Braumeister', 'Brewmaster', 'Brewmaster', 'Maestro cervecero', 'Maestro cervecero', 'Maître brasseur', 'Mastro Birraio', '양조', 'Mestre Cervejeiro', 'Хмелевар', '酒仙', '釀酒', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_brewmaster_spec.jpg', 10, 2),
(269, 'Windläufer', 'Windwalker', 'Windwalker', 'Viajero del viento', 'Viajero del viento', 'Marche-vent', 'Impeto', '풍운', 'Andarilho do Vento', 'Танцующий с ветром', '踏风', '御風', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_windwalker_spec.jpg', 10, 1),
(270, 'Nebelwirker', 'Mistweaver', 'Mistweaver', 'Tejedor de niebla', 'Tejedor de niebla', 'Tisse-brume', 'Misticismo', '운무', 'Tecelão da Névoa', 'Ткач туманов', '织雾', '織霧', 'https://render-eu.worldofwarcraft.com/icons/56/spell_monk_mistweaver_spec.jpg', 10, 3),
(577, 'Verwüstung', 'Havoc', 'Havoc', 'Devastación', 'Caos', 'Dévastation', 'Rovina', '파멸', 'Devastação', 'Истребление', '浩劫', '災虐', 'https://render-eu.worldofwarcraft.com/icons/56/ability_demonhunter_specdps.jpg', 12, 1),
(581, 'Rachsucht', 'Vengeance', 'Vengeance', 'Venganza', 'Venganza', 'Vengeance', 'Vendetta', '복수', 'Vingança', 'Месть', '复仇', '復仇', 'https://render-eu.worldofwarcraft.com/icons/56/ability_demonhunter_spectank.jpg', 12, 2);

-- --------------------------------------------------------

--
-- Structure de la table `REALMS`
--

CREATE TABLE `REALMS` (
  `ID` int NOT NULL,
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

-- --------------------------------------------------------

--
-- Structure de la table `SPECIALIZATION_ROLES`
--

CREATE TABLE `SPECIALIZATION_ROLES` (
  `ID` int NOT NULL,
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

INSERT INTO `SPECIALIZATION_ROLES` (`ID`, `DE_DE`, `EN_GB`, `EN_US`, `ES_ES`, `ES_MX`, `FR_FR`, `IT_IT`, `KO_KR`, `PT_BR`, `RU_RU`, `ZH_CN`, `ZH_TW`, `TYPE`) VALUES
(1, 'Schaden', 'Damage', 'Damage', 'Daño', 'Daño', 'Dégâts', 'Assaltatore', '공격 전담', 'Dano', 'Боец', '伤害输出', '傷害輸出', 'DAMAGE'),
(2, 'Tank', 'Tank', 'Tank', 'Tanque', 'Tanque', 'Tank', 'Difensore', '방어 전담', 'Tanque', 'Танк', '坦克', '坦克', 'TANK'),
(3, 'Heilung', 'Healer', 'Healer', 'Sanador', 'Sanador', 'Soins', 'Guaritore', '치유 전담', 'Cura', 'Лекарь', '治疗', '治療者', 'HEALER');

-- --------------------------------------------------------

--
-- Structure de la table `USER_ACCOUNT`
--

CREATE TABLE `USER_ACCOUNT` (
  `BLIZZARD_ID` int NOT NULL,
  `BATTLE_TAG` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CREATION_DATE_TIME` datetime NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LAST_LOGIN_DATE_TIME` datetime NOT NULL,
  `NICK_NAME` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `VERSION` int NOT NULL
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
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKmdh4jrxucuaco7ul8euyt1gyx` (`GUILD_ID`);

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
-- Contraintes pour la table `GUILD_RANKS`
--
ALTER TABLE `GUILD_RANKS`
  ADD CONSTRAINT `FKmdh4jrxucuaco7ul8euyt1gyx` FOREIGN KEY (`GUILD_ID`) REFERENCES `GUILDS` (`ID`);

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
