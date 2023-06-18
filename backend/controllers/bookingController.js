import { pool } from "../index.js";
import Booking from "../models/Booking.js";

/**
 * Create a new booking.
 *
 * @async
 * @name createBooking
 * @function
 * @memberof module:controllers/bookingController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 * @throws {object} Error object if booking creation fails.
 */
export const createBooking = async (req, res) => {
  const { tour_name, group_size, book_at } = req.body;
  const user_id = req.user.id;

  if (!tour_name || !group_size || !book_at || !user_id) {
    return res.status(400).json({
      success: false,
      message: "Missing required fields",
    });
  }

  try {
    const query = `
      INSERT INTO bookings (tour_name, group_size, book_at, user_id) 
      VALUES ($1, $2, $3, $4) 
      RETURNING *
    `;
    const values = [tour_name, group_size, book_at, user_id];

    const result = await pool.query(query, values);
    const savedBooking = result.rows[0];

    // Update user's membership level
    await updateMembershipLevel(user_id);

    res.status(200).json({
      success: true,
      message: "Your tour is booked",
      data: savedBooking,
    });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      success: false,
      message: "Internal Server Error",
    });
  }
};

/**
 * Calculate the membership level based on the booking count.
 *
 * @name getMembershipLevel
 * @function
 * @memberof module:controllers/bookingController
 * @param {number} bookingCount - The number of bookings made by the user.
 * @returns {string} The membership level.
 */
const getMembershipLevel = (bookingCount) => {
  if (bookingCount < 2) {
    return "Bronze";
  } else if (bookingCount < 5) {
    return "Silver";
  } else if (bookingCount < 10) {
    return "Gold";
  } else if (bookingCount < 15) {
    return "Diamond";
  } else {
    return "Platinum";
  }
};

/**
 * Update the user's membership level based on the booking count.
 *
 * @async
 * @name updateMembershipLevel
 * @function
 * @memberof module:controllers/bookingController
 * @param {number} user_id - The ID of the user.
 * @returns {void}
 * @throws {object} Error object if the membership level update fails.
 */
const updateMembershipLevel = async (user_id) => {
  try {
    const query = `
      SELECT COUNT(*) AS booking_count
      FROM bookings
      WHERE user_id = $1
    `;
    const values = [user_id];

    const result = await pool.query(query, values);
    const bookingCount = result.rows[0].booking_count;

    const membershipLevel = getMembershipLevel(bookingCount);

    const updateQuery = `
      UPDATE users
      SET membership_level = $1
      WHERE id = $2
    `;
    const updateValues = [membershipLevel, user_id];

    await pool.query(updateQuery, updateValues);
  } catch (err) {
    console.error(err);
    throw new Error("Failed to update membership level");
  }
};

/**
 * Get a single booking by ID.
 *
 * @async
 * @name getBooking
 * @function
 * @memberof module:controllers/bookingController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getBooking = async (req, res) => {
  const { id } = req.params;

  const query = `
    SELECT bookings.*, users.full_name, users.email, users.phone_num
    FROM bookings
    JOIN users ON bookings.user_id = users.id
    WHERE bookings.id = $1
  `;
  const values = [id];

  try {
    const result = await pool.query(query, values);
    const booking = result.rows[0];

    if (!booking) {
      return res.status(404).json({
        success: false,
        message: "Booking not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Successfully fetched booking",
      data: booking,
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      success: false,
      message: "Internal Server Error",
    });
  }
};

/**
 * Get all bookings.
 *
 * @async
 * @name getAllBooking
 * @function
 * @memberof module:controllers/bookingController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getAllBooking = async (req, res) => {
  try {
    const query = `
    SELECT bookings.*, users.full_name, users.email, users.phone_num
    FROM bookings
    JOIN users ON bookings.user_id = users.id
    `;

    const result = await pool.query(query);
    const bookings = result.rows;

    res.status(200).json({
      success: true,
      message: "Successfully fetched bookings",
      data: bookings,
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      success: false,
      message: "Internal Server Error",
    });
  }
};

/**
 * Get all bookings for a specific user.
 *
 * @async
 * @name getUserBookings
 * @function
 * @memberof module:controllers/bookingController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getUserBookings = async (req, res) => {
  const { userId } = req.params;

  const query = `
    SELECT bookings.*, users.full_name, users.email, users.phone_num
    FROM bookings
    JOIN users ON bookings.user_id = users.id
    WHERE bookings.user_id = $1
  `;
  const values = [userId];

  try {
    const result = await pool.query(query, values);
    const bookings = result.rows;

    res.status(200).json({
      success: true,
      message: "Successfully fetched user bookings",
      data: bookings,
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      success: false,
      message: "Internal Server Error",
    });
  }
};
