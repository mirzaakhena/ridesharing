package services.impl

import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import javax.inject.Inject

import dao._
import models._
import services.ConnectionService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by mirzaakhena on 11/28/16.
  */
class ConnectionServiceImpl @Inject()(userDao: UserDao) extends ConnectionService {

  override def login(name: String, password: String): Future[String] = {

    userDao.findByNameAndPassword(name, password).map(x => {
      if(x.isDefined){



        JsonWebToken(JwtHeader("HS256"), JwtClaimsSet(Map("id" -> x.get.id.toString,"a"->"asdfa")), "secret")
      } else {
        ""
      }
    })

  }

  override def showAll :Future[Seq[User]] = userDao.findAll()

  override def extractToken(token: String): String = {
    val claims: Option[Map[String, String]] = token match {
      case JsonWebToken(header, claimsSet, signature) =>
        claimsSet.asSimpleMap.toOption
      case x =>
        None
    }

    claims.getOrElse(Map.empty[String, String]).get("id").getOrElse("")

  }

}
