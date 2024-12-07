INSERT INTO user(user_id, email, phone_num) VALUES
(1, 'user1@example.com', '010-1234-5678'),
(2, 'user2@example.com', '010-2345-6789'),
(3, 'user3@example.com', '010-3456-7890'),
(4, 'user4@example.com', '010-4567-8901'),
(5, 'user5@example.com', '010-5678-9012'),
(6, 'user6@example.com', '010-6789-0123'),
(7, 'user7@example.com', '010-7890-1234'),
(8, 'user8@example.com', '010-8901-2345'),
(9, 'user9@example.com', '010-9012-3456'),
(10, 'user10@example.com', '010-0123-4567'),
(11, 'user11@example.com', '010-1123-4567'),
(12, 'user12@example.com', '010-2123-4567'),
(13, 'user13@example.com', '010-3123-4567'),
(14, 'user14@example.com', '010-4123-4567'),
(15, 'user15@example.com', '010-5123-4567'),
(16, 'user16@example.com', '010-6123-4567'),
(17, 'user17@example.com', '010-7123-4567'),
(18, 'user18@example.com', '010-8123-4567'),
(19, 'user19@example.com', '010-9123-4567'),
(20, 'user20@example.com', '010-0012-3456');

INSERT INTO general_user(g_user_id, user_id, favortie_shopping_address, my_card_num, my_bank_account) VALUES
(1, 1, '서울시 강남구', '1234-5678-9012-3456', '110-123-456-789'),
(2, 2, '서울시 서초구', '2345-6789-0123-4567', '120-234-567-890'),
(3, 3, '서울시 용산구', '3456-7890-1234-5678', '130-345-678-901'),
(4, 4, '서울시 마포구', '4567-8901-2345-6789', '140-456-789-012'),
(5, 5, '서울시 송파구', '5678-9012-3456-7890', '150-567-890-123'),
(6, 6, '부산시 해운대구', '6789-0123-4567-8901', '160-678-901-234'),
(7, 7, '부산시 수영구', '7890-1234-5678-9012', '170-789-012-345'),
(8, 8, '대구시 중구', '8901-2345-6789-0123', '180-890-123-456'),
(9, 9, '대구시 수성구', '9012-3456-7890-1234', '190-901-234-567'),
(10, 10, '인천시 남구', '0123-4567-8901-2345', '200-012-345-678'),
(11, 11, '인천시 동구', '1123-4567-8901-2345', '210-112-345-678'),
(12, 12, '대전시 중구', '2123-4567-8901-2345', '220-212-345-678'),
(13, 13, '대전시 서구', '3123-4567-8901-2345', '230-312-345-678'),
(14, 14, '광주시 남구', '4123-4567-8901-2345', '240-412-345-678'),
(15, 15, '울산시 북구', '5123-4567-8901-2345', '250-512-345-678'),
(16, 16, '세종시', '6123-4567-8901-2345', '260-612-345-678'),
(17, 17, '경기도 수원시', '7123-4567-8901-2345', '270-712-345-678'),
(18, 18, '경기도 고양시', '8123-4567-8901-2345', '280-812-345-678'),
(19, 19, '강원도 춘천시', '9123-4567-8901-2345', '290-912-345-678'),
(20, 20, '제주시', '0012-3456-7890-1234', '300-012-345-678');

-- Admin Users
-- 1 admin user per every 20 users
INSERT INTO admin_user(admin_id, user_id) VALUES
(1, 1);