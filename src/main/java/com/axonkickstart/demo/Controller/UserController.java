package com.axonkickstart.demo.Controller;

import com.axonkickstart.demo.aggregate.User;
import com.axonkickstart.demo.command.AddAddressCommand;
import com.axonkickstart.demo.command.RegisterUserCommand;
import com.axonkickstart.demo.model.AddressBean;
import com.axonkickstart.demo.model.UserBean;
import com.axonkickstart.demo.query.GetAddressQuery;
import com.axonkickstart.demo.query.GetUserQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    @Autowired
    CommandGateway commandGateway;

    @Autowired
    QueryGateway queryGateway;

    @PostMapping("/user")
    public ResponseEntity<Void> registerUser(@RequestBody UserBean userBean) {
        commandGateway.send(new RegisterUserCommand(userBean.getUserId(),
                userBean.getName()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId) throws ExecutionException, InterruptedException {
        CompletableFuture<User> future = queryGateway.query(new GetUserQuery(userId), User.class);
        return ResponseEntity.ok().body(future.get());
    }

    @PostMapping("/user/{userId}/address")
    public ResponseEntity<Void> addAddress(@PathVariable("userId") Integer userId, @RequestBody AddressBean addressBean) {
        commandGateway.send(new AddAddressCommand(userId, addressBean.getAddress()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/address")
    public ResponseEntity<List<AddressBean>> addBook(@PathVariable Integer userId) throws InterruptedException, ExecutionException {
        return ResponseEntity.ok().body(queryGateway
                .query(new GetAddressQuery(userId),
                        ResponseTypes.multipleInstancesOf(AddressBean.class)).get());
    }

}
