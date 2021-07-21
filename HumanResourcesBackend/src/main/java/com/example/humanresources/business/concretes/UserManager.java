package com.example.humanresources.business.concretes;

import com.example.humanresources.business.abstracts.UserService;
import com.example.humanresources.core.dataAccess.UserDao;
import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.*;
import com.example.humanresources.dataAccess.abstracts.CandidateDao;
import com.example.humanresources.dataAccess.abstracts.EmployerDao;
import com.example.humanresources.dataAccess.abstracts.StaffDao;
import com.example.humanresources.entities.dtos.UserLoginDto;
import com.example.humanresources.entities.dtos.UserLoginReturnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private CandidateDao candidateDao;
	private EmployerDao employerDao;
	private StaffDao staffDao;

//	@Autowired
//	public UserManager(UserDao userDao) {
//		super();
//		this.userDao = userDao;
//	}
	@Autowired
	public UserManager(UserDao userDao,CandidateDao candidateDao,EmployerDao employerDao,StaffDao staffDao) {
		this.userDao = userDao;
		this.candidateDao=candidateDao;
		this.employerDao=employerDao;
		this.staffDao=staffDao;
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

	@Override
	public DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto) {
		User user = this.userDao.findByEmail(userLoginDto.getEmail());
		if(user==null){
			return new ErrorDataResult<UserLoginReturnDto>("Hatalı email girdiniz");
		}else if(!user.getPassword().equals(userLoginDto.getPassword())){
			return new ErrorDataResult<UserLoginReturnDto>("Hatalı şifre girdiniz");
		}else if(!user.isMailVerify()){
			return new ErrorDataResult<UserLoginReturnDto>("Giriş yapmak için email onayı yapmanız gerekmektedir");
		}
		UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();
		userLoginReturnDto.setId(user.getId());
		userLoginReturnDto.setEmail(user.getEmail());

		if(this.candidateDao.existsById(user.getId())){
			userLoginReturnDto.setUserType(1);
			userLoginReturnDto.setName(this.candidateDao.getById(user.getId()).getFirstName()+" "+this.candidateDao.getById(user.getId()).getLastName());
		}else if(this.employerDao.existsById(user.getId())){
			userLoginReturnDto.setUserType(2);
			userLoginReturnDto.setName(this.employerDao.getById(user.getId()).getCompanyName());
		}else if(this.staffDao.existsById(user.getId())){
			userLoginReturnDto.setUserType(3);
			userLoginReturnDto.setName(this.staffDao.getById(user.getId()).getFirstName()+" "+this.staffDao.getById(user.getId()).getLastName());
		}else {
			return new ErrorDataResult<UserLoginReturnDto>("Bir hata oluştu");
		}

		return new SuccessDataResult<UserLoginReturnDto>(userLoginReturnDto,"Giriş yapıldı");
	}

	@Override
	public DataResult<List<User>> getVerifyedUsers() {
		return new SuccessDataResult<List<User>>(this.userDao.findByMailVerifyTrue(),"Data listelendi");
	}
	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Data listelendi");
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email),"Listelendi");
	}

}
