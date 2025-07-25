-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 25, 2025 lúc 01:08 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `doan1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacc`
--

CREATE TABLE `nhacc` (
  `id` int(11) NOT NULL,
  `mancc` varchar(10) NOT NULL,
  `tenncc` varchar(100) NOT NULL,
  `sdt` varchar(15) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `trangthai` enum('Đang hợp tác','Ngừng hợp tác') DEFAULT 'Đang hợp tác'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacc`
--

INSERT INTO `nhacc` (`id`, `mancc`, `tenncc`, `sdt`, `diachi`, `trangthai`) VALUES
(1, 'NCC1', 'Organic Quận 2', '0907654321', 'Quận 2, TP.HCM', 'Ngừng hợp tác'),
(2, 'NCC2', 'Organic Quận 1', '0931418742', 'Quận 1, TP.HCM', 'Đang hợp tác'),
(3, 'NCC3', 'Organic Quận 3', '0907123415', 'Quận 3, TP.HCM', 'Ngừng hợp tác'),
(4, 'NCC4', 'Organic Quận 4', '0907123415', 'Quận 3, TP.HCM', 'Ngừng hợp tác');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `nhacc`
--
ALTER TABLE `nhacc`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `nhacc`
--
ALTER TABLE `nhacc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
