package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.{tuple, nonEmptyText, text, email, optional}
import play.api.libs.json.JsValue

object Users extends Controller {
  
  def createForm = Action {
    Ok(views.html.users.edit(routes.Users.create))    
  }
  
  def createForm2 = Action {
    Ok(views.html.users.edit(routes.Users.create2))    
  }
   
  def create = Action {
    implicit request =>
      userForm.bindFromRequest.fold(
      failingForm => {
        Logger.info("Errors: " + failingForm.errors)
        Redirect(routes.Users.createForm)
      }, {
        case (firstName, lastName, email) => {
        	Ok("Created user: " + firstName + ", " + lastName.getOrElse("") + ", " + email.getOrElse(""))
        }
      }
    )    
  }
  
  def create2 = Action {
	  implicit request =>
	  	request.body.asJson.map { json =>
	  		(json \ "firstName").asOpt[String].map { firstName => 
	  		    val lastName = (json \ "lastName").asOpt[String].getOrElse("")
	  		    val email = (json \ "email").asOpt[String].getOrElse("")
	  			Ok("Created user: " + firstName + ", " + lastName + ", " + email)
	  		}.getOrElse {
	  			BadRequest("Missing parameter [name]")
	  		}
	  	}.getOrElse {
	  		BadRequest("Expecting Json data")
	  	}
  }
  
  def createFromJson(json : JsValue) {
    val firstName = (json \ "firstName").asOpt[String]
    
  }
  
  
  val userForm = Form(
    tuple(
      "firstName" -> nonEmptyText,
      "lastName" -> optional(text),
      "email" -> optional(email)
    )
  )
}