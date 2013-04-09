package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class FCard extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	public long id;
	public String username;
	public String bank_name;
	public float credit_money;
	public float debt_money = 0;
	public String repay_day;
	
	public FCard(String username, String bank_name, float credit_money, String repay_day) {
		this.username = username;
		this.bank_name = bank_name;
		this.credit_money = credit_money;
		this.repay_day = repay_day;
	}
	
	public static Model.Finder<Long, FCard> find = new Model.Finder<Long, FCard>(Long.class, FCard.class);
	
	public static void createCard(FCard card){
		card.save();
	}
	
	public static void updateCard(FCard card){
		card.update();
	}
	
	public static void deleteCard(long id){
		find.byId(id).delete();
	}
	
	public static void deleteCard(String bank_name){
		find.where().eq("bank_name", bank_name).findUnique().delete();
	}
	
	public static FCard getCard(long id){
		return find.byId(id);
	}
	
	public static FCard getCard(String username){
		return find.where().eq("username", username).findUnique();
	}
	
	public static boolean isBindCard(String username){
		return getCard(username) == null;
	}
	
	public static String getCardRepayDay(long id){
		return find.byId(id).repay_day;
	}
	
	public static float getCardCreditMoney(long id){
		return find.byId(id).credit_money;
	}
	
	public static void repayOk(long id){
		FCard card = find.byId(id);
		card.debt_money = 0;
		card.update();
	}

}
