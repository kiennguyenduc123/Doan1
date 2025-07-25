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
-- Cấu trúc bảng cho bảng `hoa_don`
--

CREATE TABLE `hoa_don` (
  `id` int(11) NOT NULL,
  `ma_hoa_don` varchar(50) DEFAULT NULL,
  `id_don_hang` int(11) DEFAULT NULL,
  `ngaythanhtoan` date DEFAULT NULL,
  `tongtien` decimal(12,2) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_ma_gg` int(11) DEFAULT NULL,
  `ma_kh` varchar(20) DEFAULT NULL,
  `diem_sudung` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoa_don`
--

INSERT INTO `hoa_don` (`id`, `ma_hoa_don`, `id_don_hang`, `ngaythanhtoan`, `tongtien`, `id_user`, `id_ma_gg`, `ma_kh`, `diem_sudung`) VALUES
(1, 'MHD01', 1, '2025-07-01', 1800000.00, 1, 1, 'kh1', 20),
(2, 'MHD02', 2, '2025-07-02', 1500000.00, 1, 2, 'kh2', 30),
(3, 'MHD03', 3, '2025-07-03', 500000.00, 1, 3, 'kh3', 50),
(4, 'MHD04', 4, '2025-07-04', 3000000.00, 1, 3, 'kh4', 100),
(5, 'MHD05', 5, '2025-07-05', 2500000.00, 1, 1, 'kh5', 120),
(20, 'HD1752594206500', 7, '2025-07-15', 1590000.00, 1, 1, 'mkh02', 10),
(23, 'HD1752659847761', 5, '2025-07-16', 2070000.00, 1, 1, 'mkh03', 10),
(25, 'HD1752659953741', 3, '2025-07-16', 2079000.00, 1, 1, 'mkh03', 1),
(26, 'HD1752660951912', 1, '2025-07-16', 2080000.00, 1, 1, 'mkh03', 0),
(27, 'HD1752661238749', 7, '2025-07-16', 1590000.00, 1, 1, 'mkh02', 10),
(28, 'HD1752816106092', 8, '2025-07-18', 2080000.00, 1, 1, 'mkh03', 0),
(29, 'HD1753084457805', 8, '2025-07-21', 1980000.00, 1, 1, 'MKh03', 100),
(30, 'HD1753241006263', 7, '2025-07-23', 1500000.00, 1, 1, 'mkh01', 100),
(31, 'HD1753251364383', 2, '2025-07-23', 580000.00, 1, 1, 'mkh01', 60),
(32, 'HD1753252022908', 3, '2025-07-23', 504000.00, 1, 1, 'mkh02', 56),
(33, 'HD1753252284839', 7, '2025-07-23', 1580000.00, 1, 1, 'mkh01', 20),
(34, 'HD1753252794004', 8, '2025-07-23', 2080000.00, 1, 1, 'mkh03', 0),
(35, 'HD1753253560563', 3, '2025-07-23', 530000.00, 1, 1, 'mkh02', 30),
(36, 'HD1753418502260', 9, '2025-07-25', 2460000.00, 1, 1, 'MKh01', 100);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ma_hoa_don` (`ma_hoa_don`),
  ADD KEY `id_don_hang` (`id_don_hang`),
  ADD KEY `fk_user` (`id_user`),
  ADD KEY `fk_khuyenmai` (`id_ma_gg`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoa_don`
--
ALTER TABLE `hoa_don`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `fk_khuyenmai` FOREIGN KEY (`id_ma_gg`) REFERENCES `khuyenmai` (`id`),
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `hoa_don_ibfk_1` FOREIGN KEY (`id_don_hang`) REFERENCES `lich_thue` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
