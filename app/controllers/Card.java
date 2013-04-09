package controllers;

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

public class Card extends Controller {

	public static Result credit() {
		
		String username = session().get("username");
		if(FCard.isBindCard(username)){
			return ok(add_credit.render("ok"));
		}
		FCard card = FCard.getCard(username); 
		
		String repay = StringUtil.repayDate(card.repay_day);
		String repayend = StringUtil.repayEndDate(card.repay_day);
		
		List<FAccount> faccounts = FAccount.getCreditAccounts(username);
		String pie_js = Account.get_js(faccounts);
		String line_js = get_js_line(faccounts);
		
		
		return ok(credit.render(card,pie_js,line_js,repay,repayend));
	}
	
	protected static String get_js_line(List<FAccount> faccounts){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(FAccount fa : faccounts){
			String pro = StringUtil.convertDate(fa.account_date, "yyyy-MM-dd");
			String mon = fa.account_money+"";
			sb.append("['"+pro+"',"+mon+"],");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}

	public static Result add_credit_html() {
		
		String username = session().get("username");
		if(FCard.isBindCard(username)){
			return ok(add_credit.render("ok"));
		}
		return ok(add_credit.render("no"));
	}

	public static Result add_credit() {
		Map<String,String> map = form().bindFromRequest().data();
		String bank_name = map.get(StringUtil.formdata("bank_name"));
		float credit_money = Float.parseFloat(map.get(StringUtil.formdata("credit_money")));
		String repay_day = map.get(StringUtil.formdata("repay_day"));
		String username = session().get("username");
		
		FCard card = new FCard(username, bank_name, credit_money, repay_day);
		FCard.createCard(card);
		
		String repay = StringUtil.repayDate(card.repay_day);
		String repayend = StringUtil.repayEndDate(card.repay_day);
		
		return ok(credit.render(card,"","",repay,repayend));
	}

}
