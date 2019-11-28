package br.com.we.weLearning.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
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
		User user = null;
		
		try {

			if(userService.findByusername(data.getUsername()) == null && userService.findByCpf(data.getCpf()) == null) {
				user = userService.save(data);
				retorno.put("user", user);
			}
			else {
				retorno.put("erro", "This user already exist");
			}
			
			ok = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
        if(!ok) {
        	retorno.put("response", ok);
        }
 
        return retorno; 
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> updateUser(@RequestBody User data) throws Exception{
		
		Map<String, Object> retorno = new HashMap<String, Object>();
		Boolean ok = false;
		User user = null;
		
		try {
				user = userService.update(data);
	        	if(user != null) {
	        		ok = true;
	        		retorno.put("user", user);
	        	}
	        	else {
	        		retorno.put("erro", "User not found");
	        		return retorno;
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
    public Map<String, Object> getUser(@PathVariable("idUser") Long idUser) throws UnsupportedEncodingException  { 
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        User user = null;
        try { 
        	
        	user = userService.findById(idUser);
        	if(user != null) {
        		ok = true;
        		retorno.put("user", user);
        	}
        	else {
        		retorno.put("erro", "User not found");
        		return retorno;
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        if(!ok) {
        	retorno.put("response", ok);
        }
 
        return retorno; 
    }
    
    @RequestMapping(value = "/user/inative/{idUser}", method = RequestMethod.POST, produces = "application/json") 
    public Map<String, Object> inativeUser(@PathVariable("idUser") int idUser) throws UnsupportedEncodingException  { 
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        try { 
        	userService.inativeById(idUser);
        	ok = true;
        	retorno.put("response", "Falha ao inativar Usuário");
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
    	retorno.put("response", ok);
 
        return retorno; 
    }
    
    @RequestMapping(value = "/user/{idUser}", method = RequestMethod.DELETE, produces = "application/json") 
    public Map<String, Object> delete(@PathVariable("idUser") int idUser) throws UnsupportedEncodingException  { 
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        try { 
        	userService.deleteById(idUser);
        	ok = true;
        	retorno.put("response", "Falha ao deletar Usuário");
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 

        	retorno.put("response", ok);

 
        return retorno; 
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
    
    @RequestMapping(value = "/user/getAll", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> getAll() throws Exception {
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        List<User> users = null;
        try { 
        	
        	users = userService.findAllUsers();
        	if(users.size() > 0) {
        		ok = true;
        		retorno.put("users", users);
        	}
        	else {
        		retorno.put("erro", "Users not found");
        		return retorno;
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        if(!ok) {
        	retorno.put("response", ok);
        }
 
        return retorno; 
    }
    
    @RequestMapping(value = "/user/getAllInative", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> getAlInative() throws Exception {
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        List<User> users = null;
        try { 
        	
        	users = userService.findAllUsersInvalid();
        	if(users.size() > 0) {
        		ok = true;
        		retorno.put("users", users);
        	}
        	else {
        		retorno.put("erro", "Users not found");
        		return retorno;
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        if(!ok) {
        	retorno.put("response", ok);
        }
 
        return retorno; 
    }
    
    @RequestMapping(value = "/user/getAllDeleted", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> getAllDeleted() throws Exception {
        Map<String, Object> retorno = new HashMap<String, Object>();
        Boolean ok = false; 
        List<User> users = null;
        try { 
        	
        	users = userService.findAllUsersDeleted();
        	if(users.size() > 0) {
        		ok = true;
        		retorno.put("users", users);
        	}
        	else {
        		retorno.put("erro", "Users not found");
        		return retorno;
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        if(!ok) {
        	retorno.put("response", ok);
        }
 
        return retorno; 
    }

	
}
