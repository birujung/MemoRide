import express from "express";
import dotenv from "dotenv";
import pg from "pg";
import cors from "cors";
import cookieParser from "cookie-parser";

import authRoute from "./routes/auth.js";
import tourRoute from "./routes/tours.js";
import userRoute from "./routes/users.js";
import reviewRoute from "./routes/reviews.js";
import bookingRoute from "./routes/bookings.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 80;

// Database Connection
const { PGHOST, PGDATABASE, PGUSER, PGPASSWORD } = process.env;
export const pool = new pg.Pool({
  host: PGHOST,
  database: PGDATABASE,
  user: PGUSER,
  password: PGPASSWORD,
  port: 5432,
  ssl: {
    rejectUnauthorized: false,
    sslmode: "require",
  },
});

const connect = async () => {
  try {
    await pool.connect();
    console.log("PostgreSQL database connected");
  } catch (err) {
    console.log("PostgreSQL database connection failed");
    console.error(err);
  }
};

async function getPgVersion() {
  try {
    const result = await pool.query("SELECT version()");
    console.log(result.rows[0]);
  } catch (error) {
    console.error("Failed to fetch PostgreSQL version:", error);
  }
}

// Middleware
app.use(express.json());
app.use(cookieParser());
app.use("/api/v1/auth", authRoute);
app.use("/api/v1/tours", tourRoute);
app.use("/api/v1/users", userRoute);
app.use("/api/v1/reviews", reviewRoute);
app.use("/api/v1/booking", bookingRoute);

app.listen(80, () => {
  connect();
  console.log("Server listening on port", 80);
  getPgVersion();
});
