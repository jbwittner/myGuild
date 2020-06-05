--
-- Structure of the `USER_ACCOUNT` table
--

CREATE TABLE `USER_ACCOUNT` (
  `ID` int(11) NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `NICK_NAME` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index for the `USER_ACCOUNT` table
--
ALTER TABLE `USER_ACCOUNT`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_EMAIL` (`EMAIL`),
  ADD UNIQUE KEY `UK_NICK_NAME` (`NICK_NAME`);
COMMIT;

--
-- Structure of the `USER_ROLES` table
--

CREATE TABLE `USER_ROLES` (
  `USER_ACCOUNT_ID` int(11) NOT NULL,
  `ROLES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index for the `USER_ROLES` table
--
ALTER TABLE `USER_ROLES`
  ADD KEY `FK_ACCOUNT_ID` (`USER_ACCOUNT_ID`);

--
-- Constraints for the `USER_ROLES` table
--
ALTER TABLE `USER_ROLES`
  ADD CONSTRAINT `FK_ACCOUNT_ID` FOREIGN KEY (`USER_ACCOUNT_ID`) REFERENCES `USER_ACCOUNT` (`ID`);
COMMIT;