package com.axonkickstart.demo.service;

import com.axonkickstart.demo.aggregate.User;
import com.axonkickstart.demo.query.GetUserQuery;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserProjector {
    @Autowired
    Repository<User> userRepository;

    @QueryHandler
    public User getUser(GetUserQuery query) throws ExecutionException, InterruptedException {
        CompletableFuture<User> future = new CompletableFuture<>();
        userRepository.load(query.getUserId().toString()).execute(future::complete);
        return future.get();
    }
}
