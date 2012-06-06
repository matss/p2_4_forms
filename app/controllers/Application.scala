package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
    
  val idMap = Map[Int, String](1 -> "Steve", 2 -> "Bill").withDefaultValue("Unknown Id")
  
  def index(name : String = "Guest") = Action {
    Ok(views.html.index(name))
  }
  
  def indexId(id : Int) = Action {
    Ok(views.html.index(getName(id)))
  }
  
  def getName(id : Int) = {
    idMap(id)
  }

}