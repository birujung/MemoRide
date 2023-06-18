import { pool } from "../index.js";

/**
 * Create a new tour.
 *
 * @async
 * @name createTour
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const createTour = async (req, res) => {
  const {
    title,
    city,
    address,
    distance,
    photo,
    description,
    price,
    max_group_size,
    featured,
  } = req.body;

  const query = `
    INSERT INTO tours (title, city, address, distance, photo, description, price, max_group_size, featured)
    VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)
    RETURNING *
  `;
  const values = [
    title,
    city,
    address,
    distance,
    photo,
    description,
    price,
    max_group_size,
    featured,
  ];

  try {
    const result = await pool.query(query, values);
    const savedTour = result.rows[0];

    res.status(200).json({
      success: true,
      message: "Successfully created",
      data: savedTour,
    });
  } catch (err) {
    console.error(err);
    res
      .status(500)
      .json({ success: false, message: "Failed to create. Try again" });
  }
};

/**
 * Update an existing tour.
 *
 * @async
 * @name updateTour
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const updateTour = async (req, res) => {
  const id = req.params.id;

  try {
    const query = {
      text: "UPDATE tours SET title = $1, city = $2, address = $3, distance = $4, photo = $5, description = $6, price = $7, max_group_size = $8, featured = $9, updated_at = CURRENT_TIMESTAMP WHERE id = $10 RETURNING *",
      values: [
        req.body.title,
        req.body.city,
        req.body.address,
        req.body.distance,
        req.body.photo,
        req.body.description,
        req.body.price,
        req.body.max_group_size,
        req.body.featured,
        id,
      ],
    };

    const result = await pool.query(query);
    const updatedTour = result.rows[0];

    res.status(200).json({
      success: true,
      message: "Successfully updated",
      data: updatedTour,
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      success: false,
      message: "Failed to update",
    });
  }
};

/**
 * Delete a tour.
 *
 * @async
 * @name deleteTour
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const deleteTour = async (req, res) => {
  const id = req.params.id;

  try {
    const query = {
      text: "DELETE FROM tours WHERE id = $1",
      values: [id],
    };

    await pool.query(query);

    res.status(200).json({
      success: true,
      message: "Successfully deleted",
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      success: false,
      message: "Failed to delete",
    });
  }
};

/**
 * Get a single tour by ID.
 *
 * @async
 * @name getSingleTour
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getSingleTour = async (req, res) => {
  const id = req.params.id;

  try {
    const query = {
      text: "SELECT * FROM tours WHERE id = $1",
      values: [id],
    };

    const result = await pool.query(query);
    const tour = result.rows[0];

    res.status(200).json({
      success: true,
      message: "Successfully get single tour",
      data: tour,
    });
  } catch (err) {
    console.error(err);
    res.status(404).json({
      success: false,
      message: "Not Found",
    });
  }
};

/**
 * Get all tours.
 *
 * @async
 * @name getAllTour
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getAllTour = async (req, res) => {
  const page = parseInt(req.query.page) || 0;
  const limit = 8;
  const offset = page * limit;

  try {
    const query = {
      text: "SELECT * FROM tours OFFSET $1 LIMIT $2",
      values: [offset, limit],
    };

    const result = await pool.query(query);
    const tours = result.rows;

    res.status(200).json({
      success: true,
      count: tours.length,
      message: "Successfully get all tours",
      data: tours,
    });
  } catch (err) {
    console.error(err);
    res.status(404).json({
      success: false,
      message: "Not Found",
    });
  }
};

/**
 * Get tours by search parameters.
 *
 * @async
 * @name getTourBySearch
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getTourBySearch = async (req, res) => {
  const { city, distance, maxGroupSize } = req.query;

  try {
    const query = {
      text: "SELECT * FROM tours WHERE city ILIKE $1 AND distance >= $2 AND max_group_size >= $3",
      values: [`%${city}%`, distance, maxGroupSize],
    };

    const result = await pool.query(query);
    const tours = result.rows;

    res.status(200).json({
      success: true,
      message: "Successfully",
      data: tours,
    });
  } catch (err) {
    console.error(err);
    res.status(404).json({
      success: false,
      message: "Not Found",
    });
  }
};

/**
 * Get featured tours.
 *
 * @async
 * @name getFeaturedTours
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getFeaturedTours = async (req, res) => {
  try {
    const query = {
      text: "SELECT * FROM tours WHERE featured = true LIMIT 8",
    };

    const result = await pool.query(query);
    const tours = result.rows;

    if (tours.length === 0) {
      return res.status(404).json({
        success: false,
        message: "No featured tours found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Successfully retrieved featured tours",
      data: tours,
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      success: false,
      message: "Failed to retrieve featured tours",
    });
  }
};

/**
 * Get the count of tours.
 *
 * @async
 * @name getTourCount
 * @function
 * @memberof module:controllers/tourController
 * @param {object} req - The request object.
 * @param {object} res - The response object.
 * @returns {void}
 */
export const getTourCount = async (req, res) => {
  try {
    const query = {
      text: "SELECT COUNT(*) FROM tours",
    };

    const result = await pool.query(query);
    const tourCount = result.rows[0].count;

    res.status(200).json({ success: true, data: tourCount });
  } catch (err) {
    console.error(err);
    res.status(500).json({ success: false, message: "Failed to fetch" });
  }
};

export default {
  createTour,
  updateTour,
  deleteTour,
  getSingleTour,
  getAllTour,
  getTourBySearch,
  getFeaturedTours,
  getTourCount,
};
