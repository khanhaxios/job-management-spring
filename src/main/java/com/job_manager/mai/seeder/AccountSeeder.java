package com.job_manager.mai.seeder;

import com.job_manager.mai.contrains.Roles;
import com.job_manager.mai.model.Account;
import com.job_manager.mai.model.Role;
import com.job_manager.mai.repository.AccountRepository;
import com.job_manager.mai.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountSeeder implements Ordered, CommandLineRunner {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            String adminUsername = "dangkdev@gmail.com";
            String adminPassword = "13122002";
            Role adminRole = roleRepository.findByRoleName(String.valueOf(Roles.ROLE_ADMIN)).get();
            Optional<Account> exitAccount = accountRepository.findByUsername(adminUsername);
            if (!exitAccount.isPresent()) {
                Account account = new Account();
                account.setPassword(passwordEncoder.encode(adminPassword));
                account.setUsername(adminUsername);
                account.setVerify(true);
                account.setActive(true);
                account.setRole(adminRole);
                account.setLastLoginTime(new Date(System.currentTimeMillis()));
                accountRepository.save(account);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
