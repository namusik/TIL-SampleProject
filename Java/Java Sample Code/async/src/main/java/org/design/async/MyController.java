package org.design.async;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class MyController {
    private final SynchronousService synchronousService;
    private final AsynchronousService asynchronousService;

    @GetMapping("/sync")
    public String sync() {
        return synchronousService.executeSync();
    }

    @GetMapping("/async")
    public CompletableFuture<String> async() {
        return asynchronousService.executeAsync();
    }
}
