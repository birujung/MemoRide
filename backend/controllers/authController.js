import { pool } from "../index.js";
import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";

// register user
export const register = async (req, res) => {
  try {
    const { username, email, password, photo, role, full_name, gender, phone_num } = req.body;

    if (!password || password.trim() === "") {
      return res.status(400).json({
        success: false,
        message: "Password is required",
      });
    }

    const salt = bcrypt.genSaltSync(10);
    const hash = bcrypt.hashSync(password, salt);

    const newUser = {
      username: username,
      email: email,
      password: hash,
      photo: photo,
      role: role,
      full_name: full_name,
      gender: gender,
      phone_num: phone_num,
    };

    const query = {
      text: "INSERT INTO users (username, email, password, photo, role, full_name, gender, phone_num) VALUES ($1, $2, $3, $4, $5, $6, $7, $8)",
      values: [
        newUser.username,
        newUser.email,
        newUser.password,
        newUser.photo,
        newUser.role,
        newUser.full_name,
        newUser.gender,
        newUser.phone_num,
      ],
    };

    await pool.query(query);

    res.status(200).json({
      success: true,
      message: "Successfully created an account",
    });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      success: false,
      message: "Failed to create an account. Try again",
    });
  }
};

// login user
export const login = async (req, res) => {
  const email = req.body.email;
  const password = req.body.password;

  try {
    // Find user
    const query = {
      text: "SELECT * FROM users WHERE email = $1",
      values: [email],
    };
    const result = await pool.query(query);
    const user = result.rows[0];

    // if user doesn't have an account
    if (!user) {
      return res.status(404).json({
        success: false,
        message: "User Not Found",
      });
    }

    // Compare the passwords (hash the provided password and compare with the stored hashed password)
    const isPasswordMatch = await bcrypt.compare(password, user.password);

    // if password is incorrect
    if (!isPasswordMatch) {
      return res.status(401).json({
        success: false,
        message: "Incorrect Email or Password",
      });
    }

    const { password: _, ...rest } = user;

    // create jwt token
    const token = jwt.sign(
      { id: user.id, role: user.role },
      process.env.JWT_SECRET_KEY,
      {
        expiresIn: "150d",
      }
    );

    // set token in the browser cookies and send the response to the client
    res
      .cookie("accessToken", token, {
        httpOnly: true,
        expires: new Date(Date.now() + 150 * 24 * 60 * 60 * 1000), // 150 days
      })
      .status(200)
      .json({
        token,
        data: { ...rest },
        role: user.role,
      });
  } catch (error) {
    console.error(error); // Log the error for debugging purposes

    res.status(500).json({
      success: false,
      message: "Failed to Login",
    });
  }
};
