package com.example.humanresources.business.abstracts;


import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.DataResult;
import com.example.humanresources.core.utilities.results.Result;

public interface UserService {
	Result add(User user);
	Result update(User user);
	DataResult<User> findByEmail(String email);
}
