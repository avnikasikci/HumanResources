package com.example.humanresources.business.concretes;

import com.example.humanresources.business.abstracts.UserService;
import com.example.humanresources.core.dataAccess.UserDao;
import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.DataResult;
import com.example.humanresources.core.utilities.results.Result;
import com.example.humanresources.core.utilities.results.SuccessDataResult;
import com.example.humanresources.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserManager implements UserService {

	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı eklendi");
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı güncellendi");
	}

	@Override
	public DataResult<User> findByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email)
				,"Kullanıcı bulundu");
	}

}
