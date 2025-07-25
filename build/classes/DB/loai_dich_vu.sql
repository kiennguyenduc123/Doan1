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
-- Cấu trúc bảng cho bảng `loai_dich_vu`
--

CREATE TABLE `loai_dich_vu` (
  `id` int(11) NOT NULL,
  `tenloai` varchar(100) DEFAULT NULL,
  `maloai` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loai_dich_vu`
--

INSERT INTO `loai_dich_vu` (`id`, `tenloai`, `maloai`) VALUES
(1, 'Thuê váy cưới', 'LDV01'),
(2, 'Thuê vest chú rể', 'LDV02'),
(3, 'Thuê áo dài cưới', 'LDV03'),
(4, 'Thuê đồ bê tráp', 'LDV04'),
(5, 'Thuê phụ kiện cưới', 'LDV05'),
(6, 'Trang điểm cô dâu', 'LDV06'),
(7, 'Chụp hình cưới', 'LDV07'),
(8, 'Thuê xe cưới', 'LDV08'),
(9, 'Trang trí tiệc cưới', 'LDV09'),
(10, 'Nhân sự lễ cưới', 'LDV10'),
(11, 'Thuê thợ chụp', 'LDV11');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `loai_dich_vu`
--
ALTER TABLE `loai_dich_vu`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `loai_dich_vu`
--
ALTER TABLE `loai_dich_vu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
