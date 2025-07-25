-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 25, 2025 lúc 11:27 AM
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
-- Cấu trúc bảng cho bảng `kho_nhap_dich_vu`
--

CREATE TABLE `kho_nhap_dich_vu` (
  `id` int(11) NOT NULL,
  `ma_phieu_nhap` varchar(50) DEFAULT NULL,
  `dich_vu_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `ngay_nhap` date NOT NULL,
  `gia_nhap` decimal(10,2) NOT NULL,
  `ghi_chu` text DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nha_cung_cap_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `kho_nhap_dich_vu`
--

INSERT INTO `kho_nhap_dich_vu` (`id`, `ma_phieu_nhap`, `dich_vu_id`, `user_id`, `so_luong`, `ngay_nhap`, `gia_nhap`, `ghi_chu`, `created_at`, `updated_at`, `nha_cung_cap_id`) VALUES
(1, 'MPN01', 3, 1, 2, '2025-07-15', 2000.00, 'chất lượng tốt', '2025-07-18 13:11:02', '2025-07-18 13:11:02', 2),
(2, 'MPN02', 9, 1, 12, '2025-07-16', 8000.00, 'dịch vụ ổn', '2025-07-18 13:11:02', '2025-07-18 13:11:02', 3),
(5, 'MPN03', 2, 1, 2, '2025-07-19', 2000.00, 'tottt', '2025-07-19 13:13:48', '2025-07-19 13:13:48', 2),
(10, 'MPN04', 3, 1, 2, '2025-07-19', 2500.00, 'chất lượng vải tốt', '2025-07-19 14:02:13', '2025-07-19 14:02:13', 1),
(11, 'MPN05', 4, 1, 12, '2025-07-19', 100.00, 'vải okk', '2025-07-19 14:09:40', '2025-07-19 14:09:40', 1),
(12, 'MPN06', 9, 1, 2, '2025-07-25', 36000.00, 'tốt', '2025-07-25 11:44:24', '2025-07-25 11:44:24', 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `kho_nhap_dich_vu`
--
ALTER TABLE `kho_nhap_dich_vu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ma_phieu_nhap` (`ma_phieu_nhap`),
  ADD KEY `dich_vu_id` (`dich_vu_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `fk_ncc` (`nha_cung_cap_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `kho_nhap_dich_vu`
--
ALTER TABLE `kho_nhap_dich_vu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `kho_nhap_dich_vu`
--
ALTER TABLE `kho_nhap_dich_vu`
  ADD CONSTRAINT `fk_ncc` FOREIGN KEY (`nha_cung_cap_id`) REFERENCES `nhacc` (`id`),
  ADD CONSTRAINT `kho_nhap_dich_vu_ibfk_1` FOREIGN KEY (`dich_vu_id`) REFERENCES `dich_vu` (`id`),
  ADD CONSTRAINT `kho_nhap_dich_vu_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
