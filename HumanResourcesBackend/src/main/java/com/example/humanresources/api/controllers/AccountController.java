package com.example.humanresources.api.controllers;

        import java.util.HashMap;
        import java.util.Map;

        import javax.validation.Valid;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.MethodArgumentNotValidException;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import org.springframework.web.bind.annotation.RestController;

        import com.example.humanresources.business.abstracts.UserService;
        import com.example.humanresources.core.entities.User;
        import com.example.humanresources.core.utilities.results.ErrorDataResult;

        import org.springframework.validation.FieldError;
        import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/api/account")
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {

        return ResponseEntity.ok(this.userService.add(user)) ;
    }
    @PostMapping(value="/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {

        return ResponseEntity.ok(this.userService.update(user)) ;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
            (MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }



}