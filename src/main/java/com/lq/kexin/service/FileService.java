package com.lq.kexin.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileService {

    @Resource
    private IFileDao fileDao;

    public saveFile()

    public IFileDao getFileDao() {
        return fileDao;
    }

    public void setFileDao(IFileDao fileDao) {
        this.fileDao = fileDao;
    }
}
