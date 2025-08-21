-- Insert wallets (UUIDs are manually given, balance = 0)
INSERT INTO wallets (id, balance) VALUES ('11111111-1111-1111-1111-111111111111', 0);
INSERT INTO wallets (id, balance) VALUES ('22222222-2222-2222-2222-222222222222', 0);

-- Insert users (UUIDs are manually given, wallet_id points to created wallets)
INSERT INTO users (id, f_name, l_name, age, gender, email, join_date, role, wallet_id)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Ibrahim', 'Hesham', 20, 'MALE', 'ibrahim@gmail.com', '2025-08-19', 'USER', '11111111-1111-1111-1111-111111111111');

INSERT INTO users (id, f_name, l_name, age, gender, email, join_date, role, wallet_id)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Sara', 'Ali', 22, 'FEMALE', 'sara@example.com', '2025-08-19', 'USER', '22222222-2222-2222-2222-222222222222');
