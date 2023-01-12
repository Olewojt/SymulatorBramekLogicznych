-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 12, 2023 at 07:20 PM
-- Server version: 8.0.31-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikacja`
--

-- --------------------------------------------------------

--
-- Table structure for table `AND_OutputMap`
--

CREATE TABLE `AND_OutputMap` (
  `ID` int NOT NULL,
  `input1` int NOT NULL,
  `input2` int NOT NULL,
  `output` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `AND_OutputMap`
--

INSERT INTO `AND_OutputMap` (`ID`, `input1`, `input2`, `output`) VALUES
(1, 0, 0, 0),
(2, 0, 1, 0),
(3, 1, 0, 0),
(4, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Bramki`
--

CREATE TABLE `Bramki` (
  `ID` int NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Inputs` int NOT NULL,
  `Map_Name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Bramki`
--

INSERT INTO `Bramki` (`ID`, `Name`, `Inputs`, `Map_Name`) VALUES
(1, 'AND', 2, 'AND_OutputMap'),
(2, 'OR', 2, 'OR_OutputMap'),
(3, 'NOT', 1, 'NOT_OutputMap'),
(4, 'NAND', 2, 'NAND_OutputMap'),
(5, 'NOR', 2, 'NOR_OutputMap'),
(6, 'XOR', 2, 'XOR_OutputMap');

-- --------------------------------------------------------

--
-- Table structure for table `NAND_OutputMap`
--

CREATE TABLE `NAND_OutputMap` (
  `ID` int NOT NULL,
  `input1` int NOT NULL,
  `input2` int NOT NULL,
  `output` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `NAND_OutputMap`
--

INSERT INTO `NAND_OutputMap` (`ID`, `input1`, `input2`, `output`) VALUES
(1, 0, 0, 1),
(2, 0, 1, 1),
(3, 1, 0, 1),
(4, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `NOR_OutputMap`
--

CREATE TABLE `NOR_OutputMap` (
  `ID` int NOT NULL,
  `input1` int NOT NULL,
  `input2` int NOT NULL,
  `output` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `NOR_OutputMap`
--

INSERT INTO `NOR_OutputMap` (`ID`, `input1`, `input2`, `output`) VALUES
(1, 0, 0, 1),
(2, 0, 1, 0),
(3, 1, 0, 0),
(4, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `NOT_OutputMap`
--

CREATE TABLE `NOT_OutputMap` (
  `ID` int NOT NULL,
  `input1` int NOT NULL,
  `output` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `NOT_OutputMap`
--

INSERT INTO `NOT_OutputMap` (`ID`, `input1`, `output`) VALUES
(1, 0, 1),
(2, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `OR_OutputMap`
--

CREATE TABLE `OR_OutputMap` (
  `ID` int NOT NULL,
  `input1` int NOT NULL,
  `input2` int NOT NULL,
  `output` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `OR_OutputMap`
--

INSERT INTO `OR_OutputMap` (`ID`, `input1`, `input2`, `output`) VALUES
(1, 0, 0, 0),
(2, 0, 1, 1),
(3, 1, 0, 1),
(4, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `XOR_OutputMap`
--

CREATE TABLE `XOR_OutputMap` (
  `ID` int NOT NULL,
  `input1` int NOT NULL,
  `input2` int NOT NULL,
  `output` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `XOR_OutputMap`
--

INSERT INTO `XOR_OutputMap` (`ID`, `input1`, `input2`, `output`) VALUES
(1, 0, 0, 0),
(2, 0, 1, 1),
(3, 1, 0, 1),
(4, 1, 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AND_OutputMap`
--
ALTER TABLE `AND_OutputMap`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Bramki`
--
ALTER TABLE `Bramki`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `NAND_OutputMap`
--
ALTER TABLE `NAND_OutputMap`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `NOR_OutputMap`
--
ALTER TABLE `NOR_OutputMap`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `NOT_OutputMap`
--
ALTER TABLE `NOT_OutputMap`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `OR_OutputMap`
--
ALTER TABLE `OR_OutputMap`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `XOR_OutputMap`
--
ALTER TABLE `XOR_OutputMap`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AND_OutputMap`
--
ALTER TABLE `AND_OutputMap`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `Bramki`
--
ALTER TABLE `Bramki`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `NAND_OutputMap`
--
ALTER TABLE `NAND_OutputMap`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `NOR_OutputMap`
--
ALTER TABLE `NOR_OutputMap`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `NOT_OutputMap`
--
ALTER TABLE `NOT_OutputMap`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `OR_OutputMap`
--
ALTER TABLE `OR_OutputMap`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `XOR_OutputMap`
--
ALTER TABLE `XOR_OutputMap`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
