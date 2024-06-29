package model

import java.time.ZonedDateTime

case class Order(
                customerName: String,
                contactNumber: String,
                shippingAddress: String,
                items: List[Item],
                placedAt: ZonedDateTime
                )
