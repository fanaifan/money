package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.FAccount;
import models.FCard;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.StringUtil;

import views.html.*;

public class Account extends Controller {

	public static Result add_account_html() {
		String username = session().get("username");
		List<FAccount> faccounts = FAccount.getTodayAccounts(username);
		if(FCard.isBindCard(username)){
			return ok(add_account.render(faccounts,"no"));
		}
		return ok(add_account.render(faccounts,"yes"));
	}

	public static Result add_account() {

		Map<String, String> map = form().bindFromRequest().data();
		Date account_date = StringUtil.convertDate(map.get(StringUtil.formdata("account_date")),null);
		String account_project = map.get(StringUtil.formdata("account_project"));
		float account_money = Float.parseFloat(map.get(StringUtil.formdata("account_money")));
		String account_bank = map.get(StringUtil.formdata("account_bank"));
		
		String username = session().get("username");
		if(username==null || username.equals("")){
			return redirect("/");
		}
		
		if(account_bank.equals("credit")){
			FCard card = FCard.getCard(username);
			card.debt_money += account_money;
			FCard.updateCard(card);
		}
		
		FAccount fa = new FAccount(username,account_project, account_money, account_date, account_bank);
		FAccount.createAccount(fa);

		return redirect("/add-account");
	}

	public static Result delete_account(Long id) {
		
		FAccount.deleteAccount(id);
		return redirect("/add-account");
	}
	
	protected static String get_js(List<FAccount> faccounts){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(FAccount fa : faccounts){
			String pro = fa.account_project;
			String mon = fa.account_money+"";
			sb.append("['"+pro+"',"+mon+"],");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
	public static Result my_account(){
		
		String username = session().get("username");
		List<FAccount> faccounts = FAccount.getAccounts(username, 0, 5);
		
		return ok(my_account.render(faccounts,get_js(faccounts)));
	}
	
	public static Result query_account(){
		
		Map<String,String> map = form().bindFromRequest().data();
		Logger.info(Json.toJson(map).toString());
		
		String start_date = map.get(StringUtil.formdata("start_date"));
		String end_date = map.get(StringUtil.formdata("end_date"));
		String money = map.get(StringUtil.formdata("money"));
		String username = session().get("username");
		
		List<FAccount> faccounts = new ArrayList<FAccount>();
		
		if((start_date==null||start_date.equals("")) && (money==null||money.equals(""))){
			return redirect("/my-account");
		}
		if(money==null || money.equals("")){
			faccounts = FAccount.getAccounts(username, start_date, end_date);
		}else if(start_date == null || start_date.equals("")){
			faccounts = FAccount.getAccounts(username, money);
		}else{
			faccounts = FAccount.getAccounts(username, start_date, end_date, money);
		}
		
		return ok(my_account.render(faccounts,get_js(faccounts)));
	}
	
	public static Result history_account(int page,int page_size){
		String username = session().get("username");
		List<FAccount> faccounts = FAccount.getAccounts(username, page-1, page_size);
		int totalPage = FAccount.getTotalPage(username, page, page_size);

		return ok(history.render(faccounts,page,totalPage));
	}

}
