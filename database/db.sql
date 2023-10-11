-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `power_of_speed`
--

-- --------------------------------------------------------

--
-- Table structure for table `abouts`
--

CREATE TABLE `abouts` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `state_region` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `create_at`, `status`, `update_at`, `address`, `city`, `country`, `postal_code`, `state_region`) VALUES
(1, '2020-11-09 20:32:41.750000', b'1', '2020-11-09 20:32:41.750000', 'Block A EHomse S Nam Sai Gon, Binh Hung', 'Ho Chi Minh', 'Vietnam', '80000', 'Binh Chanh'),
(2, '2020-11-09 20:32:41.896000', b'1', '2020-11-09 20:32:41.896000', '343 Pham Ngu Lao', 'Ho Chi Minh', 'Vietnam', '80000', 'Quan 1'),
(3, '2020-11-09 20:32:41.995000', b'1', '2020-11-09 20:32:41.995000', '343 Pham Ngu Lao', 'Ho Chi Minh', 'Vietnam', '80000', 'Quan 1');

-- --------------------------------------------------------

--
-- Table structure for table `bid_details`
--

CREATE TABLE `bid_details` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `auction_end` date DEFAULT NULL,
  `auction_start` date DEFAULT NULL,
  `bid_increment` int(11) DEFAULT NULL,
  `current_price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bid_details`
--

INSERT INTO `bid_details` (`id`, `create_at`, `status`, `update_at`, `auction_end`, `auction_start`, `bid_increment`, `current_price`) VALUES
(1, '2020-11-09 21:51:38.397000', b'1', '2020-11-09 21:51:38.397000', '2020-11-28', '2020-11-01', 5, 0),
(2, '2020-11-09 21:55:10.712000', b'1', '2020-11-09 21:55:10.712000', '2020-12-01', '2020-11-01', 5, 0),
(3, '2020-11-09 21:55:26.741000', b'1', '2020-11-09 21:55:26.741000', '2020-12-01', '2020-11-01', 5, 0),
(4, '2020-11-09 21:55:35.349000', b'1', '2020-11-09 21:55:35.349000', '2020-12-01', '2020-11-01', 5, 0),
(5, '2020-11-09 21:55:59.035000', b'1', '2020-11-09 21:55:59.035000', '2020-12-01', '2020-11-01', 5, 0),
(6, '2020-11-09 21:56:14.136000', b'1', '2020-11-09 21:56:14.136000', '2020-12-01', '2020-11-01', 5, 0),
(7, '2020-11-09 21:56:38.126000', b'1', '2020-11-09 21:56:38.126000', '2020-12-01', '2020-11-01', 5, 85);

-- --------------------------------------------------------

--
-- Table structure for table `bid_history`
--

CREATE TABLE `bid_history` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `bid_detail_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bid_history`
--

INSERT INTO `bid_history` (`id`, `create_at`, `status`, `update_at`, `unit_price`, `bid_detail_id`, `user_id`) VALUES
(1, '2020-11-09 22:45:59.478000', b'1', '2020-11-09 22:45:59.478000', 55, 7, 1),
(2, '2020-11-10 02:12:48.154000', b'1', '2020-11-10 02:12:48.154000', 65, 7, 1),
(3, '2020-11-10 10:44:11.159000', b'1', '2020-11-10 10:44:11.159000', 75, 7, 1),
(4, '2020-11-10 11:07:42.572000', b'1', '2020-11-10 11:07:42.572000', 85, 7, 3);

-- --------------------------------------------------------

--
-- Table structure for table `blogs`
--

