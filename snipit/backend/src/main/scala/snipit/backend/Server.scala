package snipit.backend

import cask.MainRoutes
import com.genseven.models.Domain._
import org.mindrot.jbcrypt.BCrypt
import scala.concurrent.Await
import scala.concurrent.duration._

//Circe imports

import io.circe._
import io.circe.parser._
import io.circe.syntax._

object Server extends MainRoutes {
  val db = PostgresDB
  println("Connecting to the db....")
  Await.result(db.init(), 30.seconds)
  println("Connection Done......")

  //Helper 1 parse JSON (String -> Object)
  // We use a generic function [T]. It asks for an implicit Decoder[T].
  def parseJson[T](json: String)(implicit decoder: Decoder[T]): T = {
    decode[T](json) match {
      case Right(value) => value
      case Left(error) => throw new Exception(s"Invalid Json ${error.getMessage}")
    }
  }

  //Helper 2 write JSON (Object -> String)
  def writeJson[T](value: T)(implicit encoder: Encoder[T]): String = {
    value.asJson.noSpaces
  }

  //----Routes for the server-----
  @cask.post("/api/register")
  def register(request: cask.Request) = {
    try {
      //let's use our helper to parse
      val body = parseJson[LoginReq](request.text())
      val hash = BCrypt.hashpw(body.password, BCrypt.gensalt())
      val result = Await.result(db.register(body.username, hash), 2.seconds)
      result match {
        case Left(error) => cask.Response(error, statusCode = 400)
        case Right(user) => cask.Response(
          writeJson(SuccessMsg("Success", user.id)), //
          headers = Seq("Content-Type" -> "application/json")

        )

      }

    }
    catch {
      case e: Exception => cask.Response(e.getMessage, statusCode = 500)
    }
  }

  @cask.post("/api/snippets")
  def createSnippet(request: cask.Request) = {
    try {
      val req = parseJson[SnippetReq](request.text())
      val snippet = Await.result(db.createSnippet(req), 2.seconds)
      snippet match {
        case Left(error) => cask.Response(error, statusCode = 400)
        case Right(snippet) => cask.Response(
          writeJson(SuccessMsg("Success", req.userId)),
          headers = Seq("Content-Type" -> "application/json")
        )
      }

    }
    catch {
      case e: Exception => cask.Response(e.getMessage, statusCode = 500)
    }
  }

  @cask.get("/api/snippets/:userId")
  def listSnippets(userId: String) = {
    try {
      val list = Await.result(db.listSnippets(userId), 2.seconds)
      cask.Response(
        writeJson(list),
        headers = Seq("Content-Type" -> "application/json")
      )
    } catch {
      case e: Exception => cask.Response(e.getMessage, statusCode = 500)
    }
  }
  override def host = "0.0.0.0"
    override def port = 8000
    initialize()


}


