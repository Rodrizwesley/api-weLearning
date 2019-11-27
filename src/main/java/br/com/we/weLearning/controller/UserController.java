package br.com.we.weLearning.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.we.weLearning.enums.DatabaseStatus;
import br.com.we.weLearning.enums.Profile;
import br.com.we.weLearning.model.User;
import br.com.we.weLearning.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Lazy
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json")
	public Map<String, Object> newUser(@RequestBody User data) throws Exception{
		
		Map<String, Object> retorno = new HashMap<String, Object>();
		Boolean ok = false;
		
		try {
			if(userService.findByusername(data.getUsername()) == null && userService.findByCpf(data.getCpf()) == null) {
				userService.save(data);
			}
			else {
				System.out.println("teste");
			}
			
			ok = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		retorno.put("response", ok);
		
		return retorno;
	}
	
    @RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET, produces = "application/json") 
    public ResponseEntity<User> getUser(@PathVariable("idUser") Long idUser) throws UnsupportedEncodingException  { 
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        User user = null;
        try { 
        	
        	user = userService.findById(idUser);
        	if(user != null) {
        		ok = true;
        	}
        	else {
        		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        if(!ok) {
        	retorno.put("response", ok);
        }
 
        return new ResponseEntity<User>(user, HttpStatus.OK); 
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> doLogin(@RequestBody User data) throws Exception{
    	
    	try {
    		String username = data.getUsername();
    		String password = data.getPassword();
    		
//    		System.out.println(username);
//    		System.out.println(password);
    		
    		User user = userService.doLogin(username, password);
    		
    		if(user != null) {
    			return new ResponseEntity<User>(user, HttpStatus.OK); 
    		}
    		else {
    			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
    		}
    	}
    	catch (Exception e) {
    		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
    }
	
}