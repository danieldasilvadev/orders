package model

import java.time.ZonedDateTime

case class Product(
                  name: String,
                  category: String,
                  weight: BigDecimal,
                  price: BigDecimal,
                  createdDateAt: ZonedDateTime
                  )
