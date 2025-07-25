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
-- Cấu trúc bảng cho bảng `lich_thue`
--

CREATE TABLE `lich_thue` (
  `id` int(11) NOT NULL,
  `ma_lich_thue` varchar(100) DEFAULT NULL,
  `id_khach_hang` int(11) DEFAULT NULL,
  `ngaydat` date DEFAULT NULL,
  `ngaysudung` date DEFAULT NULL,
  `tinhtrang` enum('đã đặt','đã hoàn thành','đã hủy') DEFAULT NULL,
  `ghichu` text DEFAULT NULL,
  `nhan_vien_id` int(11) DEFAULT NULL,
  `ngay_tra` date DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lich_thue`
--

INSERT INTO `lich_thue` (`id`, `ma_lich_thue`, `id_khach_hang`, `ngaydat`, `ngaysudung`, `tinhtrang`, `ghichu`, `nhan_vien_id`, `ngay_tra`, `created_at`, `updated_at`) VALUES
(1, 'MLT01', 1, '2025-07-01', '2025-07-10', 'đã đặt', 'Đặt váy cưới và phụ kiện', 1, '2025-07-09', '2025-07-07 08:49:43', '2025-07-09 08:47:33'),
(2, 'MLT02', 2, '2025-07-02', '2025-07-11', 'đã hoàn thành', 'Đã thuê trọn gói đồ cưới', 2, '2025-07-16', '2025-07-07 08:49:43', '2025-07-09 08:47:42'),
(3, 'MLT03', 23, '2025-07-03', '2025-07-15', 'đã hủy', 'Hủy do thay đổi lịch cưới', 1, '2025-07-23', '2025-07-07 08:49:43', '2025-07-21 14:51:52'),
(4, 'MLT04', 1, '2025-07-04', '2025-07-12', 'đã đặt', 'Thuê thêm xe cưới và trang điểm', 2, '2025-07-30', '2025-07-07 08:49:43', '2025-07-09 08:47:57'),
(5, 'MLT05', 2, '2025-07-05', '2025-07-13', 'đã đặt', 'Trang trí lễ cưới tại nhà', 3, '2025-08-13', '2025-07-07 08:49:43', '2025-07-09 08:48:03'),
(6, 'MLT06', 1, '2025-07-13', '2025-07-17', 'đã đặt', NULL, 1, '2025-07-19', '2025-07-13 13:55:52', '2025-07-13 13:55:52'),
(7, 'MLT07', 2, '2025-07-13', '2025-07-15', 'đã hoàn thành', NULL, 1, '2025-07-21', '2025-07-15 20:59:37', '2025-07-15 20:59:37'),
(8, 'MLT08', 24, '2025-07-15', '2025-07-21', 'đã đặt', NULL, 1, '2025-07-23', '2025-07-15 21:16:45', '2025-07-21 14:52:21'),
(9, 'MLT09', 2, '2025-07-25', '2025-07-30', 'đã hoàn thành', NULL, 1, '2025-08-04', '2025-07-25 10:25:08', '2025-07-25 10:25:08');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `lich_thue`
--
ALTER TABLE `lich_thue`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ma_don_hang` (`ma_lich_thue`),
  ADD KEY `id_khach_hang` (`id_khach_hang`),
  ADD KEY `nhan_vien_id` (`nhan_vien_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `lich_thue`
--
ALTER TABLE `lich_thue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `lich_thue`
--
ALTER TABLE `lich_thue`
  ADD CONSTRAINT `lich_thue_ibfk_1` FOREIGN KEY (`id_khach_hang`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `lich_thue_ibfk_2` FOREIGN KEY (`nhan_vien_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
