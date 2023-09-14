-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 14, 2023 at 12:12 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `available` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `author`, `available`, `description`, `name`) VALUES
(1, '--', 'deleted', 'Thriller', 'Deleted book'),
(2, 'J.K', 'borrowed', 'Fantasy', 'Harry Potter'),
(3, 'J.K', 'available', 'Fantasy', 'Harry Potter2'),
(4, 'J.K', 'available', 'Fantasy', 'Harry Potter3'),
(5, 'Gillian Flynn', 'available', 'Thriller', 'Gone Girl'),
(6, 'Humayun Ahmed', 'available', 'Comedy', 'Himu'),
(7, 'Mohammad Jafor Akbar', 'available', 'Comedy', 'Misir Ali');

-- --------------------------------------------------------

--
-- Table structure for table `borrow_book`
--

CREATE TABLE `borrow_book` (
  `book_book_id` int(11) DEFAULT NULL,
  `borrow_id` int(11) NOT NULL,
  `due_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrow_book`
--

INSERT INTO `borrow_book` (`book_book_id`, `borrow_id`, `due_date`, `return_date`, `user_user_id`) VALUES
(2, 2, '2023-09-21', '2023-09-14', 2),
(2, 3, '2023-09-21', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `review_book`
--

CREATE TABLE `review_book` (
  `book_book_id` int(11) DEFAULT NULL,
  `ratings` float NOT NULL,
  `review_id` int(11) NOT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `review_book`
--

INSERT INTO `review_book` (`book_book_id`, `ratings`, `review_id`, `user_user_id`, `review`) VALUES
(2, 8.9, 1, 4, 'Very very good'),
(2, 8, 3, 2, 'nice');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `address`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(1, 'Admin', 'admin@gmail.com', 'Admin', 'Admin', '$2a$10$sCm1shAmrLnDTFVANaooIeyP8Uu52QlUL8oTmOymQ/fu7D1cn1MOy', 'ADMIN'),
(2, 'Shyamoli', 'maliha@gmail.com', 'Maliha', 'Tabassum', '$2a$10$s8GVinF/Bh5VFr6ZtjdRnOf21ZKTXKdHYcoCjs2ZGzNnc7lksXBau', 'CUSTOMER'),
(3, 'Badda', 'zilani@gmail.com', 'Zilani', 'Mia', '$2a$10$o0MeCjIRp4NkXAv5/IXx8.EG6Y4Rkh8ezxgB4BT9gLbAkSuQLV.fa', 'CUSTOMER'),
(4, 'Malibag', 'moen@gmail.com', 'Moen', 'Uddin', '$2a$10$BFu3uSYcTDBL4XKwGR/sf.FTEH4VUd1yElaF1XbNYaz59wc8V6M3O', 'CUSTOMER'),
(6, 'Shiganshina', 'eren@gmail.com', 'Eren', 'Yaeger', '$2a$10$M0w6owGsFxWa1c9dBhg4G.JEpaLtSMvw0x9JQUJa4TBWv6QYPAp22', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `borrow_book`
--
ALTER TABLE `borrow_book`
  ADD PRIMARY KEY (`borrow_id`),
  ADD KEY `FKaosmy2ecmtr2jhhegsr3pb76n` (`book_book_id`),
  ADD KEY `FKm5ud4sfhcf8bai6sw0vja0nj8` (`user_user_id`);

--
-- Indexes for table `review_book`
--
ALTER TABLE `review_book`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `FK5a3rx7kfc0fp0dh4ov3evgerr` (`book_book_id`),
  ADD KEY `FK1o0k17fxnc0vt4k71fidj86jp` (`user_user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `borrow_book`
--
ALTER TABLE `borrow_book`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `review_book`
--
ALTER TABLE `review_book`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrow_book`
--
ALTER TABLE `borrow_book`
  ADD CONSTRAINT `FKaosmy2ecmtr2jhhegsr3pb76n` FOREIGN KEY (`book_book_id`) REFERENCES `book` (`book_id`),
  ADD CONSTRAINT `FKm5ud4sfhcf8bai6sw0vja0nj8` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `review_book`
--
ALTER TABLE `review_book`
  ADD CONSTRAINT `FK1o0k17fxnc0vt4k71fidj86jp` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FK5a3rx7kfc0fp0dh4ov3evgerr` FOREIGN KEY (`book_book_id`) REFERENCES `book` (`book_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
