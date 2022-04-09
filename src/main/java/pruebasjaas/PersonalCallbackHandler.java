package pruebasjaas;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.HttpServletRequest;

import com.ibm.wsspi.security.auth.callback.WSServletRequestCallback;


public class PersonalCallbackHandler implements CallbackHandler{
   String name;
   String password;
  HttpServletRequest request;
	   
   
	public PersonalCallbackHandler(String name, String password, HttpServletRequest request) {
		super();
		this.name = name;
		this.password = password;
		this.request = request;
	}


	
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSServletRequestCallback requestCB = null;
		NameCallback nameCallback = null;
		PasswordCallback passwordCallback = null;
		
		// TODO Auto-generated method stub
		for(Callback callback : callbacks) {
			if(callback instanceof NameCallback) {
				nameCallback = (NameCallback) callback;
				if(request != null) {					
					nameCallback.setName(name);
				}
			}else if(callback instanceof PasswordCallback) {
				passwordCallback = (PasswordCallback) callback;
				if(request != null) {
					passwordCallback.setPassword(password.toCharArray());
				}
				
			}else if(callback instanceof WSServletRequestCallback) {
				requestCB = (WSServletRequestCallback)callback;
				requestCB.setHttpServletRequest(request);
			}
		}
	}

}
