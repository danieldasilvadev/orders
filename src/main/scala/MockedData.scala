import model.{Item, Order, Product}

import java.time.ZonedDateTime

object MockedData {

  def getOrders() ={

    val prod1 = Product("Apple", "Fruit", 0.5, 1.0, ZonedDateTime.parse("2024-05-01T00:00:00Z")) // "1-3"
    val prod2 = Product("Banana", "Fruit", 0.3, 0.5, ZonedDateTime.parse("2024-04-01T00:00:00Z")) // "1-3
    val prod3 = Product("Orange", "Fruit", 0.4, 0.8, ZonedDateTime.parse("2024-02-01T00:00:00Z")) // "4-7"
    val prod4 = Product("Iphone", "Electronics", 0.5, 1000.0, ZonedDateTime.parse("2024-01-01T00:00:00Z")) // "4-7"
    val prod5 = Product("Galaxy Note", "Electronics", 0.6, 900.0, ZonedDateTime.parse("2023-11-01T00:00:00Z")) // "7-12"
    val prod6 = Product("Pixel", "Electronics", 0.4, 800.0, ZonedDateTime.parse("2023-10-01T00:00:00Z")) // "7-12"
    val prod7 = Product("Macbook", "Electronics", 1.0, 2000.0, ZonedDateTime.parse("2023-05-01T00:00:00Z")) // ">12"
    val prod8 = Product("Macbook", "Electronics", 1.0, 2000.0, ZonedDateTime.parse("2023-01-01T00:00:00Z")) // ">12"
    val prod9 = Product("Macbook", "Electronics", 1.0, 2000.0, ZonedDateTime.parse("2022-10-01T00:00:00Z")) // ">12"

    // mock the orders
    val order1 = Order("Daniel", "+551234567890", "Rua do Brasil, 80 apto 10", List(Item(prod1, 1.0, 0.1, 0.1), Item(prod2, 0.5, 0.05, 0.05)), ZonedDateTime.parse("2021-08-01T00:00:00Z"))
    val order2 = Order("John", "+551234567891", "Rua Sao Paulo, 70, apto 1", List(Item(prod2, 0.8, 0.08, 0.08), Item(prod3, 1000.0, 100.0, 100.0), Item(prod4, 1.2, 0.08, 0.05)), ZonedDateTime.parse("2021-09-01T00:00:00Z"))
    val order3 = Order("Mary", "+551234567892", "Rua Rio de Janeiro, 60, apto 20", List(Item(prod5, 900.0, 90.0, 90.0), Item(prod6, 800.0, 80.0, 80.0), Item(prod7, 2000.0, 200.0, 200.0)), ZonedDateTime.parse("2021-10-01T00:00:00Z"))
    val order4 = Order("Lucas", "+551234567893", "Rua Minas Gerais, 50, apto 30", List(Item(prod1, 1.0, 0.1, 0.1), Item(prod2, 0.5, 0.05, 0.05), Item(prod3, 0.8, 0.08, 0.08), Item(prod4, 1000.0, 100.0, 100.0), Item(prod5, 900.0, 90.0, 90.0), Item(prod6, 800.0, 80.0, 80.0), Item(prod7, 2000.0, 200.0, 200.0)), ZonedDateTime.parse("2021-11-01T00:00:00Z"))
    val order5 = Order("Ana", "+551234567894", "Rua Espirito Santo, 40, apto 40", List(Item(prod1, 1.0, 0.1, 0.1), Item(prod2, 0.5, 0.05, 0.05), Item(prod3, 0.8, 0.08, 0.08), Item(prod4, 1000.0, 100.0, 100.0), Item(prod5, 900.0, 90.0, 90.0), Item(prod6, 800.0, 80.0, 80.0), Item(prod7, 2000.0, 200.0, 200.0)), ZonedDateTime.parse("2021-12-01T00:00:00Z"))

    List(order1, order2, order3, order4, order5)
//    List(order3)
  }



}
