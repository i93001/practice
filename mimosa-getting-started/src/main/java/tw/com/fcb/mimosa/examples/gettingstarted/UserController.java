package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	static List<User> users = new ArrayList<User>();
	
	@Autowired
	UserDtoMapper mapper;
	
	@GetMapping("/getUsers")
	List<UserDto> getUsers() {
//		User user = new User();
//		user.setName("FCB-i93001");
//		user.setAge(20);
//		return user;
		//return User.builder().age(20).name("matt").build();
		//return users;
		List<UserDto> lst = new ArrayList<UserDto>();
//		for (User user:users)
//		for (int i=0;i<users.size();i++) {
//			User user = users.get(i);
//			UserDto u = new UserDto();
//			u.setName(user.getName());
//			lst.add(u);
//		}
		for (User user:users) {
//			UserDto dto = UserDto.builder().name(user.getName()).build();
			UserDto dto = mapper.from(user);
			lst.add(dto);
		}
		
//		users.stream().map(user->UserDto.builder().name(user.getName()))
		return lst;
	}
	
	@PostMapping("/addUser")
	void createUser(@RequestBody User user) {
		users.add(user);
		
	}
	//GitHub Name   2021-10-26	-> public
	//modify user
	@PutMapping("/modifyUser")
	void modifyUser(@RequestParam(value="name", required=true) String name,
					@RequestParam(value="age",required=true) int age) {
		User u = new User();
		u.setName(name);
		int i = users.indexOf(u);
		if ( i>= 0) {
			users.get(i).setAge(age);
			System.out.println("modify");
		}
	}
	
	
	
	//delete user
	//DeleteMapping
	@DeleteMapping("/delUser")
	void deleteUser(@RequestParam(value="name", required=true) String name) {
		System.out.println("name->:" + name);
		User u = new User();
		u.setName(name);
		int i = users.indexOf(u);
		if ( i>= 0) {
			users.remove(i);
			System.out.println("del");
		}
	}

}
