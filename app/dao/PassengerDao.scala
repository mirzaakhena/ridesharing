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

class PassengerDao @Inject() (dbConfigProvider: DatabaseConfigProvider) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  val table = TableQuery[PassengerTable]

  def save(passenger: Passenger) : Future[Int] =
    dbConfig.db.run(table returning table.map(_.id) += passenger)

  def findById(id: Int): Future[Option[Passenger]] =
    dbConfig.db.run(table.filter(_.id === id).result.headOption)

  def findAll() : Future[Seq[Passenger]] =
    dbConfig.db.run(table.result)

  def updateStatus(idPassenger: Int, status: String) =
    (for {c <- table if c.id === idPassenger} yield c.status) update status
}

class PassengerTable(tag: Tag) extends Table[Passenger](tag, "PASSENGER") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def status = column[String]("STATUS")
  
  def * = (id, status) <> ((Passenger.apply _).tupled, Passenger.unapply _)

}