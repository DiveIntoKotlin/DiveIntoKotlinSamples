package samples.ch04

sealed class Coupon {
	companion object {
		final val CashType = "CASH"
		final val DiscountType = "DISCOUNT"
		final val GiftType = "GIFT"
	}

	class CashCoupon(val id: Long, val type: String, val leastCost: Long, val reduceCost: Long) : Coupon()
	class DiscountCoupon(val id: Long, val type: String, val discount: Int) : Coupon()
	class GiftCoupon(val id: Long, val type: String, val gift: String) : Coupon()
}

data class User(val name: String)
sealed class CouponStatus {
	data class StatusNotFetched(val coupon: Coupon) : CouponStatus()
	data class StatusFetched(val coupon: Coupon, val user: User) : CouponStatus()
	data class StatusUsed(val coupon: Coupon, val user: User) : CouponStatus()
	data class StatusExpired(val coupon: Coupon) : CouponStatus()
	data class StatusUnAvilable(val coupon: Coupon) : CouponStatus()
}
