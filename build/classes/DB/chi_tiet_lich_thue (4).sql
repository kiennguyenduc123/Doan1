-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 25, 2025 lúc 11:26 AM
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
-- Cấu trúc bảng cho bảng `chi_tiet_lich_thue`
--

CREATE TABLE `chi_tiet_lich_thue` (
  `id` int(11) NOT NULL,
  `id_lich_thue` int(11) DEFAULT NULL,
  `id_dich_vu` int(11) DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `dongia` decimal(10,2) DEFAULT NULL,
  `ngay_su_dung` date DEFAULT NULL,
  `ghi_chu` text DEFAULT NULL,
  `id_chi_tiet_hoa_don` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_lich_thue`
--

INSERT INTO `chi_tiet_lich_thue` (`id`, `id_lich_thue`, `id_dich_vu`, `soluong`, `dongia`, `ngay_su_dung`, `ghi_chu`, `id_chi_tiet_hoa_don`) VALUES
(1, 1, 1, 1, 1500000.00, '2025-07-31', 'Size L, gói Vip', 1),
(2, 1, 6, 1, 300000.00, '2025-07-17', 'Đến sớm trước 5h sáng để makeup', 2),
(3, 2, 2, 1, 800000.00, '2025-08-12', 'Quá tuyệt vời', 3),
(4, 3, 3, 1, 700000.00, '2025-08-19', 'Gói thường và đơn giản', 4),
(5, 4, 9, 1, 2000000.00, '2025-07-23', 'Gói hoàn hảo và đồ phải được sớm nhất có thể', 5),
(6, 4, 7, 1, 1000000.00, '2025-07-26', 'Đồ gọn gàng sạch sẽ', 2),
(7, 5, 10, 1, 2500000.00, '2025-07-28', 'Đồ make up và phụ kiện đẹp và chuẩn bị kỹ càng', 3),
(10, 6, 8, 2, 3000000.00, '2025-07-13', 'deepp', 3),
(11, 6, 7, 2, 1000000.00, '2025-07-13', 'quaa', 2),
(12, 6, 4, 2, 400000.00, '2025-07-13', 'dii', 1),
(13, 7, 7, 2, 1000000.00, '2025-07-15', 'dappp', 5),
(14, 8, 7, 2, 1000000.00, '2025-07-16', 'aaaaa', NULL),
(15, 9, 6, 2, 300000.00, '2025-07-16', 'quaaaa', NULL),
(16, 9, 2, 2, 800000.00, '2025-07-25', 'hàng tốt', NULL),
(17, 9, 7, 1, 1000000.00, '2025-07-25', 'xinh', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi_tiet_lich_thue`
--
ALTER TABLE `chi_tiet_lich_thue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_don_hang` (`id_lich_thue`),
  ADD KEY `id_dich_vu` (`id_dich_vu`),
  ADD KEY `fk_cthd` (`id_chi_tiet_hoa_don`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chi_tiet_lich_thue`
--
ALTER TABLE `chi_tiet_lich_thue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_lich_thue`
--
ALTER TABLE `chi_tiet_lich_thue`
  ADD CONSTRAINT `chi_tiet_lich_thue_ibfk_1` FOREIGN KEY (`id_lich_thue`) REFERENCES `lich_thue` (`id`),
  ADD CONSTRAINT `chi_tiet_lich_thue_ibfk_2` FOREIGN KEY (`id_dich_vu`) REFERENCES `dich_vu` (`id`),
  ADD CONSTRAINT `fk_cthd` FOREIGN KEY (`id_chi_tiet_hoa_don`) REFERENCES `chi_tiet_hoa_don` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
