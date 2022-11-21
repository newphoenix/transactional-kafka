package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MessageRequest;
import com.example.model.Message;
import com.example.service.MessageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
	
	private MessageService messageService;

	@PostMapping
	public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest msg) throws InterruptedException {

		Message message = Message.builder().name(msg.getMsg()).amount(msg.getAmount()).build();
		return new ResponseEntity<>(messageService.save(message),HttpStatus.CREATED);
	}

}
