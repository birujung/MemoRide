import { pool } from "../index.js";

/**
 * Create a new review for a tour.
 *
 * @async
 * @name createReview
 * @function
 * @memberof module:controllers/reviewController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const createReview = async (req, res) => {
  const tourId = req.params.tourId;
  const { username, reviewText, rating } = req.body;

  try {
    const query = {
      text: "INSERT INTO reviews (product_id, username, review_text, rating) VALUES ($1, $2, $3, $4) RETURNING *",
      values: [tourId, username, reviewText, rating],
    };

    const result = await pool.query(query);
    const savedReview = result.rows[0];

    res.status(200).json({
      success: true,
      message: "Review submitted",
      data: savedReview,
    });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      success: false,
      message: "Failed to submit",
    });
  }
};

/**
 * Get all reviews for a tour.
 *
 * @async
 * @name getAllReviews
 * @function
 * @memberof module:controllers/reviewController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getAllReviews = async (req, res) => {
  const { tourId } = req.params;

  try {
    const query = {
      text: "SELECT * FROM reviews WHERE product_id = $1",
      values: [tourId],
    };

    const result = await pool.query(query);
    const reviews = result.rows;

    res.status(200).json({
      success: true,
      message: "Reviews fetched",
      data: reviews,
    });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      success: false,
      message: "Failed to fetch reviews",
    });
  }
};

/**
 * Get all reviews by a user.
 *
 * @async
 * @name getUserReviews
 * @function
 * @memberof module:controllers/reviewController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getUserReviews = async (req, res) => {
  const { userId } = req.params;

  try {
    const query = {
      text: "SELECT r.*, u.username FROM reviews r JOIN users u ON r.username = u.username WHERE u.id = $1",
      values: [req.user.id],
    };

    const result = await pool.query(query);
    const reviews = result.rows;

    res.status(200).json({
      success: true,
      message: "User reviews fetched",
      data: reviews,
    });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      success: false,
      message: "Failed to fetch user reviews",
    });
  }
};
