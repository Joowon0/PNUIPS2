package pnuips;

import java.util.Date;

public class ClientBean {
	private String userID;
	private String password;
	
	private String firstName;
	private String lastName;
	
	private String birth;
	
	private Boolean isAdmin;
	private Boolean coupon10;
	private Boolean coupon30;
	
	public void initiate(String userid, String password, String firstName, String lastName,
			String birth, Boolean isAdmin, Boolean coupon10, Boolean coupon30 ) {
		this.userID = userid;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birth = birth;
		this.isAdmin = isAdmin;
		this.coupon10 = coupon10;
		this.coupon30 = coupon30;
	}
	public void initiate(String userid, String password, String firstName, String lastName,
			String birth) {
		this.userID = userid;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birth = birth;
	}
	
	public void xprint() {
		System.out.println("User ID  : " + userID);
		System.out.println("Password : " + password);
		System.out.println("Name     : " + firstName + " " + lastName);
		System.out.println("Birth    : " + birth);
		System.out.println("Is Admin : " + isAdmin);
		System.out.println("coupon10 : " + coupon10);
		System.out.println("coupon30 : " + coupon30);
	}
	
	public String getId() {
		return userID;
	}
	public void setID(String userID) {
		this.userID = userID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
    public String getBirth() {
        return birth;
    }
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public Boolean getCoupon10() {
        return coupon10;
    }
    public void useCoupon10() {
    	coupon10 = false;
    }
    public Boolean getCoupon30() {
        return coupon30;
    }
    public void useCoupon30() {
    	coupon30 = false;
    }
}
