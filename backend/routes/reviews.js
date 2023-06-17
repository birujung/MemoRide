import express from "express";
import {
  createReview,
  getAllReviews,
  getUserReviews,
} from "../controllers/reviewController.js";
import { verifyUser } from "../utils/verifyToken.js";

const router = express.Router();

router.post("/:tourId", verifyUser, createReview);
router.get("/:tourId", getAllReviews);
router.get("/user/:userId", verifyUser, getUserReviews);

export default router;
