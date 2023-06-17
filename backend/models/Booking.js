const bookingSchema = `
CREATE TABLE IF NOT EXISTS bookings (
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES users (id),
  tour_name VARCHAR(100) NOT NULL,
  group_size INTEGER NOT NULL,
  book_at TIMESTAMPTZ NOT NULL,
  created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS bookings_user_id_idx ON bookings (user_id);
`;

export default bookingSchema;
