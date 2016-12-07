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

class UserDao @Inject()(val dbConfigProvider: DatabaseConfigProvider) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  val table = TableQuery[UserTable]

  def save(user: User): Future[Int] =
    dbConfig.db.run(table returning table.map(_.id) += user)

  def findById(id: Int): Future[Option[User]] =
    dbConfig.db.run(table.filter(_.id === id).result.headOption)

  def findAll(): Future[Seq[User]] =
    dbConfig.db.run(table.result)

  def findByNameAndPassword(name: String, password: String) : Future[Option[User]] =
    dbConfig.db.run(table.filter(c => c.name === name && c.password === password).result.headOption)

}

class UserTable(tag: Tag) extends Table[User](tag, "USER") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME")

  def password = column[String]("PASSWORD")

  def role = column[String]("ROLE")

  def * = (id, name, password, role) <> ((User.apply _).tupled, User.unapply _)

}