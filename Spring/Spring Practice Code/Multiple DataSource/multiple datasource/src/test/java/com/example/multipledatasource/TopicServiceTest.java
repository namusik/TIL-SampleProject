package com.example.multipledatasource;

import com.example.multipledatasource.todo.Todo;
import com.example.multipledatasource.topic.Topic;
import com.example.multipledatasource.todo.TodoRepository;
import com.example.multipledatasource.topic.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TopicServiceTest {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TopicRepository topicRepository;
    private Topic topic;
    private Todo todo;

    @BeforeEach
    void setUp() {
        topic = new Topic("topic title");
        todo = new Todo("todo title", false);
    }

    @Test
    void save() {
        Todo savedTodo = todoRepository.save(todo);
        Topic savedTopic = topicRepository.save(topic);
    }
}
