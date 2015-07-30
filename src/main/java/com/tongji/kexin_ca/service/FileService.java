package com.tongji.kexin_ca.service;

import com.tongji.kexin_ca.dao.IFileDao;
import com.tongji.kexin_ca.entity.File;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileService {

    @Resource
    private IFileDao fileDao;

    public int saveFile(File file) {
        return fileDao.insert(file);
    }

    public File getFile(String name) {
        return fileDao.selectByFileName(name);
    }

    public IFileDao getFileDao() {
        return fileDao;
    }

    public void setFileDao(IFileDao fileDao) {
        this.fileDao = fileDao;
    }
}