CREATE TABLE `blogs` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blogs`
--

INSERT INTO `blogs` (`id`, `create_at`, `status`, `update_at`, `description`, `image`, `title`, `user_id`) VALUES
(1, '2020-11-10 01:10:56.694000', b'1', '2020-11-10 01:10:56.694000', '<p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif;\"><span style=\"font-size: 10.5pt; font-family: &quot;Times New Roman&quot;, serif; color: rgb(47, 47, 43);\">While Black Friday is still 3 weeks away, we wanted to give you a little taste of the turkey! (No, I will not apologize for how corny that was!)<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif;\"><span style=\"font-size: 10.5pt; font-family: &quot;Times New Roman&quot;, serif; color: rgb(47, 47, 43);\">Anyway, right now you can get 30% off selected models. The sale features a wide range of models, so I\'m sure you\'ll be able to find something you love. (Or something for a friend!)<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif;\"><span style=\"font-size: 10.5pt; font-family: &quot;Times New Roman&quot;, serif; color: rgb(47, 47, 43);\">So take a look at the \"Specials\" section on our website and click the link that says Turkey Day Sale.&nbsp;I will also provide the link below.<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif;\"><span style=\"font-size: 10.5pt; font-family: &quot;Times New Roman&quot;, serif; color: rgb(47, 47, 43);\">Also I want to let you all know that our Holiday Gift guides will be added soon. So stayed tuned for that!<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif;\"><span style=\"font-size: 10.5pt; font-family: &quot;Times New Roman&quot;, serif; color: rgb(47, 47, 43);\">Enjoy the sale, and enjoy your weekend everyone. I\'ll be back in 3 weeks to announce the Black Friday sale!<o:p></o:p></span></p>', '85712626-6bdf-4222-ab18-d051a484495f_Picture1.png', 'Black Friday Warmup!', NULL),
(2, '2020-11-10 01:12:57.403000', b'1', '2020-11-10 01:12:57.403000', '<p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">Happy Oktoberfest everyone!<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">While we may not be able to travel to Germany this year to experience all the joys of Oktberfest, we can still find a way to celebrate.&nbsp;Take a look at your favorite German models and take 10% off.&nbsp;<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">Who wouldn\'t love to bring a home a sleek BMW? or a classic Mercedes Benz? Some of these models do not go on sale often so it is a good time to catch them while you can.<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">So have a beer, order a soft pretzel from your favorite delivery app and browse our collection of German models!&nbsp;Hopefully next year we\'ll be able get our party on!<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">Hoping everyone is safe out there and wishing you all a happy October!<o:p></o:p></span></p>', '3ac72dd4-4a16-4526-bbcb-8d3729b93c08_Picture2.png', 'Happy Oktoberfest! ', NULL),
(3, '2020-11-10 01:13:58.370000', b'1', '2020-11-10 01:13:58.370000', '<p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">Hey you guys!<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">We have created a new survey to find out some of your favorite models!<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">It is a very quick 5 question survey that takes no more than 2 mins. If you have time, please fill it out so we can get a better idea of what you want!&nbsp;<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0in 0in 0.0001pt; font-size: 12pt; font-family: Calibri, sans-serif; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><span style=\"font-size: 10.5pt; font-family: Arial, sans-serif; color: rgb(47, 47, 43);\">I have provided the link below! Thanks a bunch!<o:p></o:p></span></p>', '992500c0-874d-4a8c-a61f-ffb189816f1f_Picture3.png', 'What\'s Your Favorite?', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `name`, `slug`) VALUES
(1, 'Airplane', 'airplane'),
(2, 'Car', 'car'),
(3, 'MotorBike', 'motorbike'),
(4, 'Truck', 'truck');

-- --------------------------------------------------------

--
-- Table structure for table `contact_us`
--

CREATE TABLE `contact_us` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `image_id` int(11) NOT NULL,
  `about_id` int(11) DEFAULT NULL,
  `blog_id` int(11) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `main_image` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`image_id`, `about_id`, `blog_id`, `description`, `main_image`, `name`, `product_id`, `user_id`) VALUES
(1, 1, 1, 'Bmw535i', b'0', '1bb96208-83e9-4c73-a08a-fb0c64998f43_3.jpg', 1, 1),
(2, 1, 1, 'Bugati chiron sport', b'1', '36cf8333-f207-4a95-8815-63876ef7ed5d_5.jpg', 2, 1),
(3, 1, 1, 'suzuki VStrom', b'0', '1174aabb-2a48-43f6-8eea-b44a26b74566_2.jpg', 3, 1),
(4, 1, 1, 'Suzuki XSR- R-1000', b'0', 'b4813c84-5299-4859-9179-d13503a3286d_4.jpg', 4, 1),
(5, 1, 1, 'bmw 535i', b'0', '78bff4fd-0975-488e-a1ad-c6e17220be5e_3.jpg', 1, 1),
(6, 1, 1, 'bmw 535i', b'0', '0352052e-502e-4777-bb20-6d02751c3ded_4.jpg', 1, 1),
(7, 1, 1, 'bmw 535i', b'1', 'b21e6e01-e12c-461f-a0c5-f3970b85c98f_2.jpg', 1, 1),
(8, 1, 1, 'bugati chiron', b'0', 'b041ded1-0fc7-4f11-8090-fc9432bbcfe8_4.jpg', 2, 1),
(9, 1, 1, 'bugati chiron', b'0', '88fcdb1b-d523-491a-b8db-69913c57fade_2.jpg', 2, 1),
(10, 1, 1, 'bugati chiron', b'0', '9510826b-f99f-4383-a7ad-ff511dcd3b33_6.jpg', 2, 1),
(11, 1, 1, 'Suzuki VStrom', b'1', '1b9bb504-933a-4b10-ae35-8fb5aabd40b5_1.jpg', 3, 1),
(12, 1, 1, 'Suzuki VStrom', b'0', '0f4e9224-2881-40c4-9bdc-b043293df627_2.jpg', 3, 1),
(13, 1, 1, 'Suzuki VStrom', b'0', '79486abb-5a6e-4db9-a93d-02a08a02e5b9_4.jpg', 3, 1),
(14, 1, 1, 'Suzuki VStrom', b'0', 'a4d21b90-1d7f-48f7-b7e8-c884e7374dfe_3.jpg', 3, 1),
(15, 1, 1, 'suzuki GSXR R-1000', b'0', 'a1114f52-c69b-4001-a156-892f6d2505a5_2.jpg', 4, 1),
(16, 1, 1, 'suzuki GSXR R-1000', b'1', '999fef9c-800e-4ce1-ab3d-81b7a3f17752_1.jpg', 4, 1),
(17, 1, 1, 'suzuki GSXR R-1000', b'0', 'c62d18e8-7237-4984-b4a8-c31441f6b90b_3.jpg', 4, 1),
(18, 1, 1, 'suzuki GSXR R-1000', b'0', '0b1db80e-97bc-442e-b47d-74d960f4f3ad_4.jpg', 4, 1),
(19, 1, 1, 'rolls royce Phantom', b'1', '60544c9d-6b05-4f7e-92a1-072ffb9a763e_2.jpg', 5, 1),
(20, 1, 1, 'rolls royce Phantom', b'0', 'c0e1bb5b-b1bd-479a-989a-7615e8b18a15_1.jpg', 5, 1),
(21, 1, 1, 'Ferrari Leferrari', b'1', '92c39cca-b215-4126-900e-b216c7915306_1.jpg', 6, 1),
(22, 1, 1, 'Ferrari Leferrari', b'0', '96d9d630-e23f-4a4b-915f-c8bec5433d25_2.jpg', 6, 1),
(23, 1, 1, 'Ferrari Leferrari', b'0', '6d212aa6-0d14-44cb-ac4d-912f16b4ee6e_3.jpg', 6, 1),
(24, 1, 1, 'Ferrari Leferrari', b'0', '307101f7-1e30-46c6-a86d-567829b325e8_4.jpg', 6, 1),
(25, 1, 1, 'Hurracan Lp610', b'1', '1d1d6474-8643-490c-9ad1-8672c4069187_2.jpg', 7, 1),
(26, 1, 1, 'Hurracan Lp610', b'0', '383b0c66-e319-4a4f-9ca2-3fc5b2328311_3.jpg', 7, 1),
(27, 1, 1, 'Hurracan Lp610', b'0', '4f4f1370-0ff6-4ef3-a1a2-f1956562e125_4.jpg', 7, 1),
(28, 1, 1, 'Hurracan Lp610', b'0', '0e61c0f5-98b7-417d-8f27-3023739717ac_5.jpg', 7, 1),
(29, 1, 1, 'Lamborghini Centenario', b'1', 'f1324a01-38a9-4c90-a132-d3ad79de8c50_1.jpg', 13, 1),
(30, 1, 1, 'Lamborghini Centenario', b'0', '53022bac-f942-45db-aad0-1608af68b264_2.jpg', 13, 1),
(31, 1, 1, 'Lamborghini Centenario', b'0', '69ad5f8a-2e7d-47d9-bc7e-73443e4767b5_3.jpg', 13, 1),
(32, 1, 1, 'Lamborghini Centenario', b'0', '72af7d49-f2bf-492f-9462-87f2004d184f_4.jpg', 13, 1),
(33, 1, 1, 'Z1000-R', b'1', 'a25b8ca5-b830-4879-9992-408c51a7f2f1_3.jpg', 14, 1),
(34, 1, 1, 'Z1000-R', b'0', '549b18a4-8602-4a6c-b19d-1311fd4b1ff5_4.jpg', 14, 1),
(39, 1, 1, 'Test them hinh anh', b'1', 'ded0161a-d7ae-41d9-bab1-d1e0e232bdc0_1.jpg', 17, 1),
(40, 1, 1, 'Test them hinh anh', b'0', 'e6666184-757a-4abf-b562-925f3c63b521_2.jpg', 17, 1);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `inventory_id` int(11) NOT NULL,
  `inventory_on_hand` int(11) DEFAULT NULL,
  `purchase_date` datetime(6) NOT NULL,
  `quantity_received` int(11) DEFAULT NULL,
  `starting_inventory` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `payment_method` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `state_region` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `create_at`, `status`, `update_at`, `address`, `amount`, `city`, `country`, `first_name`, `last_name`, `payment_method`, `phone_number`, `postal_code`, `state_region`) VALUES
(1, '2020-11-10 02:11:13.829000', b'1', '2020-11-10 02:11:13.829000', 'Block A EHomse S Nam Sai Gon, Binh Hung', 80, 'Ho Chi Minh', 'Vietnam', 'Trinh Hao', 'Hiep', 0, '0123456789', '80000', 'Binh Chanh'),
(2, '2020-11-10 09:36:20.249000', b'1', '2020-11-10 09:36:20.249000', '343 Pham Ngu Lao', 85, 'Ho Chi Minh', 'Vietnam', 'To Dao', 'Viet Hoang', 0, '0123456789', '80000', 'Quan 1'),
(3, '2020-11-10 10:43:01.422000', b'1', '2020-11-10 10:43:01.422000', 'Block A EHomse S Nam Sai Gon, Binh Hung', 85, 'Ho Chi Minh', 'Vietnam', 'Trinh Hao', 'Hiep', 0, '0123456789', '80000', 'Binh Chanh'),
(4, '2020-11-10 11:06:39.378000', b'1', '2020-11-10 11:06:39.378000', '343 Pham Ngu Lao', 85, 'Ho Chi Minh', 'Vietnam', 'To Dao', 'Viet Hoang', 0, '0123456789', '80000', 'Quan 1');

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`id`, `create_at`, `status`, `update_at`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(1, '2020-11-10 02:11:13.856000', b'1', '2020-11-10 02:11:13.856000', 80, 1, 1, 1),
(2, '2020-11-10 09:36:20.269000', b'1', '2020-11-10 09:36:20.269000', 50, 1, 2, 1),
(3, '2020-11-10 09:36:20.287000', b'1', '2020-11-10 09:36:20.287000', 35, 1, 2, 3),
(4, '2020-11-10 10:43:01.434000', b'1', '2020-11-10 10:43:01.434000', 50, 1, 3, 1),
(5, '2020-11-10 10:43:01.455000', b'1', '2020-11-10 10:43:01.455000', 35, 1, 3, 3),
(6, '2020-11-10 11:06:39.391000', b'1', '2020-11-10 11:06:39.391000', 50, 1, 4, 1),
(7, '2020-11-10 11:06:39.428000', b'1', '2020-11-10 11:06:39.428000', 35, 1, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `inventory_shipped` int(11) DEFAULT NULL,
  `product_content` text,
  `product_description` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_type` int(11) DEFAULT NULL,
  `save_price` int(11) NOT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `unit_price` int(11) NOT NULL,
  `weight` double DEFAULT NULL,
  `bid_detail_id` bigint(20) DEFAULT NULL,
  `subcategory_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `create_at`, `status`, `update_at`, `hot`, `inventory_shipped`, `product_content`, `product_description`, `product_name`, `product_type`, `save_price`, `sku`, `slug`, `stock`, `unit_price`, `weight`, `bid_detail_id`, `subcategory_id`) VALUES
(1, '2020-11-09 21:14:16.630000', b'1', '2020-11-09 21:14:16.630000', 10, NULL, '', 'Bmw 535i - 1:24', 'bmw535i-Welly', 0, 50, 'b001', 'bmw535i-welly-1', -4, 65, 6, NULL, 4),
(2, '2020-11-09 21:37:20.329000', b'1', '2020-11-09 21:37:20.329000', 0, NULL, '', 'Bugati Sport 16 118', 'Bugati Chiron', 0, 40, 'R001', 'bugati-chiron-2', 0, 45, 0, NULL, 14),
(3, '2020-11-09 21:40:20.970000', b'1', '2020-11-09 21:40:20.970000', 0, NULL, '', 'Suzuki VStrom', 'Suzuki VStrom 112', 0, 35, 'Su001', 'suzuki-vstrom-112-3', -3, 40, 0, NULL, 15),
(4, '2020-11-09 21:42:22.379000', b'1', '2020-11-09 21:42:22.379000', 0, NULL, '', 'Suzuki GSX-R 1000R', 'suzuki GSX', 0, 38, 'Su002', 'suzuki-gsx-4', 0, 44, 0, NULL, 15),
(5, '2020-11-09 21:48:34.202000', b'1', '2020-11-09 21:48:34.202000', 0, NULL, '', 'Rolls Royce Phantom', 'Rolls Royce Phantom', 0, 49, 'Ro001', 'rolls-royce-phantom-5', 0, 54, 0, NULL, 14),
(6, '2020-11-09 21:51:38.376000', b'1', '2020-11-09 21:51:38.376000', 2, NULL, '', 'Ferrari Leferrari 164', 'Ferrari', 1, 61, 'fe001', 'ferrari-6', 0, 65, 0, 1, 17),
(7, '2020-11-09 21:53:18.981000', b'1', '2020-11-09 21:53:18.981000', 1, NULL, '', 'Hurracan Lp610', 'Lamborghini', 0, 55, 'La001', 'lamborghini-7', 0, 65, 0, NULL, 16),
(13, '2020-11-09 21:56:38.109000', b'1', '2020-11-09 21:56:38.109000', 19, NULL, '<p><br></p>gfdfdgdfggffghghf', 'Centenario Lp770', 'Lamborghini centenario', 1, 71, 'La002', 'lamborghini-centenario-13', 0, 77, 0, 7, 16),
(14, '2020-11-09 21:58:17.226000', b'1', '2020-11-09 21:58:17.226000', 0, NULL, '', 'Z1000-R', 'Kawasaki Z1000-R', 0, 20, 'Ka001', 'kawasaki-z1000-r-14', 0, 30, 0, NULL, 9),
(17, '2020-11-10 11:09:53.890000', b'1', '2020-11-10 11:09:53.890000', 0, NULL, '<p>Test test them xoa sua</p>', 'Test them san pham', 'Test 1', 0, 15, '', 'test-1-17', 0, 20, 0, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `create_at`, `status`, `update_at`, `name`) VALUES
(1, '2020-11-09 20:32:41.616000', b'1', '2020-11-09 20:32:41.616000', 'ADMIN'),
(2, '2020-11-09 20:32:41.645000', b'1', '2020-11-09 20:32:41.645000', 'STAFF'),
(3, '2020-11-09 20:32:41.649000', b'1', '2020-11-09 20:32:41.649000', 'CUSTOMER');

-- --------------------------------------------------------

--
-- Table structure for table `sub_categories`
--

CREATE TABLE `sub_categories` (
  `subcat_id` int(11) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `sub_name` varchar(255) NOT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sub_categories`
