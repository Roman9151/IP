package com.app.request

case class EndPoint(root: String, path: String) {
  def getUrl: String = s"$root$path"
}

