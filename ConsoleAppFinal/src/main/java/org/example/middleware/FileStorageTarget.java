package org.example.remote;

import org.example.Middleware.UserDetailsFileRepository;
import org.example.remote.StorageTarget;
import org.example.remote.UserDetailsRepository;

public class FileStorageTarget implements StorageTarget {
    @Override
    public UserDetailsRepository getUserDetailsRepository() {
        return new UserDetailsFileRepository("account.txt");
    }
}
