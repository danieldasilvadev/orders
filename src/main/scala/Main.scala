import model.Order

import java.time.{LocalDate, ZoneId, ZoneOffset, ZonedDateTime}
import java.time.format.DateTimeFormatter
import scala.util.{Failure, Success, Try}

case class ArgsValidated(startDate: ZonedDateTime, endDate: ZonedDateTime, intervalsOpt: Option[List[String]])

object Main {
  val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"))
  val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  val regexTwoIntervals = "^[0-9]+-[0-9]+$"
  val regexGreaterLessThan = "^[<>][0-9]+$"

  val defaultIntervals = List("1-3", "4-7", "7-12", ">12")

  private def validateArgs(args: Array[String]): ArgsValidated = {

    def validateDates(dateStr: String): Either[Throwable, ZonedDateTime] = {
      Try {
        val formatWithTime = Try(ZonedDateTime.parse(dateStr, dateTimeFormatter))
        val formatWithoutTime = Try(LocalDate.parse(dateStr, dateFormatter).atStartOfDay(ZoneOffset.UTC))
        formatWithTime.orElse(formatWithoutTime).get
      } match {
        case Success(zdt) => Right(zdt)
        case Failure(_) => Left(throw new IllegalArgumentException(s"The following date is invalid: $dateStr"))
      }
    }

    def validateIntervals(args: Array[String]): Either[Throwable, Option[List[String]]] = {

      def validateInterval(interval: String) = {
        interval match {
          case i if i.matches(regexTwoIntervals) =>
            val Array(start, end) = i.split("-").map(_.toInt)
            if (start > end) throw new IllegalArgumentException(s"The following interval is invalid: $interval")
            interval
          case i if i.matches(regexGreaterLessThan) => i
          case _ => throw new IllegalArgumentException(s"The following interval is invalid: $interval")
        }
      }
      val intervalsList = if (args.isEmpty) None else Some(args.toList)
      Right(intervalsList.map(_.map(validateInterval))
      )
    }

    if (args.length < 2){
      throw new IllegalArgumentException("Please provide start and end date")
    }

    (
      for {
      startDate <- validateDates(args(0))
      endDate <- validateDates(args(1))
      _ <- if (startDate.isAfter(endDate)) Left(throw new IllegalArgumentException("the start date must be before the end date")) else Right(())
      intervalsOpt <- validateIntervals(args.drop(2))
    } yield ArgsValidated(startDate, endDate, intervalsOpt)
    ) match {
      case Right(inputValidated) => inputValidated
      case Left(error) => throw error
    }
  }

  def main(args: Array[String]): Unit = {
    val datetimeNow = ZonedDateTime.now()

    val argsValidated = validateArgs(args)

    val startDate = argsValidated.startDate
    val endDate = argsValidated.endDate

    val intervals = argsValidated.intervalsOpt.getOrElse(defaultIntervals)

    val orders: List[Order] = MockedData.getOrders()

    val filterOrdersByRange = orders.filter(o => o.placedAt.isAfter(startDate) && o.placedAt.isBefore(endDate))

    val soldProducts = filterOrdersByRange.flatMap(_.items).map(_.product)

    val result = soldProducts.flatMap { prod =>
      intervals.map {
        case i if i.matches(regexTwoIntervals) =>
          val Array(start, end) = i.split("-").map(_.toInt)
          if (prod.createdDateAt.isAfter(datetimeNow.minusMonths(end)) && prod.createdDateAt.isBefore(datetimeNow.minusMonths(start))) (i, 1) else (i, 0)
        case i if i.matches(regexGreaterLessThan) =>
          val months = i.drop(1).toInt
          if (prod.createdDateAt.isBefore(datetimeNow.minusMonths(months))) (i, 1) else (i, 0)
      }
    }.foldLeft(Map.empty[String, Int]) {
      case (acc, (interval, count)) =>
        acc.updated(interval, acc.getOrElse(interval, 0) + count)
    }

    result.foreach{
      case (interval, count) =>
        println(s"$interval months: $count orders")
    }

  }
}
