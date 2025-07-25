-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 25, 2025 lúc 01:09 PM
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
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `tendangnhap` varchar(250) NOT NULL,
  `matkhau` varchar(100) NOT NULL,
  `hoten` varchar(255) DEFAULT NULL,
  `sdt` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dia_chi` varchar(200) DEFAULT NULL,
  `vai_tro` enum('admin','customer','employee') DEFAULT 'customer',
  `diem_tich_luy` int(11) DEFAULT 0,
  `ma_khachhang` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `tendangnhap`, `matkhau`, `hoten`, `sdt`, `email`, `dia_chi`, `vai_tro`, `diem_tich_luy`, `ma_khachhang`) VALUES
(1, 'admin', '202cb962ac59075b964b07152d234b70', 'NguyenKien', '213124', 'adwa@gmail.com', 'HaiPhong', 'admin', 0, 'MAD01'),
(2, 'khach1', '250cf8b51c773f3f8dc8b4be867a9a02', 'HoangDinhHieu', '232151321', 'hieu@gmail.com', 'Hà Nội', 'customer', 2700, 'MKH01'),
(3, 'nhanvien1', '68053af2923e00204c3ca7c6a3150cf7', 'QuocHuy', '032131412', 'huy@gmail.com', 'Thái Nguyên', 'employee', 1540, 'MNV01'),
(21, 'nhanvien2', '123456', 'HoangTrung', '019313131', 'hoangtrung@gmail.com', 'thái nguyên', 'employee', 0, 'MNV02'),
(22, 'nhanvien', '202cb962ac59075b964b07152d234b70', 'Nguyen Chung', '041234112', 'chung@gmail.com', 'Hải Phòng', 'employee', 0, 'MNV03'),
(23, 'khach2', '123', 'PhamSon', '041212412', 'son@gmail.com', 'Hải Phòng', 'customer', 1540, 'MKH02'),
(24, 'khach3', '123', 'Dương Thăng', '041231231', 'thangcap@gmail.com', 'Quảng Ninh', 'customer', 1600, 'MKH03');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
