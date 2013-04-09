package controllers;

import java.util.Map;

import models.FUser;

import play.mvc.Controller;
import play.mvc.Result;
import utils.StringUtil;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render(""));
	}

	public static Result login() {
		Map<String, String> map = form().bindFromRequest().data();
		String username = map.get(StringUtil.formdata("username"));
		String password = map.get(StringUtil.formdata("password"));
		FUser fuser = FUser.login(username, password);
		if (fuser != null) {
			session("username", fuser.username);
			return redirect("/add-account");
		}
		return ok(index.render("用户名或者密码错误!"));
	}

	public static Result register_html() {
		return ok(register.render());
	}
	
	public static Result register(){
		
		Map<String,String> map = form().bindFromRequest().data();
		String username = map.get(StringUtil.formdata("username"));
		String password = map.get(StringUtil.formdata("password"));
		String mobile = map.get(StringUtil.formdata("mobile"));
		String email = map.get(StringUtil.formdata("email"));
		
		FUser fuser = new FUser(username,password,mobile,email);
		FUser.createUser(fuser);
		
		return redirect("/");
	}
	
	public static Result logout(){
		session().clear();
		return redirect("/");
	}
	
	public static Result about(){
		return ok(about.render());
	}

}