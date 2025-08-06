-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 06, 2025 at 06:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_masuya`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `kode_customer` varchar(10) NOT NULL,
  `nama_customer` varchar(255) NOT NULL,
  `alamat_lengkap` text NOT NULL,
  `id_wilayah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`kode_customer`, `nama_customer`, `alamat_lengkap`, `id_wilayah`) VALUES
('2', 'test2', 'test2', 3);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `no_invoice` varchar(20) NOT NULL,
  `kode_produk` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL,
  `disc1` decimal(5,2) DEFAULT 0.00,
  `disc2` decimal(5,2) DEFAULT 0.00,
  `disc3` decimal(5,2) DEFAULT 0.00,
  `harga_net` decimal(14,2) NOT NULL,
  `jumlah` decimal(14,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail`, `no_invoice`, `kode_produk`, `qty`, `disc1`, `disc2`, `disc3`, `harga_net`, `jumlah`) VALUES
(2, 'INV/2508/0006', '2', 10, 10.00, 10.00, 10.00, 2916.00, 29160.00),
(3, 'INV/2508/0004', '2', 2, 10.00, 10.00, 20.00, 2592.00, 5184.00),
(4, 'INV/2508/0003', '2', 2, 10.00, 10.00, 10.00, 2916.00, 5832.00);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kode_produk` varchar(10) NOT NULL,
  `nama_produk` varchar(255) NOT NULL,
  `harga_produk` decimal(12,2) NOT NULL,
  `stok_produk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`kode_produk`, `nama_produk`, `harga_produk`, `stok_produk`) VALUES
('2', 'test4', 4000.00, 7),
('121', 'test5', 1000.00, 12),
('124', 'test6', 12000.00, 12),
('12', 'test4', 4000.00, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_invoice` varchar(20) NOT NULL,
  `kode_customer` varchar(10) NOT NULL,
  `tgl_invoice` date NOT NULL,
  `total` decimal(14,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_invoice`, `kode_customer`, `tgl_invoice`, `total`) VALUES
('INV/2508/0003', '2', '2025-08-06', 5832.00),
('INV/2508/0004', '2', '2025-08-06', 0.00),
('INV/2508/0005', '2', '2025-08-06', 0.00),
('INV/2508/0006', '2', '2025-08-06', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `wilayah`
--

CREATE TABLE `wilayah` (
  `id_wilayah` int(11) NOT NULL,
  `provinsi` varchar(255) NOT NULL,
  `kota` varchar(255) NOT NULL,
  `kecamatan` varchar(255) NOT NULL,
  `kelurahan` varchar(255) NOT NULL,
  `kode_pos` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wilayah`
--

INSERT INTO `wilayah` (`id_wilayah`, `provinsi`, `kota`, `kecamatan`, `kelurahan`, `kode_pos`) VALUES
(1, 'test', 'test', 'test', 'test', '1234'),
(2, 'test', 'test', 'test', 'test', 'test'),
(3, 'test2', 'test2', 'test', 'test', 'test'),
(4, 'odkawod', 'poakdpa', 'apokwd', 'poakwd', 'apokwd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`);

--
-- Indexes for table `wilayah`
--
ALTER TABLE `wilayah`
  ADD PRIMARY KEY (`id_wilayah`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `wilayah`
--
ALTER TABLE `wilayah`
  MODIFY `id_wilayah` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
