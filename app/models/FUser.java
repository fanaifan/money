package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
@Entity
@Table(name="fuser")
public class FUser extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	public String username;
	public String password;
	public String mobile;
	public String email;
	
	public FUser(String username, String password, String mobile, String email) {
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
	}

	public static Model.Finder<Long,FUser> find = new Model.Finder<Long, FUser>(Long.class, FUser.class);
	
	public static void createUser(FUser fuser){
		fuser.save();
	}
	
	public static void deleteUser(long id){
		find.byId(id).delete();
	}
	
	public static void edit(FUser fuser){
		fuser.update();
	}
	
	public static FUser login(String username,String password){
		FUser fuser = null;
		fuser = find.where().eq("username", username).findUnique();
		if(fuser == null){
			fuser = find.where().eq("mobile", username).findUnique();
		}
		if(fuser == null){
			fuser = find.where().eq("email", username).findUnique();
		}
		
		if(fuser != null){
			if(fuser.password.equals(password)){
				return fuser;
			}
		}
		return null;
	}
	
	public static FUser getUser(long id){
		return find.byId(id);
	}
	
	public static FUser getUser(String username){
		return find.where().eq("username", username).findUnique();
	}
	
	public static List<FUser> getUsers(int page,int page_size){
		return find.findPagingList(page_size).getPage(page).getList();
	}

}
