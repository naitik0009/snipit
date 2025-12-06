package snipit.backend

import scala.concurrent.ExecutionContext.Implicits.global
import snipit.core.Repository
import com.genseven.models.Domain._
import com.genseven.utils.TimeUtils

//Slick imports

import slick.jdbc.PostgresProfile.api._
import slick.jdbc.GetResult
import scala.concurrent.Future
import java.util.UUID 

object PostgresDB extends Repository {
    val db = Database.forConfig("storage")
    //let's write the mapper turning sql row into scala class
    //Get result tells slick how to read columns in order
    implicit val getUser: GetResult[User] = GetResult(
        r=> User(r.nextString(), r.nextString(), r.nextString())
    )
    implicit val getSnippet: GetResult[Snippet] = GetResult(
        r=> Snippet(r.nextString(), r.nextString(),r.nextString(), r.nextString(), r.nextString(), r.nextString())
    )
    //Let's create tables for the database
    def init(): Future[Unit] = {
        val setup = DBIO.seq(
            sqlu"""CREATE TABLE IF NOT EXISTS users (id VARCHAR PRIMARY KEY, username VARCHAR UNIQUE NOT NULL, password_hash VARCHAR NOT NULL)""",
            sqlu"""CREATE TABLE IF NOT EXISTS snippets (id VARCHAR PRIMARY KEY, user_id VARCHAR NOT NULL, title VARCHAR NOT NULL, code TEXT NOT NULL, language VARCHAR NOT NULL, created_at VARCHAR NOT NULL)"""
        )
        db.run(setup)

    }

    override def register(username: String, hash: String): Future[Either[String, User]] = {
        val id = UUID.randomUUID().toString
        //sqlu update or insert
        val query = sqlu"INSERT INTO users VALUES ($id, $username, $hash)"
        db.run(query).map(_ => Right(User(id, username, hash))).recover{
            case exception if exception.getMessage != null && exception.getMessage.contains("unique constraint") => Left("Username taken")
        }

    }

    override def findUser(username: String): Future[Option[User]] ={
        val query = sql"SELECT * FROM users WHERE username = $username".as[User].headOption //here when .as[User] is called it's actually the getUser implicit
        db.run(query)
    }

    override def createSnippet(req: SnippetReq) : Future[Either[String, Snippet]] = {
        val data = Snippet(UUID.randomUUID().toString, req.userId, req.title, req.code, req.language, TimeUtils.now())
        val query = sqlu"INSERT INTO snippets VALUES (${data.id}, ${data.userId}, ${data.title}, ${data.code}, ${data.language}, ${data.createdAt})"
        db.run(query).map(_ => Right(Snippet(data.userId, data.title, data.language, data.code, data.createdAt )))
    }

    override def listSnippets(userId: String): Future[List[Snippet]] = {
       val query = sql"SELECT * FROM snippets WHERE user_id = $userId".as[Snippet]
       db.run(query).map(_.toList)
    }

}