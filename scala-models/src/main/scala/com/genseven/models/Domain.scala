package com.genseven.models

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import com.genseven.utils.TimeUtils //this came from scala-utils

object Domain{
        //data models with codecs
    case class User(id : String, username: String, passwordHash: String)
    implicit val userCodec: Codec[User] = deriveCodec[User]
    case class Snippet(id: String, userId: String, title: String, code: String, language: String, createdAt: String = TimeUtils.now())
    implicit val snippetCodec : Codec[Snippet] = deriveCodec[Snippet]
       //DTOs with codecs
    case class LoginReq(username: String, password: String)
    implicit val loginRegCodec : Codec[LoginReq] = deriveCodec[LoginReq]
    case class SnippetReq(userId: String, title: String, code: String, language: String)
    implicit val snippetReqCodec: Codec[SnippetReq] = deriveCodec[SnippetReq]
    case class SuccessMsg(msg: String, id: String = "")
    implicit val successMsg: Codec[SuccessMsg] = deriveCodec[SuccessMsg]



}
