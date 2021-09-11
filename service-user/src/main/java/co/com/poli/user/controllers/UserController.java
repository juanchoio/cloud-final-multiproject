package co.com.poli.user.controllers;

import co.com.poli.commons.utils.FormatMessageErrorFields;
import co.com.poli.commons.utils.Response;
import co.com.poli.commons.utils.ResponseBuilder;
import co.com.poli.user.entities.User;
import co.com.poli.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@ComponentScan("co.com.poli.commons")
public class UserController {

    private final UserService userService;
    private final ResponseBuilder responseBuilder;
    private final FormatMessageErrorFields formatMessage;

    @GetMapping
    public Response findAll(){
        List<User> users = userService.findAll();
        return responseBuilder.success(users);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        User u = userService.findById(id);
        if (u != null){
            return responseBuilder.success(u);
        }
        return responseBuilder.failedNotFound();
    }


    //TODO: add @Valid annotation
    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result){
        if (result.hasErrors()){
            return responseBuilder.failed(formatMessage.formatMessageError(result));
        }
        userService.save(user);
        return responseBuilder.successCreated(user);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        User u = userService.findById(id);
        if (u == null){
            return responseBuilder.failedNotFound();
        }
        //userService.delete(u);
        //return responseBuilder.successDeleted(u);
        return userService.delete(u);
    }

}
