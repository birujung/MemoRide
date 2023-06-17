import express from "express";
import {
  createBooking,
  getAllBooking,
  getBooking,
  getUserBookings,
} from "../controllers/bookingController.js";
import { verifyAdmin, verifyUser } from "../utils/verifyToken.js";

const router = express.Router();

router.post("/", verifyUser, createBooking);
router.get("/:id", getBooking);
router.get("/user/:userId", verifyUser, getUserBookings);
router.get("/", verifyAdmin, getAllBooking);

export default router;
