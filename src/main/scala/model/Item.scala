package model

case class Item(
               product: Product,
               price: BigDecimal,
               shippingFee: BigDecimal,
               taxAmount: BigDecimal
               )
