package dao;

public class LoginBean{
    private String userId;
    private String password;   
    private String mail;   
    
    public void setUserId(String userId){
        this.userId = userId;
    }   
    public String getUserId(){
        return userId;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setMail(String mail){
        this.mail = mail;
    }
    public String getMail(){
        return mail;
    }
}
