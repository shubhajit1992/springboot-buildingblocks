package com.shubhajit.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.shubhajit.restservices.entities.User;
import com.shubhajit.restservices.exceptions.UserExistsException;
import com.shubhajit.restservices.exceptions.UserNotFoundException;
import com.shubhajit.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserExistsException("User already exists in User Repository.");
		}
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in User Repository.");
		}
		return user;
	}

	public User updateUserById(User user, Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in User Repository. Please provide the correct user ID.");
		}
		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in User Repository. Please provide the correct user ID.");
		}
		userRepository.deleteById(id);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}