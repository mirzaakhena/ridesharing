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

class DriverDao @Inject() (dbConfigProvider: DatabaseConfigProvider) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  val table = TableQuery[DriverTable]

  def save(driver: Driver) : Future[Int] =
    dbConfig.db.run(table returning table.map(_.id) += driver)

  def findById(id: Int): Future[Option[Driver]] =
    dbConfig.db.run(table.filter(_.id === id).result.headOption)

  def findAll() : Future[Seq[Driver]] =
    dbConfig.db.run(table.result)

  def updateStatus(idDriver: Int, status: String) =
    (for {c <- table if c.id === idDriver} yield c.status) update status
}

class DriverTable(tag: Tag) extends Table[Driver](tag, "driver") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def status = column[String]("status")
  def latitude = column[Double]("latitude")
  def longitude = column[Double]("longitude")

  def * = (id, status, latitude, longitude) <> ((Driver.apply _).tupled, Driver.unapply _)

}