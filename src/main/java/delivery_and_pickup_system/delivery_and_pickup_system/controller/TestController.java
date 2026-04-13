package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.response.base.BaseResponse;
import delivery_and_pickup_system.delivery_and_pickup_system.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public BaseResponse<String> test() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.success(userDetails.getUsername());
    }

    @GetMapping("/test/no-auth")
    public BaseResponse<String> testNoAuth() {

        userService.getByEmail("sadiqqenberov26@gmail.com");

//        throw BaseException.unexpected();
        return BaseResponse.success("Course ERP - No Auth");
    }
}
