const userSchema = `
CREATE TYPE gender_enum AS ENUM ('female', 'male');

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  photo VARCHAR(100),
  role VARCHAR(100) DEFAULT 'user',
  full_name VARCHAR(100),
  gender gender_enum,
  phone_num VARCHAR(20),
  membership_level VARCHAR(100),
  created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
`;

export default userSchema;
