package edu.gzhu.its.qa.model;

public class Mine {
	/* "username": "纸飞机" //我的昵称
	      ,"id": "100000" //我的ID
	      ,"status": "online" //在线状态 online：在线、hide：隐身
	      ,"sign": "在深邃的编码世界，做一枚轻盈的纸飞机" //我的签名
	      ,"avatar": "a.jpg" //我的头像
*/	    	  
	    	  private String username;
	    	  private String id;
	    	  private String status;
	    	  private String sign;
	    	  private String avatar;
	    	  public Mine(String username, String id, String status, String sign, String avatar) {
				super();
				this.username = username;
				this.id = id;
				this.status = status;
				this.sign = sign;
				this.avatar = avatar;
			}
			
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getSign() {
				return sign;
			}
			public void setSign(String sign) {
				this.sign = sign;
			}
			public String getAvatar() {
				return avatar;
			}
			public void setAvatar(String avatar) {
				this.avatar = avatar;
			}

}
