package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    CredentialMapper credentialMapper;
    UserMapper userMapper;
    EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper,
        EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public void createCredentials(Credentials credentials, String userName) {
        Integer userId = userMapper.getUser(userName).getUserid();
        credentials.setUserid(userId);

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(), encodedKey));
        credentials.setKey(encodedKey);
        credentialMapper.createCredentials(credentials);
    }

    public List<Credentials> getListOfCredentials(String userName) {
        Integer userId = userMapper.getUser(userName).getUserid();
        return credentialMapper.getCredentials(userId);
    }

    public void deleteCredentials(Integer credentialId, String userName) {
        Integer userId = userMapper.getUser(userName).getUserid();
        credentialMapper.deleteCredentials(credentialId, userId);
    }

    public void updateCredentials(Credentials credentials, String userName) {
        Integer userId = userMapper.getUser(userName).getUserid();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(), encodedKey));
        credentials.setKey(encodedKey);
        credentials.setUserid(userId);
        credentialMapper.updateCredentials(credentials);
    }

}
