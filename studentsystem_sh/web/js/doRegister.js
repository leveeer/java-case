//验证用户名是否合理
function checkUser(){
	var user=document.getElementById("uid").value;
	var utip=document.getElementById("utip");
	if(user==""){
		utip.innerHTML="用户名为空";
		return false;
	}else{
		utip.innerHTML="";
		return true;
	}
}
//验证密码是否合理
function checkPwd(){
	var pwd=document.getElementById("pwd").value;
	var ptip=document.getElementById("ptip");
	if(pwd.length<6||pwd.length>16){
		ptip.innerHTML="密码长度不符合要求";
		return false;
	}else{
		ptip.innerHTML="";
		return true;
	}
}
//验证邮箱 @.
function checkEmail(){
	var email=document.getElementById("email").value;
	var etip=document.getElementById("etip");
	if(email.indexOf("@")==-1){
		etip.innerHTML="邮箱必须包含@";
		return false;
	}
	else if(email.indexOf(".")<=email.indexOf("@")){
		etip.innerHTML="邮箱必须包含@.";
		return false;
	}else {
        etip.innerHTML="";
        return true;
    }

}
//响应表单提交事件
function check(){
	if(!checkUser()){
		return false;
	}
	else if(!checkPwd()){
		return false;
	}
	else if(!checkEmail()){
		return false;
	}
	return true;
}
