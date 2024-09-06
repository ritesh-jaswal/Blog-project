package com.example.Blog_project.serviceimpl;

import com.example.Blog_project.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService
{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File Name
        String name = file.getOriginalFilename();

        //TO AVOID CONFLICT FROM SAME FILE NAME BEING INSERTED TWICE
        String randomId = UUID.randomUUID().toString();
        assert name != null;
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        //Full Path of File
        String filePath = path + File.separator + fileName1;

        //Create Folder if not Created
        File f = new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }

        //Copy a File
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is  = new FileInputStream(fullPath);
//        db logic to return inputstream
        return is;
    }
}
