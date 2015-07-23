package com.lq.kexin.dao;

import com.lq.kexin.entity.File;

public interface IFileDao {
    int deleteByPrimaryKey(Integer fileId);

    int insert(File record);

    File selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeyWithBLOBs(File record);

    File selectByFileName(String name);

}