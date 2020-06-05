--
-- Downloading data from the `USER_ACCOUNT` table
--

INSERT INTO `USER_ACCOUNT` (`ID`, `EMAIL`, `ENABLED`, `NICK_NAME`, `PASSWORD`) VALUES
(1, 'usertestfirstname@outlook.com', b'0', 'usertestnickname', '$2a$10$f6Pxq5srHu4OeA2Y6dmrR.58nXT8.eYuEUnTpdH27K42uB4Ohbpqi'),
(2, 'admintestfirstname@outlook.com', b'0', 'admintestnickname', '$2a$10$f6Pxq5srHu4OeA2Y6dmrR.58nXT8.eYuEUnTpdH27K42uB4Ohbpqi'),
(3, 'adminandusertestfirstname@outlook.com', b'0', 'adminandusertestnickname', '$2a$10$f6Pxq5srHu4OeA2Y6dmrR.58nXT8.eYuEUnTpdH27K42uB4Ohbpqi');

--
-- Downloading data from the `USER_ROLES` table
--

INSERT INTO `USER_ROLES` (`USER_ACCOUNT_ID`, `ROLES`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_USER'),
(3, 'ROLE_ADMIN');