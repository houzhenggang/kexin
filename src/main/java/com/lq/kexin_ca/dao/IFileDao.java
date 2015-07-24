package com.lq.kexin_ca.dao;

import com.lq.kexin_ca.entity.File;

public interface IFileDao {
    int deleteByPrimaryKey(Integer fileId);

    int insert(File record);

    File selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeyWithBLOBs(File record);

    File selectByFileName(String name);

}