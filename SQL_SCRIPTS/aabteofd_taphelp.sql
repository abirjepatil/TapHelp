-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jan 14, 2016 at 03:58 AM
-- Server version: 5.5.45-37.4-log
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `aabteofd_taphelp`
--

-- --------------------------------------------------------

--
-- Table structure for table `TBL_SERVICE`
--

CREATE TABLE IF NOT EXISTS `TBL_SERVICE` (
  `SID` int(3) NOT NULL,
  `SERVICE_TYPE` int(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='DIFFERENT SERVICE TYPES THE APP WILL PROVIDE';

-- --------------------------------------------------------

--
-- Table structure for table `TBL_SERVICE_MAP`
--

CREATE TABLE IF NOT EXISTS `TBL_SERVICE_MAP` (
  `SERVICE_ID` int(3) NOT NULL,
  `UID` int(5) NOT NULL,
  `SID` int(5) NOT NULL,
  `RATING` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='MAPPING SERVICES TO SER PROVIDERS';

-- --------------------------------------------------------

--
-- Table structure for table `TBL_SERVICE_REQUEST`
--

CREATE TABLE IF NOT EXISTS `TBL_SERVICE_REQUEST` (
  `REQ_ID` int(3) NOT NULL,
  `UID` int(3) NOT NULL,
  `SERVICE_ID` int(5) NOT NULL,
  `STATUS` varchar(30) NOT NULL,
  `COMMENTS` varchar(255) NOT NULL,
  `CREATE_DATE` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `TBL_USER`
--

CREATE TABLE IF NOT EXISTS `TBL_USER` (
  `UID` int(5) NOT NULL,
  `USER_NAME` varchar(32) NOT NULL,
  `EMAIL_ID` varchar(255) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `USER_TYPE` int(1) NOT NULL,
  `FIRST_NAME` varchar(32) NOT NULL,
  `LAST_NAME` varchar(32) NOT NULL,
  `ADDRESS` varchar(64) NOT NULL,
  `PINCODE` int(8) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='USERTABLE: USERTYPE FIELD WILL BE USED TO DIFFERENTIATE BETWEEN A FACEBOOK AUTH';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
