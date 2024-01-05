package com.job_manager.mai.service.user;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.exception.AccountNotVerify;
import com.job_manager.mai.exception.UserExited;
import com.job_manager.mai.exception.UserNotFoundException;
import com.job_manager.mai.model.Account;
import com.job_manager.mai.model.User;
import com.job_manager.mai.repository.AccountRepository;
import com.job_manager.mai.repository.UserRepository;
import com.job_manager.mai.request.user.CreateUserRequest;
import com.job_manager.mai.request.user.DeleteUserRequest;
import com.job_manager.mai.request.user.UpdateUserRequest;
import com.job_manager.mai.service.base.BaseService;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIpm extends BaseService implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> store(CreateUserRequest request) throws Exception {
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));
        if (account.getUser() != null) {
            throw new UserExited(Messages.USER_EXITED);
        }
        if (!account.isVerify() || account.isActive()) {
            throw new AccountNotVerify(Messages.ACCOUNT_NOT_VERIFY);
        }
        User newUser = getMapper().map(request, User.class);
        newUser.setEmail(account.getUsername());
        newUser.setAccount(account);
        User user = userRepository.saveAndFlush(newUser);
        account.setUser(user);
        return ApiResponseHelper.resp(user, HttpStatus.OK, Messages.DEFAULT_SUCCESS_MESSAGE);
    }

    @Override
    public ResponseEntity<?> edit(UpdateUserRequest request, String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroy(String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroyAll(DeleteUserRequest request) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(String s) {
        return null;
    }

    @Override
    public ResponseEntity<?> searchByName(Pageable pageable, String name) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> SearchAndSortByProperties(Pageable pageable, String searchProperties, String sortProperties) throws Exception {
        return null;
    }
}
