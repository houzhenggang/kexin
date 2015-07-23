package com.lq.kexin.service;

import com.lq.kexin.dao.IFileDao;
import com.lq.kexin.entity.File;
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
