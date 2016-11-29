package services.impl

import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import javax.inject.Inject

import dao._
import models._
import play.api.libs.json.Json
import services.ConnectionService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by mirzaakhena on 11/28/16.
  */
class ConnectionServiceImpl @Inject()(userDao: UserDao) extends ConnectionService {


  override def login(name: String, password: String): Future[String] = {

    userDao.findByNameAndPassword("joni", "1234").map(x => {
      if(x.isDefined){
        JsonWebToken(JwtHeader("HS256"), JwtClaimsSet(Map("id" -> "dasfa")), "secret")
      } else {
        ""
      }
    })

  }

  override def showAll :Future[Seq[User]] = userDao.findAll()

  override def extractToken(token: String): String = ???

}
