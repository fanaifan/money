package models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import utils.StringUtil;

@Entity
public class FAccount extends Model{

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	public String username;
	public String account_project;
	public float account_money;
	public Date account_date;
	public String account_bank;
	public String create_at;
	
	public FAccount(String username, String account_project, float account_money, Date account_date, String account_bank) {
		this.username = username;
		this.account_project = account_project;
		this.account_money = account_money;
		this.account_date = account_date;
		this.account_bank = account_bank;
	}

	public static Model.Finder<Long, FAccount> find = new Model.Finder<Long, FAccount>(Long.class, FAccount.class);
	
	public static void createAccount(FAccount fa){
		fa.create_at = StringUtil.getStandTime();
		fa.save();
	}
	
	public static void updateAccount(FAccount fa){
		fa.create_at = StringUtil.getStandTime();
		fa.update();
	}
	
	public static void deleteAccount(long id){
		find.byId(id).delete();
	}
	
	public static void deleteAccount(String account_project){
		find.where().eq("account_project", account_project).findUnique().delete();
	}
	
	public static FAccount getAccount(long id){
		return find.byId(id);
	}
	
	public static FAccount getAccount(String username, String account_project){
		return find.where().eq("username", username).where().eq("account_project", account_project).findUnique();
	}
	
	public static List<FAccount> getAccounts(String username, int page, int page_size){
		return find.where().eq("username", username).order().desc("account_date").findPagingList(page_size).getPage(page).getList();
	}
	
	public static int getTotalPage(String username, int page, int page_size){
		return find.where().eq("username", username).order().desc("account_date").findPagingList(page_size).getTotalPageCount();
	}
	
	public static List<FAccount> getAccounts(String username, String year, String month, int page, int page_size){
		String start_date = year + "-" + month + "-01";
		String end_date =  year + "-" + month + "-31";
		return find.where().eq("username", username).between("account_date", start_date, end_date).findList();
	}
	
	public static List<FAccount> getTodayAccounts(String username){
		return find.where().eq("username", username).order().desc("account_date").findList();
	}
	
	public static List<FAccount> getAccounts(String username, String money){
		float money_f = Float.parseFloat(money);
		return find.where().eq("username", username).between("account_money", money_f-10, money_f+10).findList();
	}
	
	public static List<FAccount> getAccounts(String username, String start_date, String end_date, String money){
		float money_f = Float.parseFloat(money);
		return find.where().eq("username", username).between("account_money", money_f-10, money_f+10).between("account_date", start_date, end_date).findList();
	}
	
	public static List<FAccount> getAccounts(String username, String start_date, String end_date){
		return find.where().eq("username", username).between("account_date", start_date, end_date).findList();
	}
	
	public static List<FAccount> getCreditAccounts(String username){
		Map<String, Object> condition = new HashMap<String,Object>();
		condition.put("username", username);
		condition.put("account_bank", "credit");
		return find.where().allEq(condition).findList();
	}

}
