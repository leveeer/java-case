package com.jxau.bank.model;

public class MoneyBean {

	private String name;
	private String password;
	private double money;

	
	//本类创建一个对象
	private static MoneyBean instance = null;

	//构造方法私有化，防止其他类创建对象
	private MoneyBean() {
	}

	//为了外界能够通过类名直接调用
	public static MoneyBean getInstance(){
		if(instance == null) {
			instance = new MoneyBean ();
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "MoneyBean{" + "name='" + name + '\'' + ", password='" + password + '\'' + ", money=" + money + '}';
	}
}
