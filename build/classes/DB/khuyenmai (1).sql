-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 25, 2025 lúc 01:05 PM
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
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `id` int(11) NOT NULL,
  `magg` varchar(50) NOT NULL,
  `soluong` int(11) DEFAULT 0,
  `ngaybdau` date NOT NULL,
  `ngaykthuc` date NOT NULL,
  `ap_dung_hoa_don_tu` double DEFAULT 0,
  `gia_tri` decimal(10,2) NOT NULL,
  `hinh_thuc` enum('phan_tram','tien') NOT NULL,
  `ma_nv` int(11) DEFAULT NULL,
  `id_dich_vu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`id`, `magg`, `soluong`, `ngaybdau`, `ngaykthuc`, `ap_dung_hoa_don_tu`, `gia_tri`, `hinh_thuc`, `ma_nv`, `id_dich_vu`) VALUES
(1, 'KM01', 2, '2025-07-01', '2025-07-31', 5000, 20.00, 'phan_tram', 1, 4),
(2, 'KM02', 4, '2025-07-05', '2025-07-20', 10000, 500.00, 'tien', 1, 6),
(3, 'KM03', 4, '2025-06-02', '2025-07-17', 1000, 30.00, 'tien', 1, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `magg` (`magg`),
  ADD KEY `fk_ma_nv` (`ma_nv`),
  ADD KEY `fk_ma_dich_vu` (`id_dich_vu`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD CONSTRAINT `fk_ma_dich_vu` FOREIGN KEY (`id_dich_vu`) REFERENCES `dich_vu` (`id`),
  ADD CONSTRAINT `fk_ma_nv` FOREIGN KEY (`ma_nv`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
