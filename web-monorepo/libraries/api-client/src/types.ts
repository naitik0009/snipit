//Data Models (Matching Scala Domain.scala)

export interface User{
    id: string;
    username: string;
    passwordhash: string;
}

export interface Snippet {
    id: string;
    userid: string;
    title: string;
    code: string;
    language: string;
    createdAt: string;
}

export interface LoginReq{
    username: string;
    password: string;
}

export interface SnippetReq{
    userId: string;
    title: string;
    code:string;
    language: string;
}

export interface SuccessMsg{
    msg: string;
    id: string;
}