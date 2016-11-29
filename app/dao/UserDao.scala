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

class UserDao @Inject()(dbConfigProvider: DatabaseConfigProvider) {

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

class UserTable(tag: Tag) extends Table[User](tag, "user") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def password = column[String]("password")

  def role = column[String]("role")

  def * = (id, name, password, role) <> ((User.apply _).tupled, User.unapply _)

}