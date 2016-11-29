package dao

/**
  * Created by mirzaakhena on 11/28/16.
  */

import javax.inject.Inject

import models._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver._
import slick.driver.H2Driver.api._

import scala.concurrent.Future

class TripDao @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  val table = TableQuery[TripTable]

  def save(trip: Trip) : Future[Int] =
    dbConfig.db.run(table returning table.map(_.id) += trip)

  def findById(id: Int): Future[Option[Trip]] =
    dbConfig.db.run(table.filter(_.id === id).result.headOption)

  def findAll() : Future[Seq[Trip]] =
    dbConfig.db.run(table.result)

  def updateStatus(idTrip: Int, status: String) =
    (for {c <- table if c.id === idTrip} yield c.status) update status
}

class TripTable(tag: Tag) extends Table[Trip](tag, "trip") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def id_passenger = column[Int]("id_passenger")
  def id_driver = column[Int]("id_driver")
  def status = column[String]("status")
  def latitude_start = column[Double]("latitude_start")
  def longitude_start = column[Double]("longitude_start")
  def latitude_end = column[Double]("latitude_end")
  def longitude_end = column[Double]("longitude_end")

  def * = (id, id_passenger, id_driver, status, latitude_start, longitude_start, latitude_end, longitude_end) <> ((Trip.apply _).tupled, Trip.unapply _)

}