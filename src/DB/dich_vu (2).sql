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
-- Cấu trúc bảng cho bảng `dich_vu`
--

CREATE TABLE `dich_vu` (
  `id` int(11) NOT NULL,
  `ma_dich_vu` varchar(50) DEFAULT NULL,
  `tendichvu` varchar(255) DEFAULT NULL,
  `id_loai_dich_vu` int(11) DEFAULT NULL,
  `mota` text DEFAULT NULL,
  `giathue` decimal(10,2) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `trang_thai` enum('Còn hàng','Hết hàng','Bảo trì') DEFAULT 'Còn hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dich_vu`
--

INSERT INTO `dich_vu` (`id`, `ma_dich_vu`, `tendichvu`, `id_loai_dich_vu`, `mota`, `giathue`, `image_path`, `trang_thai`) VALUES
(1, 'MDV01', 'Váy cưới ren cao cấp', 1, 'Váy cưới ren phong cách châu Âu', 1500000.00, '—Pngtree—modern ball gown wedding dress_16056992.png', 'Hết hàng'),
(2, 'MDV02', 'Vest tuxedo đen cổ điển', 2, 'Vest cao cấp cho chú rể', 800000.00, '—Pngtree—cartoon groom wearing a suit_4579927.png', 'Hết hàng'),
(3, 'MDV03', 'Áo dài cưới đỏ truyền thống', 3, 'Trang phục cưới Việt cổ truyền', 700000.00, 'z6767313182512_b77987d8002bf88c173c39f4a9486ed1.jpg', 'Bảo trì'),
(4, 'MDV04', 'Trang phục bê tráp nam', 4, 'Đồng phục bê tráp cho nam', 400000.00, 'z6767335280581_b8c075d367ea8572d9810d0172d42ba3.jpg', 'Còn hàng'),
(5, 'MDV05', 'Trang phục bê tráp nữ', 4, 'Đồng phục bê tráp cho nữ', 400000.00, 'z6767356347853_a72447553cf70e23e9dc8596e6c6d5e3.jpg', 'Hết hàng'),
(6, 'MDV06', 'Gói phụ kiện cô dâu', 5, 'Voan, hoa, vòng cổ...', 300000.00, 'z6767456937249_63c1ed45051d0ab2da6629b99b69895f.jpg', 'Còn hàng'),
(7, 'MDV07', 'Makeup cô dâu tại nhà', 6, 'Trang điểm chuyên nghiệp', 1000000.00, 'z6767477691670_9f7aabd806e78547507812cb13562a13.jpg', 'Còn hàng'),
(8, 'MDV08', 'Gói chụp ảnh cưới studio', 7, 'Chụp ảnh trong studio chuyên nghiệp', 3000000.00, 'z6767502261249_01c109434c7fcc566d31b2e841f333fb.jpg', 'Hết hàng'),
(9, 'MDV09', 'Thuê xe cưới Mercedes', 8, 'Xe sang có hoa cưới', 2000000.00, 'z6767506174909_3493f8efb415f67971f52a2ec516b510.jpg', 'Còn hàng'),
(10, 'MDV10', 'Trang trí tiệc cưới basic', 9, 'Trang trí đơn giản cho lễ cưới', 2500000.00, 'z6767518603199_c244d0ee13ba40e3d609353e76c98bd8.jpg', 'Hết hàng'),
(13, 'MDV13', 'Váy cho cô dâu', 1, 'quá đẹp và sang trọng', 12312.00, '—Pngtree—modern ball gown wedding dress_16056992.png', 'Còn hàng'),
(21, 'MDV14', 'bộ đồ vest cho thuê', 2, 'thiết kế đẹp mắt giản dị', 20000.00, '—Pngtree—cartoon groom wearing a suit_4579927.png', 'Còn hàng'),
(22, 'MDV15', 'Trang phục thuê ', 3, 'đệp quá dii', 36000.00, '—Pngtree—modern ball gown wedding dress_16056992.png', 'Còn hàng');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ma_dich_vu` (`ma_dich_vu`),
  ADD KEY `id_loai_dich_vu` (`id_loai_dich_vu`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `dich_vu`
--
ALTER TABLE `dich_vu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD CONSTRAINT `dich_vu_ibfk_1` FOREIGN KEY (`id_loai_dich_vu`) REFERENCES `loai_dich_vu` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
