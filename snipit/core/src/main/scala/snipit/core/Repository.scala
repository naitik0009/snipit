package snipit.core

import com.genseven.models.Domain._ //coming from scala-models
import scala.concurrent.Future 

trait Repository {
    // We return Future[...] because databases are slow.
    // We return Either[String, User] to handle errors (Left="Error", Right="Success")
    def register(username: String, hash: String): Future[Either[String, User]]
    def findUser(username: String): Future[Option[User]]
    def createSnippet(request: SnippetReq): Future[Either[String, Snippet]]
    def listSnippets(userId: String): Future[List[Snippet]]
}
