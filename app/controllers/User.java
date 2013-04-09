package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class User extends Controller {

	public static Result my_info(){
		return ok("我的信息");
	}
}
