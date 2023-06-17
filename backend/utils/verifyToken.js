import jwt from "jsonwebtoken";
import { pool } from "../index.js";

export const verifyToken = (req, res, next) => {
  const token = req.cookies.accessToken;

  if (!token) {
    return res.status(401).json({
      success: false,
      message: "You're not authorized",
    });
  }

  // if token exists, then verify the token
  jwt.verify(token, process.env.JWT_SECRET_KEY, (err, user) => {
    if (err) {
      return res.status(401).json({
        success: false,
        message: "Token is invalid!",
      });
    }

    req.user = user;
    next();
  });
};

export const verifyUser = (req, res, next) => {
  verifyToken(req, res, () => {
    const { id } = req.user;

    const query = "SELECT role FROM users WHERE id = $1";
    const values = [id];

    pool.query(query, values, (err, result) => {
      if (err) {
        console.error(err);
        return res.status(500).json({
          success: false,
          message: "Failed to verify user",
        });
      }

      if (result.rows.length > 0) {
        const role = result.rows[0].role;

        if (role === "admin") {
          return res.status(404).json({
            success: false,
            message: "You can't add review",
          });
        } else {
          next();
        }
      } else {
        return res.status(404).json({
          success: false,
          message: "User not found",
        });
      }
    });
  });
};

export const verifyAdmin = (req, res, next) => {
  verifyToken(req, res, () => {
    const { id } = req.user;

    const query = "SELECT role FROM users WHERE id = $1";
    const values = [id];

    pool.query(query, values, (err, result) => {
      if (err) {
        console.error(err);
        return res.status(500).json({
          success: false,
          message: "Failed to verify admin",
        });
      }

      if (result.rows.length > 0) {
        const role = result.rows[0].role;

        if (role === "admin") {
          next();
        } else {
          return res.status(401).json({
            success: false,
            message: "You're not authorized",
          });
        }
      } else {
        return res.status(404).json({
          success: false,
          message: "User not found",
        });
      }
    });
  });
};