--

INSERT INTO `sub_categories` (`subcat_id`, `slug`, `sub_name`, `category_id`) VALUES
(1, 'boeing-747', 'Boeing 747', 1),
(2, 'mic-17', 'Mic 17', 1),
(3, 'airbus-a320', 'Airbus A320', 1),
(4, 'bmw', 'BMW', 2),
(5, 'mercedes-benz', 'Mercedes Benz', 2),
(6, 'toyota', 'Toyota', 2),
(7, 'harley-davidson', 'Harley Davidson', 3),
(8, 'ducati', 'Ducati', 3),
(9, 'kawasaki', 'Kawasaki', 3),
(10, 'iveco', 'Iveco', 4),
(11, 'scania', 'Scania', 4),
(12, 'hyundai', 'Hyundai', 4),
(13, 'bugati', 'Bugati', 2),
(14, 'rolls-royce', 'Rolls Royce', 2),
(15, 'suzuki', 'Suzuki', 3),
(16, 'lamborghini', 'Lamborghini', 2),
(17, 'ferrari', 'Ferrari', 2);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_confirmed` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_confirmed` bit(1) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `create_at`, `status`, `update_at`, `date_of_birth`, `email`, `email_confirmed`, `first_name`, `gender`, `last_name`, `password`, `phone_confirmed`, `phone_number`, `profile_picture`, `address_id`) VALUES
(1, '2020-11-09 20:32:41.656000', b'1', '2020-11-09 20:32:41.656000', '1999-07-26', 'admin@amazingtoy.com', b'1', 'Trinh Hao', 0, 'Hiep', '$2a$10$oIseFM/zxPc9JIfniH.pBO8qD8eMmZQrckufZKMQTkzQ9NfOPlPa6', b'1', '0123456789', 'avatar.png', 1),
(2, '2020-11-09 20:32:41.793000', b'1', '2020-11-09 20:32:41.793000', '1999-07-26', 'staff@amazingtoy.com', b'1', 'Nguyen Nhat', 0, 'Hoang Ha', '$2a$10$TTSIUApzujmw/JMfJuImyu2P2h5pfQYzD3b45GY92uVU347eVJ.Ey', b'1', '0123456789', 'avatar2.png', 2),
(3, '2020-11-09 20:32:41.906000', b'1', '2020-11-09 20:32:41.906000', '1999-07-26', 'customer@amazingtoy.com', b'1', 'To Dao', 0, 'Viet Hoang', '$2a$10$Op0pIxb7cNpvJkvWvVscw.KQOeLSwuQjzOOTU2xo3K80YIJTThnBK', b'1', '0123456789', 'avatar3.png', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abouts`
--
ALTER TABLE `abouts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bid_details`
--
ALTER TABLE `bid_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bid_history`
--
ALTER TABLE `bid_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKevc6pn0ms8fj8fs0ft86a519u` (`bid_detail_id`),
  ADD KEY `FKe8i01psee6t1fuvcbiuy70mxm` (`user_id`);

--
-- Indexes for table `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpg4damav6db6a6fh5peylcni5` (`user_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `UK_oul14ho7bctbefv8jywp5v3i2` (`slug`);

--
-- Indexes for table `contact_us`
--
ALTER TABLE `contact_us`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`image_id`),
  ADD KEY `FKghwsjbjo7mg3iufxruvq6iu3q` (`product_id`),
  ADD KEY `FK13ljqfrfwbyvnsdhihwta8cpr` (`user_id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inventory_id`),
  ADD KEY `FKq2yge7ebtfuvwufr6lwfwqy9l` (`product_id`),
  ADD KEY `FK1ticfk66udu8mbj1otebp2rgw` (`supplier_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
  ADD KEY `FKc7q42e9tu0hslx6w4wxgomhvn` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f55t6sm19p5lrihq24a6knota` (`product_name`),
  ADD KEY `FKqquqbnud323r37k17bmpw2wdk` (`bid_detail_id`),
  ADD KEY `FKh7nopotm4ii6cr5np9nhxs4aa` (`subcategory_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa5cmgpp2plp5oai84fkmbi63e` (`product_id`),
  ADD KEY `FK6cpw2nlklblpvc7hyt7ko6v3e` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sub_categories`
--
ALTER TABLE `sub_categories`
  ADD PRIMARY KEY (`subcat_id`),
  ADD UNIQUE KEY `UK_n683x888bxcnnnxlgvqp2geav` (`slug`),
  ADD KEY `FKjwy7imy3rf6r99x48ydq45otw` (`category_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FKe8vydtk7hf0y16bfm558sywbb` (`address_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abouts`
--
ALTER TABLE `abouts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `bid_details`
--
ALTER TABLE `bid_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `bid_history`
--
ALTER TABLE `bid_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `contact_us`
--
ALTER TABLE `contact_us`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `inventory_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sub_categories`
--
ALTER TABLE `sub_categories`
  MODIFY `subcat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bid_history`
--
ALTER TABLE `bid_history`
  ADD CONSTRAINT `FKe8i01psee6t1fuvcbiuy70mxm` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKevc6pn0ms8fj8fs0ft86a519u` FOREIGN KEY (`bid_detail_id`) REFERENCES `bid_details` (`id`);

--
-- Constraints for table `blogs`
--
ALTER TABLE `blogs`
  ADD CONSTRAINT `FKpg4damav6db6a6fh5peylcni5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `FK13ljqfrfwbyvnsdhihwta8cpr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKghwsjbjo7mg3iufxruvq6iu3q` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `FK1ticfk66udu8mbj1otebp2rgw` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `FKq2yge7ebtfuvwufr6lwfwqy9l` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `FKc7q42e9tu0hslx6w4wxgomhvn` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKh7nopotm4ii6cr5np9nhxs4aa` FOREIGN KEY (`subcategory_id`) REFERENCES `sub_categories` (`subcat_id`),
  ADD CONSTRAINT `FKqquqbnud323r37k17bmpw2wdk` FOREIGN KEY (`bid_detail_id`) REFERENCES `bid_details` (`id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK6cpw2nlklblpvc7hyt7ko6v3e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKa5cmgpp2plp5oai84fkmbi63e` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `sub_categories`
--
ALTER TABLE `sub_categories`
  ADD CONSTRAINT `FKjwy7imy3rf6r99x48ydq45otw` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKe8vydtk7hf0y16bfm558sywbb` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
