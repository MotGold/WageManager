package app.web;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.DoesNotExistException;
import app.services.MainService;

@RestController
@RequestMapping("bob")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private Map<String, Session> sessionsMap;

	@Autowired
	private MainService mainService;

	@GetMapping("login/{user}/{password}")
	public LocalSession login(@PathVariable String user, @PathVariable String password)
			throws SQLException, DoesNotExistException {
		if (mainService.login(user, password)) {
			Long currentTime = System.currentTimeMillis();
			String token = UUID.randomUUID().toString();
			Session session = new Session(mainService.findAdminByUser(user).getId(), currentTime);
			sessionsMap.put(token, session);
			LocalSession localSession = new LocalSession(token, currentTime);
			return localSession; // token;
		} else
			throw new SQLException();
	}

	@GetMapping("logout/{token}")
	public String logout(@PathVariable String token) {
		sessionsMap.remove(token);
		return "logout complete";
	}

}
